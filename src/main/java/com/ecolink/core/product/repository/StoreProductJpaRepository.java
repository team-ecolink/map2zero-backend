package com.ecolink.core.product.repository;

import static com.ecolink.core.product.domain.QStoreProduct.*;
import static com.ecolink.core.product.domain.QStoreProductPhoto.*;
import static com.ecolink.core.product.domain.QStoreProductTag.*;
import static com.ecolink.core.tag.domain.QProduct.*;
import static com.ecolink.core.tag.domain.QTag.*;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ecolink.core.product.dto.request.GetStoreProductRequest;
import com.ecolink.core.product.dto.response.GetStoreProductResponse;
import com.ecolink.core.product.dto.response.QGetStoreProductResponse;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;

import jakarta.persistence.EntityManager;

@Repository
public class StoreProductJpaRepository {

	private final JPAQueryFactory queryFactory;

	public StoreProductJpaRepository(EntityManager entityManager) {
		this.queryFactory = new JPAQueryFactory(entityManager);
	}

	public List<GetStoreProductResponse> queryByNameAndTag(Long storeId, GetStoreProductRequest request, Boolean onSale) {

		JPAQuery<GetStoreProductResponse> common = queryFactory.select(new QGetStoreProductResponse(
				storeProduct,
				product,
				storeProductPhoto.file
			))
			.from(storeProduct)
			.join(storeProduct.product, product)
			.leftJoin(storeProductPhoto)
			.on(storeProductPhoto.storeProduct.eq(storeProduct), storeProductPhoto.givenOrder.eq(0))
			.where(storeProduct.store.id.eq(storeId))
			.orderBy(storeProduct.id.desc())
			.limit(request.getSize() + 1L);

		return withCondition(common, request, onSale).fetch();
	}

	private static JPAQuery<GetStoreProductResponse> withCondition(JPAQuery<GetStoreProductResponse> common,
		GetStoreProductRequest request, Boolean onSale) {
		BooleanBuilder builder = new BooleanBuilder();
		if (request.getCursor() != null)
			builder.and(storeProduct.id.loe(request.getCursor()));

		if (request.getKeyword() != null)
			builder.and(product.name.contains(request.getKeyword()));

		if (request.getTagId() != null) {
			common.leftJoin(storeProductTag)
				.on(storeProductTag.storeProduct.eq(storeProduct))
				.join(tag)
				.on(tag.eq(storeProductTag.tag));
			builder.and(tag.id.eq(request.getTagId()));
		}

		if(onSale != null)
			builder.and(storeProduct.onSale.eq(onSale));

		return common.where(builder);
	}

}
