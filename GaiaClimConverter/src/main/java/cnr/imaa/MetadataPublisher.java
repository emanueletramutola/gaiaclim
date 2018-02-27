package cnr.imaa;

import cnr.imaa.bll.ClientService;
import cnr.imaa.bll.ConfigurationManager;
import cnr.imaa.dal.*;
import cnr.imaa.dal.MetadataXmlDAL;
import cnr.imaa.model.MetadataXml;
import cnr.imaa.model.Station;
import org.w3c.dom.Document;

import java.io.File;
import java.util.ArrayList;

public class MetadataPublisher {
    private static ArrayList<ItemToProcess> getListItemToProcess() {
        ArrayList<ItemToProcess> listItemToProcess = new ArrayList<ItemToProcess>();

        /*
        listItemToProcess.add(new ItemToProcess(
                MetadataXmlDAL.readMetadataXml("Resources/TemplateMetadataByNetwork/Gruan/Gruan_WaterVapor_Profile.xml")
                , ExcelDAL.readFromExcel("Resources/Xlsx/GRUAN.xlsx")));
        listItemToProcess.add(new ItemToProcess(
                MetadataXmlDAL.readMetadataXml("Resources/TemplateMetadataByNetwork/Gruan/Gruan_Temperature_Profile.xml")
                , ExcelDAL.readFromExcel("Resources/Xlsx/GRUAN.xlsx")));
        listItemToProcess.add(new ItemToProcess(
                MetadataXmlDAL.readMetadataXml("Resources/TemplateMetadataByNetwork/Earlinet/Earlinet_Aerosol_Profile.xml")
                , ExcelDAL.readFromExcel("Resources/Xlsx/Earlinet.xlsx")));
        listItemToProcess.add(new ItemToProcess(
                MetadataXmlDAL.readMetadataXml("Resources/TemplateMetadataByNetwork/Improve/Improve_Aerosol_Surface.xml")
                , ExcelDAL.readFromExcel("Resources/Xlsx/IMPROVE.xlsx")));
        listItemToProcess.add(new ItemToProcess(
                MetadataXmlDAL.readMetadataXml("Resources/TemplateMetadataByNetwork/Smear/SMEAR_Temperature_Surface.xml")
                , ExcelDAL.readFromExcel("Resources/Xlsx/SMEAR.xlsx")));
        listItemToProcess.add(new ItemToProcess(
                MetadataXmlDAL.readMetadataXml("Resources/TemplateMetadataByNetwork/Smear/SMEAR_Temperature_Tower.xml")
                , ExcelDAL.readFromExcel("Resources/Xlsx/SMEAR.xlsx")));
        listItemToProcess.add(new ItemToProcess(
                MetadataXmlDAL.readMetadataXml("Resources/TemplateMetadataByNetwork/Smear/SMEAR_WaterVapor_Surface.xml")
                , ExcelDAL.readFromExcel("Resources/Xlsx/SMEAR.xlsx")));
        listItemToProcess.add(new ItemToProcess(
                MetadataXmlDAL.readMetadataXml("Resources/TemplateMetadataByNetwork/Smear/Smear_WaterVapor_Tower.xml")
                , ExcelDAL.readFromExcel("Resources/Xlsx/SMEAR.xlsx")));
        listItemToProcess.add(new ItemToProcess(
                MetadataXmlDAL.readMetadataXml("Resources/TemplateMetadataByNetwork/Smear/SMEAR_Aerosol_Surface.xml")
                , ExcelDAL.readFromExcel("Resources/Xlsx/SMEAR.xlsx")));
        listItemToProcess.add(new ItemToProcess(
                MetadataXmlDAL.readMetadataXml("Resources/TemplateMetadataByNetwork/Smear/SMEAR_CarbonDioxide_Surface.xml")
                , ExcelDAL.readFromExcel("Resources/Xlsx/SMEAR.xlsx")));
        listItemToProcess.add(new ItemToProcess(
                MetadataXmlDAL.readMetadataXml("Resources/TemplateMetadataByNetwork/Smear/SMEAR_CarbonDioxide_Tower.xml")
                , ExcelDAL.readFromExcel("Resources/Xlsx/SMEAR.xlsx")));
        listItemToProcess.add(new ItemToProcess(
                MetadataXmlDAL.readMetadataXml("Resources/TemplateMetadataByNetwork/Smear/SMEAR_CarbonMonoxide_Surface.xml")
                , ExcelDAL.readFromExcel("Resources/Xlsx/SMEAR.xlsx")));
        listItemToProcess.add(new ItemToProcess(
                MetadataXmlDAL.readMetadataXml("Resources/TemplateMetadataByNetwork/Smear/SMEAR_CarbonMonoxide_Tower.xml")
                , ExcelDAL.readFromExcel("Resources/Xlsx/SMEAR.xlsx")));
        listItemToProcess.add(new ItemToProcess(
                MetadataXmlDAL.readMetadataXml("Resources/TemplateMetadataByNetwork/Smear/SMEAR_Methane_Surface.xml")
                , ExcelDAL.readFromExcel("Resources/Xlsx/SMEAR.xlsx")));
        listItemToProcess.add(new ItemToProcess(
                MetadataXmlDAL.readMetadataXml("Resources/TemplateMetadataByNetwork/Smear/SMEAR_Methane_Tower.xml")
                , ExcelDAL.readFromExcel("Resources/Xlsx/SMEAR.xlsx")));
        listItemToProcess.add(new ItemToProcess(
                MetadataXmlDAL.readMetadataXml("Resources/TemplateMetadataByNetwork/Smear/SMEAR_NitrogenDioxide_Surface.xml")
                , ExcelDAL.readFromExcel("Resources/Xlsx/SMEAR.xlsx")));
        listItemToProcess.add(new ItemToProcess(
                MetadataXmlDAL.readMetadataXml("Resources/TemplateMetadataByNetwork/Smear/SMEAR_NitrogenDioxide_Tower.xml")
                , ExcelDAL.readFromExcel("Resources/Xlsx/SMEAR.xlsx")));
        listItemToProcess.add(new ItemToProcess(
                MetadataXmlDAL.readMetadataXml("Resources/TemplateMetadataByNetwork/Smear/SMEAR_Ozone_Surface.xml")
                , ExcelDAL.readFromExcel("Resources/Xlsx/SMEAR.xlsx")));
        listItemToProcess.add(new ItemToProcess(
                MetadataXmlDAL.readMetadataXml("Resources/TemplateMetadataByNetwork/Smear/SMEAR_Ozone_Tower.xml")
                , ExcelDAL.readFromExcel("Resources/Xlsx/SMEAR.xlsx")));
        listItemToProcess.add(new ItemToProcess(
                MetadataXmlDAL.readMetadataXml("Resources/TemplateMetadataByNetwork/Castnet/Castnet_Aerosol_Surface.xml")
                , ExcelDAL.readFromExcel("Resources/Xlsx/CASTNET.xlsx")));
        listItemToProcess.add(new ItemToProcess(
                MetadataXmlDAL.readMetadataXml("Resources/TemplateMetadataByNetwork/Castnet/Castnet_Ozone_Surface.xml")
                , ExcelDAL.readFromExcel("Resources/Xlsx/CASTNET.xlsx")));
        listItemToProcess.add(new ItemToProcess(
                MetadataXmlDAL.readMetadataXml("Resources/TemplateMetadataByNetwork/Surfrad/Surfrad_Aerosol_Column.xml")
                , ExcelDAL.readFromExcel("Resources/Xlsx/SURFRAD.xlsx")));
        listItemToProcess.add(new ItemToProcess(
                MetadataXmlDAL.readMetadataXml("Resources/TemplateMetadataByNetwork/GUAN/GUAN_Temperature_Profile.xml")
                , ExcelDAL.readFromExcel("Resources/Xlsx/GUAN.xlsx")));
        listItemToProcess.add(new ItemToProcess(
                MetadataXmlDAL.readMetadataXml("Resources/TemplateMetadataByNetwork/GUAN/GUAN_WaterVapor_Profile.xml")
                , ExcelDAL.readFromExcel("Resources/Xlsx/GUAN.xlsx")));
        listItemToProcess.add(new ItemToProcess(
                MetadataXmlDAL.readMetadataXml("Resources/TemplateMetadataByNetwork/CAPMoN/CAPMoN_Aerosol_Surface.xml")
                , ExcelDAL.readFromExcel("Resources/Xlsx/CAPMoN.xlsx")));
        listItemToProcess.add(new ItemToProcess(
                MetadataXmlDAL.readMetadataXml("Resources/TemplateMetadataByNetwork/CAPMoN/CAPMoN_NOx_Surface.xml")
                , ExcelDAL.readFromExcel("Resources/Xlsx/CAPMoN.xlsx")));
        listItemToProcess.add(new ItemToProcess(
                MetadataXmlDAL.readMetadataXml("Resources/TemplateMetadataByNetwork/CAPMoN/CAPMoN_Ozone_Surface.xml")
                , ExcelDAL.readFromExcel("Resources/Xlsx/CAPMoN.xlsx")));
        listItemToProcess.add(new ItemToProcess(
                MetadataXmlDAL.readMetadataXml("Resources/TemplateMetadataByNetwork/AERONET/AERONET_WaterVapor_Column.xml")
                , ExcelDAL.readFromExcel("Resources/Xlsx/AERONET.xlsx")));
        listItemToProcess.add(new ItemToProcess(
                MetadataXmlDAL.readMetadataXml("Resources/TemplateMetadataByNetwork/AERONET/AERONET_Aerosol_Column.xml")
                , ExcelDAL.readFromExcel("Resources/Xlsx/AERONET.xlsx")));
        listItemToProcess.add(new ItemToProcess(
                MetadataXmlDAL.readMetadataXml("Resources/TemplateMetadataByNetwork/ARGO/ARGO_Temperature&Salinity_Profile.xml")
                , ArgoDAL.readFromKml("Resources/ArgoData/argo.kml")));
        listItemToProcess.add(new ItemToProcess(
                MetadataXmlDAL.readMetadataXml("Resources/TemplateMetadataByNetwork/CARSNET/CARSNET_Aerosol_Column.xml")
                , ExcelDAL.readFromExcel("Resources/Xlsx/CARSNET.xlsx")));
        listItemToProcess.add(new ItemToProcess(
                MetadataXmlDAL.readMetadataXml("Resources/TemplateMetadataByNetwork/CARSNET/CARSNET_WaterVapor_Column.xml")
                , ExcelDAL.readFromExcel("Resources/Xlsx/CARSNET.xlsx")));
        listItemToProcess.add(new ItemToProcess(
                MetadataXmlDAL.readMetadataXml("Resources/TemplateMetadataByNetwork/CAWNET/CAWNET_Aerosol_Tower.xml")
                , ExcelDAL.readFromExcel("Resources/Xlsx/CAWNET.xlsx")));
        listItemToProcess.add(new ItemToProcess(
                MetadataXmlDAL.readMetadataXml("Resources/TemplateMetadataByNetwork/RAOB/RAOB_Temperature_Profile.xml")
                , ExcelDAL.readFromExcel("Resources/Xlsx/RAOB.xlsx")));
        listItemToProcess.add(new ItemToProcess(
                MetadataXmlDAL.readMetadataXml("Resources/TemplateMetadataByNetwork/RAOB/RAOB_WaterVapor_Profile.xml")
                , ExcelDAL.readFromExcel("Resources/Xlsx/RAOB.xlsx")));
        listItemToProcess.add(new ItemToProcess(
                MetadataXmlDAL.readMetadataXml("Resources/TemplateMetadataByNetwork/NDACC/NDACC_CarbonMonoxide_Column.xml")
                , ExcelDAL.readFromExcel("Resources/Xlsx/NDACC.xlsx")));
        listItemToProcess.add(new ItemToProcess(
                MetadataXmlDAL.readMetadataXml("Resources/TemplateMetadataByNetwork/NDACC/NDACC_CarbonMonoxide_Profile.xml")
                , ExcelDAL.readFromExcel("Resources/Xlsx/NDACC.xlsx")));
        listItemToProcess.add(new ItemToProcess(
                MetadataXmlDAL.readMetadataXml("Resources/TemplateMetadataByNetwork/NDACC/NDACC_Methane_Column.xml")
                , ExcelDAL.readFromExcel("Resources/Xlsx/NDACC.xlsx")));
        listItemToProcess.add(new ItemToProcess(
                MetadataXmlDAL.readMetadataXml("Resources/TemplateMetadataByNetwork/NDACC/NDACC_Methane_Profile.xml")
                , ExcelDAL.readFromExcel("Resources/Xlsx/NDACC.xlsx")));
        listItemToProcess.add(new ItemToProcess(
                MetadataXmlDAL.readMetadataXml("Resources/TemplateMetadataByNetwork/NDACC/NDACC_NitrogenDioxide_Column.xml")
                , ExcelDAL.readFromExcel("Resources/Xlsx/NDACC.xlsx")));
        listItemToProcess.add(new ItemToProcess(
                MetadataXmlDAL.readMetadataXml("Resources/TemplateMetadataByNetwork/NDACC/NDACC_NitrogenDioxide_Profile.xml")
                , ExcelDAL.readFromExcel("Resources/Xlsx/NDACC.xlsx")));
        listItemToProcess.add(new ItemToProcess(
                MetadataXmlDAL.readMetadataXml("Resources/TemplateMetadataByNetwork/NDACC/NDACC_Ozone_Column.xml")
                , ExcelDAL.readFromExcel("Resources/Xlsx/NDACC.xlsx")));
        listItemToProcess.add(new ItemToProcess(
                MetadataXmlDAL.readMetadataXml("Resources/TemplateMetadataByNetwork/NDACC/NDACC_Ozone_Profile.xml")
                , ExcelDAL.readFromExcel("Resources/Xlsx/NDACC.xlsx")));
        listItemToProcess.add(new ItemToProcess(
                MetadataXmlDAL.readMetadataXml("Resources/TemplateMetadataByNetwork/NDACC/NDACC_WaterVapour_Column.xml")
                , ExcelDAL.readFromExcel("Resources/Xlsx/NDACC.xlsx")));
        listItemToProcess.add(new ItemToProcess(
                MetadataXmlDAL.readMetadataXml("Resources/TemplateMetadataByNetwork/NDACC/NDACC_WaterVapour_Profile.xml")
                , ExcelDAL.readFromExcel("Resources/Xlsx/NDACC.xlsx")));
        listItemToProcess.add(new ItemToProcess(
                MetadataXmlDAL.readMetadataXml("Resources/TemplateMetadataByNetwork/Shadoz/SHADOZ_Ozone_Profiles.xml")
                , ExcelDAL.readFromExcel("Resources/Xlsx/Woudc_Shadoz.xlsx")));
        listItemToProcess.add(new ItemToProcess(
                MetadataXmlDAL.readMetadataXml("Resources/TemplateMetadataByNetwork/Woudc/WOUDC_Ozone_Column.xml")
                , ExcelDAL.readFromExcel("Resources/Xlsx/Woudc_Shadoz.xlsx")));
        listItemToProcess.add(new ItemToProcess(
                MetadataXmlDAL.readMetadataXml("Resources/TemplateMetadataByNetwork/Woudc/WOUDC_Ozone_Profile.xml")
                , ExcelDAL.readFromExcel("Resources/Xlsx/Woudc_Shadoz.xlsx")));
        listItemToProcess.add(new ItemToProcess(
                MetadataXmlDAL.readMetadataXml("Resources/TemplateMetadataByNetwork/AMeDAS/AMeDAS_Temperature_Surface.xml")
                , ExcelDAL.readFromExcel("Resources/Xlsx/AMeDAS.xlsx")));
        listItemToProcess.add(new ItemToProcess(
                MetadataXmlDAL.readMetadataXml("Resources/TemplateMetadataByNetwork/ARM/ARM_Temperature_Surface.xml")
                , ExcelDAL.readFromExcel("Resources/Xlsx/ARM.xlsx")));
        listItemToProcess.add(new ItemToProcess(
                MetadataXmlDAL.readMetadataXml("Resources/TemplateMetadataByNetwork/RBSN/RBSN_Temperature_Surface.xml")
                , ExcelDAL.readFromExcel("Resources/Xlsx/RBSN.xlsx")));
        listItemToProcess.add(new ItemToProcess(
                MetadataXmlDAL.readMetadataXml("Resources/TemplateMetadataByNetwork/GSN/GSN_Temperature_Surface.xml")
                , ExcelDAL.readFromExcel("Resources/Xlsx/GSN.xlsx")));
        listItemToProcess.add(new ItemToProcess(
                MetadataXmlDAL.readMetadataXml("Resources/TemplateMetadataByNetwork/Suominet/SuomiNet_WaterVapor_Column.xml")
                , ExcelDAL.readFromExcel("Resources/Xlsx/Suominet.xlsx")));
        listItemToProcess.add(new ItemToProcess(
                MetadataXmlDAL.readMetadataXml("Resources/TemplateMetadataByNetwork/GPS-MET/GPS-MET_WaterVapor_Column.xml")
                , ExcelDAL.readFromExcel("Resources/Xlsx/GPS-MET.xlsx")));
        listItemToProcess.add(new ItemToProcess(
                MetadataXmlDAL.readMetadataXml("Resources/TemplateMetadataByNetwork/IGS/IGS_WaterVapor_Column.xml")
                , ExcelDAL.readFromExcel("Resources/Xlsx/IGS.xlsx")));
        listItemToProcess.add(new ItemToProcess(
                MetadataXmlDAL.readMetadataXml("Resources/TemplateMetadataByNetwork/EMEP/EMEP_Aerosol_Surface.xml")
                , ExcelDAL.readFromExcel("Resources/Xlsx/EMEP.xlsx")));
        listItemToProcess.add(new ItemToProcess(
                MetadataXmlDAL.readMetadataXml("Resources/TemplateMetadataByNetwork/MESONET/MESONET_Temperature_Surface.xml")
                , ExcelDAL.readFromExcel("Resources/Xlsx/MESONET.xlsx")));
        listItemToProcess.add(new ItemToProcess(
                MetadataXmlDAL.readMetadataXml("Resources/TemplateMetadataByNetwork/MESONET/MESONET_WaterVapor_Surface.xml")
                , ExcelDAL.readFromExcel("Resources/Xlsx/MESONET.xlsx")));
        listItemToProcess.add(new ItemToProcess(
                MetadataXmlDAL.readMetadataXml("Resources/TemplateMetadataByNetwork/BSRN/BSRN_Aerosol_Column.xml")
                , ExcelDAL.readFromExcel("Resources/Xlsx/BSRN.xlsx")));
        listItemToProcess.add(new ItemToProcess(
                MetadataXmlDAL.readMetadataXml("Resources/TemplateMetadataByNetwork/BSRN/BSRN_Ozone_Column.xml")
                , ExcelDAL.readFromExcel("Resources/Xlsx/BSRN.xlsx")));
        listItemToProcess.add(new ItemToProcess(
                MetadataXmlDAL.readMetadataXml("Resources/TemplateMetadataByNetwork/MPLNET/MPLNET_Aerosol_Profile.xml")
                , ExcelDAL.readFromExcel("Resources/Xlsx/MPLNET.xlsx")));
        listItemToProcess.add(new ItemToProcess(
                MetadataXmlDAL.readMetadataXml("Resources/TemplateMetadataByNetwork/MWRNET/MWRNET_Temperature_Profile.xml")
                , ExcelDAL.readFromExcel("Resources/Xlsx/MWRnet.xlsx")));
        listItemToProcess.add(new ItemToProcess(
                MetadataXmlDAL.readMetadataXml("Resources/TemplateMetadataByNetwork/MWRNET/MWRNET_WaterVapor_Column.xml")
                , ExcelDAL.readFromExcel("Resources/Xlsx/MWRnet.xlsx")));
        listItemToProcess.add(new ItemToProcess(
                MetadataXmlDAL.readMetadataXml("Resources/TemplateMetadataByNetwork/MWRNET/MWRNET_WaterVapor_Profile.xml")
                , ExcelDAL.readFromExcel("Resources/Xlsx/MWRnet.xlsx")));
        listItemToProcess.add(new ItemToProcess(
                MetadataXmlDAL.readMetadataXml("Resources/TemplateMetadataByNetwork/GAW/GAWGALION_Aerosol_Profile.xml")
                , ExcelDAL.readFromExcel("Resources/Xlsx/GAW.xlsx")));
        listItemToProcess.add(new ItemToProcess(
                MetadataXmlDAL.readMetadataXml("Resources/TemplateMetadataByNetwork/GAW/GAWPFR_Aerosol_Column.xml")
                , ExcelDAL.readFromExcel("Resources/Xlsx/GAW.xlsx")));
        listItemToProcess.add(new ItemToProcess(
                MetadataXmlDAL.readMetadataXml("Resources/TemplateMetadataByNetwork/TOLNet/TOLNet_Ozone_Profile.xml")
                , ExcelDAL.readFromExcel("Resources/Xlsx/TOLNet.xlsx")));
        listItemToProcess.add(new ItemToProcess(
                MetadataXmlDAL.readMetadataXml("Resources/TemplateMetadataByNetwork/TCCON/TCCON_CH4_Column.xml")
                , ExcelDAL.readFromExcel("Resources/Xlsx/TCCON.xlsx")));
        listItemToProcess.add(new ItemToProcess(
                MetadataXmlDAL.readMetadataXml("Resources/TemplateMetadataByNetwork/TCCON/TCCON_CO2_Column.xml")
                , ExcelDAL.readFromExcel("Resources/Xlsx/TCCON.xlsx")));
        */

        listItemToProcess.add(new ItemToProcess(
                MetadataXmlDAL.readMetadataXml("Resources/TemplateMetadataByNetwork/TCCON/TCCON_CO_Column.xml")
                , ExcelDAL.readFromExcel("Resources/Xlsx/TCCON.xlsx")));
        listItemToProcess.add(new ItemToProcess(
                MetadataXmlDAL.readMetadataXml("Resources/TemplateMetadataByNetwork/TCCON/TCCON_H2O_Column.xml")
                , ExcelDAL.readFromExcel("Resources/Xlsx/TCCON.xlsx")));

        /*
        listItemToProcess.add(new ItemToProcess(
                MetadataXmlDAL.readMetadataXml("Resources/TemplateMetadataByNetwork/ESRL/ESRL_Aerosol_Surface.xml")
                , ExcelDAL.readFromExcel("Resources/Xlsx/ESRL_GMD.xlsx")));
        listItemToProcess.add(new ItemToProcess(
                MetadataXmlDAL.readMetadataXml("Resources/TemplateMetadataByNetwork/ESRL/ESRL_CH4_Profile.xml")
                , ExcelDAL.readFromExcel("Resources/Xlsx/ESRL_GMD.xlsx")));
        listItemToProcess.add(new ItemToProcess(
                MetadataXmlDAL.readMetadataXml("Resources/TemplateMetadataByNetwork/ESRL/ESRL_CH4_Surface.xml")
                , ExcelDAL.readFromExcel("Resources/Xlsx/ESRL_GMD.xlsx")));
        listItemToProcess.add(new ItemToProcess(
                MetadataXmlDAL.readMetadataXml("Resources/TemplateMetadataByNetwork/ESRL/ESRL_CO2_Profile.xml")
                , ExcelDAL.readFromExcel("Resources/Xlsx/ESRL_GMD.xlsx")));
        listItemToProcess.add(new ItemToProcess(
                MetadataXmlDAL.readMetadataXml("Resources/TemplateMetadataByNetwork/ESRL/ESRL_CO2_Surface.xml")
                , ExcelDAL.readFromExcel("Resources/Xlsx/ESRL_GMD.xlsx")));
        listItemToProcess.add(new ItemToProcess(
                MetadataXmlDAL.readMetadataXml("Resources/TemplateMetadataByNetwork/ESRL/ESRL_CO2_Tower.xml")
                , ExcelDAL.readFromExcel("Resources/Xlsx/ESRL_GMD.xlsx")));
        listItemToProcess.add(new ItemToProcess(
                MetadataXmlDAL.readMetadataXml("Resources/TemplateMetadataByNetwork/ESRL/ESRL_CO_Profile.xml")
                , ExcelDAL.readFromExcel("Resources/Xlsx/ESRL_GMD.xlsx")));
        listItemToProcess.add(new ItemToProcess(
                MetadataXmlDAL.readMetadataXml("Resources/TemplateMetadataByNetwork/ESRL/ESRL_CO_Surface.xml")
                , ExcelDAL.readFromExcel("Resources/Xlsx/ESRL_GMD.xlsx")));
        listItemToProcess.add(new ItemToProcess(
                MetadataXmlDAL.readMetadataXml("Resources/TemplateMetadataByNetwork/ESRL/ESRL_CO_Tower.xml")
                , ExcelDAL.readFromExcel("Resources/Xlsx/ESRL_GMD.xlsx")));
        listItemToProcess.add(new ItemToProcess(
                MetadataXmlDAL.readMetadataXml("Resources/TemplateMetadataByNetwork/ESRL/ESRL_O3_Column.xml")
                , ExcelDAL.readFromExcel("Resources/Xlsx/ESRL_GMD.xlsx")));
        listItemToProcess.add(new ItemToProcess(
                MetadataXmlDAL.readMetadataXml("Resources/TemplateMetadataByNetwork/ESRL/ESRL_O3_Profile.xml")
                , ExcelDAL.readFromExcel("Resources/Xlsx/ESRL_GMD.xlsx")));
        listItemToProcess.add(new ItemToProcess(
                MetadataXmlDAL.readMetadataXml("Resources/TemplateMetadataByNetwork/ESRL/ESRL_O3_Surface.xml")
                , ExcelDAL.readFromExcel("Resources/Xlsx/ESRL_GMD.xlsx")));
        listItemToProcess.add(new ItemToProcess(
                MetadataXmlDAL.readMetadataXml("Resources/TemplateMetadataByNetwork/ESRL/ESRL_WaterVapor_Profile.xml")
                , ExcelDAL.readFromExcel("Resources/Xlsx/ESRL_GMD.xlsx")));
        listItemToProcess.add(new ItemToProcess(
                MetadataXmlDAL.readMetadataXml("Resources/TemplateMetadataByNetwork/ESRL/ESRL_Ozone_Aircraft.xml")
                , ExcelDAL.readFromExcel("Resources/Xlsx/ESRL_GMD.xlsx")));
        listItemToProcess.add(new ItemToProcess(
                MetadataXmlDAL.readMetadataXml("Resources/TemplateMetadataByNetwork/ACTRIS/ACTRIS_Aerosol_Column.xml")
                , ExcelDAL.readFromExcel("Resources/Xlsx/ACTRIS.xlsx")));
        listItemToProcess.add(new ItemToProcess(
                MetadataXmlDAL.readMetadataXml("Resources/TemplateMetadataByNetwork/ACTRIS/ACTRIS_Aerosol_Profile.xml")
                , ExcelDAL.readFromExcel("Resources/Xlsx/ACTRIS.xlsx")));
        listItemToProcess.add(new ItemToProcess(
                MetadataXmlDAL.readMetadataXml("Resources/TemplateMetadataByNetwork/ACTRIS/ACTRIS_Aerosol_Surface.xml")
                , ExcelDAL.readFromExcel("Resources/Xlsx/ACTRIS.xlsx")));
        listItemToProcess.add(new ItemToProcess(
                MetadataXmlDAL.readMetadataXml("Resources/TemplateMetadataByNetwork/ACTRIS/ACTRIS_NitrogenDioxide_Surface.xml")
                , ExcelDAL.readFromExcel("Resources/Xlsx/ACTRIS.xlsx")));
        listItemToProcess.add(new ItemToProcess(
                MetadataXmlDAL.readMetadataXml("Resources/TemplateMetadataByNetwork/AD-NET/AD-net_Aerosol_Profile.xml")
                , ExcelDAL.readFromExcel("Resources/Xlsx/AD-Net.xlsx")));
        listItemToProcess.add(new ItemToProcess(
                MetadataXmlDAL.readMetadataXml("Resources/TemplateMetadataByNetwork/LALINET-ALINE/LALINET- ALINE_Aerosol_Profile.xml")
                , ExcelDAL.readFromExcel("Resources/Xlsx/LALINET-ALINE.xlsx")));
        listItemToProcess.add(new ItemToProcess(
                MetadataXmlDAL.readMetadataXml("Resources/TemplateMetadataByNetwork/SKYNET/SKYNET_Aerosol_Column.xml")
                , ExcelDAL.readFromExcel("Resources/Xlsx/SKYNET.xlsx")));
        listItemToProcess.add(new ItemToProcess(
                MetadataXmlDAL.readMetadataXml("Resources/TemplateMetadataByNetwork/SKYNET/SKYNET_Aerosol_Profile.xml")
                , ExcelDAL.readFromExcel("Resources/Xlsx/SKYNET.xlsx")));
        listItemToProcess.add(new ItemToProcess(
                MetadataXmlDAL.readMetadataXml("Resources/TemplateMetadataByNetwork/SKYNET/SKYNET_Nitrogen_Dioxide_Column.xml")
                , ExcelDAL.readFromExcel("Resources/Xlsx/SKYNET.xlsx")));
        listItemToProcess.add(new ItemToProcess(
                MetadataXmlDAL.readMetadataXml("Resources/TemplateMetadataByNetwork/SKYNET/SKYNET_NitrogenDioxide_Profile.xml")
                , ExcelDAL.readFromExcel("Resources/Xlsx/SKYNET.xlsx")));
        listItemToProcess.add(new ItemToProcess(
                MetadataXmlDAL.readMetadataXml("Resources/TemplateMetadataByNetwork/SKYNET/SKYNET_Ozone_Column.xml")
                , ExcelDAL.readFromExcel("Resources/Xlsx/SKYNET.xlsx")));
        listItemToProcess.add(new ItemToProcess(
                MetadataXmlDAL.readMetadataXml("Resources/TemplateMetadataByNetwork/SKYNET/SKYNET_Ozone_Profile.xml")
                , ExcelDAL.readFromExcel("Resources/Xlsx/SKYNET.xlsx")));
        listItemToProcess.add(new ItemToProcess(
                MetadataXmlDAL.readMetadataXml("Resources/TemplateMetadataByNetwork/SKYNET/SKYNET_Temperature_Surface.xml")
                , ExcelDAL.readFromExcel("Resources/Xlsx/SKYNET.xlsx")));
        listItemToProcess.add(new ItemToProcess(
                MetadataXmlDAL.readMetadataXml("Resources/TemplateMetadataByNetwork/SKYNET/SKYNET_WaterVapor_Column.xml")
                , ExcelDAL.readFromExcel("Resources/Xlsx/SKYNET.xlsx")));
        listItemToProcess.add(new ItemToProcess(
                MetadataXmlDAL.readMetadataXml("Resources/TemplateMetadataByNetwork/SKYNET/SKYNET_WaterVapor_Profile.xml")
                , ExcelDAL.readFromExcel("Resources/Xlsx/SKYNET.xlsx")));
        listItemToProcess.add(new ItemToProcess(
                MetadataXmlDAL.readMetadataXml("Resources/TemplateMetadataByNetwork/SKYNET/SKYNET_WaterVapor_Surface.xml")
                , ExcelDAL.readFromExcel("Resources/Xlsx/SKYNET.xlsx")));
        listItemToProcess.add(new ItemToProcess(
                MetadataXmlDAL.readMetadataXml("Resources/TemplateMetadataByNetwork/Scripps/Scripps CO2_CO2_Surface.xml")
                , ExcelDAL.readFromExcel("Resources/Xlsx/Scripps_CO2.xlsx")));
        listItemToProcess.add(new ItemToProcess(
                MetadataXmlDAL.readMetadataXml("Resources/TemplateMetadataByNetwork/EANET/EANET_Aerosol_Surface.xml")
                , ExcelDAL.readFromExcel("Resources/Xlsx/EANET.xlsx")));
        listItemToProcess.add(new ItemToProcess(
                MetadataXmlDAL.readMetadataXml("Resources/TemplateMetadataByNetwork/EANET/EANET_NitrogenDioxide_Surface.xml")
                , ExcelDAL.readFromExcel("Resources/Xlsx/EANET.xlsx")));
        listItemToProcess.add(new ItemToProcess(
                MetadataXmlDAL.readMetadataXml("Resources/TemplateMetadataByNetwork/EANET/EANET_Ozone_Surface.xml")
                , ExcelDAL.readFromExcel("Resources/Xlsx/EANET.xlsx")));
        listItemToProcess.add(new ItemToProcess(
                MetadataXmlDAL.readMetadataXml("Resources/TemplateMetadataByNetwork/EUROSKYRAD/EuroSkyRad_Aerosol_Column.xml")
                , ExcelDAL.readFromExcel("Resources/Xlsx/EuroSkyRad.xlsx")));
        listItemToProcess.add(new ItemToProcess(
                MetadataXmlDAL.readMetadataXml("Resources/TemplateMetadataByNetwork/EUROSKYRAD/EuroSkyRad_WaterVapor_Column.xml")
                , ExcelDAL.readFromExcel("Resources/Xlsx/EuroSkyRad.xlsx")));
        listItemToProcess.add(new ItemToProcess(
                MetadataXmlDAL.readMetadataXml("Resources/TemplateMetadataByNetwork/IDAF/IDAF_Aerosol_Ssurface.xml")
                , ExcelDAL.readFromExcel("Resources/Xlsx/IDAF.xlsx")));
        listItemToProcess.add(new ItemToProcess(
                MetadataXmlDAL.readMetadataXml("Resources/TemplateMetadataByNetwork/IDAF/IDAF_NitrogenDioxide_Surface.xml")
                , ExcelDAL.readFromExcel("Resources/Xlsx/IDAF.xlsx")));
        listItemToProcess.add(new ItemToProcess(
                MetadataXmlDAL.readMetadataXml("Resources/TemplateMetadataByNetwork/IDAF/IDAF_Ozone_Surface.xml")
                , ExcelDAL.readFromExcel("Resources/Xlsx/IDAF.xlsx")));
        listItemToProcess.add(new ItemToProcess(
                MetadataXmlDAL.readMetadataXml("Resources/TemplateMetadataByNetwork/ICOS/ICOS_CH4_Surface.xml")
                , ExcelDAL.readFromExcel("Resources/Xlsx/ICOS.xlsx")));
        listItemToProcess.add(new ItemToProcess(
                MetadataXmlDAL.readMetadataXml("Resources/TemplateMetadataByNetwork/ICOS/ICOS_CH4_Tower.xml")
                , ExcelDAL.readFromExcel("Resources/Xlsx/ICOS.xlsx")));
        listItemToProcess.add(new ItemToProcess(
                MetadataXmlDAL.readMetadataXml("Resources/TemplateMetadataByNetwork/ICOS/ICOS_CO2_Surface.xml")
                , ExcelDAL.readFromExcel("Resources/Xlsx/ICOS.xlsx")));
        listItemToProcess.add(new ItemToProcess(
                MetadataXmlDAL.readMetadataXml("Resources/TemplateMetadataByNetwork/ICOS/ICOS_CO2_Tower.xml")
                , ExcelDAL.readFromExcel("Resources/Xlsx/ICOS.xlsx")));
        listItemToProcess.add(new ItemToProcess(
                MetadataXmlDAL.readMetadataXml("Resources/TemplateMetadataByNetwork/ICOS/ICOS_CO_Surface.xml")
                , ExcelDAL.readFromExcel("Resources/Xlsx/ICOS.xlsx")));
        listItemToProcess.add(new ItemToProcess(
                MetadataXmlDAL.readMetadataXml("Resources/TemplateMetadataByNetwork/ICOS/ICOS_CO_Tower.xml")
                , ExcelDAL.readFromExcel("Resources/Xlsx/ICOS.xlsx")));
        listItemToProcess.add(new ItemToProcess(
                MetadataXmlDAL.readMetadataXml("Resources/TemplateMetadataByNetwork/EUREF/EUREF_WaterVapor_Column.xml")
                , ExcelDAL.readFromExcel("Resources/Xlsx/EUREF.xlsx")));
        listItemToProcess.add(new ItemToProcess(
                MetadataXmlDAL.readMetadataXml("Resources/TemplateMetadataByNetwork/USCRN/USCRN_Temperature_Surface.xml")
                , ExcelDAL.readFromExcel("Resources/Xlsx/USCRN.xlsx")));
        listItemToProcess.add(new ItemToProcess(
                MetadataXmlDAL.readMetadataXml("Resources/TemplateMetadataByNetwork/USCRN/USCRN_WaterVapor_Surface.xml")
                , ExcelDAL.readFromExcel("Resources/Xlsx/USCRN.xlsx")));
        listItemToProcess.add(new ItemToProcess(
                MetadataXmlDAL.readMetadataXml("Resources/TemplateMetadataByNetwork/AGAGE/AGAGE_CarbonMonoxide_Surface.xml")
                , ExcelDAL.readFromExcel("Resources/Xlsx/AGAGE.xlsx")));
        listItemToProcess.add(new ItemToProcess(
                MetadataXmlDAL.readMetadataXml("Resources/TemplateMetadataByNetwork/AGAGE/AGAGE_Methane_Surface.xml")
                , ExcelDAL.readFromExcel("Resources/Xlsx/AGAGE.xlsx")));
        listItemToProcess.add(new ItemToProcess(
                MetadataXmlDAL.readMetadataXml("Resources/TemplateMetadataByNetwork/FLUXNET/FLUXNET_CarbonDioxide_Tower.xml")
                , ExcelDAL.readFromExcel("Resources/Xlsx/FLUXNET.xlsx")));
        listItemToProcess.add(new ItemToProcess(
                MetadataXmlDAL.readMetadataXml("Resources/TemplateMetadataByNetwork/FLUXNET/FLUXNET_WaterVapor_Tower.xml")
                , ExcelDAL.readFromExcel("Resources/Xlsx/FLUXNET.xlsx")));
        listItemToProcess.add(new ItemToProcess(
                MetadataXmlDAL.readMetadataXml("Resources/TemplateMetadataByNetwork/EPA/EPA_Aerosol_Surface.xml")
                , ExcelDAL.readFromExcel("Resources/Xlsx/EPA.xlsx")));
        listItemToProcess.add(new ItemToProcess(
                MetadataXmlDAL.readMetadataXml("Resources/TemplateMetadataByNetwork/EPA/EPA_CarbonMonoxide_Surface.xml")
                , ExcelDAL.readFromExcel("Resources/Xlsx/EPA.xlsx")));
        listItemToProcess.add(new ItemToProcess(
                MetadataXmlDAL.readMetadataXml("Resources/TemplateMetadataByNetwork/EPA/EPA_NitrogenDioxide_Surface.xml")
                , ExcelDAL.readFromExcel("Resources/Xlsx/EPA.xlsx")));
        listItemToProcess.add(new ItemToProcess(
                MetadataXmlDAL.readMetadataXml("Resources/TemplateMetadataByNetwork/EPA/EPA_Ozone_Surface.xml")
                , ExcelDAL.readFromExcel("Resources/Xlsx/EPA.xlsx")));
        listItemToProcess.add(new ItemToProcess(
                MetadataXmlDAL.readMetadataXml("Resources/TemplateMetadataByNetwork/NPS/NPS_Aerosol_Surface.xml")
                , ExcelDAL.readFromExcel("Resources/Xlsx/NPS.xlsx")));
        listItemToProcess.add(new ItemToProcess(
                MetadataXmlDAL.readMetadataXml("Resources/TemplateMetadataByNetwork/NPS/NPS_CarbonMonoxide_Surface.xml")
                , ExcelDAL.readFromExcel("Resources/Xlsx/NPS.xlsx")));
        listItemToProcess.add(new ItemToProcess(
                MetadataXmlDAL.readMetadataXml("Resources/TemplateMetadataByNetwork/NPS/NPS_NitrogenDioxide_Surface.xml")
                , ExcelDAL.readFromExcel("Resources/Xlsx/NPS.xlsx")));
        listItemToProcess.add(new ItemToProcess(
                MetadataXmlDAL.readMetadataXml("Resources/TemplateMetadataByNetwork/NPS/NPS_Ozone_Surface.xml")
                , ExcelDAL.readFromExcel("Resources/Xlsx/NPS.xlsx")));
        listItemToProcess.add(new ItemToProcess(
                MetadataXmlDAL.readMetadataXml("Resources/TemplateMetadataByNetwork/NPS/NPS_Temperature_Surface.xml")
                , ExcelDAL.readFromExcel("Resources/Xlsx/NPS.xlsx")));
        listItemToProcess.add(new ItemToProcess(
                MetadataXmlDAL.readMetadataXml("Resources/TemplateMetadataByNetwork/NPS/NPS_WaterVapor_Surface.xml")
                , ExcelDAL.readFromExcel("Resources/Xlsx/NPS.xlsx")));
        listItemToProcess.add(new ItemToProcess(
                MetadataXmlDAL.readMetadataXml("Resources/TemplateMetadataByNetwork/CREST/CREST_Aerosol_Profile.xml")
                , ExcelDAL.readFromExcel("Resources/Xlsx/CREST.xlsx")));
        listItemToProcess.add(new ItemToProcess(
                MetadataXmlDAL.readMetadataXml("Resources/TemplateMetadataByNetwork/CREST/CREST_WaterVapor_Profile.xml")
                , ExcelDAL.readFromExcel("Resources/Xlsx/CREST.xlsx")));
        */

        return listItemToProcess;
    }

    public static void publish() {
        boolean enablePublish = Boolean.parseBoolean(ConfigurationManager.getPropertyValue("enablePublish"));
        boolean enableSaveFileMetadata = Boolean.parseBoolean(ConfigurationManager.getPropertyValue("enableSaveFileMetadata"));
        boolean enablePopulateDB = Boolean.parseBoolean(ConfigurationManager.getPropertyValue("enablePopulateDB"));
        StationDAL dal = new StationDAL();

        cleanFolder();

        ArrayList<ItemToProcess> listItemToProcess = getListItemToProcess();

        MetadataXml metadataXml;
        ArrayList<Station> listStations;
        String dataset;

        for (ItemToProcess itemToProcess : listItemToProcess) {
            metadataXml = itemToProcess.getMetadataXml();
            listStations = itemToProcess.getListStations();
            dataset = itemToProcess.getDataset();

            //Edit XML metadata
            for (Station station : listStations) {
                if (station.getNetwork().toUpperCase().equals(metadataXml.getNetwork().toUpperCase())
                        && station.getMeasurementType().toUpperCase().equals(metadataXml.getMeasurementType().toUpperCase())
                        && station.getMeasuredEcv().toUpperCase().equals(metadataXml.getMeasuredEcv().toUpperCase())) {

                    Document doc = cnr.imaa.bll.MetadataXml.addDataFromExcel(metadataXml, station, dataset);

                    if (enablePopulateDB)
                        dal.store(doc, station);

                    if (enableSaveFileMetadata)
                        cnr.imaa.bll.MetadataXml.saveXmlFull(doc, station);

                    //Publish metadata
                    if (enablePublish) {
                        //Get XML FULL
                        String xmlFull = cnr.imaa.bll.MetadataXml.getXmlFull(doc, station);
                        if (xmlFull != null) {
                            ClientService.InsertMetadata(xmlFull);
                        }
                    }
                }
            }
        }
    }

    private static void cleanFolder() {
        boolean enableClearFolderOutput = Boolean.parseBoolean(ConfigurationManager.getPropertyValue("enableClearFolderOutput"));
        if (enableClearFolderOutput) {
            File folder = new File("StationsMetadata");

            String[] listFiles = folder.list();
            for (String s : listFiles) {
                File currentFile = new File(folder.getPath(), s);
                currentFile.delete();
            }
        }
    }

    private static class ItemToProcess {
        private MetadataXml metadataXml;
        private ArrayList<Station> listStations;
        private String dataset;

        private ItemToProcess(MetadataXml metadataXml, ArrayList<Station> listStations) {
            this.metadataXml = metadataXml;
            this.listStations = listStations;
            this.dataset = cnr.imaa.bll.MetadataXml.getDatasetValue(metadataXml.getDoc());
        }

        public MetadataXml getMetadataXml() {
            return metadataXml;
        }

        public void setMetadataXml(MetadataXml metadataXml) {
            this.metadataXml = metadataXml;
        }

        public ArrayList<Station> getListStations() {
            return listStations;
        }

        public void setListStations(ArrayList<Station> listStations) {
            this.listStations = listStations;
        }

        public String getDataset() {
            return dataset;
        }

        public void setDataset(String dataset) {
            this.dataset = dataset;
        }
    }
}
