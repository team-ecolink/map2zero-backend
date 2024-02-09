package com.ecolink.core.map.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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

}
