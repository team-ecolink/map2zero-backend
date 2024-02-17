package com.ecolink.core.map.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ecolink.core.geo.service.GeometryService;
import com.ecolink.core.map.domain.MapContent;
import com.ecolink.core.map.dto.ContentDetailDto;
import com.ecolink.core.map.repository.ContentDetailRepository;
import com.ecolink.core.store.dto.request.MapQueryRequest;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;

@Service
@Transactional
public class ContentDetailService {

	private final ContentDetailRepository contentDetailRepository;
	private final ObjectMapper mapper;
	private final GeometryService geometryService;

	public ContentDetailService(ContentDetailRepository contentDetailRepository, GeometryService geometryService) {
		this.contentDetailRepository = contentDetailRepository;
		this.geometryService = geometryService;
		this.mapper = new ObjectMapper().setPropertyNamingStrategy(PropertyNamingStrategies.UPPER_SNAKE_CASE);
	}

	public void saveContentDetail(String responseData) {
		try {
			JsonNode jsonNode = mapper.readValue(responseData, JsonNode.class);
			JsonNode body = jsonNode.get("body");

			for (JsonNode contents : body) {
				ContentDetailDto dto = mapper.treeToValue(contents, ContentDetailDto.class);
				MapContent mapContent = mapDetailToMapEntity(dto);
				contentDetailRepository.save(mapContent);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private MapContent mapDetailToMapEntity(ContentDetailDto dto) {
		MapContent mapContent = new MapContent();
		mapContent.setCotContsName(dto.getCotContsName());
		mapContent.setCotValue03(dto.getCotValue03());
		mapContent.setCotValue04(dto.getCotValue04());
		mapContent.setCotValue05(dto.getCotValue05());
		mapContent.setCotValue06(dto.getCotValue06());
		mapContent.setCotValue07(dto.getCotValue07());
		mapContent.setCotAddrFullNew(dto.getCotAddrFullNew());
		mapContent.setCotAddrFullOld(dto.getCotAddrFullOld());
		mapContent.setCotTelNo(dto.getCotTelNo());
		mapContent.setCotRegDate(dto.getCotRegDate());
		mapContent.setCotUpdateDate(dto.getCotUpdateDate());
		mapContent.setCotThemeId(dto.getCotThemeId());
		mapContent.setCotContsId(dto.getCotContsId());
		mapContent.setCotGuName(dto.getCotGuName());
		mapContent.setCotDongName(dto.getCotDongName());
		mapContent.setCotSanName(dto.getCotSanName());
		mapContent.setCotMasterNo(dto.getCotMasterNo());
		mapContent.setCotSlaveNo(dto.getCotSlaveNo());
		mapContent.setCotExtraName(dto.getCotExtraName());
		mapContent.setCotNationBaseArea(dto.getCotNationBaseArea());
		mapContent.setCotNationPointNumber(dto.getCotNationPointNumber());
		if (!dto.getCotCoordX().isEmpty() && !dto.getCotCoordY().isEmpty())
			mapContent.setCotCoordData(
				geometryService.getPoint(
					new MapQueryRequest(Double.valueOf(dto.getCotCoordX()), Double.valueOf(dto.getCotCoordY()))));
		mapContent.setCotCoordType(dto.getCotCoordType());
		mapContent.setCotCoordX(dto.getCotCoordX());
		mapContent.setCotCoordY(dto.getCotCoordY());
		mapContent.setCotContsStat(dto.getCotContsStat());
		mapContent.setCotWriter(dto.getCotWriter());
		mapContent.setCotThemeSubId(dto.getCotThemeSubId());
		mapContent.setCotExtraData01(dto.getCotExtraData01());
		mapContent.setCotExtraData02(dto.getCotExtraData02());
		mapContent.setCotMovieUrl(dto.getCotMovieUrl());
		mapContent.setCotVoiceUrl(dto.getCotVoiceUrl());
		mapContent.setCotContsDetail(dto.getCotContsDetail());
		mapContent.setCotImgMainUrl(dto.getCotImgMainUrl());
		mapContent.setCotImgMainUrl2(dto.getCotImgMainUrl2());
		mapContent.setCotImgMainUrl3(dto.getCotImgMainUrl3());
		mapContent.setCotImgMainUrl4(dto.getCotImgMainUrl4());
		mapContent.setCotImgMainUrl5(dto.getCotImgMainUrl5());
		mapContent.setCotCoordStyle(dto.getCotCoordStyle());
		mapContent.setCotLinePattern(dto.getCotLinePattern());
		mapContent.setCotLineWeight(dto.getCotLineWeight());
		mapContent.setCotLineColor(dto.getCotLineColor());

		return mapContent;
	}
}
