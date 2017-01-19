package net.saleschannel.api.product;

import org.springframework.data.annotation.Id;

public class ProductImage {

	//SC Product Image Id
	@Id
	private String id;
	
	//SC Product Image Name
	private String imageName;
	
	//SC Product Image Path
	private String imagePath;
	
	//SC Product Image Actual Path
	private String actualPath;
	
	//SC Product Id
	private String productId;
	
	//SC Market Place Id
	private String marketPlaceId;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getActualPath() {
		return actualPath;
	}

	public void setActualPath(String actualPath) {
		this.actualPath = actualPath;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public String getMarketPlaceId() {
		return marketPlaceId;
	}

	public void setMarketPlaceId(String marketPlaceId) {
		this.marketPlaceId = marketPlaceId;
	}
}
