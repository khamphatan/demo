package com.demo.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.NaturalId;

@Entity
@Table(name = "X_USER", uniqueConstraints = { @UniqueConstraint(columnNames = { "REFERENCE_CODE" }), @UniqueConstraint(columnNames = { "USERNAME" }) })
public class User extends BaseModel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;

	@NotNull
	@Column(name = "REFERENCE_CODE", length = 20)
	private String referenceCode;

	@NotNull
	@Column(name = "USERNAME", length = 50)
	private String username;

	@NotNull
	@Column(name = "ENCODED_PASSWORD", length = 255)
	private String encodedPassword;

	@NotNull
	@Enumerated(EnumType.STRING)
	@NaturalId
	@Column(name = "STATUS", length = 20)
	private Enums.APPLICATION_STATUS status = Enums.APPLICATION_STATUS.ACTIVE;

	@NotNull
	@Enumerated(EnumType.STRING)
	@NaturalId
	@Column(name = "TITLE_CODE", length = 20)
	private Enums.TITLE_NAME titleCode;

	@NotNull
	@Column(name = "FIRST_NAME", length = 100)
	private String firstName;
	@NotNull
	@Column(name = "LAST_NAME", length = 100)
	private String lastName;

	@Column(name = "FIRST_NAME_ENG", length = 100)
	private String firstNameEng;
	@Column(name = "LAST_NAME_ENG", length = 100)
	private String lastNameEng;

	@Column(name = "CITIZEN_ID", length = 15)
	private String citizenId;

	@Column(name = "ADDRESS")
	private String address;

	@Column(name = "EMAIL", length = 100)
	private String email;

	@NotNull
	@Enumerated(EnumType.STRING)
	@NaturalId
	@Column(name = "GENDER_CODE", length = 20)
	private Enums.GENDER genderCode;

	@NotNull
	@Column(name = "MOBILE_NUMBER", length = 20)
	private String mobileNumber;

	@Column(name = "PHONE_NUMBER", length = 20)
	private String phoneNumber;

	@NotNull
	@Column(name = "SALARY", length = 20)
	private double salary = 0;

	@NotNull
	@Enumerated(EnumType.STRING)
	@NaturalId
	@Column(name = "MEMBER_TYPE", length = 20)
	private Enums.MEMBER_TYPE memberType;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "X_USER_ROLES", joinColumns = @JoinColumn(name = "USER_ID"), inverseJoinColumns = @JoinColumn(name = "ROLE_ID"))
	private Set<Role> roles = new HashSet<>();

	public User() {
		super();
	}

	public User(@NotNull Date createDatetime, String createUser, Date updateDatetime, String updateUser) {
		super(createDatetime, createUser, updateDatetime, updateUser);
	}

	public User(Long id, @NotNull String referenceCode, @NotNull String username, @NotNull String encodedPassword,
			@NotNull Enums.APPLICATION_STATUS status, @NotNull Enums.TITLE_NAME titleCode, @NotNull String firstName,
			@NotNull String lastName, String firstNameEng, String lastNameEng, String citizenId, String address,
			String email, @NotNull Enums.GENDER genderCode, @NotNull String mobileNumber, String phoneNumber,
			@NotNull double salary, @NotNull Enums.MEMBER_TYPE memberType, Set<Role> roles) {
		super();
		this.id = id;
		this.referenceCode = referenceCode;
		this.username = username;
		this.encodedPassword = encodedPassword;
		this.status = status;
		this.titleCode = titleCode;
		this.firstName = firstName;
		this.lastName = lastName;
		this.firstNameEng = firstNameEng;
		this.lastNameEng = lastNameEng;
		this.citizenId = citizenId;
		this.address = address;
		this.email = email;
		this.genderCode = genderCode;
		this.mobileNumber = mobileNumber;
		this.phoneNumber = phoneNumber;
		this.salary = salary;
		this.memberType = memberType;
		this.roles = roles;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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

	public String getEncodedPassword() {
		return encodedPassword;
	}

	public void setEncodedPassword(String encodedPassword) {
		this.encodedPassword = encodedPassword;
	}

	public Enums.APPLICATION_STATUS getStatus() {
		return status;
	}

	public void setStatus(Enums.APPLICATION_STATUS status) {
		this.status = status;
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

	public Enums.MEMBER_TYPE getMemberType() {
		return memberType;
	}

	public void setMemberType(Enums.MEMBER_TYPE memberType) {
		this.memberType = memberType;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
}