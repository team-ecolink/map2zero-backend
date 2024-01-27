package com.ecolink.core.user.domain;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.ecolink.core.user.constant.RoleType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Role {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	@Column(unique = true)
	@Enumerated(EnumType.STRING)
	private RoleType type;

	public Role(RoleType type) {
		this.type = type;
	}

	public String getAuthority() {
		return type.getAuthority();
	}

	public GrantedAuthority toGrantedAuthority() {
		return new SimpleGrantedAuthority(getAuthority());
	}

}
