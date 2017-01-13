package net.saleschannel.api.customer;

import java.util.Date;

import net.saleschannel.api.base.SalesChannelBaseJsonObject;

import org.springframework.data.annotation.Id;

public class CustomerJsonModel extends SalesChannelBaseJsonObject {
	
	//SC Customer Id
	@Id
	private String id;
	
	//SC Customer User Name
	private String userName;
	
	//SC Customer User Password
	private String password;
	
	//SC Auth Token
	private String authToken;
	
	//SC Customer Active Status
	private boolean isActive;
	
	//SC Customer Locale
	private String locale;

	private String createBy;
	
	private String updatedBy;
	
	private Date createdAt;
	
	private Date updatedAt;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAuthToken() {
		return authToken;
	}

	public void setAuthToken(String authToken) {
		this.authToken = authToken;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
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

	public String getLocale() {
		return locale;
	}

	public void setLocale(String locale) {
		this.locale = locale;
	}

}
