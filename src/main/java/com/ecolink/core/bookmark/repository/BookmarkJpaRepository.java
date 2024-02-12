package com.ecolink.core.bookmark.repository;


import static com.ecolink.core.bookmark.domain.QBookmark.*;
import static com.ecolink.core.store.domain.QStore.*;
import static com.ecolink.core.store.domain.QStorePhoto.*;

import org.springframework.stereotype.Repository;

import com.ecolink.core.avatar.dto.response.MyPageBookmarkResponse;
import com.querydsl.jpa.impl.JPAQueryFactory;

import jakarta.persistence.EntityManager;

import com.ecolink.core.avatar.dto.request.MyPageBookmarkRequest;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQuery;

import java.util.List;

@Repository
public class BookmarkJpaRepository {

	private final JPAQueryFactory queryFactory;

	public BookmarkJpaRepository(EntityManager entityManager) {
		this.queryFactory = new JPAQueryFactory(entityManager);
	}

	public List<MyPageBookmarkResponse> findBookmarkedStores(MyPageBookmarkRequest request, Long lastBookmarkId, int pageSize, Long avatarId) {
		boolean authenticated = avatarId != null;

		JPAQuery<MyPageBookmarkResponse> query = queryFactory.select(new QMyPageBookmarkResponse(
				store,
				storePhoto,
				authenticated ? bookmark.isNotNull() : Expressions.FALSE))
			.from(store)
			.leftJoin(storePhoto)
			.on(storePhoto.store.eq(store), storePhoto.givenOrder.eq(0))
			.orderBy(store.bookmarkCnt.desc(), store.id.desc())
			.limit(pageSize + 1L);

		if (lastBookmarkId != null) {
			query.where(store.id.lt(lastBookmarkId));
		}

		if (authenticated) {
			query.leftJoin(bookmark)
				.on(bookmark.avatar.id.eq(avatarId), bookmark.store.eq(store));
		}

		return query.fetch();
	}
}
