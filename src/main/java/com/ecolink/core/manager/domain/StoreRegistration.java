package com.ecolink.core.manager.domain;

import com.ecolink.core.common.constant.Address;
import com.ecolink.core.manager.constant.RegistrationStatus;
import com.ecolink.core.manager.dto.request.StoreRegistrationRequest;
import com.ecolink.core.store.domain.Store;

import jakarta.annotation.Nullable;
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
import jakarta.persistence.OneToOne;
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
	private String contact;

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

	@Nullable
	@OneToOne(mappedBy = "storeRegistration")
	private Store store;

	public StoreRegistration(StoreRegistrationRequest request, Manager manager) {
		this.name = request.name();
		this.businessNumber = request.businessNumber();
		this.contact = request.contact();
		this.representative = request.representative();
		this.address = request.address();
		this.manager = manager;
		this.status = RegistrationStatus.PENDING;
	}
}
