package com.ecolink.core.store.repository;

import static com.ecolink.core.store.domain.QStoreProduct.*;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ecolink.core.store.domain.Store;
import com.ecolink.core.store.domain.StoreProduct;
import com.querydsl.jpa.impl.JPAQueryFactory;

import jakarta.persistence.EntityManager;

@Repository
public class StoreProductJpaRepository {

	private final JPAQueryFactory queryFactory;

	public StoreProductJpaRepository(EntityManager entityManager) {
		this.queryFactory = new JPAQueryFactory(entityManager);
	}

	public List<StoreProduct> findTop3ByStoreOrderByProductName(Store store) {

		return queryFactory
			.selectFrom(storeProduct)
			.where(storeProduct.store.eq(store))
			.orderBy(storeProduct.product.name.asc())
			.limit(3)
			.fetch();
	}

	public List<StoreProduct> findTop2ByStoreOrderByProductName(Store store) {

		return queryFactory
			.selectFrom(storeProduct)
			.where(storeProduct.store.eq(store))
			.orderBy(storeProduct.product.name.asc())
			.limit(2)
			.fetch();
	}

	public StoreProduct findStoreProductByProductName(String keyword) {

		return queryFactory
			.selectFrom(storeProduct)
			.where(storeProduct.product.name.eq(keyword))
			.fetchOne();
	}

}
