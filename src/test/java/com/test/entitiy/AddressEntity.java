package com.test.entitiy;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.beans.factory.annotation.Value;

import com.demo.entity.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="address")
 
public class AddressEntity extends BaseEntity {
	//具体地址
   
	private String address;
	//是否默认
	private boolean isDefault;
	//城市
	private String city;
	//省
	private String province;
	 
	@ManyToOne
	@JoinColumn(name="user_id" )
	private UserEntity user;
	
 
    public UserEntity getUser() {
        return user;
    }
 
    public void setUser(UserEntity user) {
        this.user = user;
    }
    
    public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public boolean isDefault() {
		return isDefault;
	}
	
	public void setDefault(boolean isDefault) {
		this.isDefault = isDefault;
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
}
