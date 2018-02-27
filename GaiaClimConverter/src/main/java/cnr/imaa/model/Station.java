package cnr.imaa.model;

import java.util.Date;

public class Station {
    private String locationOfTheStation;
    private String nameOfTheStation;
    private String measuredEcv;
    private String product;
    private String measurementType;
    private String measurementTechnique;
    private String network;
    private String subNetwork;
    private double latitude;
    private double longitude;
    private double altitude;
    private Date startOfOperation;
    private String pi;
    private String mailPi;
    private String website;

    public String getNameOfTheStationFULL(){
        return network + " - " + measuredEcv + " " + measurementType + " - " + nameOfTheStation;
    }

    public String getLocationOfTheStation() {
        return locationOfTheStation;
    }

    public void setLocationOfTheStation(String locationOfTheStation) {
        this.locationOfTheStation = locationOfTheStation;
    }

    public String getNameOfTheStation() {
        return nameOfTheStation;
    }

    public void setNameOfTheStation(String nameOfTheStation) {
        this.nameOfTheStation = nameOfTheStation;
    }

    public String getMeasuredEcv() {
        return measuredEcv;
    }

    public void setMeasuredEcv(String measuredEcv) {
        this.measuredEcv = measuredEcv;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getMeasurementType() {
        return measurementType;
    }

    public void setMeasurementType(String measurementType) {
        this.measurementType = measurementType;
    }

    public String getMeasurementTechnique() {
        return measurementTechnique;
    }

    public void setMeasurementTechnique(String measurementTechnique) {
        this.measurementTechnique = measurementTechnique;
    }

    public String getNetwork() {
        return network;
    }

    public void setNetwork(String network) {
        this.network = network;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getAltitude() {
        return altitude;
    }

    public void setAltitude(double altitude) {
        this.altitude = altitude;
    }

    public Date getStartOfOperation() {
        return startOfOperation;
    }

    public void setStartOfOperation(Date startOfOperation) {
        this.startOfOperation = startOfOperation;
    }

    public String getPi() {
        return pi;
    }

    public String getMailPi() {
        return mailPi;
    }

    public void setMailPi(String mailPi) {
        this.mailPi = mailPi;
    }

    public void setPi(String pi) {
        this.pi = pi;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getSubNetwork() {
        return subNetwork;
    }

    public void setSubNetwork(String subNetwork) {
        this.subNetwork = subNetwork;
    }
}
