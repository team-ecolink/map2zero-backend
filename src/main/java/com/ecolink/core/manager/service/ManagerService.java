package com.ecolink.core.manager.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ecolink.core.auth.token.UserPrincipal;
import com.ecolink.core.bookmark.dto.response.StoreInfoDto;
import com.ecolink.core.common.error.ErrorCode;
import com.ecolink.core.common.error.exception.EntityNotFoundException;
import com.ecolink.core.common.error.exception.ManagerApplicationException;
import com.ecolink.core.manager.constant.ManagerStatus;
import com.ecolink.core.manager.domain.Manager;
import com.ecolink.core.manager.dto.request.ManagerApplicationRequest;
import com.ecolink.core.manager.repository.ManagerRepository;
import com.ecolink.core.user.domain.User;
import com.ecolink.core.user.service.UserService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class ManagerService {

	private final UserService userService;
	private final StoreRegistrationService storeRegistrationService;
	private final ManagerRepository managerRepository;

	@Transactional
	public Long applyManager(ManagerApplicationRequest request, UserPrincipal principal) {
		User user = userService.getUserGraphById(principal.getUserId());
		if (checkIfPending(user.getId()))
			throw new ManagerApplicationException(ErrorCode.APPLICATION_IS_PENDING);
		Manager manager = managerRepository.save(new Manager(user));
		storeRegistrationService.applyRegistration(request.store(), manager);
		return manager.getId();
	}

	/**
	 * 대표 등록 신청 진행중인지 여부 확인하는 메서드
	 */
	public boolean checkIfPending(Long userId) {
		return managerRepository.existsByUserAndStatus(userId, ManagerStatus.PENDING);
	}

	public Manager getByUser(User user) {
		return managerRepository.findByUser(user)
			.orElseThrow(() -> new EntityNotFoundException(ErrorCode.MANAGER_NOT_FOUND));
	}

	@Transactional
	public List<StoreInfoDto> getManagingStores(Long managerId) {
		return managerRepository.findStoresByManager(managerId).stream().map(StoreInfoDto::of).toList();
	}

}
