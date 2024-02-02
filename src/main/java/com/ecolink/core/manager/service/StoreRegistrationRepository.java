package com.ecolink.core.manager.service;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecolink.core.manager.domain.StoreRegistration;

public interface StoreRegistrationRepository extends JpaRepository<StoreRegistration, Long> {
}
