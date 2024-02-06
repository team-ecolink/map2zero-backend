package com.ecolink.core.review.repository;

import static com.ecolink.core.like.domain.QReviewLike.*;
import static com.ecolink.core.review.domain.QReview.*;
import static com.ecolink.core.review.domain.QReviewPhoto.*;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ecolink.core.avatar.dto.request.MyPageReviewRequest;
import com.ecolink.core.avatar.dto.response.MyPageReviewResponse;
import com.ecolink.core.avatar.dto.response.QMyPageReviewResponse;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;

import jakarta.persistence.EntityManager;

@Repository
public class ReviewJpaRepository {

	private final JPAQueryFactory queryFactory;

	public ReviewJpaRepository(EntityManager entityManager) {
		this.queryFactory = new JPAQueryFactory(entityManager);
	}

	public List<MyPageReviewResponse> findByWriter(MyPageReviewRequest request, Long writerId, Long viewerId) {
		JPAQuery<MyPageReviewResponse> common = queryFactory.select(new QMyPageReviewResponse(
				review,
				reviewPhoto.file,
				review.store,
				writerId.equals(viewerId) ? Expressions.TRUE : Expressions.FALSE,
				reviewLike.isNotNull()
			))
			.from(review)
			.leftJoin(review.photos, reviewPhoto)
			.on(reviewPhoto.givenOrder.eq(0))
			.leftJoin(review.reviewLikes, reviewLike)
			.on(reviewLike.avatar.id.eq(viewerId))
			.where(cursorCondition(request.getCursor()))
			.orderBy(review.id.desc())
			.limit(request.getSize() + 1L);

		return common.fetch();
	}

	private Predicate cursorCondition(Long cursor) {
		BooleanBuilder builder = new BooleanBuilder();
		if (cursor == null)
			return builder;
		return builder.and(review.id.loe(cursor));
	}

}
