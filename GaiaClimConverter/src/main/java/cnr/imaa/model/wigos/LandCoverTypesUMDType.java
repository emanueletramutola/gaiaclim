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
 * <p>Java class for LandCoverTypes_UMD_Type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="LandCoverTypes_UMD_Type">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="Not applicable"/>
 *     &lt;enumeration value="Water"/>
 *     &lt;enumeration value="Evergreen Needleleaf forest"/>
 *     &lt;enumeration value="Evergreen Broadleaf forest"/>
 *     &lt;enumeration value="Deciduous Needleleaf forest"/>
 *     &lt;enumeration value="Deciduous Broadleaf forest"/>
 *     &lt;enumeration value="Mixed forest"/>
 *     &lt;enumeration value="Closed shrublands"/>
 *     &lt;enumeration value="Open shrublands"/>
 *     &lt;enumeration value="Woody savannas"/>
 *     &lt;enumeration value="Savannas"/>
 *     &lt;enumeration value="Grasslands"/>
 *     &lt;enumeration value="Croplands"/>
 *     &lt;enumeration value="Urban and built-up"/>
 *     &lt;enumeration value="Barren or sparsely vegetated"/>
 *     &lt;enumeration value="Unclassified"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "LandCoverTypes_UMD_Type")
@XmlEnum
public enum LandCoverTypesUMDType implements Serializable {

    @XmlEnumValue("Not applicable")
    NOT_APPLICABLE("Not applicable"),
    @XmlEnumValue("Water")
    WATER("Water"),
    @XmlEnumValue("Evergreen Needleleaf forest")
    EVERGREEN_NEEDLELEAF_FOREST("Evergreen Needleleaf forest"),
    @XmlEnumValue("Evergreen Broadleaf forest")
    EVERGREEN_BROADLEAF_FOREST("Evergreen Broadleaf forest"),
    @XmlEnumValue("Deciduous Needleleaf forest")
    DECIDUOUS_NEEDLELEAF_FOREST("Deciduous Needleleaf forest"),
    @XmlEnumValue("Deciduous Broadleaf forest")
    DECIDUOUS_BROADLEAF_FOREST("Deciduous Broadleaf forest"),
    @XmlEnumValue("Mixed forest")
    MIXED_FOREST("Mixed forest"),
    @XmlEnumValue("Closed shrublands")
    CLOSED_SHRUBLANDS("Closed shrublands"),
    @XmlEnumValue("Open shrublands")
    OPEN_SHRUBLANDS("Open shrublands"),
    @XmlEnumValue("Woody savannas")
    WOODY_SAVANNAS("Woody savannas"),
    @XmlEnumValue("Savannas")
    SAVANNAS("Savannas"),
    @XmlEnumValue("Grasslands")
    GRASSLANDS("Grasslands"),
    @XmlEnumValue("Croplands")
    CROPLANDS("Croplands"),
    @XmlEnumValue("Urban and built-up")
    URBAN_AND_BUILT_UP("Urban and built-up"),
    @XmlEnumValue("Barren or sparsely vegetated")
    BARREN_OR_SPARSELY_VEGETATED("Barren or sparsely vegetated"),
    @XmlEnumValue("Unclassified")
    UNCLASSIFIED("Unclassified");
    private final String value;

    LandCoverTypesUMDType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static LandCoverTypesUMDType fromValue(String v) {
        for (LandCoverTypesUMDType c: LandCoverTypesUMDType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
