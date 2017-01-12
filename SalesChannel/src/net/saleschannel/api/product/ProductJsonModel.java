package net.saleschannel.api.product;

import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;

import net.saleschannel.api.base.SalesChannelBaseJsonObject;

public class ProductJsonModel extends SalesChannelBaseJsonObject {

	@Id
	private String id;
	
	private String customerId;

	private String productName;

	private String productType;
	
	private String productCategory;
	
	private String description;
	
	private Integer quantity;
	
	private Integer cost;
	
	private String skuId;
	
	private boolean isSync;
	
	private List<ProductAttributeSetModel> productAttributes;
	
	private List<ProductAccessories> productAccessories; 
	
	private String createBy;
	
	private String updatedBy;
	
	private Date createdAt;
	
	private Date updatedAt;
	
	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	
	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductType() {
		return productType;
	}

	public void setProductType(String productType) {
		this.productType = productType;
	}

	public String getProductCategory() {
		return productCategory;
	}

	public void setProductCategory(String productCategory) {
		this.productCategory = productCategory;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Integer getCost() {
		return cost;
	}

	public void setCost(Integer cost) {
		this.cost = cost;
	}

	public String getSkuId() {
		return skuId;
	}

	public void setSkuId(String skuId) {
		this.skuId = skuId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public boolean isSync() {
		return isSync;
	}

	public void setSync(boolean isSync) {
		this.isSync = isSync;
	}

	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public List<ProductAttributeSetModel> getProductAttributes() {
		return productAttributes;
	}

	public void setProductAttributes(List<ProductAttributeSetModel> productAttributes) {
		this.productAttributes = productAttributes;
	}

	public List<ProductAccessories> getProductAccessories() {
		return productAccessories;
	}

	public void setProductAccessories(List<ProductAccessories> productAccessories) {
		this.productAccessories = productAccessories;
	}
}
