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
 *                 Specifies the environmental monitoring facility, including fixed station, moving equipment or remote
 *                 sensing platform at which the observation is made.
 *             
 * 
 * <p>Java class for StationPlatform_Type complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="StationPlatform_Type">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="regionOfOriginOfData" type="{}RegionOfOriginOfData_Type" minOccurs="0"/>
 *         &lt;element name="territoryOfOriginOfData" type="{}TerritoryOfOriginOfData_Type" minOccurs="0"/>
 *         &lt;element name="stationPlatformName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="stationPlatformType" type="{}StationPlatformType_Type"/>
 *         &lt;element name="stationPlatformModel" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="stationPlatformUniqueIdentifier" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="geospatialLocation" type="{}GeospatialLocation_Type"/>
 *         &lt;element name="dataCommunicationMethod" type="{}DataCommunicationMethod_Type" minOccurs="0"/>
 *         &lt;element name="stationStatus" type="{}StationStatus_Type"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "StationPlatform_Type", propOrder = {
    "regionOfOriginOfData",
    "territoryOfOriginOfData",
    "stationPlatformName",
    "stationPlatformType",
    "stationPlatformModel",
    "stationPlatformUniqueIdentifier",
    "geospatialLocation",
    "dataCommunicationMethod",
    "stationStatus"
})
public class StationPlatformType  implements Serializable {

    protected RegionOfOriginOfDataType regionOfOriginOfData;
    protected TerritoryOfOriginOfDataType territoryOfOriginOfData;
    @XmlElement(required = true)
    protected String stationPlatformName;
    @XmlElement(required = true)
    protected StationPlatformTypeType stationPlatformType;
    @XmlElement(required = true)
    protected String stationPlatformModel;
    @XmlElement(required = true)
    protected String stationPlatformUniqueIdentifier;
    @XmlElement(required = true)
    protected GeospatialLocationType geospatialLocation;
    protected DataCommunicationMethodType dataCommunicationMethod;
    @XmlElement(required = true)
    protected StationStatusType stationStatus;

    /**
     * Gets the value of the regionOfOriginOfData property.
     * 
     * @return
     *     possible object is
     *     {@link RegionOfOriginOfDataType }
     *     
     */
    public RegionOfOriginOfDataType getRegionOfOriginOfData() {
        return regionOfOriginOfData;
    }

    /**
     * Sets the value of the regionOfOriginOfData property.
     * 
     * @param value
     *     allowed object is
     *     {@link RegionOfOriginOfDataType }
     *     
     */
    public void setRegionOfOriginOfData(RegionOfOriginOfDataType value) {
        this.regionOfOriginOfData = value;
    }

    /**
     * Gets the value of the territoryOfOriginOfData property.
     * 
     * @return
     *     possible object is
     *     {@link TerritoryOfOriginOfDataType }
     *     
     */
    public TerritoryOfOriginOfDataType getTerritoryOfOriginOfData() {
        return territoryOfOriginOfData;
    }

    /**
     * Sets the value of the territoryOfOriginOfData property.
     * 
     * @param value
     *     allowed object is
     *     {@link TerritoryOfOriginOfDataType }
     *     
     */
    public void setTerritoryOfOriginOfData(TerritoryOfOriginOfDataType value) {
        this.territoryOfOriginOfData = value;
    }

    /**
     * Gets the value of the stationPlatformName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStationPlatformName() {
        return stationPlatformName;
    }

    /**
     * Sets the value of the stationPlatformName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStationPlatformName(String value) {
        this.stationPlatformName = value;
    }

    /**
     * Gets the value of the stationPlatformType property.
     * 
     * @return
     *     possible object is
     *     {@link StationPlatformTypeType }
     *     
     */
    public StationPlatformTypeType getStationPlatformType() {
        return stationPlatformType;
    }

    /**
     * Sets the value of the stationPlatformType property.
     * 
     * @param value
     *     allowed object is
     *     {@link StationPlatformTypeType }
     *     
     */
    public void setStationPlatformType(StationPlatformTypeType value) {
        this.stationPlatformType = value;
    }

    /**
     * Gets the value of the stationPlatformModel property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStationPlatformModel() {
        return stationPlatformModel;
    }

    /**
     * Sets the value of the stationPlatformModel property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStationPlatformModel(String value) {
        this.stationPlatformModel = value;
    }

    /**
     * Gets the value of the stationPlatformUniqueIdentifier property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStationPlatformUniqueIdentifier() {
        return stationPlatformUniqueIdentifier;
    }

    /**
     * Sets the value of the stationPlatformUniqueIdentifier property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStationPlatformUniqueIdentifier(String value) {
        this.stationPlatformUniqueIdentifier = value;
    }

    /**
     * Gets the value of the geospatialLocation property.
     * 
     * @return
     *     possible object is
     *     {@link GeospatialLocationType }
     *     
     */
    public GeospatialLocationType getGeospatialLocation() {
        return geospatialLocation;
    }

    /**
     * Sets the value of the geospatialLocation property.
     * 
     * @param value
     *     allowed object is
     *     {@link GeospatialLocationType }
     *     
     */
    public void setGeospatialLocation(GeospatialLocationType value) {
        this.geospatialLocation = value;
    }

    /**
     * Gets the value of the dataCommunicationMethod property.
     * 
     * @return
     *     possible object is
     *     {@link DataCommunicationMethodType }
     *     
     */
    public DataCommunicationMethodType getDataCommunicationMethod() {
        return dataCommunicationMethod;
    }

    /**
     * Sets the value of the dataCommunicationMethod property.
     * 
     * @param value
     *     allowed object is
     *     {@link DataCommunicationMethodType }
     *     
     */
    public void setDataCommunicationMethod(DataCommunicationMethodType value) {
        this.dataCommunicationMethod = value;
    }

    /**
     * Gets the value of the stationStatus property.
     * 
     * @return
     *     possible object is
     *     {@link StationStatusType }
     *     
     */
    public StationStatusType getStationStatus() {
        return stationStatus;
    }

    /**
     * Sets the value of the stationStatus property.
     * 
     * @param value
     *     allowed object is
     *     {@link StationStatusType }
     *     
     */
    public void setStationStatus(StationStatusType value) {
        this.stationStatus = value;
    }

}