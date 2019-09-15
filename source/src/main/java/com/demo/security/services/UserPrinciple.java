package com.demo.security.services;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.demo.model.Enums;
import com.demo.model.User;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class UserPrinciple implements UserDetails {
	private static final long serialVersionUID = 1L;

	private Long id;

	private String refcode;

	private String username;

	private String mobile;
	
	@JsonIgnore
	private Set<Enums.ROLE> roles = new HashSet<>();
	
	@JsonIgnore
	private Enums.APPLICATION_STATUS status;

	@JsonIgnore
	private String password;

	private Collection<? extends GrantedAuthority> authorities;

	public UserPrinciple(Long id, String refcode, String username, String mobile, String password, Enums.APPLICATION_STATUS status, Set<Enums.ROLE> roles,
			Collection<? extends GrantedAuthority> authorities) {
		this.id = id;
		this.refcode = refcode;
		this.username = username;
		this.mobile = mobile;
		this.status = status;
		this.roles = roles;
		this.password = password;
		this.authorities = authorities;
	}

	public static UserPrinciple build(User user) {
		List<GrantedAuthority> authorities = user.getRoles().stream().map(role -> new SimpleGrantedAuthority(role.getName().name())).collect(Collectors.toList());
		
		Set<Enums.ROLE> roles = new HashSet<>();
		user.getRoles().forEach(role -> {
			roles.add(role.getName());
		});

		return new UserPrinciple(user.getId(), user.getReferenceCode(), user.getUsername(),
				user.getMobileNumber(), user.getEncodedPassword(), user.getStatus(), roles, authorities);
	}

	public Long getId() {
		return id;
	}

	public String getMobile() {
		return mobile;
	}

	public String getRefcode() {
		return refcode;
	}

	public Set<Enums.ROLE> getRoles() {
		return roles;
	}

	public Enums.APPLICATION_STATUS getStatus() {
		return status;
	}

	@Override
	public String getUsername() {
		return username;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return getStatus() == Enums.APPLICATION_STATUS.ACTIVE;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		UserPrinciple user = (UserPrinciple) o;
		return Objects.equals(id, user.id);
	}
}