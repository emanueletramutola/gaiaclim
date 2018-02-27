package cnr.imaa.model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;

public class ModelForWigos {
    private String dataCentre;
    private String version;
    private Date creationDate;
    private String nameOfStation;
    private String piNetwork;
    private String piStation;
    private Double latitude;
    private Double longitude;
    private Double elevation;
    private LocalDate launchDate;
    private LocalTime launchTime;
    private Double highestLevelReached;
    private Double integratedO3UntilEOF;
    private Double sonde_MLSClimatology;
    private String sondeInstrument;
    private String radiosonde;
    private String kiSolution;
    private String appliedPumpCorrections;
    private Double pumpFlowRate;
    private Double backgroundCurrent;
    private Double missingOrBadValues;
    private ArrayList<String> listVariables;

    public String getDataCentre() {
        return dataCentre;
    }

    public void setDataCentre(String dataCentre) {
        this.dataCentre = dataCentre;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public String getNameOfStation() {
        return nameOfStation;
    }

    public void setNameOfStation(String nameOfStation) {
        this.nameOfStation = nameOfStation;
    }

    public String getPiNetwork() {
        return piNetwork;
    }

    public void setPiNetwork(String piNetwork) {
        this.piNetwork = piNetwork;
    }

    public String getPiStation() {
        return piStation;
    }

    public void setPiStation(String piStation) {
        this.piStation = piStation;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getElevation() {
        return elevation;
    }

    public void setElevation(Double elevation) {
        this.elevation = elevation;
    }

    public LocalDate getLaunchDate() {
        return launchDate;
    }

    public void setLaunchDate(LocalDate launchDate) {
        this.launchDate = launchDate;
    }

    public LocalTime getLaunchTime() {
        return launchTime;
    }

    public void setLaunchTime(LocalTime launchTime) {
        this.launchTime = launchTime;
    }

    public Double getHighestLevelReached() {
        return highestLevelReached;
    }

    public void setHighestLevelReached(Double highestLevelReached) {
        this.highestLevelReached = highestLevelReached;
    }

    public Double getIntegratedO3UntilEOF() {
        return integratedO3UntilEOF;
    }

    public void setIntegratedO3UntilEOF(Double integratedO3UntilEOF) {
        this.integratedO3UntilEOF = integratedO3UntilEOF;
    }

    public Double getSonde_MLSClimatology() {
        return sonde_MLSClimatology;
    }

    public void setSonde_MLSClimatology(Double sonde_MLSClimatology) {
        this.sonde_MLSClimatology = sonde_MLSClimatology;
    }

    public String getSondeInstrument() {
        return sondeInstrument;
    }

    public void setSondeInstrument(String sondeInstrument) {
        this.sondeInstrument = sondeInstrument;
    }

    public String getRadiosonde() {
        return radiosonde;
    }

    public void setRadiosonde(String radiosonde) {
        this.radiosonde = radiosonde;
    }

    public String getKiSolution() {
        return kiSolution;
    }

    public void setKiSolution(String kiSolution) {
        this.kiSolution = kiSolution;
    }

    public String getAppliedPumpCorrections() {
        return appliedPumpCorrections;
    }

    public void setAppliedPumpCorrections(String appliedPumpCorrections) {
        this.appliedPumpCorrections = appliedPumpCorrections;
    }

    public Double getPumpFlowRate() {
        return pumpFlowRate;
    }

    public void setPumpFlowRate(Double pumpFlowRate) {
        this.pumpFlowRate = pumpFlowRate;
    }

    public Double getBackgroundCurrent() {
        return backgroundCurrent;
    }

    public void setBackgroundCurrent(Double backgroundCurrent) {
        this.backgroundCurrent = backgroundCurrent;
    }

    public Double getMissingOrBadValues() {
        return missingOrBadValues;
    }

    public void setMissingOrBadValues(Double missingOrBadValues) {
        this.missingOrBadValues = missingOrBadValues;
    }

    public ArrayList<String> getListVariables() {
        return listVariables;
    }

    public void setListVariables(ArrayList<String> listVariables) {
        this.listVariables = listVariables;
    }
}