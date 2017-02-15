package com.saleschannel.api.frontcontroller;

import com.saleschannel.api.utility.SalesChannelPropertyLoader;

import org.restlet.Application;

public class SalesChannelApplication extends Application {

	public SalesChannelApplication() {
		SalesChannelPropertyLoader.loadMarketPlaceErrorProperty();
		SalesChannelPropertyLoader.loadMarketPlaceProperty();
	}
}
