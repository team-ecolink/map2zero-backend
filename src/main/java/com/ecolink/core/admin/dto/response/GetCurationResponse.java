package com.ecolink.core.admin.dto.response;

import com.ecolink.core.admin.domain.Curation;
import com.ecolink.core.common.domain.ImageFile;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.Builder;
import lombok.Getter;

@Getter
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public class GetCurationResponse {

	private final Long id;

	private final String title;

	private final String description;

	private final String url;

	private final ImageFile photo;

	private final ImageFile mobilePhoto;

	@Builder
	private GetCurationResponse(Long id, String title, String description, String url, ImageFile photo,
		ImageFile mobilePhoto) {
		this.id = id;
		this.title = title;
		this.description = description;
		this.url = url;
		this.photo = photo;
		this.mobilePhoto = mobilePhoto;
	}

	public static GetCurationResponse of(Curation curation) {
		return GetCurationResponse.builder()
			.id(curation.getId())
			.title(curation.getTitle())
			.description(curation.getDescription())
			.url(curation.getNotionUrl())
			.photo(curation.getPhoto())
			.mobilePhoto(curation.getMobilePhoto())
			.build();
	}

}
