package com.ecolink.core.store.repository;

import static com.ecolink.core.bookmark.domain.QBookmark.*;
import static com.ecolink.core.store.domain.QStore.*;
import static com.ecolink.core.store.domain.QStorePhoto.*;
import static com.ecolink.core.store.domain.QStoreProduct.*;
import static com.ecolink.core.tag.domain.QProduct.*;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ecolink.core.store.constant.SearchType;
import com.ecolink.core.store.domain.Store;
import com.ecolink.core.store.dto.request.StoreSearchRequest;
import com.ecolink.core.store.dto.response.QStoreSearchDto;
import com.ecolink.core.store.dto.response.StoreSearchDto;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;

import jakarta.persistence.EntityManager;

@Repository
public class StoreJpaRepository {
	private final JPAQueryFactory queryFactory;

	public StoreJpaRepository(EntityManager entityManager) {
		this.queryFactory = new JPAQueryFactory(entityManager);
	}

	public List<StoreSearchDto> findStoresByKeyword(StoreSearchRequest request, Long avatarId) {
		boolean authenticated = avatarId != null;

		JPAQuery<StoreSearchDto> common = queryFactory.select(new QStoreSearchDto(
				store,
				storePhoto,
				authenticated ? bookmark.isNotNull() : Expressions.FALSE))
			.from(store)
			.leftJoin(storePhoto)
			.on(storePhoto.store.eq(store), storePhoto.givenOrder.eq(0))
			.orderBy(store.bookmarkCnt.desc(), store.id.desc())
			.limit(request.getSize() + 1L);

		if (request.getCursor() != null) {
			Store cursor = getCursor(request.getCursor());
			common.where(store.bookmarkCnt.lt(cursor.getBookmarkCnt())
				.or(store.bookmarkCnt.eq(cursor.getBookmarkCnt()).and(store.id.loe(cursor.getId()))));
		}

		if (authenticated)
			common.leftJoin(bookmark)
				.on(bookmark.avatar.id.eq(avatarId), bookmark.store.eq(store));

		if (SearchType.PRODUCT.equals(request.getType())) {
			productNameCondition(common, request.getKeyword());
		} else if (SearchType.STORE.equals(request.getType())) {
			storeNameCondition(common, request.getKeyword());
		}

		return common.fetch();
	}

	private void productNameCondition(JPAQuery<?> query, String keyword) {
		query.distinct()
			.join(store.storeProducts, storeProduct)
			.join(storeProduct.product, product)
			.where(product.name.contains(keyword));
	}

	private void storeNameCondition(JPAQuery<?> query, String keyword) {
		query.where(store.name.contains(keyword));
	}

	private Store getCursor(Long id) {
		// null 처리
		return queryFactory.selectFrom(store).where(store.id.eq(id)).fetchFirst();
	}

}
