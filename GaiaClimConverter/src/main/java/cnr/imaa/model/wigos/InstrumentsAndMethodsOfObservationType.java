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
 * <p>Java class for InstrumentsAndMethodsOfObservation_Type complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="InstrumentsAndMethodsOfObservation_Type">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="sourceOfObservation" type="{}SourceOfObservation_Type"/>
 *         &lt;element name="measurementObservingMethod" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="instrumentSpecifications" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="instrumentOperatingStatus" type="{}InstrumentOperatingStatus_Type" minOccurs="0"/>
 *         &lt;element name="verticalDistanceOfSensor" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="configurationOfInstrumentation" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="instrumentControlSchedule" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="instrumentControlResult" type="{}InstrumentControlResult_Type" minOccurs="0"/>
 *         &lt;element name="instrumentModelAndSerialNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="instrumentMaintenanceSchedule" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="maintenanceParty" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="geospatialLocation" type="{}GeospatialLocation_Instruments_Type" minOccurs="0"/>
 *         &lt;element name="maintenanceActivity" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="statusOfObservation" type="{}StatusOfObservation_Type" minOccurs="0"/>
 *         &lt;element name="exposureOfInstruments" type="{}ExposureOfInstruments_Type" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "InstrumentsAndMethodsOfObservation_Type", propOrder = {
    "sourceOfObservation",
    "measurementObservingMethod",
    "instrumentSpecifications",
    "instrumentOperatingStatus",
    "verticalDistanceOfSensor",
    "configurationOfInstrumentation",
    "instrumentControlSchedule",
    "instrumentControlResult",
    "instrumentModelAndSerialNumber",
    "instrumentMaintenanceSchedule",
    "maintenanceParty",
    "geospatialLocation",
    "maintenanceActivity",
    "statusOfObservation",
    "exposureOfInstruments"
})
public class InstrumentsAndMethodsOfObservationType implements Serializable {

    @XmlElement(required = true)
    protected SourceOfObservationType sourceOfObservation;
    @XmlElement(required = true)
    protected String measurementObservingMethod;
    @XmlElement(required = true)
    protected String instrumentSpecifications;
    protected InstrumentOperatingStatusType instrumentOperatingStatus;
    protected String verticalDistanceOfSensor;
    protected String configurationOfInstrumentation;
    protected String instrumentControlSchedule;
    protected InstrumentControlResultType instrumentControlResult;
    protected String instrumentModelAndSerialNumber;
    protected String instrumentMaintenanceSchedule;
    protected String maintenanceParty;
    protected GeospatialLocationInstrumentsType geospatialLocation;
    protected String maintenanceActivity;
    protected StatusOfObservationType statusOfObservation;
    protected ExposureOfInstrumentsType exposureOfInstruments;

    /**
     * Gets the value of the sourceOfObservation property.
     * 
     * @return
     *     possible object is
     *     {@link SourceOfObservationType }
     *     
     */
    public SourceOfObservationType getSourceOfObservation() {
        return sourceOfObservation;
    }

    /**
     * Sets the value of the sourceOfObservation property.
     * 
     * @param value
     *     allowed object is
     *     {@link SourceOfObservationType }
     *     
     */
    public void setSourceOfObservation(SourceOfObservationType value) {
        this.sourceOfObservation = value;
    }

    /**
     * Gets the value of the measurementObservingMethod property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMeasurementObservingMethod() {
        return measurementObservingMethod;
    }

    /**
     * Sets the value of the measurementObservingMethod property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMeasurementObservingMethod(String value) {
        this.measurementObservingMethod = value;
    }

    /**
     * Gets the value of the instrumentSpecifications property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInstrumentSpecifications() {
        return instrumentSpecifications;
    }

    /**
     * Sets the value of the instrumentSpecifications property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInstrumentSpecifications(String value) {
        this.instrumentSpecifications = value;
    }

    /**
     * Gets the value of the instrumentOperatingStatus property.
     * 
     * @return
     *     possible object is
     *     {@link InstrumentOperatingStatusType }
     *     
     */
    public InstrumentOperatingStatusType getInstrumentOperatingStatus() {
        return instrumentOperatingStatus;
    }

    /**
     * Sets the value of the instrumentOperatingStatus property.
     * 
     * @param value
     *     allowed object is
     *     {@link InstrumentOperatingStatusType }
     *     
     */
    public void setInstrumentOperatingStatus(InstrumentOperatingStatusType value) {
        this.instrumentOperatingStatus = value;
    }

    /**
     * Gets the value of the verticalDistanceOfSensor property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVerticalDistanceOfSensor() {
        return verticalDistanceOfSensor;
    }

    /**
     * Sets the value of the verticalDistanceOfSensor property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVerticalDistanceOfSensor(String value) {
        this.verticalDistanceOfSensor = value;
    }

    /**
     * Gets the value of the configurationOfInstrumentation property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getConfigurationOfInstrumentation() {
        return configurationOfInstrumentation;
    }

    /**
     * Sets the value of the configurationOfInstrumentation property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setConfigurationOfInstrumentation(String value) {
        this.configurationOfInstrumentation = value;
    }

    /**
     * Gets the value of the instrumentControlSchedule property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInstrumentControlSchedule() {
        return instrumentControlSchedule;
    }

    /**
     * Sets the value of the instrumentControlSchedule property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInstrumentControlSchedule(String value) {
        this.instrumentControlSchedule = value;
    }

    /**
     * Gets the value of the instrumentControlResult property.
     * 
     * @return
     *     possible object is
     *     {@link InstrumentControlResultType }
     *     
     */
    public InstrumentControlResultType getInstrumentControlResult() {
        return instrumentControlResult;
    }

    /**
     * Sets the value of the instrumentControlResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link InstrumentControlResultType }
     *     
     */
    public void setInstrumentControlResult(InstrumentControlResultType value) {
        this.instrumentControlResult = value;
    }

    /**
     * Gets the value of the instrumentModelAndSerialNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInstrumentModelAndSerialNumber() {
        return instrumentModelAndSerialNumber;
    }

    /**
     * Sets the value of the instrumentModelAndSerialNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInstrumentModelAndSerialNumber(String value) {
        this.instrumentModelAndSerialNumber = value;
    }

    /**
     * Gets the value of the instrumentMaintenanceSchedule property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInstrumentMaintenanceSchedule() {
        return instrumentMaintenanceSchedule;
    }

    /**
     * Sets the value of the instrumentMaintenanceSchedule property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInstrumentMaintenanceSchedule(String value) {
        this.instrumentMaintenanceSchedule = value;
    }

    /**
     * Gets the value of the maintenanceParty property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMaintenanceParty() {
        return maintenanceParty;
    }

    /**
     * Sets the value of the maintenanceParty property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMaintenanceParty(String value) {
        this.maintenanceParty = value;
    }

    /**
     * Gets the value of the geospatialLocation property.
     * 
     * @return
     *     possible object is
     *     {@link GeospatialLocationInstrumentsType }
     *     
     */
    public GeospatialLocationInstrumentsType getGeospatialLocation() {
        return geospatialLocation;
    }

    /**
     * Sets the value of the geospatialLocation property.
     * 
     * @param value
     *     allowed object is
     *     {@link GeospatialLocationInstrumentsType }
     *     
     */
    public void setGeospatialLocation(GeospatialLocationInstrumentsType value) {
        this.geospatialLocation = value;
    }

    /**
     * Gets the value of the maintenanceActivity property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMaintenanceActivity() {
        return maintenanceActivity;
    }

    /**
     * Sets the value of the maintenanceActivity property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMaintenanceActivity(String value) {
        this.maintenanceActivity = value;
    }

    /**
     * Gets the value of the statusOfObservation property.
     * 
     * @return
     *     possible object is
     *     {@link StatusOfObservationType }
     *     
     */
    public StatusOfObservationType getStatusOfObservation() {
        return statusOfObservation;
    }

    /**
     * Sets the value of the statusOfObservation property.
     * 
     * @param value
     *     allowed object is
     *     {@link StatusOfObservationType }
     *     
     */
    public void setStatusOfObservation(StatusOfObservationType value) {
        this.statusOfObservation = value;
    }

    /**
     * Gets the value of the exposureOfInstruments property.
     * 
     * @return
     *     possible object is
     *     {@link ExposureOfInstrumentsType }
     *     
     */
    public ExposureOfInstrumentsType getExposureOfInstruments() {
        return exposureOfInstruments;
    }

    /**
     * Sets the value of the exposureOfInstruments property.
     * 
     * @param value
     *     allowed object is
     *     {@link ExposureOfInstrumentsType }
     *     
     */
    public void setExposureOfInstruments(ExposureOfInstrumentsType value) {
        this.exposureOfInstruments = value;
    }

}