package com.ecolink.core.bookmark.repository;

import static com.ecolink.core.bookmark.domain.QBookmark.*;
import static com.ecolink.core.store.domain.QStore.*;
import static com.ecolink.core.store.domain.QStorePhoto.*;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ecolink.core.avatar.dto.request.MyPageBookmarkRequest;
import com.ecolink.core.avatar.dto.response.MyPageBookmarkResponse;
import com.ecolink.core.avatar.dto.response.QMyPageBookmarkResponse;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;

import jakarta.persistence.EntityManager;

@Repository
public class BookmarkJpaRepository {

	private final JPAQueryFactory queryFactory;

	public BookmarkJpaRepository(EntityManager entityManager) {
		this.queryFactory = new JPAQueryFactory(entityManager);
	}

	public List<MyPageBookmarkResponse> findBookmarkedStores(MyPageBookmarkRequest request, Long avatarId) {

		JPAQuery<MyPageBookmarkResponse> query = queryFactory.select(new QMyPageBookmarkResponse(
				bookmark,
				store,
				storePhoto.file
			))
			.from(store)
			.join(bookmark)
			.on(bookmark.avatar.id.eq(avatarId), bookmark.store.eq(store))
			.leftJoin(store.storePhotos, storePhoto)
			.on(storePhoto.givenOrder.eq(0))
			.orderBy(bookmark.id.desc())
			.limit(request.getSize() + 1L);

		if(request.getCursor() != null)
			query.where(bookmark.id.loe(request.getCursor()));

		return query.fetch();
	}

}
