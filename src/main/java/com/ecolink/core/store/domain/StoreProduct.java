package com.ecolink.core.store.domain;

import com.ecolink.core.common.domain.BaseTimeEntity;
import com.ecolink.core.tag.domain.Product;
import com.ecolink.core.tag.domain.Tag;

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
public class StoreProduct extends BaseTimeEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	private boolean onSale;

	@NotNull
	private boolean refillable;

	@NotNull
	private boolean main;

	@NotNull
	@JoinColumn(name = "store_id")
	@ManyToOne(fetch = FetchType.LAZY)
	private Store store;

	@NotNull
	@JoinColumn(name = "product_id")
	@ManyToOne(fetch = FetchType.LAZY)
	private Product product;

	@NotNull
	@JoinColumn(name = "tag_id")
	@ManyToOne(fetch = FetchType.LAZY)
	private Tag tag;
	
}
