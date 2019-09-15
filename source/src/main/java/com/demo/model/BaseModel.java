package com.demo.model;

import java.util.Date;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class BaseModel {
	@NotNull
	@Column(name = "CREATE_DATETIME")
	private Date createDatetime = new Date();
	@Column(name = "CREATE_USER", length = 20)
	private String createUser = "SYSTEM";
	@Column(name = "UPDATE_DATETIME")
	private Date updateDatetime;
	@Column(name = "UPDATE_USER", length = 20)
	private String updateUser;

	public BaseModel() {
		super();
	}

	public BaseModel(@NotBlank Date createDatetime, String createUser, Date updateDatetime, String updateUser) {
		super();
		this.createDatetime = createDatetime;
		this.createUser = createUser;
		this.updateDatetime = updateDatetime;
		this.updateUser = updateUser;
	}

	public Date getCreateDatetime() {
		return createDatetime;
	}

	public void setCreateDatetime(Date createDatetime) {
		this.createDatetime = createDatetime;
	}

	public String getCreateUser() {
		return createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	public Date getUpdateDatetime() {
		return updateDatetime;
	}

	public void setUpdateDatetime(Date updateDatetime) {
		this.updateDatetime = updateDatetime;
	}

	public String getUpdateUser() {
		return updateUser;
	}

	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}

}