package com.ecolink.core.map.service;

import com.ecolink.core.map.domain.Map;
import com.ecolink.core.map.dto.ContentItemDto;
import com.ecolink.core.map.dto.ContentListDto;
import com.ecolink.core.map.repository.ContentListRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class ContentListService {

	private final ContentListRepository contentListRepository;
	private final ObjectMapper objectMapper;

	@Autowired
	public ContentListService(ContentListRepository contentListRepository, ObjectMapper objectMapper) {
		this.contentListRepository = contentListRepository;
		this.objectMapper = objectMapper;
	}

	public void saveContentsListAll(String responseData) {
		try {
			ContentListDto responseDTO = objectMapper.readValue(responseData, ContentListDto.class);
			List<ContentItemDto> contentList = responseDTO.getBody();

			for (ContentItemDto contentItem : contentList) {
				Map map = mapContentToMapEntity(contentItem);
				contentListRepository.save(map);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<String> getContentIds() {
		List<Map> maps = contentListRepository.findAll();
		List<String> contentIds = new ArrayList<>();
		for (Map map : maps) {
			contentIds.add(map.getMapContentId());
		}
		return contentIds;
	}

	private Map mapContentToMapEntity(ContentItemDto contentItem) {
		Map map = new Map();
		map.setMapThemeId(contentItem.getCotThemeId());
		map.setMapContentId(contentItem.getCotContsId());
		map.setMapSubCategoryId(contentItem.getCotThemeSubId());
		map.setMapContentStatus(contentItem.getCotContsStat());

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S");
		LocalDateTime updateDate = LocalDateTime.parse(contentItem.getCotUpdateDate(), formatter);
		LocalDateTime regDate = LocalDateTime.parse(contentItem.getCotRegDate(), formatter);

		map.setUpdateDate(updateDate);
		map.setRegistrationDate(regDate);
		return map;
	}
}