package cnr.imaa.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "countries")
public class CountryISO3 {
    private int id;
    private String name;
    private String code2;
    private String code3;
    private String region;

    public CountryISO3() {

    }

    public CountryISO3(int id, String name, String code2, String code3, String region) {
        this.id = id;
        this.name = name;
        this.code2 = code2;
        this.code3 = code3;
        this.region = region;
    }

    @Id
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode2() {
        return code2;
    }

    public void setCode2(String code2) {
        this.code2 = code2;
    }

    public String getCode3() {
        return code3;
    }

    public void setCode3(String code3) {
        this.code3 = code3;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }
}
