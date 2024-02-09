package com.ecolink.core.map.service;

import com.ecolink.core.map.client.StoreMapClient;
import com.ecolink.core.map.domain.Map;
import com.ecolink.core.map.dto.ContentListDto;
import com.ecolink.core.map.repository.MapRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

import com.fasterxml.jackson.databind.ObjectMapper;

@Service
@Transactional(readOnly = true)
public class StoreMapService {

	private final StoreMapClient storeMapClient;
	private final MapRepository mapRepository;
	private final ObjectMapper objectMapper;

	@Value("${api.key}")
	private String apiKey;

	@Autowired
	public StoreMapService(StoreMapClient storeMapClient, MapRepository mapRepository, ObjectMapper objectMapper) {
		this.storeMapClient = storeMapClient;
		this.mapRepository = mapRepository;
		this.objectMapper = objectMapper;
	}

	@Transactional
	public void saveContentsListAll(String themeId) {
		String responseJson = getContentsListAll(themeId);
		processResponseAndSaveContents(responseJson);
	}

	public String getContentsListAll(String themeId) {
		return storeMapClient.getContentsListAll("getContentsListAll", apiKey, themeId);
	}

	private void processResponseAndSaveContents(String responseJson) {
		try {
			ContentListDto responseDTO = objectMapper.readValue(responseJson, ContentListDto.class);
			int dataCount = responseDTO.getDataCount();
			for (int i = 0; i < dataCount; i++) {
				Map map = mapContentToMapEntity(responseDTO, i);
				mapRepository.save(map);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private Map mapContentToMapEntity(ContentListDto responseDTO, int index) {
		Map map = new Map();
		map.setMapThemeId(responseDTO.getCotThemeId().get(index));
		map.setMapContentId(responseDTO.getCotContsId().get(index));
		map.setMapSubCategoryId(responseDTO.getCotThemeSubId().get(index));
		map.setMapContentStatus(responseDTO.getCotContsStat().get(index));
		map.setUpdateDate(LocalDateTime.parse(responseDTO.getCotUpdateDate().get(index)));
		map.setRegistrationDate(LocalDateTime.parse(responseDTO.getCotRegDate().get(index)));
		return map;
	}

}
