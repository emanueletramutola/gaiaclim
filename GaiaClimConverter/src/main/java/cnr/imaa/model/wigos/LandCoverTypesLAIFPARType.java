//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.03.07 at 04:39:49 PM CET 
//


package cnr.imaa.model.wigos;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;
import java.io.Serializable;


/**
 * <p>Java class for landCoverTypes_LAI_fPAR_Type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="landCoverTypes_LAI_fPAR_Type">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="Not applicable"/>
 *     &lt;enumeration value="Water"/>
 *     &lt;enumeration value="Grasses/Cereal crops"/>
 *     &lt;enumeration value="Shrubs"/>
 *     &lt;enumeration value="Broadleaf crops"/>
 *     &lt;enumeration value="Savanna"/>
 *     &lt;enumeration value="Evergreen broadleaf forest"/>
 *     &lt;enumeration value="Deciduous broadleaf forest"/>
 *     &lt;enumeration value="Evergreen needleleaf forest"/>
 *     &lt;enumeration value="Deciduous needleleaf forest"/>
 *     &lt;enumeration value="Non vegetated"/>
 *     &lt;enumeration value="Urban"/>
 *     &lt;enumeration value="Unclassified"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "landCoverTypes_LAI_fPAR_Type")
@XmlEnum
public enum LandCoverTypesLAIFPARType implements Serializable {

    @XmlEnumValue("Not applicable")
    NOT_APPLICABLE("Not applicable"),
    @XmlEnumValue("Water")
    WATER("Water"),
    @XmlEnumValue("Grasses/Cereal crops")
    GRASSES_CEREAL_CROPS("Grasses/Cereal crops"),
    @XmlEnumValue("Shrubs")
    SHRUBS("Shrubs"),
    @XmlEnumValue("Broadleaf crops")
    BROADLEAF_CROPS("Broadleaf crops"),
    @XmlEnumValue("Savanna")
    SAVANNA("Savanna"),
    @XmlEnumValue("Evergreen broadleaf forest")
    EVERGREEN_BROADLEAF_FOREST("Evergreen broadleaf forest"),
    @XmlEnumValue("Deciduous broadleaf forest")
    DECIDUOUS_BROADLEAF_FOREST("Deciduous broadleaf forest"),
    @XmlEnumValue("Evergreen needleleaf forest")
    EVERGREEN_NEEDLELEAF_FOREST("Evergreen needleleaf forest"),
    @XmlEnumValue("Deciduous needleleaf forest")
    DECIDUOUS_NEEDLELEAF_FOREST("Deciduous needleleaf forest"),
    @XmlEnumValue("Non vegetated")
    NON_VEGETATED("Non vegetated"),
    @XmlEnumValue("Urban")
    URBAN("Urban"),
    @XmlEnumValue("Unclassified")
    UNCLASSIFIED("Unclassified");
    private final String value;

    LandCoverTypesLAIFPARType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static LandCoverTypesLAIFPARType fromValue(String v) {
        for (LandCoverTypesLAIFPARType c: LandCoverTypesLAIFPARType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
