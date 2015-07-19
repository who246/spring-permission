package com.test.entitiy;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.demo.entity.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
 
@Entity
@Table(name="user")
@NamedQuery(name = "UserEntity.findByUserName1",query = "select o from UserEntity o where o.userName = ?1")
public class UserEntity extends BaseEntity {
	//用户名
    @NotNull(message = "{username.not.empty}")
	private String userName;
	
	//性别 0: 女 1: 男 2:其他
	private int sex;
	
	//电话
	private String tel;
	
	//密码
	@JsonIgnore
	private String password;
	
	//地址信息
	@JsonIgnore
	@OneToMany(mappedBy="user",fetch = FetchType.LAZY,cascade={CascadeType.ALL})
	private List<AddressEntity> addresses = new LinkedList<AddressEntity>();

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public int getSex() {
		return sex;
	}

	public void setSex(int sex) {
		this.sex = sex;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
 
	public List<AddressEntity> getAddresses() {
		return addresses;
	}
 
	public void setAddresses(List<AddressEntity> addresses) {
		this.addresses = addresses;
	}	
	 
 
}
