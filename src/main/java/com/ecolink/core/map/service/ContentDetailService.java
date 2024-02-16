package com.ecolink.core.map.service;

import java.util.List;

import com.ecolink.core.map.domain.MapContent;
import com.ecolink.core.map.dto.ContentDetailDto;
import com.ecolink.core.map.dto.ContentListDetailDto;
import com.ecolink.core.map.repository.ContentDetailRepository;
import com.ecolink.core.geo.service.GeometryService;
import com.ecolink.core.store.dto.request.MapQueryRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ContentDetailService {

	private final ContentDetailRepository contentDetailRepository;
	private final ObjectMapper objectMapper;
	private final GeometryService geometryService;

	public ContentDetailService(ContentDetailRepository contentDetailRepository, ObjectMapper objectMapper, GeometryService geometryService) {
		this.contentDetailRepository = contentDetailRepository;
		this.objectMapper = objectMapper;
		this.geometryService = geometryService;
	}

	public void saveContentDetail(String responseData) {
		try {
			ContentListDetailDto responseDTO = objectMapper.readValue(responseData, ContentListDetailDto.class);
			List<ContentDetailDto> contentDetailList = responseDTO.getBody();

			for (ContentDetailDto contentDetail : contentDetailList) {
				MapContent mapContent = mapDetailToMapEntity(contentDetail);
				contentDetailRepository.save(mapContent);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private MapContent mapDetailToMapEntity(ContentDetailDto contentDetailDto) {
		MapContent mapContent = new MapContent();
		mapContent.setCotValue03(contentDetailDto.getCotValue03());
		mapContent.setCotValue04(contentDetailDto.getCotValue04());
		mapContent.setCotValue05(contentDetailDto.getCotValue05());
		mapContent.setCotValue06(contentDetailDto.getCotValue06());
		mapContent.setCotValue07(contentDetailDto.getCotValue07());
		mapContent.setCotAddrFullNew(contentDetailDto.getCotAddrFullNew());
		mapContent.setCotAddrFullOld(contentDetailDto.getCotAddrFullOld());
		mapContent.setCotTelNo(contentDetailDto.getCotTelNo());
		mapContent.setCotRegDate(contentDetailDto.getCotRegDate());
		mapContent.setCotUpdateDate(contentDetailDto.getCotUpdateDate());
		mapContent.setCotThemeId(contentDetailDto.getCotThemeId());
		mapContent.setCotContsId(contentDetailDto.getCotContsId());
		mapContent.setCotGuName(contentDetailDto.getCotGuName());
		mapContent.setCotDongName(contentDetailDto.getCotDongName());
		mapContent.setCotSanName(contentDetailDto.getCotSanName());
		mapContent.setCotMasterNo(contentDetailDto.getCotMasterNo());
		mapContent.setCotSlaveNo(contentDetailDto.getCotSlaveNo());
		mapContent.setCotExtraName(contentDetailDto.getCotExtraName());
		mapContent.setCotNationBaseArea(contentDetailDto.getCotNationBaseArea());
		mapContent.setCotNationPointNumber(contentDetailDto.getCotNationPointNumber());
		mapContent.setCotCoordData(contentDetailDto.getCotCoordData());
		mapContent.setCotCoordType(contentDetailDto.getCotCoordType());
		mapContent.setCotCoordX(contentDetailDto.getCotCoordX());
		mapContent.setCotCoordY(contentDetailDto.getCotCoordY());
		mapContent.setCotContsStat(contentDetailDto.getCotContsStat());
		mapContent.setCotWriter(contentDetailDto.getCotWriter());
		mapContent.setCotThemeSubId(contentDetailDto.getCotThemeSubId());
		mapContent.setCotExtraData01(contentDetailDto.getCotExtraData01());
		mapContent.setCotExtraData02(contentDetailDto.getCotExtraData02());
		mapContent.setCotMovieUrl(contentDetailDto.getCotMovieUrl());
		mapContent.setCotVoiceUrl(contentDetailDto.getCotVoiceUrl());
		mapContent.setCotContsDetail(contentDetailDto.getCotContsDetail());
		mapContent.setCotImgMainUrl(contentDetailDto.getCotImgMainUrl());
		mapContent.setCotImgMainUrl2(contentDetailDto.getCotImgMainUrl2());
		mapContent.setCotImgMainUrl3(contentDetailDto.getCotImgMainUrl3());
		mapContent.setCotImgMainUrl4(contentDetailDto.getCotImgMainUrl4());
		mapContent.setCotImgMainUrl5(contentDetailDto.getCotImgMainUrl5());
		mapContent.setCotCoordStyle(contentDetailDto.getCotCoordStyle());
		mapContent.setCotLinePattern(contentDetailDto.getCotLinePattern());
		mapContent.setCotLineWeight(contentDetailDto.getCotLineWeight());
		mapContent.setCotLineColor(contentDetailDto.getCotLineColor());

		return mapContent;
	}
}
