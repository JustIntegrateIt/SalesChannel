package net.saleschannel.api.base;

import net.saleschannel.api.utility.SalesChannelPropertyLoader;

public class SalesChannelBaseDao {
	
	public static String endecryptionKey = SalesChannelPropertyLoader.salesChannelProperties.getProperty("endecryptionKey");
	
	public static String saveImagePath = SalesChannelPropertyLoader.salesChannelProperties.getProperty("saveImagePath");
	
}
