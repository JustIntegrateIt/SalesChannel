package com.saleschannel.api.order;

import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;

public class AmazonOrderItemsJsonModel {

	//SC OrderItems Id
	@Id
	private String id;
	
	//AmazonOrderId
	private String amazonOrderId;
	
	//ASIN
	private String asin;

	//OrderItemId
	private String orderItemId;
	
	//SellerSKU
	private String sellerSKU;
	
	//BuyerCustomizedInfo CustomizedURL
	private String customizedURL;
	
	//Title
	private String title;
	
	//QuantityOrdered
	private int quantityOrdered;
	
	//QuantityShipped
	private int quantityShipped;
	
	//PointsGranted only in JP.
	//PointsNumber
	private int pointsNumber;
	
	//PointsGranted CurrencyCode
	private String pointsGrantedCurrencyCode;
	
	//PointsGranted Amount
	private String pointsGrantedAmount;
	
	//ItemPrice excludes ShippingPrice and GiftWrapPrice
	//CurrencyCode
	private String itemPriceCurrencyCode;
	
	//ItemPrice Amount
	private String itemPriceAmount;
	
	//ShippingPrice CurrencyCode
	private String shippingPriceCurrencyCode;
	
	//ShippingPrice Amount
	private String shippingPriceAmount;
	
	//GiftWrapPrice CurrencyCode
	private String giftWrapPriceCurrencyCode;
	
	//GiftWrapPrice Amount
	private String giftWrapPriceAmount;
	
	//ItemTax CurrencyCode
	private String itemTaxCurrencyCode;
	
	//ItemTax Amount
	private String itemTaxAmount;
	
	//ShippingTax CurrencyCode
	private String shippingTaxCurrencyCode;
	
	//ShippingTax Amount
	private String shippingTaxAmount;
	
	//GiftWrapTax CurrencyCode
	private String giftWrapTaxCurrencyCode;
	
	//GiftWrapTax Amount
	private String giftWrapTaxAmount;

	//ShippingDiscount CurrencyCode
	private String shippingDiscountCurrencyCode;
	
	//ShippingDiscount Amount
	private String shippingDiscountAmount;
	
	//PromotionDiscount CurrencyCode
	private String promotionDiscountCurrencyCode;
	
	//PromotionDiscount Amount
	private String promotionDiscountAmount;

	//PromotionIds
	private List<String> promotionIds;
	
	//CODFee CurrencyCode
	//only in JP.
	private String codFeeCurrencyCode;
	
	//CODFee Amount
	//only in JP.
	private String codFeeAmount;

	//CODFeeDiscount CurrencyCode
	//only in JP.
	private String codFeeDiscountCurrencyCode;
	
	//CODFeeDiscount Amount
	//only in JP.
	private String codFeeDiscountAmount;

	//GiftMessageText
	private String giftMessageText;
	
	//GiftWrapLevel
	private String giftWrapLevel;
	
	//InvoiceData only in China
	private String invoiceDataId;
	
	//ConditionNote
	private String conditionNote;
	
	//ConditionId
	private String conditionId;
	
	//ConditionSubtypeId
	private String conditionSubtypeId;
	
	//ScheduledDeliveryStartDate only in JP
	private String scheduledDeliveryStartDate;
	
	//ScheduledDeliveryEndDate only in JP
	private String scheduledDeliveryEndDate;
	
	//PriceDesignation
	private String priceDesignation;
	
	private String createdBy;
	
	private String updatedBy;
	
	private Date createdAt;
	
	private Date updatedAt;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAmazonOrderId() {
		return amazonOrderId;
	}

	public void setAmazonOrderId(String amazonOrderId) {
		this.amazonOrderId = amazonOrderId;
	}

	public String getAsin() {
		return asin;
	}

	public void setAsin(String asin) {
		this.asin = asin;
	}

	public String getOrderItemId() {
		return orderItemId;
	}

	public void setOrderItemId(String orderItemId) {
		this.orderItemId = orderItemId;
	}

	public String getSellerSKU() {
		return sellerSKU;
	}

	public void setSellerSKU(String sellerSKU) {
		this.sellerSKU = sellerSKU;
	}

	public String getCustomizedURL() {
		return customizedURL;
	}

	public void setCustomizedURL(String customizedURL) {
		this.customizedURL = customizedURL;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getQuantityOrdered() {
		return quantityOrdered;
	}

	public void setQuantityOrdered(int quantityOrdered) {
		this.quantityOrdered = quantityOrdered;
	}

	public int getQuantityShipped() {
		return quantityShipped;
	}

	public void setQuantityShipped(int quantityShipped) {
		this.quantityShipped = quantityShipped;
	}

	public int getPointsNumber() {
		return pointsNumber;
	}

	public void setPointsNumber(int pointsNumber) {
		this.pointsNumber = pointsNumber;
	}

	public String getPointsGrantedCurrencyCode() {
		return pointsGrantedCurrencyCode;
	}

	public void setPointsGrantedCurrencyCode(String pointsGrantedCurrencyCode) {
		this.pointsGrantedCurrencyCode = pointsGrantedCurrencyCode;
	}

	public String getPointsGrantedAmount() {
		return pointsGrantedAmount;
	}

	public void setPointsGrantedAmount(String pointsGrantedAmount) {
		this.pointsGrantedAmount = pointsGrantedAmount;
	}

	public String getItemPriceCurrencyCode() {
		return itemPriceCurrencyCode;
	}

	public void setItemPriceCurrencyCode(String itemPriceCurrencyCode) {
		this.itemPriceCurrencyCode = itemPriceCurrencyCode;
	}

	public String getItemPriceAmount() {
		return itemPriceAmount;
	}

	public void setItemPriceAmount(String itemPriceAmount) {
		this.itemPriceAmount = itemPriceAmount;
	}

	public String getShippingPriceCurrencyCode() {
		return shippingPriceCurrencyCode;
	}

	public void setShippingPriceCurrencyCode(String shippingPriceCurrencyCode) {
		this.shippingPriceCurrencyCode = shippingPriceCurrencyCode;
	}

	public String getShippingPriceAmount() {
		return shippingPriceAmount;
	}

	public void setShippingPriceAmount(String shippingPriceAmount) {
		this.shippingPriceAmount = shippingPriceAmount;
	}

	public String getGiftWrapPriceCurrencyCode() {
		return giftWrapPriceCurrencyCode;
	}

	public void setGiftWrapPriceCurrencyCode(String giftWrapPriceCurrencyCode) {
		this.giftWrapPriceCurrencyCode = giftWrapPriceCurrencyCode;
	}

	public String getGiftWrapPriceAmount() {
		return giftWrapPriceAmount;
	}

	public void setGiftWrapPriceAmount(String giftWrapPriceAmount) {
		this.giftWrapPriceAmount = giftWrapPriceAmount;
	}

	public String getItemTaxCurrencyCode() {
		return itemTaxCurrencyCode;
	}

	public void setItemTaxCurrencyCode(String itemTaxCurrencyCode) {
		this.itemTaxCurrencyCode = itemTaxCurrencyCode;
	}

	public String getItemTaxAmount() {
		return itemTaxAmount;
	}

	public void setItemTaxAmount(String itemTaxAmount) {
		this.itemTaxAmount = itemTaxAmount;
	}

	public String getShippingTaxCurrencyCode() {
		return shippingTaxCurrencyCode;
	}

	public void setShippingTaxCurrencyCode(String shippingTaxCurrencyCode) {
		this.shippingTaxCurrencyCode = shippingTaxCurrencyCode;
	}

	public String getShippingTaxAmount() {
		return shippingTaxAmount;
	}

	public void setShippingTaxAmount(String shippingTaxAmount) {
		this.shippingTaxAmount = shippingTaxAmount;
	}

	public String getGiftWrapTaxCurrencyCode() {
		return giftWrapTaxCurrencyCode;
	}

	public void setGiftWrapTaxCurrencyCode(String giftWrapTaxCurrencyCode) {
		this.giftWrapTaxCurrencyCode = giftWrapTaxCurrencyCode;
	}

	public String getGiftWrapTaxAmount() {
		return giftWrapTaxAmount;
	}

	public void setGiftWrapTaxAmount(String giftWrapTaxAmount) {
		this.giftWrapTaxAmount = giftWrapTaxAmount;
	}

	public String getShippingDiscountCurrencyCode() {
		return shippingDiscountCurrencyCode;
	}

	public void setShippingDiscountCurrencyCode(String shippingDiscountCurrencyCode) {
		this.shippingDiscountCurrencyCode = shippingDiscountCurrencyCode;
	}

	public String getShippingDiscountAmount() {
		return shippingDiscountAmount;
	}

	public void setShippingDiscountAmount(String shippingDiscountAmount) {
		this.shippingDiscountAmount = shippingDiscountAmount;
	}

	public String getPromotionDiscountCurrencyCode() {
		return promotionDiscountCurrencyCode;
	}

	public void setPromotionDiscountCurrencyCode(
			String promotionDiscountCurrencyCode) {
		this.promotionDiscountCurrencyCode = promotionDiscountCurrencyCode;
	}

	public String getPromotionDiscountAmount() {
		return promotionDiscountAmount;
	}

	public void setPromotionDiscountAmount(String promotionDiscountAmount) {
		this.promotionDiscountAmount = promotionDiscountAmount;
	}

	public List<String> getPromotionIds() {
		return promotionIds;
	}

	public void setPromotionIds(List<String> promotionIds) {
		this.promotionIds = promotionIds;
	}

	public String getCodFeeCurrencyCode() {
		return codFeeCurrencyCode;
	}

	public void setCodFeeCurrencyCode(String codFeeCurrencyCode) {
		this.codFeeCurrencyCode = codFeeCurrencyCode;
	}

	public String getCodFeeAmount() {
		return codFeeAmount;
	}

	public void setCodFeeAmount(String codFeeAmount) {
		this.codFeeAmount = codFeeAmount;
	}

	public String getCodFeeDiscountCurrencyCode() {
		return codFeeDiscountCurrencyCode;
	}

	public void setCodFeeDiscountCurrencyCode(String codFeeDiscountCurrencyCode) {
		this.codFeeDiscountCurrencyCode = codFeeDiscountCurrencyCode;
	}

	public String getCodFeeDiscountAmount() {
		return codFeeDiscountAmount;
	}

	public void setCodFeeDiscountAmount(String codFeeDiscountAmount) {
		this.codFeeDiscountAmount = codFeeDiscountAmount;
	}

	public String getGiftMessageText() {
		return giftMessageText;
	}

	public void setGiftMessageText(String giftMessageText) {
		this.giftMessageText = giftMessageText;
	}

	public String getGiftWrapLevel() {
		return giftWrapLevel;
	}

	public void setGiftWrapLevel(String giftWrapLevel) {
		this.giftWrapLevel = giftWrapLevel;
	}

	public String getConditionNote() {
		return conditionNote;
	}

	public void setConditionNote(String conditionNote) {
		this.conditionNote = conditionNote;
	}

	public String getConditionId() {
		return conditionId;
	}

	public void setConditionId(String conditionId) {
		this.conditionId = conditionId;
	}

	public String getConditionSubtypeId() {
		return conditionSubtypeId;
	}

	public void setConditionSubtypeId(String conditionSubtypeId) {
		this.conditionSubtypeId = conditionSubtypeId;
	}

	public String getScheduledDeliveryStartDate() {
		return scheduledDeliveryStartDate;
	}

	public void setScheduledDeliveryStartDate(String scheduledDeliveryStartDate) {
		this.scheduledDeliveryStartDate = scheduledDeliveryStartDate;
	}

	public String getScheduledDeliveryEndDate() {
		return scheduledDeliveryEndDate;
	}

	public void setScheduledDeliveryEndDate(String scheduledDeliveryEndDate) {
		this.scheduledDeliveryEndDate = scheduledDeliveryEndDate;
	}

	public String getPriceDesignation() {
		return priceDesignation;
	}

	public void setPriceDesignation(String priceDesignation) {
		this.priceDesignation = priceDesignation;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public String getInvoiceDataId() {
		return invoiceDataId;
	}

	public void setInvoiceDataId(String invoiceDataId) {
		this.invoiceDataId = invoiceDataId;
	}
}
