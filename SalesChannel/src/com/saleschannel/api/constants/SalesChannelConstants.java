package com.saleschannel.api.constants;

public class SalesChannelConstants {
	
	/* constants for the http methods */
	/** The Constant GET. */
	public static final String GET = "GET";
	
	/** The Constant POST. */
	public static final String POST = "POST";
	
	/** The Constant DELETE. */
	public static final String DELETE = "DELETE";
	
	/** The Constant PUT. */
	public static final String PUT = "PUT";

	public static final String SKU_ID = "skuId";
	public static final String COST = "cost";
	public static final String QUANTITY = "quantity";
	
	/** Separator types. */
	public static final String FILE_SEPERATOR = "/";
	public static final String DOT_SEPERATOR = ".";
	public static final String NAME_SEPERATOR = "_";
	public static final String CROSS = "x";
	
	/** File types. */
	public static final String XLSX = ".xlsx";
	public static final String TXT = ".txt";
	
	/** image types. */
	public static final String JPG = "jpg";
	public static final String PNG = "png";
	public static final String JPEG = "jpeg";
	
	/** system customer Id. */
	public static final String CUSTOMER_ID = "0";
	
	/** Collection names. */
	public static final String SC_CUSTOMER = "sc_customer";
	public static final String SC_MARKETPLACE = "sc_marketplace";
	public static final String SC_MARKETPLACE_REGION = "sc_marketplace_region";
	public static final String SC_MARKETPLACE_HEADER = "sc_marketplace_header";
	public static final String SC_MARKETPLACE_CUSTOMER = "sc_marketplace_customer";
	public static final String SC_MARKETPLACE_PRODUCT = "sc_marketplace_product";
	public static final String SC_PRODUCT = "sc_product";
	public static final String SC_ATTRIBUTE = "sc_attribute";
	public static final String SC_PRODUCT_ATTRIBUTE = "sc_product_attribute";
	public static final String SC_PRODUCT_ATTRIBUTE_COMBINATION = "sc_product_attribute_combination";
	public static final String SC_PRODUCT_ACCESSORIES = "sc_product_accessories";
	public static final String SC_PRODUCT_IMAGE = "sc_product_image";
	public static final String SC_PRODUCT_CATEGORY = "sc_product_category";
	public static final String SC_PRODUCT_CATEGORY_COLUMN_PARAMETERS = "sc_product_category_column_parameters";
	public static final String SC_PRODUCT_CATEGORY_COLUMN_VALUES = "sc_product_category_column_values";
	public static final String SC_CATEGORY_COLUMN_VALID_VALUES = "sc_category_column_valid_values";
	public static final String SC_PRODUCT_CATEGORY_MAPPING = "sc_product_category_mapping";
	public static final String SC_WAREHOUSE = "sc_warehouse";
	public static final String SC_INVENTORY = "sc_inventory";
	public static final String SC_SHELF = "sc_shelf";
	public static final String SC_BIN = "sc_bin";
	public static final String SC_BIN_CONTENT = "sc_bin_content";
	public static final String SC_POSITION = "sc_position";
	public static final String SC_ORDER = "sc_order";
	public static final String SC_AMAZON_ORDER_ITEMS = "sc_amazon_order_items";
	public static final String SC_AMAZON_ORDER_INVOICE_DATA = "sc_amazon_order_invoice_data";
	public static final String SC_AMAZON_ORDER_PAYMENT_EXECUTION_DETAILS = "sc_amazon_order_payment_execution_detail";
	public static final String SC_ORDER_SHIPPING_ADDRESS = "sc_order_shipping_address"; 
	public static final String SC_AMAZON_FLAT_FILES = "sc_amazon_flat_files";
	public static final String SC_QUARTZ_JOB = "sc_quartz_job";
	public static final String SC_AMAZON_PULL_PRODUCT_CATEGORIES = "sc_amazon_pull_product_categories";
	
	/** AMZ MWS credentials names. */
	public static final String accessKeyId = "AKIAIC2MSCXCVWE3BLJQ";  
	public static final String secretAccessKey = "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"; 
	public static final String appName = "developerApp";
	public static final String appVersion = "0.1";
	public static final String marketPlaceId = "A21TJRUUN4KGV";
	public static final String merchantIdSellerId = "A44435JW4FD32";
	public static final String authToken = "amzn.mws.4ea38b7b-f563-7709-4bae-87aeaEXAMPLE";
	
	public static final String FLATFILE_SOURCE_PATH = "/home/system6/Documents/SC-AmazonMWS/FlatFiles/Request";
	public static final String FLATFILE_RESPONSE_SOURCE_PATH = "/home/system6/Documents/SC-AmazonMWS/FlatFiles/Response";

}