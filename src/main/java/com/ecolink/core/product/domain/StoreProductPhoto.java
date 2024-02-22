package com.ecolink.core.product.domain;

import com.ecolink.core.common.domain.BaseTimeEntity;
import com.ecolink.core.common.domain.ImageFile;
import com.ecolink.core.file.domain.MultiPhoto;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class StoreProductPhoto extends BaseTimeEntity implements MultiPhoto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	private Integer givenOrder;

	@Valid
	@NotNull
	@Embedded
	private ImageFile file;

	@NotNull
	@JoinColumn(name = "store_product_id")
	@ManyToOne(fetch = FetchType.LAZY)
	private StoreProduct storeProduct;

	public StoreProductPhoto(ImageFile file, Integer givenOrder, StoreProduct storeProduct) {
		this.file = file;
		this.givenOrder = givenOrder;
		this.storeProduct = storeProduct;
	}
}
