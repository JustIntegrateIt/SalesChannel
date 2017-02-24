package com.saleschannel.api.order;

import java.util.Date;

import org.springframework.data.annotation.Id;

public class AmazonOrderInvoiceDataJsonModel {

	//SC InvoiceData Id
	@Id
	private String id;
	
	//Amazon OrderItemId
	private String orderItemId;
	
	//InvoiceRequirement
	private String invoiceRequirement;
	
	//BuyerSelectedInvoiceCategory
	private String buyerSelectedInvoiceCategory;
	
	//InvoiceTitle
	private String invoiceTitle;
	
	//InvoiceInformation
	private String invoiceInformation;
	
	private String createdBy;
	
	private String updatedBy;
	
	private Date createdAt;
	
	private Date updatedAt;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getInvoiceRequirement() {
		return invoiceRequirement;
	}

	public void setInvoiceRequirement(String invoiceRequirement) {
		this.invoiceRequirement = invoiceRequirement;
	}

	public String getBuyerSelectedInvoiceCategory() {
		return buyerSelectedInvoiceCategory;
	}

	public void setBuyerSelectedInvoiceCategory(String buyerSelectedInvoiceCategory) {
		this.buyerSelectedInvoiceCategory = buyerSelectedInvoiceCategory;
	}

	public String getInvoiceTitle() {
		return invoiceTitle;
	}

	public void setInvoiceTitle(String invoiceTitle) {
		this.invoiceTitle = invoiceTitle;
	}

	public String getInvoiceInformation() {
		return invoiceInformation;
	}

	public void setInvoiceInformation(String invoiceInformation) {
		this.invoiceInformation = invoiceInformation;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
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

	public String getOrderItemId() {
		return orderItemId;
	}

	public void setOrderItemId(String orderItemId) {
		this.orderItemId = orderItemId;
	}
}
