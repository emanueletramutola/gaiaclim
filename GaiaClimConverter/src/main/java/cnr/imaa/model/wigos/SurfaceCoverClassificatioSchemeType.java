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
 * <p>Java class for SurfaceCoverClassificatioScheme_Type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="SurfaceCoverClassificatioScheme_Type">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="Not applicable"/>
 *     &lt;enumeration value="Land cover types (IGBP)"/>
 *     &lt;enumeration value="Land cover types (UMD)"/>
 *     &lt;enumeration value="Land cover types (LAI/fPAR)"/>
 *     &lt;enumeration value="Land cover types (NPP)"/>
 *     &lt;enumeration value="Land cover types (PFT)"/>
 *     &lt;enumeration value="Land cover types (LCCS)"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "SurfaceCoverClassificatioScheme_Type")
@XmlEnum
public enum SurfaceCoverClassificatioSchemeType implements Serializable {


    /**
     * 
     *                         None of the codes in the table are applicable in the context of this particular observation (nilReason)
     *                     
     * 
     */
    @XmlEnumValue("Not applicable")
    NOT_APPLICABLE("Not applicable"),

    /**
     * 
     *                         International Geosphere-Biosphere Programme
     *                         https://lpdaac.usgs.gov/products/modis_products_table/mcd12q1
     *                     
     * 
     */
    @XmlEnumValue("Land cover types (IGBP)")
    LAND_COVER_TYPES_IGBP("Land cover types (IGBP)"),

    /**
     * 
     *                         The University of Maryland Department of Geography generated global land cover classification
     *                         collection from 1998. http://glcf.umd.edu/data/landcover/
     *                     
     * 
     */
    @XmlEnumValue("Land cover types (UMD)")
    LAND_COVER_TYPES_UMD("Land cover types (UMD)"),

    /**
     * 
     *                         Leaf Area Index (LAI) and Fractional Photosynthetically Active Radiation (FPAR). FPAR/LAI is the
     *                         Fraction of Absorbed Photosynthetically Active radiation that a plant canopy absorbs for
     *                         photosynthesis and growth in the 0.4 – 0.7nm spectral range.
     *                     
     * 
     */
    @XmlEnumValue("Land cover types (LAI/fPAR)")
    LAND_COVER_TYPES_LAI_F_PAR("Land cover types (LAI/fPAR)"),

    /**
     * 
     *                         Net Primary Production (NPP) land cover scheme
     *                     
     * 
     */
    @XmlEnumValue("Land cover types (NPP)")
    LAND_COVER_TYPES_NPP("Land cover types (NPP)"),

    /**
     * 
     *                         Plant Functional Types (PFT) land cover scheme
     *                     
     * 
     */
    @XmlEnumValue("Land cover types (PFT)")
    LAND_COVER_TYPES_PFT("Land cover types (PFT)"),

    /**
     * 
     *                         Land cover classification scheme (LCCS)
     *                     
     * 
     */
    @XmlEnumValue("Land cover types (LCCS)")
    LAND_COVER_TYPES_LCCS("Land cover types (LCCS)");
    private final String value;

    SurfaceCoverClassificatioSchemeType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static SurfaceCoverClassificatioSchemeType fromValue(String v) {
        for (SurfaceCoverClassificatioSchemeType c: SurfaceCoverClassificatioSchemeType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
