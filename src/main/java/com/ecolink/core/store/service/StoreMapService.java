package com.ecolink.core.store.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ecolink.core.common.constant.Address;
import com.ecolink.core.geo.service.GeometryService;
import com.ecolink.core.map.dto.ContentDetailDto;
import com.ecolink.core.store.domain.Store;
import com.ecolink.core.store.dto.request.MapQueryRequest;
import com.ecolink.core.store.repository.StoreRepository;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;

@Service
@Transactional
public class StoreMapService {

	private final StoreRepository storeRepository;
	private final ObjectMapper mapper;
	private final GeometryService geometryService;

	public StoreMapService(StoreRepository storeRepository, GeometryService geometryService) {
		this.storeRepository = storeRepository;
		this.geometryService = geometryService;
		this.mapper = new ObjectMapper().setPropertyNamingStrategy(PropertyNamingStrategies.UPPER_SNAKE_CASE);
	}

	public void saveStoreDetail(String responseData) {
		try {
			JsonNode jsonNode = mapper.readValue(responseData, JsonNode.class);
			JsonNode body = jsonNode.get("body");

			for (JsonNode contents : body) {
				ContentDetailDto dto = mapper.treeToValue(contents, ContentDetailDto.class);
				Store store = mapDetailToMapEntity(dto);
				storeRepository.save(store);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private Store mapDetailToMapEntity(ContentDetailDto dto) {
		Store store = new Store();
		store.setName(dto.getCotContsName());
		store.setContact(dto.getCotTelNo());
		store.setHomepageUrl(dto.getCotExtraData_02());
		store.setDescription(dto.getCotValue_04());
		store.setInstagramUrl(dto.getCotValue_05());
		store.setAddress(
			new Address("서울특별시",
				dto.getCotGuName() == null || dto.getCotGuName().isBlank() ? "구정보 없음" : dto.getCotGuName(),
				parse(dto.getCotAddrFullNew()), parse(dto.getCotAddrFullOld())));
		if (!dto.getCotCoordX().isEmpty() && !dto.getCotCoordY().isEmpty())
			store.setCoordinates(
				geometryService.getPoint(
					new MapQueryRequest(Double.valueOf(dto.getCotCoordX()), Double.valueOf(dto.getCotCoordY()))));
		return store;
	}

	private String parse(String fullAddress) {
		String[] split = fullAddress.split(" ", 3);
		if (fullAddress == null || fullAddress.isBlank() || split.length <= 2 || split[2].isBlank()) {
			System.out.println("split = " + fullAddress);
			return "파싱 불가능";
		}
		return split[2];
	}

}
