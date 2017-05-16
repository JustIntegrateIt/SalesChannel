package com.saleschannel.api.constants.amazonmws;

import java.util.Collection;
import java.util.HashSet;

import com.amazonaws.mws.model.TypeParameter;

public enum FulfillmentByAmazonReportsType {

	/*Available Fulfillment By Amazon (FBA) Reports Type*/
	GET_AMAZON_FULFILLED_SHIPMENTS_DATA("_GET_AMAZON_FULFILLED_SHIPMENTS_DATA_"),
	GET_FLAT_FILE_ALL_ORDERS_DATA_BY_LAST_UPDATE("_GET_FLAT_FILE_ALL_ORDERS_DATA_BY_LAST_UPDATE_"),
	GET_FLAT_FILE_ALL_ORDERS_DATA_BY_ORDER_DATE("_GET_FLAT_FILE_ALL_ORDERS_DATA_BY_ORDER_DATE_"),
	GET_XML_ALL_ORDERS_DATA_BY_LAST_UPDATE("_GET_XML_ALL_ORDERS_DATA_BY_LAST_UPDATE_"),
	GET_XML_ALL_ORDERS_DATA_BY_ORDER_DATE("_GET_XML_ALL_ORDERS_DATA_BY_ORDER_DATE_"),
	GET_FBA_FULFILLMENT_CUSTOMER_SHIPMENT_SALES_DATA("_GET_FBA_FULFILLMENT_CUSTOMER_SHIPMENT_SALES_DATA_"),
	GET_FBA_FULFILLMENT_CUSTOMER_SHIPMENT_PROMOTION_DATA("_GET_FBA_FULFILLMENT_CUSTOMER_SHIPMENT_PROMOTION_DATA_"),
	GET_FBA_FULFILLMENT_CUSTOMER_TAXES_DATA("_GET_FBA_FULFILLMENT_CUSTOMER_TAXES_DATA_"),
	GET_AFN_INVENTORY_DATA("_GET_AFN_INVENTORY_DATA_"),
	GET_AFN_INVENTORY_DATA_BY_COUNTRY("_GET_AFN_INVENTORY_DATA_BY_COUNTRY_"),
	GET_FBA_FULFILLMENT_CURRENT_INVENTORY_DATA("_GET_FBA_FULFILLMENT_CURRENT_INVENTORY_DATA_"),
	GET_FBA_FULFILLMENT_MONTHLY_INVENTORY_DATA("_GET_FBA_FULFILLMENT_MONTHLY_INVENTORY_DATA_"),
	GET_FBA_FULFILLMENT_INVENTORY_RECEIPTS_DATA("_GET_FBA_FULFILLMENT_INVENTORY_RECEIPTS_DATA_"),
	GET_RESERVED_INVENTORY_DATA("_GET_RESERVED_INVENTORY_DATA_"),
	GET_FBA_FULFILLMENT_INVENTORY_SUMMARY_DATA("_GET_FBA_FULFILLMENT_INVENTORY_SUMMARY_DATA_"),
	GET_FBA_FULFILLMENT_INVENTORY_ADJUSTMENTS_DATA("_GET_FBA_FULFILLMENT_INVENTORY_ADJUSTMENTS_DATA_"),
	GET_FBA_FULFILLMENT_INVENTORY_HEALTH_DATA("_GET_FBA_FULFILLMENT_INVENTORY_HEALTH_DATA_"),
	GET_FBA_MYI_UNSUPPRESSED_INVENTORY_DATA("_GET_FBA_MYI_UNSUPPRESSED_INVENTORY_DATA_"),
	GET_FBA_MYI_ALL_INVENTORY_DATA("_GET_FBA_MYI_ALL_INVENTORY_DATA_"),
	GET_FBA_FULFILLMENT_CROSS_BORDER_INVENTORY_MOVEMENT_DATA("_GET_FBA_FULFILLMENT_CROSS_BORDER_INVENTORY_MOVEMENT_DATA_"),
	GET_RESTOCK_INVENTORY_RECOMMENDATIONS_REPORT("_GET_RESTOCK_INVENTORY_RECOMMENDATIONS_REPORT_"),
	GET_FBA_FULFILLMENT_INBOUND_NONCOMPLIANCE_DATA("_GET_FBA_FULFILLMENT_INBOUND_NONCOMPLIANCE_DATA_"),
	GET_STRANDED_INVENTORY_UI_DATA("_GET_STRANDED_INVENTORY_UI_DATA_"),
	GET_STRANDED_INVENTORY_LOADER_DATA("_GET_STRANDED_INVENTORY_LOADER_DATA_"),
	GET_FBA_INVENTORY_AGED_DATA("_GET_FBA_INVENTORY_AGED_DATA_"),
	GET_EXCESS_INVENTORY_DATA("_GET_EXCESS_INVENTORY_DATA_"),
	GET_FBA_ESTIMATED_FBA_FEES_TXT_DATA("_GET_FBA_ESTIMATED_FBA_FEES_TXT_DATA_"),
	GET_FBA_REIMBURSEMENTS_DATA("_GET_FBA_REIMBURSEMENTS_DATA_"),
	GET_FBA_FULFILLMENT_CUSTOMER_RETURNS_DATA("_GET_FBA_FULFILLMENT_CUSTOMER_RETURNS_DATA_"),
	GET_FBA_FULFILLMENT_CUSTOMER_SHIPMENT_REPLACEMENT_DATA("_GET_FBA_FULFILLMENT_CUSTOMER_SHIPMENT_REPLACEMENT_DATA_"),
	GET_FBA_RECOMMENDED_REMOVAL_DATA("_GET_FBA_RECOMMENDED_REMOVAL_DATA_"),
	GET_FBA_FULFILLMENT_REMOVAL_ORDER_DETAIL_DATA("_GET_FBA_FULFILLMENT_REMOVAL_ORDER_DETAIL_DATA_"),
	GET_FBA_FULFILLMENT_REMOVAL_SHIPMENT_DETAIL_DATA("_GET_FBA_FULFILLMENT_REMOVAL_SHIPMENT_DETAIL_DATA_"),;
	
	private String value;
	private Collection<TypeParameter> parameters = new HashSet<TypeParameter>();

	private FulfillmentByAmazonReportsType(String s) {
		this.value = s;
	}
	
	@SuppressWarnings("unused")
	private void addParameter(TypeParameter parameter) {
		parameters.add(parameter);		
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();

		sb.append(this.value);
		
		for(TypeParameter p : parameters) {
			sb.append(";").append(p);
		}
		
		return sb.toString();
	}
}