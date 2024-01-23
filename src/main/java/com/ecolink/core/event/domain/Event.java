package com.ecolink.core.event.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.ecolink.core.common.domain.BaseTimeEntity;
import com.ecolink.core.event.constant.EventStatus;
import com.ecolink.core.store.domain.Store;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
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
	@Enumerated(EnumType.STRING)
	private EventStatus status;

	@NotNull
	@JoinColumn(name = "store_id")
	@ManyToOne(fetch = FetchType.LAZY)
	private Store store;

	@OneToMany(mappedBy = "event", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<EventPhoto> eventPhotos = new ArrayList<>();
}
