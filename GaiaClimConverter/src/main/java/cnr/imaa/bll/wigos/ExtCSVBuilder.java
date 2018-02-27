package cnr.imaa.bll.wigos;

import cnr.imaa.model.woudc.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class ExtCSVBuilder {
    private final static String CONTENT = "#CONTENT";
    private final static String DATA_GENERATION = "#DATA_GENERATION";
    private final static String PLATFORM = "#PLATFORM";
    private final static String INSTRUMENT = "#INSTRUMENT";
    private final static String LOCATION = "#LOCATION";
    private final static String TIMESTAMP = "#TIMESTAMP";
    private final static String FLIGHT_SUMMARY = "#FLIGHT_SUMMARY";
    private final static String AUXILIARY_DATA = "#AUXILIARY_DATA";
    private final static String PROFILE = "#PROFILE";
    private final static List<String> LIST_SECTIONS = Arrays.asList(CONTENT, DATA_GENERATION, PLATFORM, INSTRUMENT, LOCATION, TIMESTAMP, FLIGHT_SUMMARY, AUXILIARY_DATA, PROFILE);

    public static ExtCSV getExtCSV(String pathFile) {
        ExtCSV extCSV = new ExtCSV();
        int numElementsCurrentRow = 0;

        try {
            List<String> listRows = Files.readAllLines(Paths.get(pathFile), StandardCharsets.UTF_8);

            Iterator<String> row = listRows.iterator();
            while (row.hasNext()) {
                String sectionRow = row.next();
                String currentRow = null;

                if (LIST_SECTIONS.contains(sectionRow.toUpperCase()) && row.hasNext())
                    currentRow = row.next();

                switch (sectionRow.toUpperCase()) {
                    case CONTENT:
                        currentRow = row.next();

                        numElementsCurrentRow = currentRow.split(",").length;

                        ExtCSV_CONTENT content = new ExtCSV_CONTENT();

                        if (0 < numElementsCurrentRow) content.setContent_class(currentRow.split(",")[0].trim());
                        if (1 < numElementsCurrentRow) content.setCategory(currentRow.split(",")[1].trim());
                        if (2 < numElementsCurrentRow) content.setLevel(currentRow.split(",")[2].trim());
                        if (3 < numElementsCurrentRow) content.setForm(currentRow.split(",")[3].trim());

                        extCSV.setContent(content);

                        break;
                    case DATA_GENERATION:
                        currentRow = row.next();

                        numElementsCurrentRow = currentRow.split(",").length;

                        ExtCSV_DATA_GENERATION dataGeneration = new ExtCSV_DATA_GENERATION();

                        if (0 < numElementsCurrentRow) dataGeneration.setDate(currentRow.split(",")[0].trim());
                        if (1 < numElementsCurrentRow) dataGeneration.setAgency(currentRow.split(",")[1].trim());
                        if (2 < numElementsCurrentRow) dataGeneration.setVersion(currentRow.split(",")[2].trim());
                        if (3 < numElementsCurrentRow)
                            dataGeneration.setScientificAuthority(currentRow.split(",")[3].trim());

                        extCSV.setData_generation(dataGeneration);

                        break;
                    case PLATFORM:
                        currentRow = row.next();

                        numElementsCurrentRow = currentRow.split(",").length;

                        ExtCSV_PLATFORM platform = new ExtCSV_PLATFORM();

                        if (0 < numElementsCurrentRow) platform.setType(currentRow.split(",")[0].trim());
                        if (1 < numElementsCurrentRow) platform.setId(currentRow.split(",")[1].trim());
                        if (2 < numElementsCurrentRow) platform.setName(currentRow.split(",")[2].trim());
                        if (3 < numElementsCurrentRow) platform.setCountry(currentRow.split(",")[3].trim());
                        if (4 < numElementsCurrentRow) platform.setGaw_id(currentRow.split(",")[4].trim());

                        extCSV.setPlatform(platform);

                        break;
                    case INSTRUMENT:
                        currentRow = row.next();

                        numElementsCurrentRow = currentRow.split(",").length;

                        ExtCSV_INSTRUMENT instrument = new ExtCSV_INSTRUMENT();

                        if (0 < numElementsCurrentRow) instrument.setName(currentRow.split(",")[0].trim());
                        if (1 < numElementsCurrentRow) instrument.setModel(currentRow.split(",")[1].trim());
                        if (2 < numElementsCurrentRow) instrument.setNumber(currentRow.split(",")[2].trim());

                        extCSV.setInstrument(instrument);
                        break;
                    case LOCATION:
                        currentRow = row.next();

                        numElementsCurrentRow = currentRow.split(",").length;

                        ExtCSV_LOCATION location = new ExtCSV_LOCATION();

                        if (0 < numElementsCurrentRow) location.setLatitude(currentRow.split(",")[0].trim());
                        if (1 < numElementsCurrentRow) location.setLongitude(currentRow.split(",")[1].trim());
                        if (2 < numElementsCurrentRow) location.setHeight(currentRow.split(",")[2].trim());

                        extCSV.setLocation(location);
                        break;
                    case TIMESTAMP:
                        currentRow = row.next();

                        numElementsCurrentRow = currentRow.split(",").length;

                        ExtCSV_TIMESTAMP timestamp = new ExtCSV_TIMESTAMP();

                        if (0 < numElementsCurrentRow) timestamp.setUtcoffset(currentRow.split(",")[0].trim());
                        if (1 < numElementsCurrentRow) timestamp.setDate(currentRow.split(",")[1].trim());
                        if (2 < numElementsCurrentRow) timestamp.setTime(currentRow.split(",")[2].trim());

                        extCSV.setTimestamp(timestamp);
                        break;
                    case FLIGHT_SUMMARY:
                        currentRow = row.next();

                        numElementsCurrentRow = currentRow.split(",").length;

                        ExtCSV_FLIGHT_SUMMARY flightSummary = new ExtCSV_FLIGHT_SUMMARY();

                        if (0 < numElementsCurrentRow) flightSummary.setIntegratedo3(currentRow.split(",")[0].trim());
                        if (1 < numElementsCurrentRow) flightSummary.setCorrectioncode(currentRow.split(",")[1].trim());
                        if (2 < numElementsCurrentRow) flightSummary.setSondetotalo3(currentRow.split(",")[2].trim());
                        if (3 < numElementsCurrentRow)
                            flightSummary.setCorrectionfactor(currentRow.split(",")[3].trim());
                        if (4 < numElementsCurrentRow) flightSummary.setTotalo3(currentRow.split(",")[4].trim());
                        if (5 < numElementsCurrentRow) flightSummary.setWlcode(currentRow.split(",")[5].trim());
                        if (6 < numElementsCurrentRow) flightSummary.setObstype(currentRow.split(",")[6].trim());
                        if (7 < numElementsCurrentRow) flightSummary.setInstrument(currentRow.split(",")[7].trim());
                        if (8 < numElementsCurrentRow) flightSummary.setNumber(currentRow.split(",")[8].trim());

                        extCSV.setFlight_summary(flightSummary);
                        break;
                    case AUXILIARY_DATA:
                        currentRow = row.next();

                        numElementsCurrentRow = currentRow.split(",").length;

                        ExtCSV_AUXILIARY_DATA auxiliaryData = new ExtCSV_AUXILIARY_DATA();

                        if (0 < numElementsCurrentRow) auxiliaryData.setMeteosonde(currentRow.split(",")[0].trim());
                        if (1 < numElementsCurrentRow) auxiliaryData.setIb1(currentRow.split(",")[1].trim());
                        if (2 < numElementsCurrentRow) auxiliaryData.setIb2(currentRow.split(",")[2].trim());
                        if (3 < numElementsCurrentRow) auxiliaryData.setPumprate(currentRow.split(",")[3].trim());
                        if (4 < numElementsCurrentRow) auxiliaryData.setBackgroundcorr(currentRow.split(",")[4].trim());
                        if (5 < numElementsCurrentRow)
                            auxiliaryData.setSampletemperaturetype(currentRow.split(",")[5].trim());
                        if (6 < numElementsCurrentRow)
                            auxiliaryData.setMinutesgroundo3(currentRow.split(",")[6].trim());

                        extCSV.setAuxiliary_data(auxiliaryData);
                        break;
                    case PROFILE:
                        /*
                        currentRow = currentRow.replace(",LevelCode", "");
                        currentRow = currentRow.replace(",SampleTemperature", "");
                        currentRow = currentRow.replace(",Duration", "");
                        currentRow = currentRow.replace(",GPHeight", "");
                        */

                        ArrayList<String> listProfile = new ArrayList<>(Arrays.asList(currentRow.split(",")));

                        extCSV.setListProfiles(listProfile);

                        //go to the last row
                        String lastRow = "";
                        String tmpRow = "";
                        do {
                            tmpRow = row.next();

                            if (!tmpRow.trim().equals("")) {
                                lastRow = tmpRow;
                            }
                        } while (row.hasNext() && !tmpRow.trim().equals(""));

                        extCSV.setTemporalExtend(Integer.parseInt(lastRow.split(",")[6]));
                        extCSV.setSpatialExtent(Integer.parseInt(lastRow.split(",")[7]));

                        break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ArrayIndexOutOfBoundsException e) {
            e.printStackTrace();
        }

        return extCSV;
    }
}
