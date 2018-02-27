package cnr.imaa.dal;

import cnr.imaa.model.Station;
import cnr.imaa.model.StationForDB;
import org.json.JSONObject;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class ArgoDAL {
    static XPath xPath = XPathFactory.newInstance().newXPath();
    StationDAL stationDAL = new StationDAL();

    //final static String XPATHEXPRESSION_ACTIVEFLOATS = "kml/Document/Folder[1]/Folder[1]/Folder[1]/Placemark";
    final static String XPATHEXPRESSION_ACTIVEFLOATS = "/kml/Folder/Placemark";
    final static String XPATHEXPRESSION_NAME_OF_STATION = "name";
    final static String XPATHEXPRESSION_COORDINATES = "Point/coordinates";

    public List<StationForDB> listArgoStationWithoutBeginDate() {
        List<StationForDB> result = stationDAL.getListStationsByNetwork("ARGO");

        result.removeIf(x -> x.getBeginDate() != null);

        return result;
    }

    public void update(StationForDB stationForDB) {
        stationDAL.update(stationForDB);
    }

    public static ArrayList<Station> readFromKml(String pathFile) {
        File fXmlFile;
        Document doc;
        DocumentBuilderFactory dbFactory;
        DocumentBuilder dBuilder;
        ArrayList<Station> listStations = new ArrayList<Station>();

        try {
            fXmlFile = new File(pathFile);
            dbFactory = DocumentBuilderFactory.newInstance();
            dBuilder = dbFactory.newDocumentBuilder();
            doc = dBuilder.parse(fXmlFile);

            doc.getDocumentElement().normalize();

            NodeList listActiveFloats = (NodeList) xPath.compile(XPATHEXPRESSION_ACTIVEFLOATS).evaluate(doc, XPathConstants.NODESET);

            Node nodeTmp;
            Station station;
            String coordinates;
            for (int i = 0; i < listActiveFloats.getLength(); i++) {
                station = new Station();

                nodeTmp = listActiveFloats.item(i);

                station.setNameOfTheStation((String) xPath.compile(XPATHEXPRESSION_NAME_OF_STATION).evaluate(nodeTmp, XPathConstants.STRING));

                //station.setWebsite("http://www.jcommops.org/jcommops-ptf/WebObjects/jcommops-ptf.woa/wa/PtfComponent?code=" + station.getNameOfTheStation() + "&prog=Argo&fromGE=true");
                station.setWebsite("http://www.jcommops.org/board/wa/InspectPtfModule?ref=" + station.getNameOfTheStation());

                coordinates = (String) xPath.compile(XPATHEXPRESSION_COORDINATES).evaluate(nodeTmp, XPathConstants.STRING);
                station.setLongitude(Double.parseDouble(coordinates.split(",")[0]));
                station.setLatitude(Double.parseDouble(coordinates.split(",")[1]));

                station.setMeasuredEcv("temperature, salinity");
                station.setProduct("temperature, salinity");
                station.setMeasurementTechnique("Free-drifting profiling floats");
                station.setNetwork("ARGO");
                station.setMeasurementType("profile");

                station.setStartOfOperation(getBeginDate(station.getNameOfTheStation()));

                listStations.add(station);
            }
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (XPathExpressionException e) {
            e.printStackTrace();
        }

        return listStations;
    }

    public static Date getBeginDate(String nameStation) {
        Date result = null;

        try {
            String s = "http://www.jcommops.org/board/wa/Inspect?_dc=1467204524838&page=1&start=0&limit=25&ptfRef=";

            s += URLEncoder.encode(nameStation, "UTF-8");
            URL url = new URL(s);

            // read from the URL
            Scanner scan = new Scanner(url.openStream());
            String str = new String();
            while (scan.hasNext())
                str += scan.nextLine();
            scan.close();

            str = str.substring(1, str.lastIndexOf("]"));

            JSONObject obj = new JSONObject(str);

            String dateBegin = obj.getString("deplDate");

            DateFormat df = new SimpleDateFormat("yyyy-MM-dd", Locale.ITALY);
            result = df.parse(dateBegin);
        } catch (MalformedURLException e) {
            System.out.println(nameStation);
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println(nameStation);
            e.printStackTrace();
        } catch (ParseException e) {
            System.out.println(nameStation);
            e.printStackTrace();
        } catch (NullPointerException e) {
            System.out.println(nameStation);
            e.printStackTrace();
        }

        return result;
    }
}
