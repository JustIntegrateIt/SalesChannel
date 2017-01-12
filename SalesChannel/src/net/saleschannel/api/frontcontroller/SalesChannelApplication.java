package net.saleschannel.api.frontcontroller;

import net.saleschannel.api.utility.SalesChannelPropertyLoader;

import org.restlet.Application;

public class SalesChannelApplication extends Application {

	public SalesChannelApplication() {
		SalesChannelPropertyLoader.loadMarketPlaceErrorProperty();
		SalesChannelPropertyLoader.loadMarketPlaceProperty();
	}
}
