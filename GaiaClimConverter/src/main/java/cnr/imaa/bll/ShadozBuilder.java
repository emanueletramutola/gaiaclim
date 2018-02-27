package cnr.imaa.bll;

import cnr.imaa.model.ModelForWigos;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.*;

public class ShadozBuilder {
    final static int DATACENTRE = 1;
    final static int VERSION = 2;
    final static int CREATIONDATE = 3;
    final static int NAMEOFSTATION = 4;
    final static int PINETWORK = 5;
    final static int PISTATION = 6;
    final static int LATITUDE = 7;
    final static int LONGITUDE = 8;
    final static int ELEVATION = 9;
    final static int LAUNCHDATE = 10;
    final static int LAUNCHTIME = 11;
    final static int HIGHESTLEVELREACHED = 12;
    final static int INTEGRATEDO3UNTILEOF = 13;
    final static int SONDE_MLSCLIMATOLOGY = 14;
    final static int SONDEINSTRUMENT = 15;
    final static int RADIOSONDE = 16;
    final static int KISOLUTION = 17;
    final static int APPLIEDPUMPCORRECTIONS = 18;
    final static int PUMPFLOWRATE = 19;
    final static int BACKGROUNDCURRENT = 20;
    final static int MISSINGORBADVALUES = 21;
    final static int LISTVARIABLES = 22;

    public static ModelForWigos getWigosModel() {
        ModelForWigos wigosObj = null;
        String path = "Resources/Wigos-Data/costarica_20160304_V05.1_R.dat";
        int indexRows = -1;
        String dataToSet = null;

        try {
            List<String> listRows = Files.readAllLines(Paths.get("Resources/Wigos-Data/costarica_20160304_V05.1_R.dat"), StandardCharsets.UTF_8);

            wigosObj = new ModelForWigos();

            Iterator<String> row = listRows.iterator();
            while (row.hasNext()) {
                String currentRow = row.next();

                indexRows += 1;

                if (currentRow.contains(": "))
                    dataToSet = currentRow.split(": ")[1];
                else
                    dataToSet = currentRow;

                switch (indexRows) {
                    case DATACENTRE:
                        wigosObj.setDataCentre(dataToSet);
                        break;
                    case VERSION:
                        wigosObj.setVersion(dataToSet);
                        break;
                    case CREATIONDATE:
                        SimpleDateFormat formatter = new SimpleDateFormat("dd MMMM, yyyy", Locale.ENGLISH);
                        Date date = formatter.parse(dataToSet);
                        wigosObj.setCreationDate(date);

                        break;
                    case NAMEOFSTATION:
                        wigosObj.setNameOfStation(dataToSet);
                        break;
                    case PINETWORK:
                        wigosObj.setPiNetwork(dataToSet);
                        break;
                    case PISTATION:
                        wigosObj.setPiStation(dataToSet);
                        break;
                    case LATITUDE:
                        wigosObj.setLatitude(Double.parseDouble(dataToSet));
                        break;
                    case LONGITUDE:
                        wigosObj.setLongitude(Double.parseDouble(dataToSet));
                        break;
                    case ELEVATION:
                        wigosObj.setElevation(Double.parseDouble(dataToSet));
                        break;
                    case LAUNCHDATE:
                        SimpleDateFormat formatter_LaunchDate = new SimpleDateFormat("yyyyMMdd");
                        Date date_LaunchDate = formatter_LaunchDate.parse(dataToSet);

                        wigosObj.setLaunchDate(date_LaunchDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
                        break;
                    case LAUNCHTIME:
                        int hour = Integer.parseInt(dataToSet.split(":")[0]);
                        int minute = Integer.parseInt(dataToSet.split(":")[1]);
                        int seconds = Integer.parseInt(dataToSet.split(":")[2]);

                        wigosObj.setLaunchTime(LocalTime.of(hour, minute, seconds));
                        break;
                    case HIGHESTLEVELREACHED:
                        wigosObj.setHighestLevelReached(Double.parseDouble(dataToSet));
                        break;
                    case INTEGRATEDO3UNTILEOF:
                        wigosObj.setIntegratedO3UntilEOF(Double.parseDouble(dataToSet));
                        break;
                    case SONDE_MLSCLIMATOLOGY:
                        wigosObj.setSonde_MLSClimatology(Double.parseDouble(dataToSet));
                        break;
                    case SONDEINSTRUMENT:
                        wigosObj.setSondeInstrument(dataToSet);
                        break;
                    case RADIOSONDE:
                        wigosObj.setRadiosonde(dataToSet);
                        break;
                    case KISOLUTION:
                        wigosObj.setKiSolution(dataToSet);
                        break;
                    case APPLIEDPUMPCORRECTIONS:
                        wigosObj.setAppliedPumpCorrections(dataToSet);
                        break;
                    case PUMPFLOWRATE:
                        wigosObj.setPumpFlowRate(Double.parseDouble(dataToSet));
                        break;
                    case BACKGROUNDCURRENT:
                        wigosObj.setBackgroundCurrent(Double.parseDouble(dataToSet));
                        break;
                    case MISSINGORBADVALUES:
                        wigosObj.setMissingOrBadValues(Double.parseDouble(dataToSet));
                        break;
                    case LISTVARIABLES:
                        //wigosObj.setListVariables(dataToSet);
                        break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("indexRows: " + indexRows);
        }

        return wigosObj;
    }
}
