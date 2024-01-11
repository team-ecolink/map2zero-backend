package com.ecolink.core.event.domain;

import java.time.LocalDateTime;

import com.ecolink.core.common.domain.BaseTimeEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
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

	@NotNull
	@JoinColumn(name = "event_photo_id")
	@OneToOne(fetch = FetchType.LAZY)
	private EventPhoto photo;

}