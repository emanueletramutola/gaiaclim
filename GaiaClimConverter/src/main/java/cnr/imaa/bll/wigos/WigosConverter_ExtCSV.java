package cnr.imaa.bll.wigos;

import cnr.imaa.model.CountryISO3;
import cnr.imaa.model.wigos.*;
import cnr.imaa.model.woudc.ExtCSV;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

public class WigosConverter_ExtCSV {
    private ExtCSV extCSV;
    private CountryISO3Utility countryISO3Utility;

    public WigosConverter_ExtCSV(ExtCSV extCSV) {
        this.extCSV = extCSV;
        countryISO3Utility = new CountryISO3Utility();
    }

    public GaiaClimMetadataType build() {
        GaiaClimMetadataType gaiaClimMetadata = new GaiaClimMetadataType();

        gaiaClimMetadata.setObservedVariable(getObservedVariable());
        gaiaClimMetadata.setPurposeOfObservation(getPurposeOfObservation());
        gaiaClimMetadata.setStationPlatform(getStationPlatform());
        gaiaClimMetadata.setEnvironment(getEnvironment());
        gaiaClimMetadata.setInstrumentsAndMethodsOfObservation(getInstrumentsAndMethodsOfObservation());
        gaiaClimMetadata.setSampling(getSampling());
        gaiaClimMetadata.setDataProcessingAndReporting(getDataProcessingAndReporting());
        gaiaClimMetadata.setDataQuality(getDataQuality());
        gaiaClimMetadata.setOwnershipAndDataPolicy(getOwnershipAndDataPolicy());
        gaiaClimMetadata.setContact(getContact());

        return gaiaClimMetadata;
    }

    private List<ObservedVariableType> getObservedVariable() {
        List<ObservedVariableType> observedVariableTypeList = new ArrayList<>();

        ObservedVariableType observedVariableType = null;

        ArrayList<String> listVariable = extCSV.getListProfiles();
        listVariable.add("uwind");
        listVariable.add("vwind");
        listVariable.add("latitude");
        listVariable.add("longitude");

        for (String variable : listVariable) {
            observedVariableType = new ObservedVariableType();

            ObservedVariableMeasurandType observedVariableMeasurandType = new ObservedVariableMeasurandType();
            MeasurementUnitType measurementUnitType = new MeasurementUnitType();

            observedVariableMeasurandType.setDomain(DomainType.ATMOSPHERIC);
            observedVariableMeasurandType.setVariable(variable);
            observedVariableMeasurandType.setModeOfObservation(Arrays.asList(ModeOfObservationType.I));

            switch (variable) {
                case "Duration":
                    measurementUnitType.setName("second");
                    measurementUnitType.setAbbreviation("s");
                    break;
                case "Pressure":
                    observedVariableMeasurandType.setSubdomain(SubdomainType.ATMOSPHERIC_PRESSURE);
                    measurementUnitType.setName("hectopascal");
                    measurementUnitType.setAbbreviation("hPa");
                    break;
                case "O3PartialPressure":
                    observedVariableMeasurandType.setSubdomain(SubdomainType.OZONE);
                    measurementUnitType.setName("millipascal");
                    measurementUnitType.setAbbreviation("mPa");
                    break;
                case "Temperature":
                    observedVariableMeasurandType.setSubdomain(SubdomainType.TEMPERATURE);
                    measurementUnitType.setName("degree Kelvin");
                    measurementUnitType.setAbbreviation("K");
                    break;
                case "WindSpeed":
                    observedVariableMeasurandType.setSubdomain(SubdomainType.WIND);
                    measurementUnitType.setName("metres per second");
                    measurementUnitType.setAbbreviation("m s-1");
                    break;
                case "WindDirection":
                    observedVariableMeasurandType.setSubdomain(SubdomainType.WIND);
                    measurementUnitType.setName("degree");
                    measurementUnitType.setAbbreviation("deg");
                    break;
                case "RelativeHumidity":
                    observedVariableMeasurandType.setSubdomain(SubdomainType.WATER_VAPOUR);
                    measurementUnitType.setName("per cent");
                    measurementUnitType.setAbbreviation("%");
                    break;
                case "uwind":
                case "vwind":
                    observedVariableMeasurandType.setSubdomain(SubdomainType.WIND);
                    measurementUnitType.setName("metres per second");
                    measurementUnitType.setAbbreviation("m s-1");
                    break;
                case "SampleTemperature":
                    observedVariableMeasurandType.setSubdomain(SubdomainType.TEMPERATURE);
                    measurementUnitType.setName("degree Kelvin");
                    measurementUnitType.setAbbreviation("K");
                    break;
                case "GPHeight":
                    measurementUnitType.setName("meter");
                    measurementUnitType.setAbbreviation("m");
                    break;
                case "latitude":
                    measurementUnitType.setName("degrees north");
                    break;
                case "longitude":
                    measurementUnitType.setName("degrees east");
                    break;
            }

            observedVariableType.setObservedVariableMeasurand(observedVariableMeasurandType);
            observedVariableType.setMeasurementUnit(measurementUnitType);

            Calendar startDate = getStartDate();
            int secondsElapsed = extCSV.getTemporalExtend();
            Calendar stopDate = Calendar.getInstance();
            stopDate.setTime(startDate.getTime());
            stopDate.add(Calendar.SECOND, secondsElapsed);

            String temporalExtent = "StartTime: " + WigosBuilder.DATE_FORMAT.format(startDate.getTime())
                    + "; StopTime: " + WigosBuilder.DATE_FORMAT.format(stopDate.getTime())
                    + "; TemporalExtent:" + secondsElapsed + " s";

            observedVariableType.setTemporalExtent(temporalExtent);
            observedVariableType.setSpatialExtent("from " + extCSV.getLocation().getHeight() + "m to " + Integer.toString(extCSV.getSpatialExtent()) + "m");
            observedVariableType.setRepresentativenessOfObservation(RepresentativenessType.MESOSCALE);

            observedVariableTypeList.add(observedVariableType);
        }

        return observedVariableTypeList;
    }

    private Calendar getStartDate() {
        Calendar startDate;

        String[] date = extCSV.getTimestamp().getDate().split("-");
        String[] time = extCSV.getTimestamp().getTime().split(":");

        startDate = Calendar.getInstance();
        startDate.set(Calendar.YEAR, Integer.parseInt(date[0]));
        startDate.set(Calendar.MONTH, Integer.parseInt(date[1]));
        startDate.set(Calendar.DAY_OF_MONTH, Integer.parseInt(date[2]));
        startDate.set(Calendar.HOUR_OF_DAY, Integer.parseInt(time[0]));
        startDate.set(Calendar.MINUTE, Integer.parseInt(time[1]));
        startDate.set(Calendar.SECOND, Integer.parseInt(time[2]));

        return startDate;
    }

    private PurposeOfObservationType getPurposeOfObservation() {
        PurposeOfObservationType purposeOfObservationType = new PurposeOfObservationType();

        purposeOfObservationType.setApplicationArea(ApplicationAreaType.CLIMATE_MONITORING_AS_UNDERTAKEN_THROUGH_THE_GLOBAL_CLIMATE_OBSERVING_SYSTEM_GCOS);

        ProgrammeNetworkAffiliationType programmeNetworkAffiliationType = new ProgrammeNetworkAffiliationType();
        programmeNetworkAffiliationType.setName("NDACC");
        programmeNetworkAffiliationType.setDefinition("Network for Detection of Atmospheric Composition Change");
        programmeNetworkAffiliationType.setSponsorContributing("GALION; WMO/GAW");

        purposeOfObservationType.setProgrammeNetworkAffiliation(programmeNetworkAffiliationType);

        return purposeOfObservationType;
    }

    private CountryISO3 getISO3CountryInfo() {
        CountryISO3 countryISO3;

        String code3 = extCSV.getPlatform().getCountry();

        CountryISO3Utility countryISO3Utility = new CountryISO3Utility();
        countryISO3 = countryISO3Utility.getISO3CountryInfoByCode3(code3);

        return countryISO3;
    }

    private StationPlatformType getStationPlatform() {
        StationPlatformType stationPlatformType = new StationPlatformType();

        TerritoryOfOriginOfDataType territoryOfOriginOfDataType = new TerritoryOfOriginOfDataType();

        CountryISO3 countryISO3 = getISO3CountryInfo();

        territoryOfOriginOfDataType.setISO3CountryCode(countryISO3.getCode3());

        GeospatialLocationType geospatialLocationType = new GeospatialLocationType();
        geospatialLocationType.setCoordinatesSourceDevice(CoordinatesSourceDeviceType.GPS);
        geospatialLocationType.setCoordinatesReference(CoordinatesReferenceType.WGS_84);

        stationPlatformType.setRegionOfOriginOfData(countryISO3Utility.getRegionEnum(countryISO3.getRegion()));
        territoryOfOriginOfDataType.setName(countryISO3.getName());

        stationPlatformType.setTerritoryOfOriginOfData(territoryOfOriginOfDataType);
        stationPlatformType.setStationPlatformName(extCSV.getPlatform().getName());
        stationPlatformType.setStationPlatformType(StationPlatformTypeType.LAND_STATION);
        stationPlatformType.setStationPlatformModel(extCSV.getContent().getCategory());

        stationPlatformType.setStationPlatformUniqueIdentifier(extCSV.getPlatform().getId());
        stationPlatformType.setGeospatialLocation(geospatialLocationType);
        //stationPlatformType.setDataCommunicationMethod();
        stationPlatformType.setStationStatus(StationStatusType.OPERATIONAL_REPORTING);

        return stationPlatformType;
    }

    private EnvironmentType getEnvironment() {
        EnvironmentType environmentType = new EnvironmentType();

        //environmentType.setSurfaceCover();
        //environmentType.setSurfaceCoverClassificatioScheme();
        //environmentType.setTopographyOrBathymetry();
        //environmentType.setEventsAtStationPlatform();

        String siteDescription = "Name:" + extCSV.getPlatform().getName()
                + " Country:" + extCSV.getPlatform().getCountry()
                + " lat.:" + extCSV.getLocation().getLatitude()
                + " long.:" + extCSV.getLocation().getLongitude()
                + " height:" + extCSV.getLocation().getHeight();

        environmentType.setSiteInformation(siteDescription);

        return environmentType;
    }

    private InstrumentsAndMethodsOfObservationType getInstrumentsAndMethodsOfObservation() {
        InstrumentsAndMethodsOfObservationType instrumentsAndMethodsOfObservationType = new InstrumentsAndMethodsOfObservationType();

        instrumentsAndMethodsOfObservationType.setSourceOfObservation(SourceOfObservationType.AUTOMATIC_OBSERVATION);
        instrumentsAndMethodsOfObservationType.setMeasurementObservingMethod(extCSV.getContent().getCategory());
        instrumentsAndMethodsOfObservationType.setInstrumentSpecifications("specification for "
                + extCSV.getInstrument().getName() + " "
                + extCSV.getInstrument().getModel());
        instrumentsAndMethodsOfObservationType.setInstrumentOperatingStatus(InstrumentOperatingStatusType.OPERATIONAL);
        //instrumentsAndMethodsOfObservationType.setVerticalDistanceOfSensor();
        //instrumentsAndMethodsOfObservationType.setConfigurationOfInstrumentation();
        //instrumentsAndMethodsOfObservationType.setInstrumentControlSchedule();

        instrumentsAndMethodsOfObservationType.setInstrumentControlResult(InstrumentControlResultType.NO_CHANGES_IN_CALIBRATION);

        String instrumentDescription = "Name:" + extCSV.getInstrument().getName()
                + " Model:" + extCSV.getInstrument().getModel()
                + " Number:" + extCSV.getInstrument().getNumber();
        instrumentsAndMethodsOfObservationType.setInstrumentModelAndSerialNumber(instrumentDescription);

        //instrumentsAndMethodsOfObservationType.setInstrumentMaintenanceSchedule();
        //instrumentsAndMethodsOfObservationType.setMaintenanceParty();
        //instrumentsAndMethodsOfObservationType.setMaintenanceActivity();
        instrumentsAndMethodsOfObservationType.setStatusOfObservation(StatusOfObservationType.PRIMARY);
        instrumentsAndMethodsOfObservationType.setExposureOfInstruments(ExposureOfInstrumentsType.CLASS_2);

        return instrumentsAndMethodsOfObservationType;
    }

    private SamplingType getSampling() {
        SamplingType samplingType = new SamplingType();

        samplingType.setSamplingProcedures("Measurements are performed using a ballon-borne radiosonde equipped with an ECC pump for the measurement of ozone; products are vertical profiles of ozone concentration from the ground level to about 40 km above.");
        samplingType.setSampleTreatment("Heating and cooling, ozone calibration");
        samplingType.setScheduleOfObservation("Ozonesoudings are performed at minimum once per month");
        samplingType.setTemporalSamplingInterval("Ozonesoundings measure at different height level each 1-2 seconds and cover the entire range from the ground to the LS in about one hour.");
        samplingType.setSpatialSamplingResolution("10 m");

        samplingType.setSamplingStrategy(SamplingStrategyType.DISCRETE);
        //samplingType.setSamplingTimePeriod();
        //samplingType.setDiurnalBaseTime();

        return samplingType;
    }

    private DataProcessingAndReportingType getDataProcessingAndReporting() {
        DataProcessingAndReportingType dataProcessingAndReportingType = new DataProcessingAndReportingType();

        dataProcessingAndReportingType.setDataProcessingMethodsAndAlgorithms("manufacturer software for "
                + extCSV.getAuxiliary_data().getMeteosonde());

        dataProcessingAndReportingType.setProcessingAnalysisCentre(extCSV.getPlatform().getId()
                + " " + extCSV.getPlatform().getName()
                + " " + extCSV.getPlatform().getCountry());

        dataProcessingAndReportingType.setLevelOfData(getLevelOfData());
        dataProcessingAndReportingType.setDataFormat(extCSV.getContent().getContent_class());
        dataProcessingAndReportingType.setVersionOfDataFormat(extCSV.getContent().getForm());
        dataProcessingAndReportingType.setReferenceTime(ReferenceTimeType.TIME_SERVER);
        dataProcessingAndReportingType.setNumericalResolution("Temperature = 0.1°C; pressure = 0.1 hPa; Humidity = 1%; ozone concentration = 0.005 ppm");

        //dataProcessingAndReportingType.setTemporalReportingPeriod(getTemporalReportingPeriod());
        //dataProcessingAndReportingType.setSpatialReportingInterval(getSpatialReportingInterval());
        //dataProcessingAndReportingType.setSoftwareProcessorAndVersion(getSoftwareProcessorAndVersion());
        //dataProcessingAndReportingType.setReferenceDatum(getReferenceDatum());
        //dataProcessingAndReportingType.setLatencyOfReporting(getLatencyOfReporting());

        return dataProcessingAndReportingType;
    }

    private LevelOfDataType getLevelOfData() {
        LevelOfDataType levelOfDataType = null;

        int level = (int) Double.parseDouble(extCSV.getContent().getLevel());

        switch (level) {
            case 0:
                levelOfDataType = LevelOfDataType.LEVEL_0;
                break;
            case 1:
                levelOfDataType = LevelOfDataType.LEVEL_I;
                break;
            case 2:
                levelOfDataType = LevelOfDataType.LEVEL_II;
                break;
            case 3:
                levelOfDataType = LevelOfDataType.LEVEL_III;
                break;
            case 4:
                levelOfDataType = LevelOfDataType.LEVEL_IV;
                break;
        }

        return levelOfDataType;
    }

    private DataQualityType getDataQuality() {
        DataQualityType dataQualityType = new DataQualityType();

        dataQualityType.setUncertaintyOfMeasurement("Uncertainty not estimated");
//        dataQualityType.setProcedureUsedToEstimateUncertainty(getProcedureUsedToEstimateUncertainty());

        QualityFlagType qualityFlagType = new QualityFlagType();
        qualityFlagType.setQualityFlagOGC(QualityFlagOGCType.UNCHECKED);
        dataQualityType.setQualityFlag(qualityFlagType);

        dataQualityType.setQualityFlaggingSystem(QualityFlaggingSystemType.OTHER_QUALITY_FLAGGING_SYSTEM);
        dataQualityType.setTraceability(TraceabilityType.TRACEABLE_TO_INTERNATIONAL_STANDARD);

        return dataQualityType;
    }

    private OwnershipAndDataPolicyType getOwnershipAndDataPolicy() {
        OwnershipAndDataPolicyType ownershipAndDataPolicyType = new OwnershipAndDataPolicyType();

        ownershipAndDataPolicyType.setSupervisingOrganization(extCSV.getContent().getContent_class());
        ownershipAndDataPolicyType.setDataPolicyUseConstraints(DataPolicyUseConstraintsType.WMO_ESSENTIAL);

        return ownershipAndDataPolicyType;
    }

    private ContactType getContact() {
        ContactType contactType = new ContactType();

        contactType.setContact(extCSV.getData_generation().getScientificAuthority());

        return contactType;
    }
}