package com.ecolink.core.event.repository;

import static com.ecolink.core.event.domain.QEvent.*;
import static com.ecolink.core.event.domain.QEventPhoto.*;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ecolink.core.event.constant.EventStatus;
import com.ecolink.core.event.dto.request.GetEventRequest;
import com.ecolink.core.event.dto.response.GetEventListResponse;
import com.ecolink.core.event.dto.response.QGetEventListResponse;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;

import jakarta.persistence.EntityManager;

@Repository
public class EventJpaRepository {

	private final JPAQueryFactory queryFactory;

	public EventJpaRepository(EntityManager entityManager) {
		this.queryFactory = new JPAQueryFactory(entityManager);
	}

	public List<GetEventListResponse> findEventByStoreAndStatus(Long id, GetEventRequest request, EventStatus status) {
		JPAQuery<GetEventListResponse> common = queryFactory.select(new QGetEventListResponse(
				event,
				eventPhoto.file))
			.from(event)
			.leftJoin(eventPhoto)
			.on(eventPhoto.event.eq(event), eventPhoto.givenOrder.eq(0))
			.where(event.store.id.eq(id))
			.orderBy(event.id.asc())
			.limit(request.getSize() + 1L);

		return withCondition(common, request, status).fetch();
	}

	private static JPAQuery<GetEventListResponse> withCondition(JPAQuery<GetEventListResponse> common,
		GetEventRequest request, EventStatus status) {
		BooleanBuilder builder = new BooleanBuilder();
		if (request.getCursor() != null) {
			builder.and(event.id.goe(request.getCursor()));
		}
		if (status != null) {
			builder.and(event.status.eq(status));
		}

		return common.where(builder);
	}
}
