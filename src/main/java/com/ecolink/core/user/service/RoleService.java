package com.ecolink.core.user.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ecolink.core.user.constant.RoleType;
import com.ecolink.core.user.domain.Role;
import com.ecolink.core.user.repository.RoleRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class RoleService {

	private final RoleRepository roleRepository;

	@Transactional
	public Role getUserRole() {
		return roleRepository.findByType(RoleType.USER)
			.orElseGet(() -> roleRepository.save(new Role(RoleType.USER)));
	}
}
