package com.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.NaturalId;

@Entity
@Table(name = "X_ROLES")
public class Role {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;

	@NotNull
	@Enumerated(EnumType.STRING)
	@NaturalId
	@Column(name = "NAME", length = 50)
	private Enums.ROLE name = Enums.ROLE.ROLE_USER;

	public Role() {
		super();
	}

	public Role(Long id, Enums.ROLE name) {
		super();
		this.id = id;
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Enums.ROLE getName() {
		return name;
	}

	public void setName(Enums.ROLE name) {
		this.name = name;
	}
}