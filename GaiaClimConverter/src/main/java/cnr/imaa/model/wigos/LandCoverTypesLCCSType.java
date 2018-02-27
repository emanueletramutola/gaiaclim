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
 * <p>Java class for landCoverTypes_LCCS_Type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="landCoverTypes_LCCS_Type">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="Not applicable"/>
 *     &lt;enumeration value="Cultivated and Managed Terrestrial Areas"/>
 *     &lt;enumeration value="Natural and Semi-Natural Terrestrial Vegetation"/>
 *     &lt;enumeration value="Cultivated Aquatic or Regularly Flooded Areas"/>
 *     &lt;enumeration value="Natural and Semi-Natural Aquatic or Regularly Flooded Vegetation"/>
 *     &lt;enumeration value="Artificial Surfaces and Associated Areas"/>
 *     &lt;enumeration value="Bare Areas"/>
 *     &lt;enumeration value="Artificial Waterbodies, Snow and Ice"/>
 *     &lt;enumeration value="Natural Waterbodies, Snow and Ice"/>
 *     &lt;enumeration value="Unclassified"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "landCoverTypes_LCCS_Type")
@XmlEnum
public enum LandCoverTypesLCCSType implements Serializable {

    @XmlEnumValue("Not applicable")
    NOT_APPLICABLE("Not applicable"),
    @XmlEnumValue("Cultivated and Managed Terrestrial Areas")
    CULTIVATED_AND_MANAGED_TERRESTRIAL_AREAS("Cultivated and Managed Terrestrial Areas"),
    @XmlEnumValue("Natural and Semi-Natural Terrestrial Vegetation")
    NATURAL_AND_SEMI_NATURAL_TERRESTRIAL_VEGETATION("Natural and Semi-Natural Terrestrial Vegetation"),
    @XmlEnumValue("Cultivated Aquatic or Regularly Flooded Areas")
    CULTIVATED_AQUATIC_OR_REGULARLY_FLOODED_AREAS("Cultivated Aquatic or Regularly Flooded Areas"),
    @XmlEnumValue("Natural and Semi-Natural Aquatic or Regularly Flooded Vegetation")
    NATURAL_AND_SEMI_NATURAL_AQUATIC_OR_REGULARLY_FLOODED_VEGETATION("Natural and Semi-Natural Aquatic or Regularly Flooded Vegetation"),
    @XmlEnumValue("Artificial Surfaces and Associated Areas")
    ARTIFICIAL_SURFACES_AND_ASSOCIATED_AREAS("Artificial Surfaces and Associated Areas"),
    @XmlEnumValue("Bare Areas")
    BARE_AREAS("Bare Areas"),
    @XmlEnumValue("Artificial Waterbodies, Snow and Ice")
    ARTIFICIAL_WATERBODIES_SNOW_AND_ICE("Artificial Waterbodies, Snow and Ice"),
    @XmlEnumValue("Natural Waterbodies, Snow and Ice")
    NATURAL_WATERBODIES_SNOW_AND_ICE("Natural Waterbodies, Snow and Ice"),
    @XmlEnumValue("Unclassified")
    UNCLASSIFIED("Unclassified");
    private final String value;

    LandCoverTypesLCCSType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static LandCoverTypesLCCSType fromValue(String v) {
        for (LandCoverTypesLCCSType c: LandCoverTypesLCCSType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
