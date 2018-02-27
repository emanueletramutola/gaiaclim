package cnr.imaa.dal;

import cnr.imaa.bll.MetadataXml;
import cnr.imaa.model.Station;
import cnr.imaa.model.StationForDB;
import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.Point;
import org.w3c.dom.Document;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

public class StationDAL {
    DbBinder dbBinder = new DbBinder();

    public List<StationForDB> getListStationsByName(String  stationName) {
        String query = "from StationForDB s where s.stationName = ?";
        List<StationForDB> result = (List<StationForDB>) dbBinder.getList(query, StationForDB.class, stationName);

        return result;
    }

    public List<StationForDB> getListStationsByNetwork(String network) {
        String query = "from StationForDB s where s.network = ?";
        List<StationForDB> result = (List<StationForDB>) dbBinder.getList(query, StationForDB.class, network);

        return result;
    }

    public void store(Document doc, Station station) {
        StationForDB stationForDB = getStationForDBFromDocument(doc, station);

        dbBinder.store(stationForDB);
    }

    public void update(StationForDB stationForDB) {
        dbBinder.update(stationForDB);
    }

    private StationForDB getStationForDBFromDocument(Document doc, Station station) {
        StationForDB stationForDB = new StationForDB();

        String beginDate;
        GeometryFactory gf = new GeometryFactory();
        Coordinate coord;
        Point point;
        double longitude;
        double latitude;
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.US);

        try {
            beginDate = MetadataXml.getNodeStartOfOperation(doc).getTextContent();
            if (beginDate != null && beginDate.trim() != "") {
                stationForDB.setBeginDate(formatter.parse(beginDate));
            }

            stationForDB.setInstrument(cnr.imaa.bll.MetadataXml.getNodeMeasurementTechnique(doc).getTextContent());

            longitude = Double.parseDouble(cnr.imaa.bll.MetadataXml.getNodeEastBoundLongitude(doc).getTextContent());
            latitude = Double.parseDouble(cnr.imaa.bll.MetadataXml.getNodeNorthBoundLatitude(doc).getTextContent());
            coord = new Coordinate(longitude, latitude);
            point = gf.createPoint(coord);
            point.setSRID(4326);

            stationForDB.setCoordinates(point);

            stationForDB.setDiscipline(cnr.imaa.bll.MetadataXml.getNodeDiscipline(doc).getTextContent());
            stationForDB.setFeatureType(cnr.imaa.bll.MetadataXml.getNodeFeatureType(doc).getTextContent().toLowerCase());
            stationForDB.setPlatform(cnr.imaa.bll.MetadataXml.getNodePlatform(doc).getTextContent());
            stationForDB.setProduct(cnr.imaa.bll.MetadataXml.getNodeProduct(doc).getTextContent());
            stationForDB.setStationName(station.getNameOfTheStation());
            stationForDB.setNetwork(station.getNetwork());
            stationForDB.setSubnetwork(station.getSubNetwork());
            stationForDB.setVerticalExtent_MinimumValue(Double.parseDouble(cnr.imaa.bll.MetadataXml.getNodeMinimumValue(doc).getTextContent()));
            stationForDB.setVerticalExtent_MaximumValue(Double.parseDouble(cnr.imaa.bll.MetadataXml.getNodeMaximumValue(doc).getTextContent()));
            stationForDB.setEcv(station.getMeasuredEcv());
            stationForDB.setWebsite(station.getWebsite());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return stationForDB;
    }
}
