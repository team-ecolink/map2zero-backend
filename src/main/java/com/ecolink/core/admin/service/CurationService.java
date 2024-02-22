package com.ecolink.core.admin.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ecolink.core.admin.dto.response.GetCurationResponse;
import com.ecolink.core.admin.repository.CurationRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class CurationService {

	private final CurationRepository curationRepository;

	public List<GetCurationResponse> getCurations() {
		return curationRepository.findAll().stream().map(GetCurationResponse::of).toList();
	}

}
