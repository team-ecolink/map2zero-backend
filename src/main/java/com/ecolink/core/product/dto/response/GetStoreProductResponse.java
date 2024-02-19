package com.ecolink.core.product.dto.response;

import com.ecolink.core.common.domain.ImageFile;
import com.ecolink.core.product.domain.StoreProduct;
import com.ecolink.core.product.util.PriceUtil;
import com.ecolink.core.tag.domain.Product;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.querydsl.core.annotations.QueryProjection;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Getter
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public class GetStoreProductResponse {

	@Schema(description = "매장 상품 ID", example = "1")
	private final Long id;

	@Schema(description = "판매중 여부", example = "true")
	private final boolean onSale;

	@Schema(description = "리필 아이템 여부", example = "true")
	private final boolean refillable;

	@Schema(description = "메인 상품 여부", example = "false")
	private final boolean main;

	@Schema(description = "단위당 가격", example = "10000")
	private final String price;

	@Schema(description = "매장 상품 이름", example = "연필")
	private final String name;

	@Schema(description = "매장 상품의 첫번째 사진", example = "연필")
	private final ImageFile photo;

	@QueryProjection
	public GetStoreProductResponse(StoreProduct storeProduct, Product product, ImageFile photo) {
		this.id = storeProduct.getId();
		this.onSale = storeProduct.isOnSale();
		this.refillable = storeProduct.isRefillable();
		this.main = storeProduct.isMain();
		this.price = PriceUtil.formatPrice(storeProduct.getPrice());
		this.name = product.getName();
		this.photo = photo;
	}

}
