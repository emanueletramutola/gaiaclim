package cnr.imaa.bll.wigos;

import cnr.imaa.bll.ConfigurationManager;
import cnr.imaa.dal.CountryISO3DAL;
import cnr.imaa.model.CountryISO3;
import cnr.imaa.model.wigos.RegionOfOriginOfDataType;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;

import javax.ws.rs.core.UriBuilder;

public class CountryISO3Utility {
    public CountryISO3 getISO3CountryInfoByService(String latitude, String longitude) {
        CountryISO3 countryISO3;

        ClientConfig config = new DefaultClientConfig();
        Client client = Client.create(config);

        String urlDecodeCountryCode = ConfigurationManager.getPropertyValue("urlDecodeCountryCode");

        latitude = latitude.trim();
        longitude = longitude.trim();

        if (latitude.contains("°")) latitude = latitude.replace("°", "").trim();
        if (longitude.contains("°")) longitude = longitude.replace("°", "").trim();

        urlDecodeCountryCode = urlDecodeCountryCode.replace("__LATITUDE__", latitude);
        urlDecodeCountryCode = urlDecodeCountryCode.replace("__LONGITUDE__", longitude);

        WebResource service = client.resource(UriBuilder.fromUri(urlDecodeCountryCode).build());
        ClientResponse response = service.accept("application/json").get(ClientResponse.class);

        if (response.getStatus() != 200) {
            throw new RuntimeException("Failed : HTTP error code : "
                    + response.getStatus());
        }

        String code2 = response.getEntity(String.class).replace("\r\n", "");

        CountryISO3DAL dal = new CountryISO3DAL();
        countryISO3 = dal.getListCountriesByCode2(code2).get(0);

        return countryISO3;
    }

    public CountryISO3 getISO3CountryInfoByCode3(String code3) {
        CountryISO3DAL dal = new CountryISO3DAL();
        CountryISO3 countryISO3 = dal.getListCountriesByCode3(code3).get(0);

        return countryISO3;
    }

    public RegionOfOriginOfDataType getRegionEnum(String region) {
        RegionOfOriginOfDataType regionOfOriginOfDataType = null;

        switch (region.toUpperCase()) {
            case "I":
                regionOfOriginOfDataType = RegionOfOriginOfDataType.I;
                break;
            case "II":
                regionOfOriginOfDataType = RegionOfOriginOfDataType.II;
                break;
            case "III":
                regionOfOriginOfDataType = RegionOfOriginOfDataType.III;
                break;
            case "IV":
                regionOfOriginOfDataType = RegionOfOriginOfDataType.IV;
                break;
            case "V":
                regionOfOriginOfDataType = RegionOfOriginOfDataType.V;
                break;
            case "VI":
                regionOfOriginOfDataType = RegionOfOriginOfDataType.VI;
                break;
            case "VII":
                regionOfOriginOfDataType = RegionOfOriginOfDataType.VII;
                break;
        }

        return regionOfOriginOfDataType;
    }
}
