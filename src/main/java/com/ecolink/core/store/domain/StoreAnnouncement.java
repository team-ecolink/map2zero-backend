package com.ecolink.core.store.domain;

import com.ecolink.core.common.domain.BaseTimeEntity;

import jakarta.persistence.Entity;
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
public class StoreAnnouncement extends BaseTimeEntity {

	@Id
	@NotNull
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	private String title;

	@NotNull
	private String content;

	@NotNull
	@JoinColumn(name = "store_id")
	@ManyToOne(fetch = FetchType.LAZY)
	private Store store;

}
