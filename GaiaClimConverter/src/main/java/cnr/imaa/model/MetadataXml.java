package cnr.imaa.model;

import org.w3c.dom.Document;

public class MetadataXml {
    private String measuredEcv;
    private String measurementType;
    private String network;
    private Document doc;

    public static final String MEASUREMENTTYPE_PROFILE = "PROFILE";
    public static final String MEASUREMENTTYPE_TOWER = "TOWER";
    public static final String MEASUREMENTTYPE_COLUMN = "COLUMN";
    public static final String MEASUREMENTTYPE_SURFACE = "SURFACE";

    public String getMeasuredEcv() {
        return measuredEcv;
    }

    public Document getDoc() {
        return doc;
    }

    public void setDoc(Document doc) {
        this.doc = doc;
    }

    public void setMeasuredEcv(String measuredEcv) {
        this.measuredEcv = measuredEcv;
    }

    public String getMeasurementType() {
        return measurementType;
    }

    public void setMeasurementType(String measurementType) {
        this.measurementType = measurementType;
    }

    public String getNetwork() {
        return network;
    }

    public void setNetwork(String network) {
        this.network = network;
    }
}
