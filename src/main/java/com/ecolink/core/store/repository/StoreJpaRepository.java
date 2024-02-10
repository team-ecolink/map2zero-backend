package com.ecolink.core.store.repository;

import static com.ecolink.core.bookmark.domain.QBookmark.*;
import static com.ecolink.core.product.domain.QStoreProduct.*;
import static com.ecolink.core.store.domain.QStore.*;
import static com.ecolink.core.store.domain.QStorePhoto.*;
import static com.ecolink.core.tag.domain.QProduct.*;

import java.util.List;

import org.locationtech.jts.geom.Point;
import org.springframework.stereotype.Repository;

import com.ecolink.core.store.constant.SearchType;
import com.ecolink.core.store.domain.QStore;
import com.ecolink.core.store.domain.QStorePhoto;
import com.ecolink.core.store.domain.Store;
import com.ecolink.core.store.dto.MapStoreInfoDto;
import com.ecolink.core.store.dto.QMapStoreInfoDto;
import com.ecolink.core.store.dto.request.StoreSearchRequest;
import com.ecolink.core.store.dto.response.QStoreSearchDto;
import com.ecolink.core.store.dto.response.StoreSearchDto;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.NumberExpression;
import com.querydsl.core.types.dsl.NumberPath;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;

import jakarta.persistence.EntityManager;

@Repository
public class StoreJpaRepository {

	private final JPAQueryFactory queryFactory;

	public StoreJpaRepository(EntityManager em) {
		this.queryFactory = new JPAQueryFactory(em);
	}

	public List<StoreSearchDto> findStoresByKeyword(StoreSearchRequest request, Long avatarId) {
		boolean authenticated = avatarId != null;

		JPAQuery<StoreSearchDto> common = queryFactory.select(new QStoreSearchDto(
				QStore.store,
				QStorePhoto.storePhoto,
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

		processBookmark(common, avatarId);

		if (SearchType.PRODUCT.equals(request.getType())) {
			productNameCondition(common, request.getKeyword());
		} else if (SearchType.STORE.equals(request.getType())) {
			storeNameCondition(common, request.getKeyword());
		}

		return common.fetch();
	}

	private void processBookmark(JPAQuery<?> common, Long avatarId) {
		if (avatarId != null)
			common.leftJoin(bookmark)
				.on(bookmark.avatar.id.eq(avatarId), bookmark.store.eq(store));
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
		return queryFactory.selectFrom(store).where(store.id.eq(id)).fetchFirst();
	}

	public List<MapStoreInfoDto> findByPoint(Point currentPosition, Long limit, Long avatarId) {
		NumberPath<Double> distance = Expressions.numberPath(Double.class, "distance");

		JPAQuery<MapStoreInfoDto> common = queryFactory.select(new QMapStoreInfoDto(
				store,
				storePhoto.file,
				getDistanceExpression(currentPosition, distance),
				avatarId != null ? bookmark.isNotNull() : Expressions.FALSE))
			.from(store)
			.leftJoin(store.storePhotos, storePhoto)
			.on(storePhoto.givenOrder.eq(0))
			.orderBy(distance.asc());

		processBookmark(common, avatarId);
		processLimit(common, limit);

		return common.fetch();
	}

	private void processLimit(JPAQuery<?> common, Long limit) {
		if (limit != null)
			common.limit(limit);
	}

	private NumberExpression<Double> getDistanceExpression(Point point, NumberPath<Double> distance) {
		return Expressions.numberTemplate(Double.class, "ST_Distance_Sphere({0}, {1})",
			point, store.coordinates).as(distance);
	}

}
