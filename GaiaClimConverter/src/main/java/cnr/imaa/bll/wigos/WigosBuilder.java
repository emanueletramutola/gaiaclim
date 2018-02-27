package cnr.imaa.bll.wigos;

import cnr.imaa.bll.ConfigurationManager;
import cnr.imaa.model.wigos.GaiaClimMetadataType;
import cnr.imaa.model.woudc.ExtCSV;
import ucar.nc2.NetcdfFile;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class WigosBuilder {
    protected final static DateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");

    public static void build() {
        String wigosInputFormat = ConfigurationManager.getPropertyValue("wigosInputFormat");
        String wigosPathInput = ConfigurationManager.getPropertyValue("wigosPathInput");
        String wigosPathOutput = ConfigurationManager.getPropertyValue("wigosPathOutput");

        try {
            Files.walk(Paths.get(wigosPathInput)).forEach(fileDat -> {
                GaiaClimMetadataType gaiaClimMetadata = null;
                if (Files.isRegularFile(fileDat)) {
                    try {
                        switch (wigosInputFormat.toUpperCase()) {
                            case "EXTCSV":
                                ExtCSV extCSV = ExtCSVBuilder.getExtCSV(fileDat.toString());

                                WigosConverter_ExtCSV converter_extCSV = new WigosConverter_ExtCSV(extCSV);

                                gaiaClimMetadata = converter_extCSV.build();
                                break;
                            case "NETCDF_GRUAN":
                            case "NETCDF_EARLINET":
                                NetcdfFile ncfile = NetcdfFile.open(fileDat.toString());

                                WigosConverter_Netcdf converter_netcdf = new WigosConverter_Netcdf(ncfile, wigosInputFormat.toUpperCase());

                                gaiaClimMetadata = converter_netcdf.build();
                                break;
                        }

                        StringWriter sw = new StringWriter();
                        JAXBContext jc = null;

                        jc = JAXBContext.newInstance(GaiaClimMetadataType.class.getPackage().getName());

                        Unmarshaller u = jc.createUnmarshaller();
                        Marshaller jaxbMarshaller = jc.createMarshaller();
                        jaxbMarshaller.marshal(gaiaClimMetadata, sw);

                        PrintWriter out = new PrintWriter(wigosPathOutput + fileDat.getFileName().toString() + ".xml");
                        out.println(sw.toString());
                        out.close();
                    } catch (JAXBException e) {
                        e.printStackTrace();
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
