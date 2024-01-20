package com.ecolink.core.store.repository;

import static com.ecolink.core.store.domain.QStoreProduct.*;

import java.util.ArrayList;
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

	public List<StoreProduct> findTop3ByStoreOrderByProductName(String keyword, Store store) {

		StoreProduct firstProduct = queryFactory
			.selectFrom(storeProduct)
			.where(
				storeProduct.product.name.like("%" + keyword + "%")
					.and(storeProduct.store.eq(store))
			)
			.fetchOne();

		assert firstProduct != null;
		List<StoreProduct> restProducts = queryFactory
			.selectFrom(storeProduct)
			.where(
				storeProduct.store.eq(store)
					.and(storeProduct.id.ne(firstProduct.getId()))
			)
			.orderBy(storeProduct.product.name.asc())
			.limit(2)
			.fetch();

		List<StoreProduct> result = new ArrayList<>();
		result.add(firstProduct);
		result.addAll(restProducts);

		return result;
	}

}
