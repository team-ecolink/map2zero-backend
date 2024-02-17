package com.ecolink.core.map.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Map {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String mapThemeId;
	private String mapContentId;
	private String mapSubCategoryId;
	private String mapContentStatus;
	private LocalDateTime updateDate;
	private LocalDateTime registrationDate;

	public Map(String mapThemeId, String mapContentId, String mapSubCategoryId, String mapContentStatus, LocalDateTime updateDate, LocalDateTime registrationDate) {
		this.mapThemeId = mapThemeId;
		this.mapContentId = mapContentId;
		this.mapSubCategoryId = mapSubCategoryId;
		this.mapContentStatus = mapContentStatus;
		this.updateDate = updateDate;
		this.registrationDate = registrationDate;
	}
}