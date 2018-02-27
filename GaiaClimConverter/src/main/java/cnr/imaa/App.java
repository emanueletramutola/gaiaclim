package cnr.imaa;

import cnr.imaa.bll.ConfigurationManager;
import cnr.imaa.bll.wigos.WigosBuilder;
import cnr.imaa.dal.ArgoDAL;
import cnr.imaa.dal.StationDAL;
import cnr.imaa.model.StationForDB;
import com.fasterxml.jackson.xml.XmlMapper;
import com.google.common.primitives.Floats;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.Point;
import org.codehaus.jackson.map.ObjectMapper;
import org.joda.time.DateTime;
import ucar.ma2.Array;
import ucar.nc2.Attribute;
import ucar.nc2.NetcdfFile;
import ucar.nc2.Variable;

import javax.ws.rs.core.UriBuilder;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

public class App {
    public static void main(String[] args) {
        try {
            //MetadataPublisher.publish();

            //WigosBuilder.build();

            //exportToJson();

            //CSWClient.retrieveData();

            //populateArgoBeginDate();

            //readNetcdf();

            //detectGruanCountries();

            //updateDB();

            getSatelliteOrbits();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private static void getSatelliteOrbits() {
        ClientConfig config = new DefaultClientConfig();
        Client client = Client.create(config);

        String urlSatelliteOrbits = ConfigurationManager.getPropertyValue("urlSatelliteOrbits");
        Path satelliteConfig = Paths.get(ConfigurationManager.getPropertyValue("satelliteConfig"));

        try {
            String satelliteFile = new String(Files.readAllBytes(satelliteConfig));
            String noradId;
            String satelliteName;
            Path destinationDirectory;
            Path destinationFile;
            String startDate;
            String endDate;
            for (String row : satelliteFile.split(System.getProperty("line.separator"))) {
                noradId = row.split(";")[0];
                satelliteName = row.split(";")[1];

                destinationDirectory = Paths.get("Resources/Satellite/" + noradId + "_" + satelliteName);

                if (!Files.exists(destinationDirectory)) {
                    Files.createDirectories(destinationDirectory);
                }

                startDate = DateTime.now().toString("yyyy-MM-dd_HH:mm:ss");
                endDate  = DateTime.now().toString("yyyy-MM-dd_HH:mm:ss");

                urlSatelliteOrbits = urlSatelliteOrbits.replace("__SATELLITEID__", noradId);
                urlSatelliteOrbits = urlSatelliteOrbits.replace("__STARTDATE__", startDate);
                urlSatelliteOrbits = urlSatelliteOrbits.replace("__ENDDATE__", endDate);

                WebResource service = client.resource(UriBuilder.fromUri(urlSatelliteOrbits).build());
                ClientResponse clientResponse = service.accept("application/json").get(ClientResponse.class);

                if (clientResponse.getStatus() == 200) {
                    destinationFile = Paths.get("Resources/Satellite/" + noradId + "_" + satelliteName + "/" + noradId + "_" + satelliteName + ".czml");

                    Files.write(destinationFile, clientResponse.getEntity(String.class).getBytes());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void detectGruanCountries() {
        ArrayList<String> listCountries = new ArrayList<String>();

        //get coordinates from DB
        StationDAL dal = new StationDAL();
        List<StationForDB> listStations = dal.getListStationsByNetwork("GRUAN");

        double longitudeStation = 0;
        double latitudeStation = 0;

        ClientConfig config = new DefaultClientConfig();
        Client client = Client.create(config);

        String urlDecodeCountryCode = ConfigurationManager.getPropertyValue("urlDecodeCountryCode");
        String url;
        for (StationForDB s : listStations) {
            Point point = s.getCoordinates();
            Coordinate c = point.getCoordinate();
            longitudeStation = c.x;
            latitudeStation = c.y;

            url = urlDecodeCountryCode.replace("__LATITUDE__", Double.toString(latitudeStation));
            url = url.replace("__LONGITUDE__", Double.toString(longitudeStation));

            WebResource service = client.resource(UriBuilder.fromUri(url).build());
            ClientResponse clientResponse = service.accept("application/json").get(ClientResponse.class);

            if (clientResponse.getStatus() != 200) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + clientResponse.getStatus());
            }

            String response = clientResponse.getEntity(String.class);
            try {
                ObjectMapper mapper = new ObjectMapper();

                Map obj = mapper.readValue(response, Map.class);

                String countryCode = (String) obj.get("countryCode");

                List<String> listCountriesFiltered = listCountries.stream().filter(x -> x.equals(countryCode)).collect(Collectors.toList());
                if (listCountriesFiltered.size() == 0) {
                    listCountries.add(countryCode);
                }

                clientResponse.close();
                client.destroy();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        listCountries.forEach(x -> System.out.println("- " + x));
    }

    private static void readNetcdf() {
        String filename = "Resources/Wigos-Data/dataGruan/POT-RS-01_2_RS92-GDP_002_20160107T173400_1-000-001.nc";
        NetcdfFile ncfile = null;
        try {
            ncfile = NetcdfFile.open(filename);

            Variable variableAltitude = ncfile.findVariable("alt");
            Array dataAltitude = variableAltitude.read();
            float[] arrDataAltitude = (float[]) dataAltitude.get1DJavaArray(float.class);

            List<Float> listdataAltitude = (List<Float>) Floats.asList(arrDataAltitude);
            ArrayList<Float> listdataAltitudeFiltered = new ArrayList<Float>();
            //ArrayList<Float> listdataAltitudeFiltered = (ArrayList) listdataAltitude;
            //listdataAltitudeFiltered.removeIf(x -> Float.isNaN(x));

            for (Float f : listdataAltitude) {
                if (!Float.isNaN(f)) {
                    listdataAltitudeFiltered.add(f);
                }
            }

            float maxValue = Collections.max(listdataAltitudeFiltered);
            float minValue = Collections.min(listdataAltitudeFiltered);

            List<Variable> listVariables = ncfile.getVariables();
            //List<Attribute> listGlobalAttributes = ncfile.getGlobalAttributes();
            Attribute attrStartTime = ncfile.findGlobalAttribute("g.Ascent.StartTime");

            DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
            Date date = df.parse(attrStartTime.getStringValue());
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);

            Variable v = listVariables.get(0);
            Array data = v.read();

            float[] storage = (float[]) data.getStorage();

            int secondsElapsed = (int) storage[(((int) storage.length) - 1)];

            cal.add(Calendar.SECOND, secondsElapsed);

            String dataFinale = df.format(cal.getTime());

            //get country code
            ClientConfig config = new DefaultClientConfig();
            Client client = Client.create(config);

            String urlDecodeCountryCode = ConfigurationManager.getPropertyValue("urlDecodeCountryCode");

            String latitude = ncfile.findGlobalAttribute("g.MeasuringSystem.Latitude").getStringValue().trim();
            String longitude = ncfile.findGlobalAttribute("g.MeasuringSystem.Longitude").getStringValue().trim();

            if (latitude.contains("°")) latitude = latitude.replace("°", "").trim();
            if (longitude.contains("°")) longitude = longitude.replace("°", "").trim();

            urlDecodeCountryCode = urlDecodeCountryCode.replace("__LATITUDE__", latitude);
            urlDecodeCountryCode = urlDecodeCountryCode.replace("__LONGITUDE__", longitude);

            WebResource service = client.resource(UriBuilder.fromUri(urlDecodeCountryCode).build());
            ClientResponse response = service.accept("application/json").get(ClientResponse.class);

            if (response.getStatus() != 200) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + response.getStatus());
            }

            String[] output = response.getEntity(String.class).split(",");//.replaceAll("(\\r|\\n)", "");

            String a = "";

        } catch (IOException ex) {
            //log("trying to open " + filename, ioe);
            ex.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (null != ncfile) try {
                ncfile.close();
            } catch (IOException ioe) {
                //log("trying to close " + filename, ioe);
            }
        }
    }

    private static void populateArgoBeginDate() {
        ArgoDAL dal = new ArgoDAL();

        List<StationForDB> listArgoStation = dal.listArgoStationWithoutBeginDate();
        for (StationForDB station : listArgoStation) {
            station.setBeginDate(ArgoDAL.getBeginDate(station.getWebsite()));

            dal.update(station);
        }
    }

    private static void updateDB() {
        double longitude = 55.4;
        double latitude = -21.1;
        String nameToSearch = "LA REUNION, REU";
        GeometryFactory gf = new GeometryFactory();
        StationDAL dal = new StationDAL();

        List<StationForDB> listStations = dal.getListStationsByName(nameToSearch);
        for (StationForDB station : listStations) {
            Coordinate coord = new Coordinate(longitude, latitude);
            Point point = gf.createPoint(coord);
            point.setSRID(4326);

            station.setCoordinates(point);

            dal.update(station);
        }
    }

    private static void exportToJson() {
        XmlMapper xmlMapper = new XmlMapper();
        List entries;
        String json;

        try {
            entries = xmlMapper.readValue(new File("StationsMetadata/GRUAN - temperature profile - POTENZA, POT.xml"), List.class);

            ObjectMapper jsonMapper = new ObjectMapper();

            json = jsonMapper.writeValueAsString(entries);

            PrintWriter out = new PrintWriter("StationsMetadata/jsonOutput");

            out.println(json);

            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
