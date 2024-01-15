package com.ecolink.core.store.domain;

import java.time.LocalTime;

import com.ecolink.core.common.domain.BaseTimeEntity;
import com.ecolink.core.store.constant.DayOfWeek;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class StoreOperatingHours extends BaseTimeEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	@Enumerated(EnumType.STRING)
	private DayOfWeek dayOfWeek;

	@NotNull
	private LocalTime startTime;

	@NotNull
	private LocalTime endTime;

	private LocalTime breakStartTime;

	private LocalTime breakEndTime;

	private boolean regularHoliday;

	@NotNull
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "store_id")
	private Store store;

}
