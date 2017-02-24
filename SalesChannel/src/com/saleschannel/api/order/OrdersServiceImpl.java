package com.saleschannel.api.order;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.amazonservices.mws.orders._2013_09_01.model.Address;
import com.amazonservices.mws.orders._2013_09_01.model.GetOrderResponse;
import com.amazonservices.mws.orders._2013_09_01.model.ListOrderItemsResponse;
import com.amazonservices.mws.orders._2013_09_01.model.Order;
import com.amazonservices.mws.orders._2013_09_01.model.OrderItem;
import com.amazonservices.mws.orders._2013_09_01.model.PaymentExecutionDetailItem;

public class OrdersServiceImpl implements OrdersService {

	private static final Logger LOGGERS = Logger.getLogger(OrdersServiceImpl.class);
	
	private OrdersDaoImpl ordersDao;

	public OrdersDaoImpl getOrdersDao() {
		return ordersDao;
	}

	public void setOrdersDao(OrdersDaoImpl ordersDao) {
		this.ordersDao = ordersDao;
	}
	
	public boolean insertAmazonOrders(GetOrderResponse orderResponse) {
		boolean status = false;
		try {
			if(orderResponse != null) {
				if(orderResponse.getGetOrderResult() != null) {
					if(orderResponse.getGetOrderResult().getOrders() != null && orderResponse.getGetOrderResult().getOrders().size() > 0) {
						for(Order order : orderResponse.getGetOrderResult().getOrders()) {
							AmazonOrdersJsonModel amazonOrdersJsonModel = new AmazonOrdersJsonModel();
							amazonOrdersJsonModel.setAmazonOrderId(order.getAmazonOrderId());
							amazonOrdersJsonModel.setSellerOrderId(order.getSellerOrderId());
							amazonOrdersJsonModel.setPurchaseDate(order.getPurchaseDate());
							amazonOrdersJsonModel.setLastUpdateDate(order.getLastUpdateDate());
							amazonOrdersJsonModel.setOrderStatus(order.getOrderStatus());
							amazonOrdersJsonModel.setFulfillmentChannel(order.getFulfillmentChannel());
							amazonOrdersJsonModel.setSalesChannel(order.getSalesChannel());
							amazonOrdersJsonModel.setOrderChannel(order.getOrderChannel());
							amazonOrdersJsonModel.setShipmentServiceLevelCategory(order.getShipServiceLevel());
							/*insert Amazon Order Shipping Address */
							if(order.getShippingAddress() != null) {
								Address address = order.getShippingAddress();
								AmazonOrderShippingAddressJsonModel shippingAddress = new AmazonOrderShippingAddressJsonModel();
								shippingAddress.setAddressLine1(address.getAddressLine1());
								shippingAddress.setAddressLine2(address.getAddressLine2());
								shippingAddress.setAddressLine3(address.getAddressLine3());
								shippingAddress.setAmazonOrderId(order.getAmazonOrderId());
								shippingAddress.setCity(address.getCity());
								shippingAddress.setCountryCode(address.getCountryCode());
								shippingAddress.setCounty(address.getCounty());
								shippingAddress.setCreatedAt(new Date());
								shippingAddress.setDistrict(address.getDistrict());
								shippingAddress.setName(address.getName());
								shippingAddress.setPhone(address.getPhone());
								shippingAddress.setPostalCode(address.getPostalCode());
								shippingAddress.setStateOrRegion(address.getStateOrRegion());
								if(shippingAddress != null) {
									AmazonOrderShippingAddressJsonModel orderAddress = null;
									if(ordersDao.insertShippingAddress(shippingAddress)) {
										orderAddress = ordersDao.getAmazonOrderShippingAddressByVariable(
												"amazonOrderId", order.getAmazonOrderId());
									}
									if(orderAddress != null) {
										amazonOrdersJsonModel.setShippingAddressId(orderAddress.getId());
									}
								}
							}
							if(order.getOrderTotal() != null) {
								amazonOrdersJsonModel.setOrderTotalAmount(order.getOrderTotal().getAmount());
								amazonOrdersJsonModel.setOrderTotalCurrencyCode(order.getOrderTotal().getCurrencyCode());
							}
							amazonOrdersJsonModel.setNumberOfItemsShipped(order.getNumberOfItemsShipped());
							amazonOrdersJsonModel.setNumberOfItemsUnshipped(order.getNumberOfItemsUnshipped());
							/*insert Amazon Order Payment Execution Detail */
							if(order.getPaymentExecutionDetail() != null && order.getPaymentExecutionDetail().size() > 0) {
								for(PaymentExecutionDetailItem paymentExecutionDetailItem : order.getPaymentExecutionDetail()) {
									AmazonOrderPaymentExeDetailJsonModel paymentExeDetail = new AmazonOrderPaymentExeDetailJsonModel();
									paymentExeDetail.setAmazonOrderId(order.getAmazonOrderId());
									if(paymentExecutionDetailItem.getPaymentMethod() != null) {
										paymentExeDetail.setAmount(paymentExecutionDetailItem.getPayment().getAmount());
										paymentExeDetail.setCurrencyCode(paymentExecutionDetailItem.getPayment().getCurrencyCode());
									}
									paymentExeDetail.setPaymentMethod(paymentExecutionDetailItem.getPaymentMethod());
									paymentExeDetail.setCreatedAt(new Date());
									ordersDao.insertAmazonPaymentExeDetail(paymentExeDetail);
								}
								amazonOrdersJsonModel.setPaymentExecutionDetailAvail(true);
							}
							amazonOrdersJsonModel.setPaymentMethod(order.getPaymentMethod());
							amazonOrdersJsonModel.setMarketplaceId(order.getMarketplaceId());
							amazonOrdersJsonModel.setBuyerName(order.getBuyerName());
							amazonOrdersJsonModel.setBuyerEmail(order.getBuyerEmail());
							amazonOrdersJsonModel.setShipmentServiceLevelCategory(order.getShipmentServiceLevelCategory());
							amazonOrdersJsonModel.setShippedByAmazonTFM(order.getShippedByAmazonTFM());
							amazonOrdersJsonModel.setTfmShipmentStatus(order.getTFMShipmentStatus());
							amazonOrdersJsonModel.setCbaDisplayableShippingLabel(order.getCbaDisplayableShippingLabel());
							amazonOrdersJsonModel.setOrderType(order.getOrderType());
							amazonOrdersJsonModel.setEarliestShipDate(order.getEarliestShipDate());
							amazonOrdersJsonModel.setLatestShipDate(order.getLatestShipDate());
							amazonOrdersJsonModel.setEarliestDeliveryDate(order.getEarliestDeliveryDate());
							amazonOrdersJsonModel.setLatestDeliveryDate(order.getLatestDeliveryDate());
							amazonOrdersJsonModel.setIsBusinessOrder(order.isIsBusinessOrder());
							amazonOrdersJsonModel.setPurchaseOrderNumber(order.getPurchaseOrderNumber());
							amazonOrdersJsonModel.setIsPrime(order.getIsPrime());
							amazonOrdersJsonModel.setIsPremiumOrder(order.getIsPremiumOrder());
							amazonOrdersJsonModel.setCreatedAt(new Date());
							/*insert Amazon Order*/
							status = insertAmazonOrder(amazonOrdersJsonModel);
							if(!status) {
								LOGGERS.info("Amazon Order "+order.getAmazonOrderId()+" not inserted in DB");
							}
						}
					}
				}
			}
		} catch (Exception e) {
			LOGGERS.error("error occured while insert AmazonOrders");
			e.printStackTrace();
		}
		return status;
	}
	
	public boolean insertAmazonOrder(AmazonOrdersJsonModel amazonOrdersJsonObject) {
		boolean status = false;
		try {
			status = ordersDao.insertOrder(amazonOrdersJsonObject);
		} catch(Exception e) {
			LOGGERS.error("error occured while insertAmazonOrder");
			e.printStackTrace();
		}
		return status;
	}
	
	public boolean updateAmazonOrder(AmazonOrdersJsonModel amazonOrdersJsonObject) {
		boolean status = false;
		try {
			status = ordersDao.updateOrder(amazonOrdersJsonObject);
		} catch(Exception e) {
			LOGGERS.error("error occured while updateOrder");
			e.printStackTrace();
		}
		return status;
	}
	
	public OrdersJsonObject getAmazonOrderByVariable(String variableName, String variableValue) {
		OrdersJsonObject ordersJsonObject = null;
		try {
			AmazonOrdersJsonModel amazonOrdersJsonObject = ordersDao.getAmazonOrderByVariable(variableName, variableValue);
			amazonOrdersJsonObject.getAmazonOrderId();
		} catch(Exception e) {
			LOGGERS.error("error occured while get AmazonOrderByVariable");
			e.printStackTrace();
		}
		return ordersJsonObject;
	}

	public boolean insertAmazonOrderItems(ListOrderItemsResponse orderItemsResponse) {
		boolean status = false;
		try {
			if(orderItemsResponse != null && orderItemsResponse.getListOrderItemsResult() != null) {
				if(orderItemsResponse.getListOrderItemsResult().getOrderItems() != null 
						&& orderItemsResponse.getListOrderItemsResult().getOrderItems().size() > 0) {
					for(OrderItem orderItem : orderItemsResponse.getListOrderItemsResult().getOrderItems()) {
						AmazonOrderItemsJsonModel amazonOrderItems = new AmazonOrderItemsJsonModel();
						amazonOrderItems.setAmazonOrderId(orderItemsResponse.getListOrderItemsResult().getAmazonOrderId());
						amazonOrderItems.setAsin(orderItem.getASIN());
						amazonOrderItems.setOrderItemId(orderItem.getOrderItemId());
						amazonOrderItems.setSellerSKU(orderItem.getSellerSKU());
						if(orderItem.getBuyerCustomizedInfo() != null)
							amazonOrderItems.setCustomizedURL(orderItem.getBuyerCustomizedInfo().getCustomizedURL());
						amazonOrderItems.setTitle(orderItem.getTitle());
						amazonOrderItems.setQuantityOrdered(orderItem.getQuantityOrdered());
						amazonOrderItems.setQuantityShipped(orderItem.getQuantityShipped());
						if(orderItem.getPointsGranted() != null) {
							amazonOrderItems.setPointsNumber(orderItem.getPointsGranted().getPointsNumber());
							if(orderItem.getPointsGranted().getPointsMonetaryValue() != null) {
								amazonOrderItems.setPointsGrantedAmount(orderItem.getPointsGranted().getPointsMonetaryValue().getAmount());
								amazonOrderItems.setPointsGrantedCurrencyCode(orderItem.getPointsGranted().getPointsMonetaryValue().getCurrencyCode());
							}
						}
						if(orderItem.getItemPrice() != null) {
							amazonOrderItems.setItemPriceCurrencyCode(orderItem.getItemPrice().getCurrencyCode());
							amazonOrderItems.setItemPriceAmount(orderItem.getItemPrice().getAmount());
						}
						if(orderItem.getShippingPrice() != null) {
							amazonOrderItems.setShippingPriceCurrencyCode(orderItem.getShippingPrice().getCurrencyCode());
							amazonOrderItems.setShippingPriceAmount(orderItem.getShippingPrice().getAmount());
						}
						if(orderItem.getGiftWrapPrice() != null) {
							amazonOrderItems.setGiftWrapPriceCurrencyCode(orderItem.getGiftWrapPrice().getCurrencyCode());
							amazonOrderItems.setGiftWrapPriceAmount(orderItem.getGiftWrapPrice().getAmount());
						}
						if(orderItem.getItemTax() != null) {
							amazonOrderItems.setItemTaxCurrencyCode(orderItem.getItemTax().getCurrencyCode());
							amazonOrderItems.setItemTaxAmount(orderItem.getItemTax().getAmount());
						}
						if(orderItem.getShippingTax() != null) {
							amazonOrderItems.setShippingTaxCurrencyCode(orderItem.getShippingTax().getCurrencyCode());
							amazonOrderItems.setShippingTaxAmount(orderItem.getShippingTax().getAmount());
						}
						if(orderItem.getGiftWrapTax() != null) {
							amazonOrderItems.setGiftWrapTaxCurrencyCode(orderItem.getGiftWrapTax().getCurrencyCode());
							amazonOrderItems.setGiftWrapTaxAmount(orderItem.getGiftWrapTax().getAmount());
						}
						if(orderItem.getShippingDiscount() != null) {
							amazonOrderItems.setShippingDiscountCurrencyCode(orderItem.getShippingDiscount().getCurrencyCode());
							amazonOrderItems.setShippingDiscountAmount(orderItem.getShippingDiscount().getAmount());
						}
						if(orderItem.getPromotionDiscount() != null) {
							amazonOrderItems.setPromotionDiscountCurrencyCode(orderItem.getPromotionDiscount().getCurrencyCode());
							amazonOrderItems.setPromotionDiscountAmount(orderItem.getPromotionDiscount().getAmount());
						}
						amazonOrderItems.setPromotionIds(orderItem.getPromotionIds());
						if(orderItem.getCODFee() != null) {
							amazonOrderItems.setCodFeeCurrencyCode(orderItem.getCODFee().getCurrencyCode());
							amazonOrderItems.setCodFeeAmount(orderItem.getCODFee().getAmount());
						}
						if(orderItem.getCODFeeDiscount() != null) {
							amazonOrderItems.setCodFeeDiscountCurrencyCode(orderItem.getCODFeeDiscount().getCurrencyCode());
							amazonOrderItems.setCodFeeDiscountAmount(orderItem.getCODFeeDiscount().getAmount());
						}
						amazonOrderItems.setGiftMessageText(orderItem.getGiftMessageText());
						amazonOrderItems.setGiftWrapLevel(orderItem.getGiftWrapLevel());
						/*insert Amazon Order Item Invoice Data */
						if(orderItem.getInvoiceData() != null) {
							AmazonOrderInvoiceDataJsonModel amazonOrderInvoiceData = new AmazonOrderInvoiceDataJsonModel();
							amazonOrderInvoiceData.setOrderItemId(orderItem.getOrderItemId());
							amazonOrderInvoiceData.setInvoiceRequirement(orderItem.getInvoiceData().getInvoiceRequirement());
							amazonOrderInvoiceData.setBuyerSelectedInvoiceCategory(orderItem.getInvoiceData().getBuyerSelectedInvoiceCategory());
							amazonOrderInvoiceData.setInvoiceTitle(orderItem.getInvoiceData().getInvoiceTitle());
							amazonOrderInvoiceData.setInvoiceInformation(orderItem.getInvoiceData().getInvoiceInformation());
							String invoiceDateId = ordersDao.insertAmazonOrderInvoiceData(amazonOrderInvoiceData);
							amazonOrderItems.setInvoiceDataId(invoiceDateId);
						}
						amazonOrderItems.setConditionNote(orderItem.getConditionNote());
						amazonOrderItems.setConditionId(orderItem.getConditionId());
						amazonOrderItems.setConditionSubtypeId(orderItem.getConditionSubtypeId());
						amazonOrderItems.setScheduledDeliveryStartDate(orderItem.getScheduledDeliveryStartDate());
						amazonOrderItems.setScheduledDeliveryEndDate(orderItem.getScheduledDeliveryEndDate());
						amazonOrderItems.setPriceDesignation(orderItem.getPriceDesignation());
						amazonOrderItems.setCreatedAt(new Date());
						amazonOrderItems.setCreatedBy(null);
						/*insert Amazon Order Item*/
						boolean isInserted = ordersDao.insertAmazonOrderItem(amazonOrderItems);
						if(!isInserted) {
							LOGGERS.info("Amazon Order Item "+orderItem.getOrderItemId()+" not inserted in DB");
						}
					}
				}
			}
		} catch(Exception e) {
			LOGGERS.error("error occured while insert AmazonOrderItems");
			e.printStackTrace();
		}
		return status;
	}
	
	public boolean insertAmazonOrderItems(AmazonOrderItemsJsonModel amazonOrderItem) {
		boolean status = false;
		try {
			
		} catch(Exception e) {
			LOGGERS.error("error occured while insert AmazonOrderItems");
			e.printStackTrace();
		}
		return status;
	}
	
	public boolean updateAmazonOrderItems(AmazonOrderItemsJsonModel amazonOrderItem)  {
		boolean status = false;
		try {
			
		} catch(Exception e) {
			LOGGERS.error("error occured while update AmazonOrderItems");
			e.printStackTrace();
		}
		return status;
	}
	
	public List<AmazonOrderItemsJsonModel> getAmazonOrderItemsByVariable(String variableName, String variableValue)  {
		try {
			
		} catch(Exception e) {
			LOGGERS.error("error occured while get AmazonOrderItemsByVariable");
			e.printStackTrace();
		}
		return null;
	}

}
