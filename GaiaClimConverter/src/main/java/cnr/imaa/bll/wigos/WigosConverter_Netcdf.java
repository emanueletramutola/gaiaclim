package cnr.imaa.bll.wigos;

import cnr.imaa.model.CountryISO3;
import cnr.imaa.model.wigos.*;
import com.google.common.primitives.Floats;
import ucar.ma2.Array;
import ucar.nc2.Attribute;
import ucar.nc2.NetcdfFile;
import ucar.nc2.Variable;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class WigosConverter_Netcdf {
    private NetcdfFile ncfile;

    private final static DateFormat DATE_FORMAT_EARLINET = new SimpleDateFormat("yyyyMMdd");

    private CountryISO3Utility countryISO3Utility;
    private String latitude;
    private String longitude;
    private CountryISO3 countryISO3;
    private String wigosInputFormat;

    public WigosConverter_Netcdf(NetcdfFile ncfile, String wigosInputFormat) {
        this.ncfile = ncfile;

        this.wigosInputFormat = wigosInputFormat;
        this.countryISO3Utility = new CountryISO3Utility();

        switch (wigosInputFormat) {
            case "NETCDF_GRUAN":
                this.latitude = ncfile.findGlobalAttribute("g.MeasuringSystem.Latitude").getStringValue().trim();
                this.longitude = ncfile.findGlobalAttribute("g.MeasuringSystem.Longitude").getStringValue().trim();
                break;
            case "NETCDF_EARLINET":
                this.latitude = ncfile.findGlobalAttribute("Latitude_degrees_north").getNumericValue().toString().trim();
                this.longitude = ncfile.findGlobalAttribute("Longitude_degrees_east").getNumericValue().toString().trim();
                break;
        }

        if (this.latitude.contains("°")) this.latitude = this.latitude.replace("°", "").trim();
        if (this.longitude.contains("°")) this.longitude = this.longitude.replace("°", "").trim();

        this.countryISO3 = getISO3CountryInfo();
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

    private String getVariableName(Variable variable) {
        String variableName;

        switch (this.wigosInputFormat) {
            case "NETCDF_GRUAN":
                List<Attribute> listAttributes = variable.getAttributes().stream()
                        .filter(x -> x.toString().contains("standard_name"))
                        .collect(Collectors.toList());

                if (listAttributes.size() == 1) {
                    variableName = listAttributes.get(0).getStringValue();
                } else {
                    variableName = variable.getDescription();
                }

                break;
            case "NETCDF_EARLINET":
                variableName = variable.getShortName();
                break;
            default:
                variableName = "";
                break;
        }

        return variableName;
    }

    private List<ObservedVariableType> getObservedVariable() {
        List<ObservedVariableType> observedVariableTypeList = new ArrayList<>();

        ObservedVariableType observedVariableType = null;

        for (Variable variable : ncfile.getVariables()) {
            observedVariableType = new ObservedVariableType();

            ObservedVariableMeasurandType observedVariableMeasurandType = new ObservedVariableMeasurandType();
            MeasurementUnitType measurementUnitType = new MeasurementUnitType();

            observedVariableMeasurandType.setDomain(DomainType.ATMOSPHERIC);

            String variableName = getVariableName(variable);

            observedVariableMeasurandType.setVariable(variableName);
            observedVariableMeasurandType.setModeOfObservation(Arrays.asList(ModeOfObservationType.I));

            switch (variableName.toLowerCase()) {
                case "air_pressure":
                case "air_pressure standard_error":
                    observedVariableMeasurandType.setSubdomain(SubdomainType.ATMOSPHERIC_PRESSURE);
                    measurementUnitType.setName("hectopascal");
                    measurementUnitType.setAbbreviation("hPa");

                    break;
                case "air_temperature":
                case "frostpoint":
                case "air_temperature correction":
                case "air_temperature correlated_uncertainty":
                case "air_temperature standard_deviation":
                case "air_temperature standard_error":
                    observedVariableMeasurandType.setSubdomain(SubdomainType.TEMPERATURE);
                    measurementUnitType.setName("degree Kelvin");
                    measurementUnitType.setAbbreviation("K");

                    break;
                case "wind_speed":
                case "eastward_wind":
                case "northward_wind":
                case "wind_speed standard_error":
                    observedVariableMeasurandType.setSubdomain(SubdomainType.WIND);
                    measurementUnitType.setName("metres per second");
                    measurementUnitType.setAbbreviation("m s-1");

                    break;
                case "wind_from_direction":
                case "wind_from_direction standard_error":
                    observedVariableMeasurandType.setSubdomain(SubdomainType.WIND);
                    measurementUnitType.setName("degree");
                    measurementUnitType.setAbbreviation("deg");

                    break;
                case "relative_humidity":
                    observedVariableMeasurandType.setSubdomain(SubdomainType.WATER_VAPOUR);
                    measurementUnitType.setName("per cent");
                    measurementUnitType.setAbbreviation("%");

                    break;
                case "time":
                    measurementUnitType.setName("second");
                    measurementUnitType.setAbbreviation("s");

                    break;
                case "geopotential_height":
                case "altitude":
                case "altitude standard_error":
                case "verticalresolution":
                case "mixinglayerheight":
                case "dustlayerheight":
                    measurementUnitType.setName("meter");
                    measurementUnitType.setAbbreviation("m");
                    break;
                case "longitude":
                    measurementUnitType.setName("degrees east");

                    break;
                case "latitude":
                    measurementUnitType.setName("degrees north");
                    break;
                case "water_vapor_mixing_ratio":
                case "relative_humidity standard_deviation":
                case "relative_humidity correction":
                case "relative_humidity correlated_uncertainty":
                case "relative_humidity standard_error":
                    observedVariableMeasurandType.setSubdomain(SubdomainType.WATER_VAPOUR);
                    break;
                case "ascent/descent speed":
                    measurementUnitType.setName("metres per second");
                    measurementUnitType.setAbbreviation("m s-1");

                    break;
                case "short_wave_radiation":
                case "short_wave_radiation standard_error":
                    observedVariableMeasurandType.setSubdomain(SubdomainType.GLOBAL_DOWNWARD_SOLAR_RADIATION);

                    measurementUnitType.setName("watts per square metre");
                    measurementUnitType.setAbbreviation("W m-2");
                    break;
                case "relative_humidity resolution":
                    observedVariableMeasurandType.setSubdomain(SubdomainType.WATER_VAPOUR);
                    measurementUnitType.setName("seconds");
                    measurementUnitType.setAbbreviation("s");
                    break;
                case "backscatter":
                case "errorbackscatter":
                    measurementUnitType.setAbbreviation("1/(m*sr)");
                    break;
                case "extinction":
                case "errorextinction":
                case "rayleighextinction":
                    measurementUnitType.setAbbreviation("1/m");
                    break;
                case "errorparticledepolarization":
                case "errorvolumedepolarization":
                case "particledepolarization":
                case "volumedepolarization":
                case "depolarisation":
                case "errordepolarisation":
                    // no measurement unit
                    break;
                case "errorwatervapor":
                case "watervapormixingratio":
                    observedVariableMeasurandType.setSubdomain(SubdomainType.WATER_VAPOUR);
                    measurementUnitType.setAbbreviation("g/Kg");
                    break;
                default:
                    System.out.println("Missing decode variable: " + variableName);
                    break;
            }

            observedVariableType.setObservedVariableMeasurand(observedVariableMeasurandType);
            observedVariableType.setMeasurementUnit(measurementUnitType);

            Calendar startDate = getStartDate();
            int secondsElapsed = getSecondsElapsed(startDate);
            Calendar stopDate = Calendar.getInstance();
            stopDate.setTime(startDate.getTime());
            stopDate.add(Calendar.SECOND, secondsElapsed);

            String temporalExtent = "StartTime: " + WigosBuilder.DATE_FORMAT.format(startDate.getTime())
                    + "; StopTime: " + WigosBuilder.DATE_FORMAT.format(stopDate.getTime())
                    + "; TemporalExtent:" + secondsElapsed + " s";

            observedVariableType.setTemporalExtent(temporalExtent);

            ArrayList<Float> listAltitudeMinMaxValue = getSpatialExtent();
            observedVariableType.setSpatialExtent("from " + listAltitudeMinMaxValue.get(0) + "m to " + listAltitudeMinMaxValue.get(1) + "m");
            observedVariableType.setRepresentativenessOfObservation(RepresentativenessType.MESOSCALE);

            observedVariableTypeList.add(observedVariableType);
        }

        return observedVariableTypeList;
    }

    private ArrayList<Float> getSpatialExtent() {
        ArrayList<Float> listAltitudeMinMaxValue = new ArrayList<>();

        List<Float> listdataAltitude = null;
        List<Float> listdataAltitudeFiltered = null;

        switch (this.wigosInputFormat) {
            case "NETCDF_GRUAN":
                listdataAltitude = getListData("alt");
                break;
            case "NETCDF_EARLINET":
                listdataAltitude = getListData("Altitude");
                break;
        }

        listdataAltitudeFiltered = listdataAltitude.stream().filter(x -> !x.isNaN()).collect(Collectors.toList());

        float minValue = Collections.min(listdataAltitudeFiltered);
        float maxValue = Collections.max(listdataAltitudeFiltered);

        listAltitudeMinMaxValue.add(minValue);
        listAltitudeMinMaxValue.add(maxValue);

        return listAltitudeMinMaxValue;
    }

    private Calendar getStartDate() {
        Calendar cal = null;
        Attribute attrStartDate;
        Attribute attrStartTime = null;
        Date date = null;
        try {
            switch (wigosInputFormat) {
                case "NETCDF_GRUAN":
                    attrStartDate = ncfile.findGlobalAttribute("g.Ascent.StartTime");

                    date = WigosBuilder.DATE_FORMAT.parse(attrStartDate.getStringValue());
                    break;
                case "NETCDF_EARLINET":
                    attrStartDate = ncfile.findGlobalAttribute("StartDate");
                    attrStartTime = ncfile.findGlobalAttribute("StartTime_UT");

                    date = DATE_FORMAT_EARLINET.parse(attrStartDate.getNumericValue().toString());

                    break;
            }

            cal = Calendar.getInstance();

            cal.setTime(date);

            if (attrStartTime != null) {
                cal.set(Calendar.HOUR_OF_DAY, Integer.parseInt(attrStartTime.getNumericValue().toString().substring(0, 2)));
                cal.set(Calendar.MINUTE, Integer.parseInt(attrStartTime.getNumericValue().toString().substring(2, 4)));
                cal.set(Calendar.SECOND, Integer.parseInt(attrStartTime.getNumericValue().toString().substring(4, 6)));
            }

        } catch (ParseException e) {
            e.printStackTrace();
        }

        return cal;
    }

    private int getSecondsElapsed(Calendar startDate) {
        int secondsElapsed = 0;

        switch (wigosInputFormat) {
            case "NETCDF_GRUAN":
                Variable varTime = ncfile.findVariable("time");

                Array data = null;
                try {
                    data = varTime.read();

                    float[] storage = (float[]) data.getStorage();

                    secondsElapsed = (int) storage[((storage.length) - 1)];
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case "NETCDF_EARLINET":
                Attribute attrStopTime = ncfile.findGlobalAttribute("StopTime_UT");

                Calendar calStopTime = Calendar.getInstance();

                calStopTime.setTime(startDate.getTime());
                calStopTime.set(Calendar.HOUR_OF_DAY, Integer.parseInt(attrStopTime.getNumericValue().toString().substring(0, 2)));
                calStopTime.set(Calendar.MINUTE, Integer.parseInt(attrStopTime.getNumericValue().toString().substring(2, 4)));
                calStopTime.set(Calendar.SECOND, Integer.parseInt(attrStopTime.getNumericValue().toString().substring(4, 6)));

                if (calStopTime.get(Calendar.HOUR_OF_DAY) < startDate.get(Calendar.HOUR_OF_DAY)) {
                    calStopTime.add(Calendar.DAY_OF_MONTH, 1);
                }

                long diff = calStopTime.getTimeInMillis() - startDate.getTimeInMillis();
                secondsElapsed = (int) TimeUnit.SECONDS.convert(diff, TimeUnit.MILLISECONDS);

                break;
        }

        return secondsElapsed;
    }

    private PurposeOfObservationType getPurposeOfObservation() {
        PurposeOfObservationType purposeOfObservationType = new PurposeOfObservationType();

        purposeOfObservationType.setApplicationArea(ApplicationAreaType.CLIMATE_MONITORING_AS_UNDERTAKEN_THROUGH_THE_GLOBAL_CLIMATE_OBSERVING_SYSTEM_GCOS);

        ProgrammeNetworkAffiliationType programmeNetworkAffiliationType = new ProgrammeNetworkAffiliationType();

        switch (wigosInputFormat) {
            case "NETCDF_GRUAN":
                programmeNetworkAffiliationType.setName("GRUAN");
                programmeNetworkAffiliationType.setDefinition("GCOS Reference Upper Air Network");
                programmeNetworkAffiliationType.setSponsorContributing("GCOS");
                break;
            case "NETCDF_EARLINET":
                programmeNetworkAffiliationType.setName("EARLINET");
                programmeNetworkAffiliationType.setDefinition("European Aerosol Research Lidar Network");
                programmeNetworkAffiliationType.setSponsorContributing("GALION; WMO/GAW");
                break;
        }

        purposeOfObservationType.setProgrammeNetworkAffiliation(programmeNetworkAffiliationType);

        return purposeOfObservationType;
    }

    private StationPlatformType getStationPlatform() {
        StationPlatformType stationPlatformType = new StationPlatformType();

        TerritoryOfOriginOfDataType territoryOfOriginOfDataType = new TerritoryOfOriginOfDataType();
        territoryOfOriginOfDataType.setISO3CountryCode(countryISO3.getCode3());

        GeospatialLocationType geospatialLocationType = new GeospatialLocationType();
        geospatialLocationType.setCoordinatesSourceDevice(CoordinatesSourceDeviceType.GPS);
        geospatialLocationType.setCoordinatesReference(CoordinatesReferenceType.WGS_84);

        stationPlatformType.setRegionOfOriginOfData(countryISO3Utility.getRegionEnum(countryISO3.getRegion()));
        territoryOfOriginOfDataType.setName(countryISO3.getName());

        stationPlatformType.setTerritoryOfOriginOfData(territoryOfOriginOfDataType);

        Attribute attrSiteName = null;

        switch (wigosInputFormat) {
            case "NETCDF_GRUAN":
                attrSiteName = ncfile.findGlobalAttribute("g.General.SiteName");

                Attribute attrSiteCode = ncfile.findGlobalAttribute("g.General.SiteCode");
                stationPlatformType.setStationPlatformUniqueIdentifier(attrSiteCode.getStringValue());
                stationPlatformType.setStationPlatformModel("Radiosonde");
                break;
            case "NETCDF_EARLINET":
                attrSiteName = ncfile.findGlobalAttribute("Location");
                stationPlatformType.setStationPlatformModel("Lidar");
                break;
        }

        stationPlatformType.setStationPlatformName(attrSiteName.getStringValue());
        stationPlatformType.setStationPlatformType(StationPlatformTypeType.LAND_STATION);


        stationPlatformType.setGeospatialLocation(geospatialLocationType);
        //stationPlatformType.setDataCommunicationMethod();
        stationPlatformType.setStationStatus(StationStatusType.OPERATIONAL_REPORTING);

        return stationPlatformType;
    }

    private CountryISO3 getISO3CountryInfo() {
        countryISO3 = countryISO3Utility.getISO3CountryInfoByService(latitude, longitude);

        return countryISO3;
    }

    private EnvironmentType getEnvironment() {
        EnvironmentType environmentType = new EnvironmentType();

        //environmentType.setSurfaceCover();
        //environmentType.setSurfaceCoverClassificatioScheme();
        //environmentType.setTopographyOrBathymetry();
        //environmentType.setEventsAtStationPlatform();

        Attribute attrSiteName = null;
        Attribute attrAltitude = null;
        String altitude = "";

        switch (this.wigosInputFormat) {
            case "NETCDF_GRUAN":
                attrSiteName = ncfile.findGlobalAttribute("g.General.SiteName");
                attrAltitude = ncfile.findGlobalAttribute("g.MeasuringSystem.Altitude");

                altitude = attrAltitude.getStringValue();
                break;
            case "NETCDF_EARLINET":
                attrSiteName = ncfile.findGlobalAttribute("Location");
                attrAltitude = ncfile.findGlobalAttribute("Altitude_meter_asl");

                altitude = attrAltitude.getNumericValue().toString();
                break;
        }

        String siteDescription = "Name:" + attrSiteName.getStringValue()
                + " Country:" + countryISO3.getCode3()
                + " lat.:" + latitude
                + " long.:" + longitude
                + " height:" + altitude;

        environmentType.setSiteInformation(siteDescription);

        return environmentType;
    }

    private InstrumentsAndMethodsOfObservationType getInstrumentsAndMethodsOfObservation() {
        InstrumentsAndMethodsOfObservationType instrumentsAndMethodsOfObservationType = new InstrumentsAndMethodsOfObservationType();

        Attribute attrManufacturer = null;
        Attribute attrType = null;
        Attribute attrSerialNumber = null;
        Attribute attrSoftwareVersion = null;
        String instrumentDescription = "";
        String instrumentSpecification = "";

        switch (this.wigosInputFormat) {
            case "NETCDF_GRUAN":
                attrManufacturer = ncfile.findGlobalAttribute("g.Instrument.Manufacturer");
                attrType = ncfile.findGlobalAttribute("g.Instrument.Type");
                attrSerialNumber = ncfile.findGlobalAttribute("g.Instrument.SerialNumber");
                attrSoftwareVersion = ncfile.findGlobalAttribute("g.Instrument.SoftwareVersion");

                instrumentsAndMethodsOfObservationType.setMeasurementObservingMethod("Radiosonde");

                instrumentSpecification = "specification for ";
                if (attrManufacturer != null) {
                    instrumentDescription += "Name:" + attrManufacturer.getStringValue() + " ";
                    instrumentSpecification += attrManufacturer.getStringValue() + " ";
                }

                if (attrType != null) {
                    instrumentDescription += "Model:" + attrType.getStringValue();
                    instrumentSpecification += attrType.getStringValue() + " ";
                }

                if (attrSerialNumber != null)
                    instrumentDescription += "Number:" + attrSerialNumber.getStringValue() + " ";
                if (attrSoftwareVersion != null)
                    instrumentDescription += "Firmware:" + attrSoftwareVersion.getStringValue() + " ";

                instrumentSpecification = instrumentSpecification.trim();

                break;
            case "NETCDF_EARLINET":
                attrManufacturer = ncfile.findGlobalAttribute("System");

                if (attrManufacturer != null) {
                    instrumentDescription += "Name:" + attrManufacturer.getStringValue() + " ";
                }

                Attribute attrEmissionWavelength_nm = ncfile.findGlobalAttribute("EmissionWavelength_nm");
                Attribute attrDetectionWavelength_nm = ncfile.findGlobalAttribute("DetectionWavelength_nm");
                Attribute attrDetectionMode = ncfile.findGlobalAttribute("DetectionMode");

                instrumentSpecification = "EmissionWavelength_nm: " + attrEmissionWavelength_nm.getNumericValue() + " "
                        + "DetectionWavelength_nm: " + attrDetectionWavelength_nm.getNumericValue() + " "
                        + "DetectionMode: " + attrDetectionMode.getStringValue();

                Attribute attrEvaluationMethod = ncfile.findGlobalAttribute("EvaluationMethod");

                instrumentsAndMethodsOfObservationType.setMeasurementObservingMethod("Lidar (" + attrEvaluationMethod.getStringValue() + ")");
                break;
        }

        instrumentDescription = instrumentDescription.trim();

        instrumentsAndMethodsOfObservationType.setSourceOfObservation(SourceOfObservationType.AUTOMATIC_OBSERVATION);
        instrumentsAndMethodsOfObservationType.setInstrumentSpecifications(instrumentSpecification);
        instrumentsAndMethodsOfObservationType.setInstrumentOperatingStatus(InstrumentOperatingStatusType.OPERATIONAL);

        //instrumentsAndMethodsOfObservationType.setVerticalDistanceOfSensor();
        //instrumentsAndMethodsOfObservationType.setConfigurationOfInstrumentation();
        //instrumentsAndMethodsOfObservationType.setInstrumentControlSchedule();

        instrumentsAndMethodsOfObservationType.setInstrumentControlResult(InstrumentControlResultType.NO_CHANGES_IN_CALIBRATION);
        instrumentsAndMethodsOfObservationType.setInstrumentModelAndSerialNumber(instrumentDescription);

        //instrumentsAndMethodsOfObservationType.setInstrumentMaintenanceSchedule();
        //instrumentsAndMethodsOfObservationType.setMaintenanceParty();
        //instrumentsAndMethodsOfObservationType.setMaintenanceActivity();
        instrumentsAndMethodsOfObservationType.setStatusOfObservation(StatusOfObservationType.PRIMARY);
        instrumentsAndMethodsOfObservationType.setExposureOfInstruments(ExposureOfInstrumentsType.CLASS_1);

        return instrumentsAndMethodsOfObservationType;
    }

    private SamplingType getSampling() {
        SamplingType samplingType = new SamplingType();

        switch (this.wigosInputFormat) {
            case "NETCDF_GRUAN":
                samplingType.setSamplingProcedures("Measurements are performed using a ballon-borne radiosonde; products are vertical profiles of temperature, humidity, pressure and wind from the ground level to about 30-35 km above.");
                samplingType.setSampleTreatment("heating and cooling");
                samplingType.setScheduleOfObservation("Radiosoudings are performed at minimum one per week; station also operating within GRUAN or RAOB perfumed regular soundings once or twice a day at 00 and 12 UT.");
                samplingType.setTemporalSamplingInterval("radiosoundings measure at different height level each 1-2 seconds and cover the entire range from the ground to the LS in about one hour.");
                samplingType.setSpatialSamplingResolution("10 m");
                break;
            case "NETCDF_EARLINET":
                Attribute attrShotsAveraged = ncfile.findGlobalAttribute("ShotsAveraged");
                Attribute attrResolutionRaw_meter = ncfile.findGlobalAttribute("ResolutionRaw_meter");
                Attribute attrResolutionEvaluated = ncfile.findGlobalAttribute("ResolutionEvaluated");

                samplingType.setSamplingProcedures("Measurements are performed using Multiwavelenght/Raman/elastic lidar techniques; products are vertical profiles aerosol backscattering and extinction coefficients at the available wavelengths.");
                samplingType.setSampleTreatment("Laser axis vs telescope axis alignment");
                samplingType.setScheduleOfObservation("Aerosol measurements are perfumed on a systematic bias there times a week, on Monday and Thursday 30 minutes after the sunset, on Thursday at 14 local solar time.");

                Calendar startDate = getStartDate();
                int secondsElapsed = getSecondsElapsed(startDate);
                Calendar stopDate = Calendar.getInstance();
                stopDate.setTime(startDate.getTime());
                stopDate.add(Calendar.SECOND, secondsElapsed);

                samplingType.setTemporalSamplingInterval("ShotsAveraged: " + attrShotsAveraged.getNumericValue() + " "
                        + "StartDate: " + WigosBuilder.DATE_FORMAT.format(startDate.getTime()) + " "
                        + "StopTime: " + WigosBuilder.DATE_FORMAT.format(stopDate.getTime()));
                samplingType.setSpatialSamplingResolution("ResolutionRaw_meter: " + attrResolutionRaw_meter.getNumericValue() + " "
                        + "ResolutionEvaluated: " + attrResolutionEvaluated.getStringValue());
                break;
        }

        samplingType.setSamplingStrategy(SamplingStrategyType.DISCRETE);
        //samplingType.setSamplingTimePeriod();
        //samplingType.setDiurnalBaseTime();

        return samplingType;
    }

    private DataProcessingAndReportingType getDataProcessingAndReporting() {
        DataProcessingAndReportingType dataProcessingAndReportingType = new DataProcessingAndReportingType();
        String processingAnalysisCentre = "";

        switch (this.wigosInputFormat) {
            case "NETCDF_GRUAN":
                dataProcessingAndReportingType.setDataProcessingMethodsAndAlgorithms("gruan_DP_calcRsDataProduct.pro ref:www.atmos-meas-tech.net/7/4463/2014/amt-7-4463-2014.pdf");

                Attribute attrAnalysisCentre = ncfile.findGlobalAttribute("g.Product.Producer");
                processingAnalysisCentre = attrAnalysisCentre.getStringValue();

                dataProcessingAndReportingType.setSoftwareProcessorAndVersion("GRUAN IDL Library, 2012-08");
                dataProcessingAndReportingType.setLevelOfData(getLevelOfData());

                Attribute attrFileTypeVersion = ncfile.findGlobalAttribute("g.General.FileTypeVersion");
                dataProcessingAndReportingType.setVersionOfDataFormat(attrFileTypeVersion.getStringValue());
                dataProcessingAndReportingType.setNumericalResolution("Temperature = 0.1°C; pressure = 0.1 hPa; Humidity = 1%.");
                break;
            case "NETCDF_EARLINET":
                processingAnalysisCentre = "Earlinet";
                dataProcessingAndReportingType.setNumericalResolution("Variable depending on the SNR of each lidar system at the different wavelengths");
                break;
        }

        dataProcessingAndReportingType.setProcessingAnalysisCentre(processingAnalysisCentre);
        dataProcessingAndReportingType.setDataFormat(ncfile.getFileTypeDescription());
        dataProcessingAndReportingType.setReferenceTime(ReferenceTimeType.TIME_SERVER);
//      dataProcessingAndReportingType.setTemporalReportingPeriod(getTemporalReportingPeriod());
//      dataProcessingAndReportingType.setSpatialReportingInterval(getSpatialReportingInterval());
//      dataProcessingAndReportingType.setReferenceDatum(getReferenceDatum());
//      dataProcessingAndReportingType.setLatencyOfReporting(getLatencyOfReporting());

        return dataProcessingAndReportingType;
    }

    private LevelOfDataType getLevelOfData() {
        LevelOfDataType levelOfDataType = null;

        Attribute attrLevel = ncfile.findGlobalAttribute("g.Product.Level");
        int level = (int) Double.parseDouble(attrLevel.getStringValue().trim());

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

        dataQualityType.setUncertaintyOfMeasurement(getUncertaintyOfMeasurement());
        //dataQualityType.setProcedureUsedToEstimateUncertainty(getProcedureUsedToEstimateUncertainty());

        QualityFlagType qualityFlagType = new QualityFlagType();
        switch (this.wigosInputFormat) {
            case "NETCDF_GRUAN":
                Attribute attrQualityFlag = ncfile.findGlobalAttribute("g.Product.Status");
                if (attrQualityFlag.getStringValue().equals("Reject")) {
                    qualityFlagType.setQualityFlagOGC(QualityFlagOGCType.POOR);
                } else {
                    qualityFlagType.setQualityFlagOGC(QualityFlagOGCType.GOOD);
                }

                dataQualityType.setTraceability(TraceabilityType.TRACEABLE_TO_INTERNATIONAL_STANDARD);
                break;
            case "NETCDF_EARLINET":
                dataQualityType.setTraceability(TraceabilityType.TRACEABLE_TO_OTHER_STANDARD);
                qualityFlagType.setQualityFlagOGC(QualityFlagOGCType.MISSING);
                break;
        }

        dataQualityType.setQualityFlag(qualityFlagType);
        dataQualityType.setQualityFlaggingSystem(QualityFlaggingSystemType.OTHER_QUALITY_FLAGGING_SYSTEM);

        return dataQualityType;
    }

    private String getUncertaintyOfMeasurement() {
        String uncertaintyVariables = "";
        List<Float> listData;
        List<Float> listDataFiltered;
        List<Float> listDataAlt = null;
        float min;
        float max;
        int minIndex;
        int maxIndex;
        String variableName;
        String row;

        switch (this.wigosInputFormat) {
            case "NETCDF_GRUAN":
                listDataAlt = getListData("alt");
                break;
            case "NETCDF_EARLINET":
                listDataAlt = getListData("Altitude");
                break;
        }

        List<Variable> listVariable = ncfile.getVariables();
        for (Variable variable : listVariable) {
            if (isUncertainty(variable)) {
                listData = getListData(variable);
                listDataFiltered = listData.stream().filter(x -> (!x.isNaN() && x != 9.96921E36f)).collect(Collectors.toList());

                if (listDataFiltered.size() > 0) {
                    min = Collections.min(listDataFiltered);
                    max = Collections.max(listDataFiltered);
                    minIndex = listData.indexOf(min);
                    maxIndex = listData.indexOf(max);

                    variableName = getVariableName(variable);

                    row = variableName
                            + " - min: " + min + ", alt: " + listDataAlt.get(minIndex)
                            + " - max: " + max + ", alt: " + listDataAlt.get(maxIndex);

                    uncertaintyVariables += row + System.getProperty("line.separator");
                }
            }
        }

        return uncertaintyVariables;
    }

    private boolean isUncertainty(Variable variable) {
        boolean test = false;

        switch (this.wigosInputFormat) {
            case "NETCDF_GRUAN":
                Attribute attribute = variable.getAttributes().stream().filter(x -> x.getShortName().equals("g_column_type")).collect(Collectors.toList()).get(0);

                if (attribute.getStringValue().contains("uncertainty")) {
                    test = true;
                }
                break;
            case "NETCDF_EARLINET":
                String variableName = getVariableName(variable);

                if (variableName.toLowerCase().contains("error")) {
                    test = true;
                }
                break;
        }

        return test;
    }

    private List<Float> getListData(String variable) {
        Variable var = ncfile.findVariable(variable);

        return getListData(var);
    }

    private List<Float> getListData(Variable var) {
        List<Float> listData = null;
        Array data;
        float[] arrData;

        try {
            data = var.read();
            arrData = (float[]) data.get1DJavaArray(float.class);
            listData = Floats.asList(arrData);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return listData;
    }

    private OwnershipAndDataPolicyType getOwnershipAndDataPolicy() {
        OwnershipAndDataPolicyType ownershipAndDataPolicyType = new OwnershipAndDataPolicyType();

        switch (this.wigosInputFormat) {
            case "NETCDF_GRUAN":
                Attribute attrInstitution = ncfile.findGlobalAttribute("institution");

                ownershipAndDataPolicyType.setSupervisingOrganization(attrInstitution.getStringValue());
                ownershipAndDataPolicyType.setDataPolicyUseConstraints(DataPolicyUseConstraintsType.WMO_ESSENTIAL);
                break;
            case "NETCDF_EARLINET":
                ownershipAndDataPolicyType.setDataPolicyUseConstraints(DataPolicyUseConstraintsType.WMO_ADDITIONAL);
                break;
        }

        return ownershipAndDataPolicyType;
    }

    private ContactType getContact() {
        ContactType contactType = new ContactType();

        switch (this.wigosInputFormat) {
            case "NETCDF_GRUAN":
                Attribute attrInstitution = ncfile.findGlobalAttribute("institution");

                contactType.setContact(attrInstitution.getStringValue());
                break;
            case "NETCDF_EARLINET":
                break;
        }

        return contactType;
    }
}
