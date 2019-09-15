package com.demo.message.response;

import com.demo.model.Enums;

public class UserInfoResponse {
	private String referenceCode;

	private String username;
	private Enums.APPLICATION_STATUS status;

	private String firstName;
	private String lastName;
	private String firstNameEng;
	private String lastNameEng;
	private String citizenId;
	private String address;
	private String email;
	private String mobileNumber;
	private String phoneNumber;
	private Double salary;
	private Enums.MEMBER_TYPE memberType;

	private Enums.TITLE_NAME titleCode;
	private String titleName;
	private String titleNameEng;

	private Enums.GENDER genderCode;
	private String genderName;
	private String genderNameEng;

	public String getReferenceCode() {
		return referenceCode;
	}

	public void setReferenceCode(String referenceCode) {
		this.referenceCode = referenceCode;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Enums.APPLICATION_STATUS getStatus() {
		return status;
	}

	public void setStatus(Enums.APPLICATION_STATUS status) {
		this.status = status;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFirstNameEng() {
		return firstNameEng;
	}

	public void setFirstNameEng(String firstNameEng) {
		this.firstNameEng = firstNameEng;
	}

	public String getLastNameEng() {
		return lastNameEng;
	}

	public void setLastNameEng(String lastNameEng) {
		this.lastNameEng = lastNameEng;
	}

	public String getCitizenId() {
		return citizenId;
	}

	public void setCitizenId(String citizenId) {
		this.citizenId = citizenId;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public Double getSalary() {
		return salary;
	}

	public void setSalary(Double salary) {
		this.salary = salary;
	}

	public Enums.MEMBER_TYPE getMemberType() {
		return memberType;
	}

	public void setMemberType(Enums.MEMBER_TYPE memberType) {
		this.memberType = memberType;
	}

	public Enums.TITLE_NAME getTitleCode() {
		return titleCode;
	}

	public void setTitleCode(Enums.TITLE_NAME titleCode) {
		this.titleCode = titleCode;
	}

	public String getTitleName() {
		return titleName;
	}

	public void setTitleName(String titleName) {
		this.titleName = titleName;
	}

	public String getTitleNameEng() {
		return titleNameEng;
	}

	public void setTitleNameEng(String titleNameEng) {
		this.titleNameEng = titleNameEng;
	}

	public Enums.GENDER getGenderCode() {
		return genderCode;
	}

	public void setGenderCode(Enums.GENDER genderCode) {
		this.genderCode = genderCode;
	}

	public String getGenderName() {
		return genderName;
	}

	public void setGenderName(String genderName) {
		this.genderName = genderName;
	}

	public String getGenderNameEng() {
		return genderNameEng;
	}

	public void setGenderNameEng(String genderNameEng) {
		this.genderNameEng = genderNameEng;
	}
}
