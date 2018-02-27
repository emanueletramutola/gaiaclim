package cnr.imaa.model.woudc;

import java.util.ArrayList;

public class ExtCSV {
    private ExtCSV_CONTENT content;
    private ExtCSV_DATA_GENERATION data_generation;
    private ExtCSV_PLATFORM platform;
    private ExtCSV_INSTRUMENT instrument;
    private ExtCSV_LOCATION location;
    private ExtCSV_TIMESTAMP timestamp;
    private ExtCSV_FLIGHT_SUMMARY flight_summary;
    private ExtCSV_AUXILIARY_DATA auxiliary_data;
    private ArrayList<String> listProfiles;
    private int temporalExtend;
    private int spatialExtent;

    public int getTemporalExtend() {
        return temporalExtend;
    }

    public void setTemporalExtend(int temporalExtend) {
        this.temporalExtend = temporalExtend;
    }

    public int getSpatialExtent() {
        return spatialExtent;
    }

    public void setSpatialExtent(int spatialExtent) {
        this.spatialExtent = spatialExtent;
    }

    public ExtCSV_CONTENT getContent() {
        return content;
    }

    public void setContent(ExtCSV_CONTENT content) {
        this.content = content;
    }

    public ExtCSV_DATA_GENERATION getData_generation() {
        return data_generation;
    }

    public void setData_generation(ExtCSV_DATA_GENERATION data_generation) {
        this.data_generation = data_generation;
    }

    public ExtCSV_PLATFORM getPlatform() {
        return platform;
    }

    public void setPlatform(ExtCSV_PLATFORM platform) {
        this.platform = platform;
    }

    public ExtCSV_INSTRUMENT getInstrument() {
        return instrument;
    }

    public void setInstrument(ExtCSV_INSTRUMENT instrument) {
        this.instrument = instrument;
    }

    public ExtCSV_LOCATION getLocation() {
        return location;
    }

    public void setLocation(ExtCSV_LOCATION location) {
        this.location = location;
    }

    public ExtCSV_TIMESTAMP getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(ExtCSV_TIMESTAMP timestamp) {
        this.timestamp = timestamp;
    }

    public ExtCSV_FLIGHT_SUMMARY getFlight_summary() {
        return flight_summary;
    }

    public void setFlight_summary(ExtCSV_FLIGHT_SUMMARY flight_summary) {
        this.flight_summary = flight_summary;
    }

    public ExtCSV_AUXILIARY_DATA getAuxiliary_data() {
        return auxiliary_data;
    }

    public void setAuxiliary_data(ExtCSV_AUXILIARY_DATA auxiliary_data) {
        this.auxiliary_data = auxiliary_data;
    }

    public ArrayList<String> getListProfiles() {
        return listProfiles;
    }

    public void setListProfiles(ArrayList<String> listProfiles) {
        this.listProfiles = listProfiles;
    }
}
