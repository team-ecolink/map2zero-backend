package com.ecolink.core.store.dto.response;

import java.util.List;

import com.ecolink.core.common.domain.ImageFile;
import com.ecolink.core.store.domain.StoreProduct;
import com.ecolink.core.store.domain.StoreProductPhoto;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Getter
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public class GetStoreProductResponse {

    @Schema(description = "매장 상품 ID", example = "1")
    private final Long id;

    @Schema(description = "매장 상품 이름", example = "연필")
    private final String name;

    @Schema(description = "매장 상품 가격", example = "1000원")
    private final int price;

	@Schema(description = "매장 상품 사진")
	private final List<ImageFile> productPhotos;

    public GetStoreProductResponse(StoreProduct storeProduct) {
        this.id = storeProduct.getId();
        this.name = storeProduct.getProduct().getName();
        this.price = storeProduct.getPrice();
		this.productPhotos = storeProduct.getStoreProductPhotos().stream()
			.map(StoreProductPhoto::getFile).toList();
    }

    public static GetStoreProductResponse of(StoreProduct storeProduct) {
        return new GetStoreProductResponse(storeProduct);
    }
}
