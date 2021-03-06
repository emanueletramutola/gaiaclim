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
 *                 Description of human action or natural event at the station or in the vicinity that may influence the observation
 *             
 * 
 * <p>Java class for EventsAtStationPlatform_Type complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="EventsAtStationPlatform_Type">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;choice>
 *         &lt;element name="freeText" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="event" type="{}EventsAtStationPlatformNotFreeText_Type"/>
 *       &lt;/choice>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "EventsAtStationPlatform_Type", propOrder = {
    "freeText",
    "event"
})
public class EventsAtStationPlatformType implements Serializable {

    protected String freeText;
    protected EventsAtStationPlatformNotFreeTextType event;

    /**
     * Gets the value of the freeText property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFreeText() {
        return freeText;
    }

    /**
     * Sets the value of the freeText property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFreeText(String value) {
        this.freeText = value;
    }

    /**
     * Gets the value of the event property.
     * 
     * @return
     *     possible object is
     *     {@link EventsAtStationPlatformNotFreeTextType }
     *     
     */
    public EventsAtStationPlatformNotFreeTextType getEvent() {
        return event;
    }

    /**
     * Sets the value of the event property.
     * 
     * @param value
     *     allowed object is
     *     {@link EventsAtStationPlatformNotFreeTextType }
     *     
     */
    public void setEvent(EventsAtStationPlatformNotFreeTextType value) {
        this.event = value;
    }

}
