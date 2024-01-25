package com.ecolink.core.store.repository;

import static com.ecolink.core.store.domain.QStore.*;
import static com.ecolink.core.store.domain.QStoreProduct.*;
import static com.ecolink.core.tag.domain.QProduct.*;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Repository;

import com.ecolink.core.store.constant.SearchType;
import com.ecolink.core.store.domain.Store;
import com.ecolink.core.store.dto.request.StoreSearchRequest;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;

import jakarta.persistence.EntityManager;

@Repository
public class StoreJpaRepository {
	private final JPAQueryFactory queryFactory;

	public StoreJpaRepository(EntityManager entityManager) {
		this.queryFactory = new JPAQueryFactory(entityManager);
	}

	public Page<Store> findStoresByKeywordContainingOrderByBookmarkCntDesc(StoreSearchRequest request) {

		BooleanExpression condition;

		if (request.getType() == SearchType.STORE) {
			condition = store.name.like("%" + request.getKeyword() + "%");
		} else {
			condition = storeProduct.product.name.like("%" + request.getKeyword() + "%");
		}

		if (request.getCursor() != null) {
			condition = condition.and(
				store.bookmarkCnt.lt(request.getCursor())
					.or(store.bookmarkCnt.eq(request.getCursor().intValue()))
					.and(store.id.lt(request.getStoreId())));
		}

		List<Store> stores = queryFactory
			.selectFrom(store)
			.leftJoin(store.storeProducts, storeProduct)
			.leftJoin(storeProduct.product, product)
			.where(condition)
			.orderBy(store.bookmarkCnt.desc(), store.name.asc())
			.limit(request.getPageSize())
			.fetch();

		return new PageImpl<>(stores);
	}

}
