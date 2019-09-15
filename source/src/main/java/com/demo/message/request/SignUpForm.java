package com.demo.message.request;

import java.util.Set;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.demo.model.Enums;

public class SignUpForm extends BaseRequest {
	@NotBlank
	@Size(min = 3, max = 50)
	private String username;
	
	@NotBlank
	@Size(min = 6, max = 40)
	private String password;

	
	@NotNull(message = "Please enter Title Code")
	@Enumerated(EnumType.STRING)
	private Enums.TITLE_NAME titleCode;

	@NotBlank
	@Size(min = 1, max = 100)
	private String firstName;
	@NotBlank
	@Size(min = 1, max = 100)
	private String lastName;

	@Size(max = 100)
	private String firstNameEng;
	@Size(max = 100)
	private String lastNameEng;

	@Size(max = 15)
	private String citizenId;

	@Size(max = 500)
	private String address;

	@Email
	@Size(max = 60)
	private String email;

	@NotNull(message = "Please enter Gender Code")
	@Enumerated(EnumType.STRING)
	private Enums.GENDER genderCode;

	@NotBlank
	@Size(min = 10, max = 10)
	private String mobileNumber;

	@Size(max = 20)
	private String phoneNumber;

	@NotNull(message = "Please enter Salary")
	private Double salary;
	
	private Set<String> role;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Enums.TITLE_NAME getTitleCode() {
		return titleCode;
	}

	public void setTitleCode(Enums.TITLE_NAME titleCode) {
		this.titleCode = titleCode;
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

	public Enums.GENDER getGenderCode() {
		return genderCode;
	}

	public void setGenderCode(Enums.GENDER genderCode) {
		this.genderCode = genderCode;
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

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	public Set<String> getRole() {
		return role;
	}

	public void setRole(Set<String> role) {
		this.role = role;
	}
	
}