package cnr.imaa.dal;

import cnr.imaa.model.CountryISO3;

import java.util.List;

public class CountryISO3DAL {
    DbBinder dbBinder = new DbBinder();

    public List<CountryISO3> getListCountriesByCode2(String code2) {
        String query = "from CountryISO3 c where c.code2 = ?";
        List<CountryISO3> result = (List<CountryISO3>) dbBinder.getList(query, CountryISO3.class, code2);

        return result;
    }

    public List<CountryISO3> getListCountriesByCode3(String code3) {
        String query = "from CountryISO3 c where c.code3 = ?";
        List<CountryISO3> result = (List<CountryISO3>) dbBinder.getList(query, CountryISO3.class, code3);

        return result;
    }

    public void update(CountryISO3 country) {
        dbBinder.update(country);
    }
}
