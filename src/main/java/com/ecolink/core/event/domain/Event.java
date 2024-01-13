package com.ecolink.core.event.domain;

import java.time.LocalDateTime;

import com.ecolink.core.common.domain.BaseTimeEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Event extends BaseTimeEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "event_id")
	private Long id;

	@NotNull
	private String title;

	private LocalDateTime startDate;

	private LocalDateTime endDate;

	private String applicationUrl;

	@NotNull
	private String description;

	@NotNull
	private String status;

}