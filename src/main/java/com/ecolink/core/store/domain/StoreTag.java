package com.ecolink.core.store.domain;

import com.ecolink.core.common.domain.BaseTimeEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class StoreTag extends BaseTimeEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	// @NotNull
	// @ManyToOne(fetch = FetchType.LAZY)
	// @JoinColumn(name = "store_id")
	// private Store store;
	//
	// @NotNull
	// @ManyToOne(fetch = FetchType.LAZY)
	// @JoinColumn(name = "tag_id")
	// private Tag tag;
}
