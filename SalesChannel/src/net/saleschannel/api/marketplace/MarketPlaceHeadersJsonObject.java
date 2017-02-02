package net.saleschannel.api.marketplace;

public class MarketPlaceHeadersJsonObject {

	//SC Market Place header value Id
	private String id;
	
	//Market Place header key
	private String headerKey;
	
	//Market Place header value
	private String headerValue;

	public String getHeaderKey() {
		return headerKey;
	}

	public void setHeaderKey(String headerKey) {
		this.headerKey = headerKey;
	}

	public String getHeaderValue() {
		return headerValue;
	}

	public void setHeaderValue(String headerValue) {
		this.headerValue = headerValue;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}
