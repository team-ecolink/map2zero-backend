package com.ecolink.core.manager.domain;

import com.ecolink.core.common.constant.Address;
import com.ecolink.core.manager.constant.RegistrationStatus;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class StoreRegistration {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	private String name;

	@NotNull
	private String businessNumber;

	@NotNull
	private String phoneNumber;

	@NotNull
	private String representative;

	@Valid
	@NotNull
	@Embedded
	private Address address;

	@NotNull
	@Enumerated(EnumType.STRING)
	private RegistrationStatus status;

	@NotNull
	@JoinColumn(name = "manager_id")
	@ManyToOne(fetch = FetchType.LAZY)
	private Manager manager;

}
