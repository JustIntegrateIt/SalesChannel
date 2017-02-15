package com.saleschannel.api.utility;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;

/**
 * The Class SalesChannelPropertyLoader.
 */
public class SalesChannelPropertyLoader {
	
	/** The loggers. */
	private final static Logger LOGGERS = Logger.getLogger(SalesChannelPropertyLoader.class);
	
	/** The salesChannel properties. */
	public static Properties salesChannelProperties;
	
	/** The salesChannel property stream. */
	public static InputStream salesChannelPropertyStream = SalesChannelPropertyLoader.class.getResourceAsStream("/net/saleschannel/api/config/salesChannel.properties");
	
	/** The salesChannel error properties. */
	public static Properties salesChannelErrorProperties;
	
	/** The salesChannel error property stream. */
	public static InputStream salesChannelErrorPropertyStream = SalesChannelPropertyLoader.class.getResourceAsStream("/net/saleschannel/api/config/errormessage.properties");;
	
	/**
	 * Load salesChannel property.
	 */
	public static void loadMarketPlaceProperty() {
		salesChannelProperties = new Properties();
		try {
			salesChannelProperties.load(salesChannelPropertyStream);
		} catch (final IOException e) {
			LOGGERS.error("Error in loading the salesChannel property file: " + e.getMessage());
		}
	}
	
	/**
	 * Load salesChannel error property.
	 */
	public static void loadMarketPlaceErrorProperty() {
		salesChannelErrorProperties = new Properties();
		try {
			salesChannelErrorProperties.load(salesChannelErrorPropertyStream);
		} catch (final IOException e) {
			LOGGERS.error("Error in loading the salesChannel error property file: " + e.getMessage());
		}
	}
	
}
