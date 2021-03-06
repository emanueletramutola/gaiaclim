//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.03.07 at 04:39:49 PM CET 
//


package cnr.imaa.model.wigos;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;
import java.io.Serializable;


/**
 * 
 *                 The shape or configuration of a geographical feature, represented on a map by contour lines
 * 
 *                 NOTE 1:Topography shall be formally expressed with the four
 *                 elements ‘local topography’, ‘relative elevation’, ‘topographic context’,
 *                 and ‘altitude/depth’.
 * 
 *                 NOTE 2:
 *                 The term ‘altitude’ is used for elevations above mean sea level. The
 *                 term ‘depth’ is used for elevations below mean sea level.
 * 
 *                 EXAMPLES (can be converted into entries of the code table):
 *                 “a ridge at low relative elevation within valleys of middle altitude”
 *                 “a depression within plains of very low depth”
 *             
 * 
 * <p>Java class for TopographyOrBathymetry_Type complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="TopographyOrBathymetry_Type">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="localTopography" type="{}LocalTopography_Type" minOccurs="0"/>
 *         &lt;element name="relativeElevation" type="{}RelativeElevation_Type" minOccurs="0"/>
 *         &lt;element name="topographicContext" type="{}TopographicContext_Type" minOccurs="0"/>
 *         &lt;element name="altitudeDepth" type="{}AltitudeDepth_Type" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TopographyOrBathymetry_Type", propOrder = {
    "localTopography",
    "relativeElevation",
    "topographicContext",
    "altitudeDepth"
})
public class TopographyOrBathymetryType implements Serializable {

    protected LocalTopographyType localTopography;
    protected RelativeElevationType relativeElevation;
    protected TopographicContextType topographicContext;
    protected AltitudeDepthType altitudeDepth;

    /**
     * Gets the value of the localTopography property.
     * 
     * @return
     *     possible object is
     *     {@link LocalTopographyType }
     *     
     */
    public LocalTopographyType getLocalTopography() {
        return localTopography;
    }

    /**
     * Sets the value of the localTopography property.
     * 
     * @param value
     *     allowed object is
     *     {@link LocalTopographyType }
     *     
     */
    public void setLocalTopography(LocalTopographyType value) {
        this.localTopography = value;
    }

    /**
     * Gets the value of the relativeElevation property.
     * 
     * @return
     *     possible object is
     *     {@link RelativeElevationType }
     *     
     */
    public RelativeElevationType getRelativeElevation() {
        return relativeElevation;
    }

    /**
     * Sets the value of the relativeElevation property.
     * 
     * @param value
     *     allowed object is
     *     {@link RelativeElevationType }
     *     
     */
    public void setRelativeElevation(RelativeElevationType value) {
        this.relativeElevation = value;
    }

    /**
     * Gets the value of the topographicContext property.
     * 
     * @return
     *     possible object is
     *     {@link TopographicContextType }
     *     
     */
    public TopographicContextType getTopographicContext() {
        return topographicContext;
    }

    /**
     * Sets the value of the topographicContext property.
     * 
     * @param value
     *     allowed object is
     *     {@link TopographicContextType }
     *     
     */
    public void setTopographicContext(TopographicContextType value) {
        this.topographicContext = value;
    }

    /**
     * Gets the value of the altitudeDepth property.
     * 
     * @return
     *     possible object is
     *     {@link AltitudeDepthType }
     *     
     */
    public AltitudeDepthType getAltitudeDepth() {
        return altitudeDepth;
    }

    /**
     * Sets the value of the altitudeDepth property.
     * 
     * @param value
     *     allowed object is
     *     {@link AltitudeDepthType }
     *     
     */
    public void setAltitudeDepth(AltitudeDepthType value) {
        this.altitudeDepth = value;
    }

}
