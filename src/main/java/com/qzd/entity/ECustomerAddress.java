package org.ieforex.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

import org.ieforex.utils.charEcordingFilterUtil;


/**
 * 
 * 
 * @author zero
 * @email 15638559970
 * @date 2017-08-28 17:42:24
 */
 @Table(name="e_customer_address")
public class ECustomerAddress implements Serializable {
	private static final long serialVersionUID = 1L;
	
    @Id
	@Column(name="address_id")
	private Long addressId;

    private String province;

    private String city;

    private String county;

    private String address;

    @Column(name="customer_id")
	private Long customerId;

    @Column(name="is_default")
	private String isDefault;

    private String name;

    private String telephone;
    
    

	/**
	 * 获取表字段address_id
	 */
	public Long getAddressId() {
		return addressId;
	}

	/**
	 * 设置表字段address_id
	 */
	public void setAddressId(Long addressId) {
		this.addressId = addressId;
	}
	/**
	 * 获取表字段province
	 */
	public String getProvince() {
		return province;
	}

	/**
	 * 设置表字段province
	 */
	public void setProvince(String province) {
		this.province = province;
	}
	/**
	 * 获取表字段city
	 */
	public String getCity() {
		return city;
	}

	/**
	 * 设置表字段city
	 */
	public void setCity(String city) {
		this.city = city;
	}
	/**
	 * 获取表字段county
	 */
	public String getCounty() {
		return county;
	}

	/**
	 * 设置表字段county
	 */
	public void setCounty(String county) {
		this.county = county;
	}
	/**
	 * 获取表字段address
	 */
	public String getAddress() {
		return address;
	}
	/**
	 * 获取表字段address转换
	 */
	public String getAddressView() {
		return charEcordingFilterUtil.specialCharFilter(address);
	}

	/**
	 * 设置表字段address
	 */
	public void setAddress(String address) {
		this.address = address;
	}
	/**
	 * 获取表字段customer_id
	 */
	public Long getCustomerId() {
		return customerId;
	}

	/**
	 * 设置表字段customer_id
	 */
	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}
	/**
	 * 获取表字段is_default
	 */
	public String getIsDefault() {
		return isDefault;
	}

	/**
	 * 设置表字段is_default
	 */
	public void setIsDefault(String isDefault) {
		this.isDefault = isDefault;
	}
	/**
	 * 获取表字段name
	 */
	public String getName() {
		return name;
	}

	/**
	 * 设置表字段name
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 获取表字段telephone
	 */
	public String getTelephone() {
		return telephone;
	}
	
	/**
	 * 获取表字段telephoneView
	 */
	public String getTelephoneView() {
		return telephone.replaceAll("(\\d{3})\\d{4}(\\d{4})","$1****$2");
	}

	/**
	 * 设置表字段telephone
	 */
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
}
