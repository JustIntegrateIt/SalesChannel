package com.saleschannel.api.base;

import com.saleschannel.api.utility.SalesChannelPropertyLoader;

public class SalesChannelBaseDao {
	
	public static String endecryptionKey = SalesChannelPropertyLoader.salesChannelProperties.getProperty("endecryptionKey");
	
	public static String saveImagePath = SalesChannelPropertyLoader.salesChannelProperties.getProperty("saveImagePath");
	
}
