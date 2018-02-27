package cnr.imaa.dal;

import cnr.imaa.bll.ConfigurationManager;
import cnr.imaa.model.Station;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Iterator;

public class ExcelDAL {
    public static ArrayList<Station> readFromExcel(String pathFile) {
        boolean enableLogExcelData = Boolean.parseBoolean(ConfigurationManager.getPropertyValue("enableLogExcelData"));

        int indexRows = -1;
        int indexCells = -1;

        final int LOCATION_OF_THE_STATION = 0;
        final int NAME_OF_THE_STATION = 1;
        final int MEASURED_ECV = 2;
        final int PRODUCT = 3;
        final int MEASUREMENT_TYPE = 4;
        final int MEASUREMENT_TECHNIQUE = 5;
        final int NETWORK = 6;
        final int SUB_NETWORK = 7;
        final int LATITUDE = 8;
        final int LONGITUDE = 9;
        final int ALTITUDE = 10;
        final int START_OF_OPERATION = 11;
        final int PI = 12;
        final int MAIL_PI = 13;
        final int WEBSITE = 14;

        ArrayList<Station> listStations = new ArrayList<Station>();
        Station station;

        try {
            FileInputStream file = new FileInputStream(new File(pathFile));

            XSSFWorkbook workbook = new XSSFWorkbook(file);

            XSSFSheet sheet = workbook.getSheetAt(0);

            Iterator<Row> rowIterator = sheet.iterator();
            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();

                indexRows += 1;

                if (indexRows > 0) {
                    station = new Station();

                    Iterator<Cell> cellIterator = row.cellIterator();
                    while (cellIterator.hasNext()) {
                        indexCells += 1;

                        Cell cell = cellIterator.next();

                        switch (indexCells) {
                            case LOCATION_OF_THE_STATION:
                                if (enableLogExcelData)
                                    System.out.println("LOCATION_OF_THE_STATION: " + cell.getStringCellValue().trim());
                                station.setLocationOfTheStation(cell.getStringCellValue().trim());
                                break;
                            case NAME_OF_THE_STATION:
                                if (enableLogExcelData)
                                    System.out.println("NAME_OF_THE_STATION: " + cell.getStringCellValue().trim());
                                station.setNameOfTheStation(cell.getStringCellValue().trim());
                                break;
                            case MEASURED_ECV:
                                if (enableLogExcelData)
                                    System.out.println("MEASURED_ECV: " + cell.getStringCellValue().trim());
                                station.setMeasuredEcv(cell.getStringCellValue().toUpperCase().trim());
                                break;
                            case PRODUCT:
                                if (enableLogExcelData)
                                    System.out.println("PRODUCT: " + cell.getStringCellValue().trim());
                                station.setProduct(cell.getStringCellValue().trim());
                                break;
                            case MEASUREMENT_TYPE:
                                if (enableLogExcelData)
                                    System.out.println("MEASUREMENT_TYPE: " + cell.getStringCellValue().trim());
                                station.setMeasurementType(cell.getStringCellValue().toLowerCase().trim());
                                break;
                            case MEASUREMENT_TECHNIQUE:
                                if (enableLogExcelData)
                                    System.out.println("MEASUREMENT_TECHNIQUE: " + cell.getStringCellValue().trim());
                                station.setMeasurementTechnique(cell.getStringCellValue().trim());
                                break;
                            case NETWORK:
                                if (enableLogExcelData)
                                    System.out.println("NETWORK: " + cell.getStringCellValue().trim());
                                station.setNetwork(cell.getStringCellValue().trim());
                                break;
                            case SUB_NETWORK:
                                if (enableLogExcelData)
                                    System.out.println("SUB_NETWORK: " + cell.getStringCellValue().trim());
                                station.setSubNetwork(cell.getStringCellValue().trim());
                                break;
                            case LATITUDE:
                                if (enableLogExcelData) System.out.println("LATITUDE: " + cell.getNumericCellValue());
                                station.setLatitude(cell.getNumericCellValue());
                                break;
                            case LONGITUDE:
                                if (enableLogExcelData) System.out.println("LONGITUDE: " + cell.getNumericCellValue());
                                station.setLongitude(cell.getNumericCellValue());
                                break;
                            case ALTITUDE:
                                if (enableLogExcelData) System.out.println("ALTITUDE: " + cell.getNumericCellValue());
                                station.setAltitude(cell.getNumericCellValue());
                                break;
                            case START_OF_OPERATION:
                                GregorianCalendar g = new GregorianCalendar();

                                if (DateUtil.isCellDateFormatted(cell)) {
                                    g.setTime(cell.getDateCellValue());

                                    if (g.get(Calendar.YEAR) > 1900) {
                                        if (enableLogExcelData) {
                                            System.out.println("START_OF_OPERATION: " + cell.getDateCellValue());
                                        }

                                        station.setStartOfOperation(cell.getDateCellValue());
                                    }
                                }
                                break;
                            case PI:
                                if (enableLogExcelData) System.out.println("PI: " + cell.getStringCellValue().trim());
                                station.setPi(cell.getStringCellValue().trim());
                                break;
                            case MAIL_PI:
                                if (enableLogExcelData) System.out.println("MAIL_PI: " + cell.getStringCellValue().trim());
                                station.setMailPi(cell.getStringCellValue().trim());
                                break;
                            case WEBSITE:
                                if (enableLogExcelData)
                                    System.out.println("WEBSITE: " + cell.getStringCellValue().trim());
                                station.setWebsite(cell.getStringCellValue().trim());
                                break;
                        }
                    }
                    indexCells = -1;

                    listStations.add(station);
                }
            }
            file.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return listStations;
    }
}
