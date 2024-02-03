package com.ecolink.core.tag.constant;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(enumAsRef = true,
	description = """
		* `STORE` - 매장(시설)
				
		* `PRODUCT` - 제품
				
		* `REVIEW` - 리뷰
				
		* `ALL` - 전체
		""")
public enum TagCategory {
	STORE, PRODUCT, REVIEW, ALL
}
