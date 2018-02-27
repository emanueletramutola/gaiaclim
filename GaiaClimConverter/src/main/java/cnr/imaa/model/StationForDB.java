package cnr.imaa.model;

import com.vividsolutions.jts.geom.Point;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "Station")
public class StationForDB {
    private Long id;
    private String stationName;
    private String network;
    private Date beginDate;
    private double verticalExtent_MinimumValue;
    private double verticalExtent_MaximumValue;
    private String discipline;
    private String instrument;
    private String product;
    private String platform;
    private String featureType;
    @Type(type="org.hibernate.spatial.GeometryType")
    private Point coordinates;
    private String ecv;
    private int mm_metadata;
    private int mm_documentation;
    private int mm_uncertainty;
    private int mm_public;
    private int mm_usage;
    private int mm_sustainability;
    private int mm_software;
    private String subnetwork;
    private String website;

    public StationForDB() {
    }

    public StationForDB(String stationName, Date beginDate, double verticalExtent_MinimumValue, double verticalExtent_MaximumValue, String discipline, String instrument, String product, String platform, String featureType, Point coordinates) {
        this.stationName = stationName;
        this.beginDate = beginDate;
        this.verticalExtent_MinimumValue = verticalExtent_MinimumValue;
        this.verticalExtent_MaximumValue = verticalExtent_MaximumValue;
        this.discipline = discipline;
        this.instrument = instrument;
        this.product = product;
        this.platform = platform;
        this.featureType = featureType;
        this.coordinates = coordinates;
    }

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    public Long getId() {
        return id;
    }

    private void setId(Long id) {
        this.id = id;
    }

    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

    public Date getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(Date beginDate) {
        this.beginDate = beginDate;
    }

    public double getVerticalExtent_MinimumValue() {
        return verticalExtent_MinimumValue;
    }

    public void setVerticalExtent_MinimumValue(double verticalExtent_MinimumValue) {
        this.verticalExtent_MinimumValue = verticalExtent_MinimumValue;
    }

    public double getVerticalExtent_MaximumValue() {
        return verticalExtent_MaximumValue;
    }

    public void setVerticalExtent_MaximumValue(double verticalExtent_MaximumValue) {
        this.verticalExtent_MaximumValue = verticalExtent_MaximumValue;
    }

    public String getDiscipline() {
        return discipline;
    }

    public void setDiscipline(String discipline) {
        this.discipline = discipline;
    }

    public String getInstrument() {
        return instrument;
    }

    public void setInstrument(String instrument) {
        this.instrument = instrument;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getFeatureType() {
        return featureType;
    }

    public void setFeatureType(String featureType) {
        this.featureType = featureType;
    }

    public Point getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Point coordinates) {
        if (coordinates.getSRID() == 0) {
            coordinates.setSRID(4326);
        }
        this.coordinates = coordinates;
    }

    public String getNetwork() {
        return network;
    }

    public void setNetwork(String network) {
        this.network = network;
    }

    public String getEcv() {
        return ecv;
    }

    public void setEcv(String ecv) {
        this.ecv = ecv;
    }

    public int getMm_metadata() {
        return mm_metadata;
    }

    public void setMm_metadata(int mm_metadata) {
        this.mm_metadata = mm_metadata;
    }

    public int getMm_documentation() {
        return mm_documentation;
    }

    public void setMm_documentation(int mm_documentation) {
        this.mm_documentation = mm_documentation;
    }

    public int getMm_uncertainty() {
        return mm_uncertainty;
    }

    public void setMm_uncertainty(int mm_uncertainty) {
        this.mm_uncertainty = mm_uncertainty;
    }

    public int getMm_public() {
        return mm_public;
    }

    public void setMm_public(int mm_public) {
        this.mm_public = mm_public;
    }

    public int getMm_usage() {
        return mm_usage;
    }

    public void setMm_usage(int mm_usage) {
        this.mm_usage = mm_usage;
    }

    public int getMm_sustainability() {
        return mm_sustainability;
    }

    public void setMm_sustainability(int mm_sustainability) {
        this.mm_sustainability = mm_sustainability;
    }

    public int getMm_software() {
        return mm_software;
    }

    public void setMm_software(int mm_software) {
        this.mm_software = mm_software;
    }

    public String getSubnetwork() {
        return subnetwork;
    }

    public void setSubnetwork(String subnetwork) {
        this.subnetwork = subnetwork;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }
}