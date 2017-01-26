package net.saleschannel.api.customer;

import org.springframework.data.annotation.Id;

import net.saleschannel.api.base.SalesChannelBaseJsonObject;

public class CustomerJsonObject  extends SalesChannelBaseJsonObject {
	
	//SC Customer Id
	@Id
	private String id;
	
	//SC Customer First Name
	private String customerFirstName;
	
	//SC Customer Last Name
	private String customerLastName;
	
	//SC Customer UserName
	private String userName;
	
	//SC Customer Password
	private String password;
	
	//SC Customer Email
	private String email;
	
	//SC Customer Phone
	private String phone;
	
	//SC Customer Address1
	private String address1;
	
	//SC Customer Address2
	private String address2;
	
	//SC Customer City
	private String city;
	
	//SC Customer Province
	private String province;
	
	//SC Customer Province Code
	private String provinceCode;
	
	//SC Customer Country
	private String country;

	//SC Customer Locale
	private String locale;
	
	//SC Customer Active Status
	private boolean isActive;
		
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCustomerFirstName() {
		return customerFirstName;
	}

	public void setCustomerFirstName(String customerFirstName) {
		this.customerFirstName = customerFirstName;
	}

	public String getCustomerLastName() {
		return customerLastName;
	}

	public void setCustomerLastName(String customerLastName) {
		this.customerLastName = customerLastName;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress1() {
		return address1;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	public String getAddress2() {
		return address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getProvinceCode() {
		return provinceCode;
	}

	public void setProvinceCode(String provinceCode) {
		this.provinceCode = provinceCode;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getLocale() {
		return locale;
	}

	public void setLocale(String locale) {
		this.locale = locale;
	}

	public boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(boolean isActive) {
		this.isActive = isActive;
	}
		
}
