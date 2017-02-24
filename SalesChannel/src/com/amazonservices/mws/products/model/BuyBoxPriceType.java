/*******************************************************************************
 * Copyright 2009-2016 Amazon Services. All Rights Reserved.
 * Licensed under the Apache License, Version 2.0 (the "License"); 
 *
 * You may not use this file except in compliance with the License. 
 * You may obtain a copy of the License at: http://aws.amazon.com/apache2.0
 * This file is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR 
 * CONDITIONS OF ANY KIND, either express or implied. See the License for the 
 * specific language governing permissions and limitations under the License.
 *******************************************************************************
 * Buy Box Price Type
 * API Version: 2011-10-01
 * Library Version: 2016-06-01
 * Generated: Mon Jun 13 10:07:47 PDT 2016
 */
package com.amazonservices.mws.products.model;

import javax.xml.bind.annotation.*;

import com.amazonservices.mws.client.*;

/**
 * BuyBoxPriceType complex type.
 *
 * XML schema:
 *
 * <pre>
 * &lt;complexType name="BuyBoxPriceType"&gt;
 *    &lt;complexContent&gt;
 *       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *          &lt;sequence&gt;
 *             &lt;element name="LandedPrice" type="{http://mws.amazonservices.com/schema/Products/2011-10-01}MoneyType"/&gt;
 *             &lt;element name="ListingPrice" type="{http://mws.amazonservices.com/schema/Products/2011-10-01}MoneyType"/&gt;
 *             &lt;element name="Shipping" type="{http://mws.amazonservices.com/schema/Products/2011-10-01}MoneyType"/&gt;
 *             &lt;element name="Points" type="{http://mws.amazonservices.com/schema/Products/2011-10-01}Points" minOccurs="0"/&gt;
 *          &lt;/sequence&gt;
 *          &lt;attribute name="condition" use="required" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *       &lt;/restriction&gt;
 *    &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name="BuyBoxPriceType", propOrder={
    "landedPrice",
    "listingPrice",
    "shipping",
    "points"
})
@XmlRootElement(name = "BuyBoxPriceType")
public class BuyBoxPriceType extends AbstractMwsObject {

    @XmlAttribute(required=true)
    private String condition;

    @XmlElement(name="LandedPrice",required=true)
    private MoneyType landedPrice;

    @XmlElement(name="ListingPrice",required=true)
    private MoneyType listingPrice;

    @XmlElement(name="Shipping",required=true)
    private MoneyType shipping;

    @XmlElement(name="Points")
    private Points points;

    /**
     * Get the value of condition.
     *
     * @return The value of condition.
     */
    public String getCondition() {
        return condition;
    }

    /**
     * Set the value of condition.
     *
     * @param condition
     *            The new value to set.
     */
    public void setCondition(String condition) {
        this.condition = condition;
    }

    /**
     * Check to see if condition is set.
     *
     * @return true if condition is set.
     */
    public boolean isSetCondition() {
        return condition != null;
    }

    /**
     * Set the value of condition, return this.
     *
     * @param condition
     *             The new value to set.
     *
     * @return This instance.
     */
    public BuyBoxPriceType withCondition(String condition) {
        this.condition = condition;
        return this;
    }

    /**
     * Get the value of LandedPrice.
     *
     * @return The value of LandedPrice.
     */
    public MoneyType getLandedPrice() {
        return landedPrice;
    }

    /**
     * Set the value of LandedPrice.
     *
     * @param landedPrice
     *            The new value to set.
     */
    public void setLandedPrice(MoneyType landedPrice) {
        this.landedPrice = landedPrice;
    }

    /**
     * Check to see if LandedPrice is set.
     *
     * @return true if LandedPrice is set.
     */
    public boolean isSetLandedPrice() {
        return landedPrice != null;
    }

    /**
     * Set the value of LandedPrice, return this.
     *
     * @param landedPrice
     *             The new value to set.
     *
     * @return This instance.
     */
    public BuyBoxPriceType withLandedPrice(MoneyType landedPrice) {
        this.landedPrice = landedPrice;
        return this;
    }

    /**
     * Get the value of ListingPrice.
     *
     * @return The value of ListingPrice.
     */
    public MoneyType getListingPrice() {
        return listingPrice;
    }

    /**
     * Set the value of ListingPrice.
     *
     * @param listingPrice
     *            The new value to set.
     */
    public void setListingPrice(MoneyType listingPrice) {
        this.listingPrice = listingPrice;
    }

    /**
     * Check to see if ListingPrice is set.
     *
     * @return true if ListingPrice is set.
     */
    public boolean isSetListingPrice() {
        return listingPrice != null;
    }

    /**
     * Set the value of ListingPrice, return this.
     *
     * @param listingPrice
     *             The new value to set.
     *
     * @return This instance.
     */
    public BuyBoxPriceType withListingPrice(MoneyType listingPrice) {
        this.listingPrice = listingPrice;
        return this;
    }

    /**
     * Get the value of Shipping.
     *
     * @return The value of Shipping.
     */
    public MoneyType getShipping() {
        return shipping;
    }

    /**
     * Set the value of Shipping.
     *
     * @param shipping
     *            The new value to set.
     */
    public void setShipping(MoneyType shipping) {
        this.shipping = shipping;
    }

    /**
     * Check to see if Shipping is set.
     *
     * @return true if Shipping is set.
     */
    public boolean isSetShipping() {
        return shipping != null;
    }

    /**
     * Set the value of Shipping, return this.
     *
     * @param shipping
     *             The new value to set.
     *
     * @return This instance.
     */
    public BuyBoxPriceType withShipping(MoneyType shipping) {
        this.shipping = shipping;
        return this;
    }

    /**
     * Get the value of Points.
     *
     * @return The value of Points.
     */
    public Points getPoints() {
        return points;
    }

    /**
     * Set the value of Points.
     *
     * @param points
     *            The new value to set.
     */
    public void setPoints(Points points) {
        this.points = points;
    }

    /**
     * Check to see if Points is set.
     *
     * @return true if Points is set.
     */
    public boolean isSetPoints() {
        return points != null;
    }

    /**
     * Set the value of Points, return this.
     *
     * @param points
     *             The new value to set.
     *
     * @return This instance.
     */
    public BuyBoxPriceType withPoints(Points points) {
        this.points = points;
        return this;
    }

    /**
     * Read members from a MwsReader.
     *
     * @param r
     *      The reader to read from.
     */
    //@Override
    public void readFragmentFrom(MwsReader r) {
        condition = r.readAttribute("condition", String.class); 
        landedPrice = r.read("LandedPrice", MoneyType.class);
        listingPrice = r.read("ListingPrice", MoneyType.class);
        shipping = r.read("Shipping", MoneyType.class);
        points = r.read("Points", Points.class);
    }

    /**
     * Write members to a MwsWriter.
     *
     * @param w
     *      The writer to write to.
     */
    //@Override
    public void writeFragmentTo(MwsWriter w) {
        w.writeAttribute("condition",condition);
        w.write("LandedPrice", landedPrice);
        w.write("ListingPrice", listingPrice);
        w.write("Shipping", shipping);
        w.write("Points", points);
    }

    /**
     * Write tag, xmlns and members to a MwsWriter.
     *
     * @param w
     *         The Writer to write to.
     */
    //@Override
    public void writeTo(MwsWriter w) {
        w.write("http://mws.amazonservices.com/schema/Products/2011-10-01", "BuyBoxPriceType",this);
    }

    /** Value constructor. */
    public BuyBoxPriceType(String condition,MoneyType landedPrice,MoneyType listingPrice,MoneyType shipping,Points points) {
        this.condition = condition;
        this.landedPrice = landedPrice;
        this.listingPrice = listingPrice;
        this.shipping = shipping;
        this.points = points;
    }    
    

    /** Default constructor. */
    public BuyBoxPriceType() {
        super();
    }

}
