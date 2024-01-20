package com.ecolink.core.store.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Repository;

import com.ecolink.core.store.domain.QStore;
import com.ecolink.core.store.domain.QStoreProduct;
import com.ecolink.core.store.domain.Store;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;

import jakarta.persistence.EntityManager;

@Repository
public class StoreJpaRepository {
	private final JPAQueryFactory queryFactory;

	public StoreJpaRepository(EntityManager entityManager) {
		this.queryFactory = new JPAQueryFactory(entityManager);
	}

	public Page<Store> findPageByNameContainingOrderByBookmarkCntDesc(String keyword, Long cursorId,
		int pageSize) {

		QStore store = QStore.store;

		BooleanExpression condition = store.name.like("%" + keyword + "%");

		if (cursorId != null) {
			condition = condition
				.and(store.bookmarkCnt.lt(cursorId)
					.or(store.bookmarkCnt.eq(cursorId.intValue())));
		}

		List<Store> stores = queryFactory
			.selectFrom(store)
			.where(condition)
			.orderBy(store.bookmarkCnt.desc(), store.name.asc())
			.limit(pageSize)
			.fetch();

		return new PageImpl<>(stores);

	}

	public Page<Store> findPageByProductNameOrderByBookmarkCntDesc(String keyword, Long cursorId,
		int pageSize) {

		QStore store = QStore.store;
		QStoreProduct storeProduct = QStoreProduct.storeProduct;

		BooleanExpression condition = storeProduct.product.name.like("%" + keyword + "%");

		if (cursorId != null) {
			condition = condition
				.and(store.bookmarkCnt.lt(cursorId)
					.or(store.bookmarkCnt.eq(cursorId.intValue())));
		}

		List<Store> stores = queryFactory
			.selectFrom(store)
			.join(storeProduct).on(store.id.eq(storeProduct.store.id))
			.where(condition)
			.orderBy(store.bookmarkCnt.desc(), store.name.asc())
			.limit(pageSize)
			.fetch();

		return new PageImpl<>(stores);
	}
}
