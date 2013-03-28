//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2013.03.28 at 02:10:13 PM CET 
//


package org.fiteagle.interactors.sfa.rspec;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAnyAttribute;
import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlElementRefs;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.namespace.QName;
import org.w3c.dom.Element;


/**
 * <p>Java class for LinkContents complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="LinkContents">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;choice maxOccurs="unbounded" minOccurs="0">
 *         &lt;choice>
 *           &lt;group ref="{http://www.geni.net/resources/rspec/3}AnyExtension"/>
 *           &lt;element ref="{http://www.geni.net/resources/rspec/3}property"/>
 *           &lt;element ref="{http://www.geni.net/resources/rspec/3}link_type"/>
 *           &lt;element ref="{http://www.geni.net/resources/rspec/3}interface_ref"/>
 *         &lt;/choice>
 *         &lt;element ref="{http://www.geni.net/resources/rspec/3}component_manager"/>
 *       &lt;/choice>
 *       &lt;attGroup ref="{http://www.geni.net/resources/rspec/3}AnyExtension"/>
 *       &lt;attribute name="component_id" use="required" type="{http://www.w3.org/2001/XMLSchema}anySimpleType" />
 *       &lt;attribute name="component_name" type="{http://www.w3.org/2001/XMLSchema}anySimpleType" />
 *       &lt;anyAttribute processContents='lax' namespace='##other'/>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "LinkContents", propOrder = {
    "anyOrPropertyOrLinkType"
})
public class LinkContents {

    @XmlElementRefs({
        @XmlElementRef(name = "component_manager", namespace = "http://www.geni.net/resources/rspec/3", type = ComponentManager.class, required = false),
        @XmlElementRef(name = "interface_ref", namespace = "http://www.geni.net/resources/rspec/3", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "link_type", namespace = "http://www.geni.net/resources/rspec/3", type = LinkType.class, required = false),
        @XmlElementRef(name = "property", namespace = "http://www.geni.net/resources/rspec/3", type = JAXBElement.class, required = false)
    })
    @XmlAnyElement(lax = true)
    protected List<Object> anyOrPropertyOrLinkType;
    @XmlAttribute(name = "component_id", required = true)
    @XmlSchemaType(name = "anySimpleType")
    protected String componentId;
    @XmlAttribute(name = "component_name")
    @XmlSchemaType(name = "anySimpleType")
    protected String componentName;
    @XmlAnyAttribute
    private Map<QName, String> otherAttributes = new HashMap<QName, String>();

    /**
     * Gets the value of the anyOrPropertyOrLinkType property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the anyOrPropertyOrLinkType property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAnyOrPropertyOrLinkType().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Object }
     * {@link Element }
     * {@link ComponentManager }
     * {@link JAXBElement }{@code <}{@link InterfaceRefContents }{@code >}
     * {@link LinkType }
     * {@link JAXBElement }{@code <}{@link LinkPropertyContents }{@code >}
     * 
     * 
     */
    public List<Object> getAnyOrPropertyOrLinkType() {
        if (anyOrPropertyOrLinkType == null) {
            anyOrPropertyOrLinkType = new ArrayList<Object>();
        }
        return this.anyOrPropertyOrLinkType;
    }

    /**
     * Gets the value of the componentId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getComponentId() {
        return componentId;
    }

    /**
     * Sets the value of the componentId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setComponentId(String value) {
        this.componentId = value;
    }

    /**
     * Gets the value of the componentName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getComponentName() {
        return componentName;
    }

    /**
     * Sets the value of the componentName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setComponentName(String value) {
        this.componentName = value;
    }

    /**
     * Gets a map that contains attributes that aren't bound to any typed property on this class.
     * 
     * <p>
     * the map is keyed by the name of the attribute and 
     * the value is the string value of the attribute.
     * 
     * the map returned by this method is live, and you can add new attribute
     * by updating the map directly. Because of this design, there's no setter.
     * 
     * 
     * @return
     *     always non-null
     */
    public Map<QName, String> getOtherAttributes() {
        return otherAttributes;
    }

}
