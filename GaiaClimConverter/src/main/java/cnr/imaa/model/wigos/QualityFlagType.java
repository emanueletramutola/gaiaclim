//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.03.07 at 04:39:49 PM CET 
//


package cnr.imaa.model.wigos;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.io.Serializable;


/**
 * 
 *                 An ordered list of qualifiers indicating the result of a quality control process applied to the observation.
 * 
 *                 NOTE 1:
 *                 BUFR code table series 0-33 contains data quality flags/definitions.
 * 
 *                 NOTE 2:
 *                 To be recorded by data providers for each individual observation
 *             
 * 
 * <p>Java class for QualityFlag_Type complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="QualityFlag_Type">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="qualityFlag_BUFR" type="{}QualityFlag_BUFR_Type" minOccurs="0"/>
 *         &lt;element name="qualityFlag_OGC" type="{}QualityFlag_OGC_Type" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "QualityFlag_Type", propOrder = {
    "qualityFlagBUFR",
    "qualityFlagOGC"
})
public class QualityFlagType  implements Serializable {

    @XmlElement(name = "qualityFlag_BUFR")
    protected QualityFlagBUFRType qualityFlagBUFR;
    @XmlElement(name = "qualityFlag_OGC")
    protected QualityFlagOGCType qualityFlagOGC;

    /**
     * Gets the value of the qualityFlagBUFR property.
     * 
     * @return
     *     possible object is
     *     {@link QualityFlagBUFRType }
     *     
     */
    public QualityFlagBUFRType getQualityFlagBUFR() {
        return qualityFlagBUFR;
    }

    /**
     * Sets the value of the qualityFlagBUFR property.
     * 
     * @param value
     *     allowed object is
     *     {@link QualityFlagBUFRType }
     *     
     */
    public void setQualityFlagBUFR(QualityFlagBUFRType value) {
        this.qualityFlagBUFR = value;
    }

    /**
     * Gets the value of the qualityFlagOGC property.
     * 
     * @return
     *     possible object is
     *     {@link QualityFlagOGCType }
     *     
     */
    public QualityFlagOGCType getQualityFlagOGC() {
        return qualityFlagOGC;
    }

    /**
     * Sets the value of the qualityFlagOGC property.
     * 
     * @param value
     *     allowed object is
     *     {@link QualityFlagOGCType }
     *     
     */
    public void setQualityFlagOGC(QualityFlagOGCType value) {
        this.qualityFlagOGC = value;
    }

}