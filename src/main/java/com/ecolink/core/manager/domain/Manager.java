package com.ecolink.core.manager.domain;

import java.util.ArrayList;
import java.util.List;

import com.ecolink.core.manager.constant.ManagerStatus;
import com.ecolink.core.manager.constant.RegistrationStatus;
import com.ecolink.core.user.domain.User;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Manager {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	@Enumerated(EnumType.STRING)
	private ManagerStatus status;

	@NotNull
	@JoinColumn(name = "user_id")
	@OneToOne(fetch = FetchType.LAZY)
	private User user;

	@OneToMany(mappedBy = "manager")
	private List<StoreRegistration> storeRegistrations = new ArrayList<>();

	public Manager(User user) {
		this.status = ManagerStatus.PENDING;
		this.user = user;
	}

	public List<Long> getManagingStores() {
		return this.storeRegistrations.stream()
			.filter(sr -> RegistrationStatus.ACTIVE.equals(sr.getStatus()) && sr.getStore() != null)
			.map(sr -> sr.getStore().getId())
			.toList();
	}

}
