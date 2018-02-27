package cnr.imaa.gaiaclimservice.bll;

import cesiumlanguagewriter.*;
import cnr.imaa.gaiaclimservice.dal.SatelliteDAL;
import cnr.imaa.gaiaclimservice.model.TLEBean;
import org.apache.commons.io.IOUtils;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.Days;
import org.joda.time.Seconds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import uk.me.g4dpz.satellite.GroundStationPosition;
import uk.me.g4dpz.satellite.LEOSatellite;
import uk.me.g4dpz.satellite.SatelliteFactory;
import uk.me.g4dpz.satellite.TLE;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@ComponentScan
public class Satellite {
    SatelliteDAL satelliteDAL = new SatelliteDAL();

    /*
    private static final Logger logger = LoggerFactory
            .getLogger(Satellite.class);
    */

    @Autowired
    private ConfigurationManager configurationM;

    private ArrayList<TLEBean> getListTLEBean(String urlTle) {
        String bodyTLE = "";
        /*
        String pathTLE = "";
        try {
            pathTLE = configurationM.getPathTLE();
            logger.info("pathTLE: " + pathTLE);
        } catch (Exception ex) {
            logger.error("**** ERROR ****: " + ex.getMessage());
        }

        String filename = sc.getUrltle().substring(sc.getUrltle().lastIndexOf("/"));
        if (sc.getLastupdate() == null || Days.daysBetween(new DateTime(sc.getLastupdate()), DateTime.now()).getDays() > 1) {
        */
        try {
            //URL url = new URL(sc.getUrltle());
            URL url = new URL(urlTle);
            URLConnection con = url.openConnection();
            InputStream in = con.getInputStream();
            String encoding = con.getContentEncoding();
            encoding = encoding == null ? "UTF-8" : encoding;
            bodyTLE = IOUtils.toString(in, encoding);
/*
                PrintWriter out = new PrintWriter(pathTLE + filename);
                out.println(bodyTLE);
                out.close();

                sc.setLastupdate(DateTime.now().toDate());
                classificationDAL.update(sc);
*/
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    /*
    }
         else {

            try {
                Path path = FileSystems.getDefault().getPath(pathTLE + filename);

                bodyTLE = IOUtils.toString(Files.readAllBytes(path), "UTF-8");
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
*/
        return readBodyTLE(bodyTLE);
    }

    private ArrayList<TLEBean> readBodyTLE(String body) {
        ArrayList<TLEBean> tleBeanList = new ArrayList<TLEBean>();

        TLEBean tleBean = null;
        for (String row : body.split("\r\n")) {
            if (row.toCharArray()[0] != '1'
                    && row.toCharArray()[0] != '2') {

                tleBean = new TLEBean();
                tleBean.setName(row.trim());
            } else if (tleBean != null) {
                if (row.toCharArray()[0] == '1') {
                    tleBean.setFirstRow(row);
                } else if (row.toCharArray()[0] == '2') {
                    tleBean.setSecondRow(row);
                    tleBeanList.add(tleBean);

                    tleBean = null;
                }
            }
        }

        return tleBeanList;
    }

    public ArrayList<String> getGroundtrack(int noradId, String dateStart, String dateEnd, int sampling) {
        ArrayList<String> groundtrack = new ArrayList<>();

        List<cnr.imaa.gaiaclimservice.model.Satellite> listSatellites = satelliteDAL.getListSatellite();
        //List<SatelliteClassification> listClassifications = classificationDAL.getListSatelliteClassification();

        cnr.imaa.gaiaclimservice.model.Satellite sat = listSatellites.stream().filter(x -> x.getNoradId() == noradId).collect(Collectors.toList()).get(0);
        //SatelliteClassification classification = listClassifications.stream().filter(x -> x.getId() == sat.getIdclassification()).collect(Collectors.toList()).get(0);

        ArrayList<TLEBean> tleBeans = getListTLEBean(sat.getUrltle());

        TLEBean tleBean = tleBeans.stream().filter(x -> x.getNoradId() == noradId).collect(Collectors.toList()).get(0);

        TLE tle = new TLE(new String[]{tleBean.getName(), tleBean.getFirstRow(), tleBean.getSecondRow()});

        uk.me.g4dpz.satellite.Satellite satellite = SatelliteFactory.createSatellite(tle);

        GroundStationPosition GSP = new GroundStationPosition(0, 0, 0);

        DateTime dataStartJoda = new DateTime(dateStart, DateTimeZone.UTC);
        DateTime dateEndJoda = new DateTime(dateEnd, DateTimeZone.UTC);

        ArrayList<DateTime> dateTimes = getDataTimes(dataStartJoda, dateEndJoda, sampling);

        float latitude;
        float longitude;
        for (DateTime date : dateTimes) {
            latitude = (float) (satellite.getPosition(GSP, date.toDate()).getLatitude() * 180.0 / Math.PI);
            longitude = (float) (satellite.getPosition(GSP, date.toDate()).getLongitude() * 180.0 / Math.PI);

            if (longitude > 180.0f) {
                longitude = -(360.0f - longitude);
            }

            groundtrack.add(date.toString());
            groundtrack.add(Float.toString(latitude));
            groundtrack.add(Float.toString(longitude));
        }

        return groundtrack;
    }

    public String getOrbit(int noradId, String dateStartString, String dateEndString) {
        String orbit = "";

        DateTime dateStart = new DateTime(dateStartString, DateTimeZone.UTC);
        DateTime dateEnd = new DateTime(dateEndString, DateTimeZone.UTC);

        List<cnr.imaa.gaiaclimservice.model.Satellite> listSatellites = satelliteDAL.getListSatellite();
        cnr.imaa.gaiaclimservice.model.Satellite sat = listSatellites.stream().filter(x -> x.getNoradId() == noradId).collect(Collectors.toList()).get(0);

        ArrayList<TLEBean> tleBeans = getListTLEBean(sat.getUrltle());
        TLEBean tleBean = tleBeans.stream().filter(x -> x.getNoradId() == noradId).collect(Collectors.toList()).get(0);
        TLE tle = new TLE(new String[]{tleBean.getName(), tleBean.getFirstRow(), tleBean.getSecondRow()});

        try {
            StringWriter sw = new StringWriter();

            CesiumOutputStream output = new CesiumOutputStream(sw);
            output.setPrettyFormatting(true);
            CesiumStreamWriter writer = new CesiumStreamWriter();

            output.writeStartSequence();

            PacketCesiumWriter packet = writer.openPacket(output);

            packet.writeVersion("1.0");
            packet.writeId("document");

            ClockCesiumWriter clockCesiumWriter = packet.openClockProperty();

            clockCesiumWriter.writeStep(ClockStep.SYSTEM_CLOCK_MULTIPLIER);
            clockCesiumWriter.writeMultiplier(60);
            clockCesiumWriter.writeRange(ClockRange.LOOP_STOP);

            //clockCesiumWriter.writeCurrentTime(new JulianDate(startDateGDate));
            //clockCesiumWriter.writeInterval(new JulianDate(startDateGDate), new JulianDate(endDateGDate));
            clockCesiumWriter.writeCurrentTime(new JulianDate(dateStart));
            clockCesiumWriter.writeInterval(new JulianDate(dateStart), new JulianDate(dateEnd));
            clockCesiumWriter.close();
            packet.close();

            packet = writer.openPacket(output);
            BillboardCesiumWriter billboardCesiumWriter = packet.openBillboardProperty();
            billboardCesiumWriter.writeScaleProperty(1.0);
            billboardCesiumWriter.writeShowProperty(true);

            //BufferedImage image = BitmapHelper.create(EmbeddedData.read("satellite.png"));
            //cesiumlanguagewriter.CesiumResource resource = CesiumResource. fromImage(image, CesiumImageFormat.PNG);

            billboardCesiumWriter.writeImageProperty(
                    //URI.create("https://cdn3.iconfinder.com/data/icons/softwaredemo/PNG/16x16/Circle_Blue.png"),
                    URI.create("http://localhost:8080/cesium/Apps/GaiaClim/img/satellite.png"),
                    CesiumResourceBehavior.EMBED);
            billboardCesiumWriter.close();
            packet.writeId(Integer.toString(noradId));
            packet.writeDescriptionProperty("Orbit of " + sat.getName());

            LabelCesiumWriter labelCesiumWriter = packet.openLabelProperty();
            labelCesiumWriter.writeFontProperty("11pt Lucida Console");
            labelCesiumWriter.writeTextProperty(sat.getName());
            labelCesiumWriter.writeShowProperty(true);
            labelCesiumWriter.writeOutlineWidthProperty(2.0);
            labelCesiumWriter.writeFillColorProperty(new Color(213, 255, 0, 255));
            labelCesiumWriter.writeOutlineColorProperty(new Color(0, 0, 0, 255));
            labelCesiumWriter.writePixelOffsetProperty(12, 0);

            HorizontalOriginCesiumWriter horizontalOriginCesiumWriter = labelCesiumWriter.openHorizontalOriginProperty();
            horizontalOriginCesiumWriter.writeHorizontalOrigin(CesiumHorizontalOrigin.LEFT);
            horizontalOriginCesiumWriter.close();
            labelCesiumWriter.close();

            packet.writeAvailability(new JulianDate(dateStart), new JulianDate(dateEnd));

            PositionCesiumWriter positionCesiumWriter = packet.openPositionProperty();
            positionCesiumWriter.writeReferenceFrame("INERTIAL");
            positionCesiumWriter.writeInterpolationAlgorithm(CesiumInterpolationAlgorithm.LAGRANGE);
            positionCesiumWriter.writeInterpolationDegree(5);

            ArrayList<JulianDate> listJulianDate = new ArrayList<>();
            ArrayList<Cartesian> listCartesian = new ArrayList<>();

            JulianDate date = new JulianDate(dateStart);

            double deltaTime = 300.0000000000000; //seconds

            int seconds = Seconds.secondsBetween(dateStart, dateEnd).getSeconds();

            int secondsProcessed = 0;

            while (secondsProcessed <= seconds) {
                listJulianDate.add(date);
                listCartesian.add(getCartesian(tle, date.toDateTime().toDate()));

                date = date.addSeconds(deltaTime);

                secondsProcessed += deltaTime;
            }

            positionCesiumWriter.writeCartesian(listJulianDate, listCartesian);

            positionCesiumWriter.close();

            writePath(packet, tle, seconds, new JulianDate(dateStart), new JulianDate(dateEnd));

            packet.close();
            sw.close();

            output.writeEndSequence();

            orbit = sw.toString();
        } catch (IOException e) {
            //log exception
        }

        return orbit;
    }

    private Cartesian getCartesian(TLE tle, Date date) {
        Cartesian cartesian = null;

        if (tle != null) {
            LEOSatellite satellite = (LEOSatellite) SatelliteFactory.createSatellite(tle);

            satellite.calculateSatelliteVectors(date);

            cartesian = new Cartesian(satellite.position.getX() * 1000, satellite.position.getY() * 1000, satellite.position.getZ() * 1000);
        }

        return cartesian;
    }

    private void writePath(PacketCesiumWriter packet, TLE tle, int seconds, JulianDate startDate, JulianDate endDate) {
        PathCesiumWriter pathCesiumWriter = packet.openPathProperty();
        BooleanCesiumWriter booleanCesiumWriter = pathCesiumWriter.openShowProperty();
        booleanCesiumWriter.writeBoolean(true);
        booleanCesiumWriter.close();

        PolylineMaterialCesiumWriter polylineMaterialCesiumWriter = pathCesiumWriter.openMaterialProperty();
        SolidColorMaterialCesiumWriter solidColorMaterialCesiumWriter = polylineMaterialCesiumWriter.openSolidColorProperty();
        solidColorMaterialCesiumWriter.writeColorProperty(new Color(213, 255, 0, 255));
        solidColorMaterialCesiumWriter.close();
        polylineMaterialCesiumWriter.close();

        DoubleCesiumWriter doubleCesiumWriter_Lead = pathCesiumWriter.openLeadTimeProperty();
        CesiumIntervalListWriter<DoubleCesiumWriter> cesiumIntervalListWriter_Lead = doubleCesiumWriter_Lead.openMultipleIntervals();

        double deltaTime;
        double orbitalPeriod = getOrbitalPeriod(tle);
        double giriCompleti = Math.floor(seconds / orbitalPeriod);
        double secondiGiroIncompleto = (seconds - (giriCompleti * orbitalPeriod));
        boolean isFirstInterval = true;

        int secondsProcessed = 0;
        ArrayList<JulianDate> listJulianDate = new ArrayList<>();

        JulianDate date = startDate;
        listJulianDate.add(date);
        while (secondsProcessed <= seconds) {
            if (isFirstInterval) {
                deltaTime = secondiGiroIncompleto;
                isFirstInterval = false;
            } else {
                deltaTime = orbitalPeriod;
            }

            date = date.addSeconds(deltaTime);
            listJulianDate.add(date);
            secondsProcessed += deltaTime;
        }

        ArrayList<JulianDate> datesLead = new ArrayList<>();
        datesLead.add(listJulianDate.get(0));
        datesLead.add(listJulianDate.get(0).addSeconds(orbitalPeriod));

        ArrayList<Double> valuesLead = new ArrayList<>();
        valuesLead.add(orbitalPeriod);
        valuesLead.add(0.0);
        for (int i = 0; i < listJulianDate.size() - 1; i++) {
            DoubleCesiumWriter leadIntervalWriter = cesiumIntervalListWriter_Lead.openInterval();
            leadIntervalWriter.writeInterval(listJulianDate.get(i), listJulianDate.get(i + 1));

            leadIntervalWriter.writeNumber(datesLead, valuesLead, 0, valuesLead.size());
            leadIntervalWriter.close();
        }
        cesiumIntervalListWriter_Lead.close();
        doubleCesiumWriter_Lead.close();

        DoubleCesiumWriter doubleCesiumWriter_Trail = pathCesiumWriter.openTrailTimeProperty();
        CesiumIntervalListWriter<DoubleCesiumWriter> cesiumIntervalListWriter_Trail = doubleCesiumWriter_Trail.openMultipleIntervals();

        ArrayList<Double> valuesTrial = new ArrayList<>();
        valuesTrial.add(0.0);
        valuesTrial.add(orbitalPeriod);
        for (int i = 0; i < listJulianDate.size() - 1; i++) {
            DoubleCesiumWriter trailIntervalWriter = cesiumIntervalListWriter_Trail.openInterval();
            trailIntervalWriter.writeInterval(listJulianDate.get(i), listJulianDate.get(i + 1));

            trailIntervalWriter.writeNumber(datesLead, valuesTrial, 0, valuesTrial.size());
            trailIntervalWriter.close();
        }
        cesiumIntervalListWriter_Trail.close();
        doubleCesiumWriter_Trail.close();

        pathCesiumWriter.writeWidthProperty(1.0);
        pathCesiumWriter.writeResolutionProperty(120);
        pathCesiumWriter.close();
    }

    private double getOrbitalPeriod(TLE tle) {
        double orbitalPeriod;

        double semiMajorAxis = Math.pow(8681663.653 / tle.getMeanmo(), (2.0 / 3.0));
        double perigeeHeight = (semiMajorAxis * (1 - tle.getEccn())) - 6371;
        double apogeeHeight = (semiMajorAxis * (1 + tle.getEccn())) - 6371;

        double media = ((perigeeHeight + apogeeHeight) / 2) * 1000;

        double TWO_PI = Math.PI * 2.0;
        long GM = 398600441800000L;

        orbitalPeriod = Math.sqrt(Math.pow(media + 6371000, 3) / GM) * TWO_PI;

        return orbitalPeriod;
    }

    private ArrayList<DateTime> getDataTimes(DateTime dateStart, DateTime dateEnd, int sampling) {
        ArrayList<DateTime> dateTimes = new ArrayList<>();

        DateTime dateTmp = dateStart;

        do {
            dateTimes.add(dateTmp);
            dateTmp = dateTmp.plusSeconds(sampling);

        } while (dateEnd.isAfter(dateTmp));

        return dateTimes;
    }
}
