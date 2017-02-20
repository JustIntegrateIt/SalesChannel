package com.saleschannel.api.order;

import java.util.Date;

import org.springframework.data.annotation.Id;

public class OrdersJsonModel {

	//SC Orders Id
	@Id
	private String id;
	
	//AmazonOrderId
	private String amazonOrderId;
	
	//SellerOrderId
	private String sellerOrderId;
	
	//SC Customer Id
	private String customerId;
		
	//PurchaseDate
	private Date purchaseDate;
	
	//LastUpdateDate
	private Date lastUpdateDate;
	
	//OrderStatus
	private String orderStatus;

	//FulfillmentChannel by Amazon (AFN) or by the seller (MFN).
	private String fulfillmentChannel;
	
	//SalesChannel
	private String salesChannel;
	
	//OrderChannel
	private String orderChannel;
	//ShipServiceLevel
	
	private String shipServiceLevel;	
	
	//Order ShippingAddress Id
	private String shippingAddressId;
	
	//OrderTotal CurrencyCode
	private String orderTotalCurrencyCode;
	
	//OrderTotal Amount
	private String orderTotalAmount;
	
	//NumberOfItemsShipped
	private int numberOfItemsShipped;

	//NumberOfItemsUnshipped
	private int numberOfItemsUnshipped;
	
	//PaymentExecutionDetail Id
	//Returned only for COD orders. Available only in CN and JP.
	private String paymentExecutionDetailId;
	
	//PaymentMethod
	private String paymentMethod;
	
	//MarketplaceId
	private String marketplaceId;
	
	//BuyerName
	private String buyerName;
	
	//BuyerEmail
	private String buyerEmail;
	
	//ShipmentServiceLevelCategory
	private String shipmentServiceLevelCategory;
	
	//ShippedByAmazonTFM Amazon Transportation for Merchants (only in China). 
	private boolean shippedByAmazonTFM;
	
	//TFMShipmentStatus (only in China).
	private String tfmShipmentStatus;
	
	//CbaDisplayableShippingLabel only to sellers in the US, UK, DE.
	private String cbaDisplayableShippingLabel;
	
	//OrderType
	private String orderType;
	
	//EarliestShipDate
	//Returned only for Merchant Fulfillment Network (MFN) orders.
	private Date earliestShipDate;
	
	//LatestShipDate
	//Returned for MFN and Amazon Fulfillment Network (AFN) orders.
	private Date latestShipDate;
	
	//EarliestDeliveryDate
	/*Returned only for MFN orders that do not 
	 *have a PendingAvailability, Pending, or Canceled status.*/
	private Date earliestDeliveryDate; 
	
	//LatestDeliveryDate
	/*Returned only for MFN orders that do not 
	 *have a PendingAvailability, Pending, or Canceled status.*/
	private Date latestDeliveryDate;
	
	//IsBusinessOrder
	private boolean isBusinessOrder;
	
	//PurchaseOrderNumber
	/*Returned only for orders where 
	 *the buyer entered a PO number at checkout.*/
	private String purchaseOrderNumber;
	
	//IsPrime
	private boolean isPrime;
	
	//IsPremiumOrder
	private boolean isPremiumOrder;
	
	private String createdBy;
	
	private String updatedBy;
	
	private Date createdAt;
	
	private Date updatedAt;
	
	public String getAmazonOrderId() {
		return amazonOrderId;
	}

	public void setAmazonOrderId(String amazonOrderId) {
		this.amazonOrderId = amazonOrderId;
	}

	public String getSellerOrderId() {
		return sellerOrderId;
	}

	public void setSellerOrderId(String sellerOrderId) {
		this.sellerOrderId = sellerOrderId;
	}

	public Date getPurchaseDate() {
		return purchaseDate;
	}

	public void setPurchaseDate(Date purchaseDate) {
		this.purchaseDate = purchaseDate;
	}

	public Date getLastUpdateDate() {
		return lastUpdateDate;
	}

	public void setLastUpdateDate(Date lastUpdateDate) {
		this.lastUpdateDate = lastUpdateDate;
	}

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	public String getFulfillmentChannel() {
		return fulfillmentChannel;
	}

	public void setFulfillmentChannel(String fulfillmentChannel) {
		this.fulfillmentChannel = fulfillmentChannel;
	}

	public String getSalesChannel() {
		return salesChannel;
	}

	public void setSalesChannel(String salesChannel) {
		this.salesChannel = salesChannel;
	}

	public String getOrderChannel() {
		return orderChannel;
	}

	public void setOrderChannel(String orderChannel) {
		this.orderChannel = orderChannel;
	}

	public String getShipServiceLevel() {
		return shipServiceLevel;
	}

	public void setShipServiceLevel(String shipServiceLevel) {
		this.shipServiceLevel = shipServiceLevel;
	}

	public String getShippingAddressId() {
		return shippingAddressId;
	}

	public void setShippingAddressId(String shippingAddressId) {
		this.shippingAddressId = shippingAddressId;
	}

	public String getOrderTotalCurrencyCode() {
		return orderTotalCurrencyCode;
	}

	public void setOrderTotalCurrencyCode(String orderTotalCurrencyCode) {
		this.orderTotalCurrencyCode = orderTotalCurrencyCode;
	}

	public String getOrderTotalAmount() {
		return orderTotalAmount;
	}

	public void setOrderTotalAmount(String orderTotalAmount) {
		this.orderTotalAmount = orderTotalAmount;
	}

	public int getNumberOfItemsShipped() {
		return numberOfItemsShipped;
	}

	public void setNumberOfItemsShipped(int numberOfItemsShipped) {
		this.numberOfItemsShipped = numberOfItemsShipped;
	}

	public int getNumberOfItemsUnshipped() {
		return numberOfItemsUnshipped;
	}

	public void setNumberOfItemsUnshipped(int numberOfItemsUnshipped) {
		this.numberOfItemsUnshipped = numberOfItemsUnshipped;
	}

	public String getPaymentExecutionDetailId() {
		return paymentExecutionDetailId;
	}

	public void setPaymentExecutionDetailId(String paymentExecutionDetailId) {
		this.paymentExecutionDetailId = paymentExecutionDetailId;
	}

	public String getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public String getMarketplaceId() {
		return marketplaceId;
	}

	public void setMarketplaceId(String marketplaceId) {
		this.marketplaceId = marketplaceId;
	}

	public String getBuyerName() {
		return buyerName;
	}

	public void setBuyerName(String buyerName) {
		this.buyerName = buyerName;
	}

	public String getBuyerEmail() {
		return buyerEmail;
	}

	public void setBuyerEmail(String buyerEmail) {
		this.buyerEmail = buyerEmail;
	}

	public String getShipmentServiceLevelCategory() {
		return shipmentServiceLevelCategory;
	}

	public void setShipmentServiceLevelCategory(String shipmentServiceLevelCategory) {
		this.shipmentServiceLevelCategory = shipmentServiceLevelCategory;
	}

	public boolean isShippedByAmazonTFM() {
		return shippedByAmazonTFM;
	}

	public void setShippedByAmazonTFM(boolean shippedByAmazonTFM) {
		this.shippedByAmazonTFM = shippedByAmazonTFM;
	}

	public String getTfmShipmentStatus() {
		return tfmShipmentStatus;
	}

	public void setTfmShipmentStatus(String tfmShipmentStatus) {
		this.tfmShipmentStatus = tfmShipmentStatus;
	}

	public String getCbaDisplayableShippingLabel() {
		return cbaDisplayableShippingLabel;
	}

	public void setCbaDisplayableShippingLabel(String cbaDisplayableShippingLabel) {
		this.cbaDisplayableShippingLabel = cbaDisplayableShippingLabel;
	}

	public String getOrderType() {
		return orderType;
	}

	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}

	public Date getEarliestShipDate() {
		return earliestShipDate;
	}

	public void setEarliestShipDate(Date earliestShipDate) {
		this.earliestShipDate = earliestShipDate;
	}

	public Date getLatestShipDate() {
		return latestShipDate;
	}

	public void setLatestShipDate(Date latestShipDate) {
		this.latestShipDate = latestShipDate;
	}

	public Date getEarliestDeliveryDate() {
		return earliestDeliveryDate;
	}

	public void setEarliestDeliveryDate(Date earliestDeliveryDate) {
		this.earliestDeliveryDate = earliestDeliveryDate;
	}

	public Date getLatestDeliveryDate() {
		return latestDeliveryDate;
	}

	public void setLatestDeliveryDate(Date latestDeliveryDate) {
		this.latestDeliveryDate = latestDeliveryDate;
	}

	public boolean isBusinessOrder() {
		return isBusinessOrder;
	}

	public void setIsBusinessOrder(boolean isBusinessOrder) {
		this.isBusinessOrder = isBusinessOrder;
	}

	public String getPurchaseOrderNumber() {
		return purchaseOrderNumber;
	}

	public void setPurchaseOrderNumber(String purchaseOrderNumber) {
		this.purchaseOrderNumber = purchaseOrderNumber;
	}

	public boolean isPrime() {
		return isPrime;
	}

	public void setIsPrime(boolean isPrime) {
		this.isPrime = isPrime;
	}

	public boolean isPremiumOrder() {
		return isPremiumOrder;
	}

	public void setIsPremiumOrder(boolean isPremiumOrder) {
		this.isPremiumOrder = isPremiumOrder;
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

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
}
