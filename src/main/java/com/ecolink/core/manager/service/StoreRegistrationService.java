package com.ecolink.core.manager.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ecolink.core.manager.domain.Manager;
import com.ecolink.core.manager.domain.StoreRegistration;
import com.ecolink.core.manager.dto.request.StoreRegistrationRequest;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class StoreRegistrationService {

	private final StoreRegistrationRepository storeRegistrationRepository;

	public Long applyRegistration(StoreRegistrationRequest request, Manager manager) {
		return storeRegistrationRepository.save(new StoreRegistration(request, manager)).getId();
	}

}
