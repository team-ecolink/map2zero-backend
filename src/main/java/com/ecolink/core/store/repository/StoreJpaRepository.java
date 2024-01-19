package com.ecolink.core.store.repository;

import static com.ecolink.core.store.domain.QStore.*;
import static com.ecolink.core.store.domain.QStoreProduct.*;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ecolink.core.store.domain.Store;
import com.querydsl.jpa.impl.JPAQueryFactory;

import jakarta.persistence.EntityManager;

@Repository
public class StoreJpaRepository {
	private final JPAQueryFactory queryFactory;

	public StoreJpaRepository(EntityManager entityManager) {
		this.queryFactory = new JPAQueryFactory(entityManager);
	}

	public List<Store> findAllByProductNameOrderByBookmarkCntDesc(String keyword) {
		return queryFactory
			.selectFrom(store)
			.join(storeProduct)
			.where(storeProduct.product.name.eq(keyword))
			.orderBy(store.bookmarkCnt.desc())
			.fetch();
	}
}
