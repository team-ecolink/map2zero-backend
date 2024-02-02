package com.ecolink.core.event.repository;

import static com.ecolink.core.event.domain.QEvent.*;
import static com.ecolink.core.event.domain.QEventPhoto.*;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;

import com.ecolink.core.event.constant.EventStatus;
import com.ecolink.core.event.dto.request.EventListRequest;
import com.ecolink.core.event.dto.response.GetEventResponse;
import com.ecolink.core.event.dto.response.QGetEventResponse;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;

import jakarta.persistence.EntityManager;

@Repository
public class EventJpaRepository {

	private final JPAQueryFactory queryFactory;

	public EventJpaRepository(EntityManager entityManager) {
		this.queryFactory = new JPAQueryFactory(entityManager);
	}

	public Page<GetEventResponse> findEventByStoreAndStatus(Long id, EventListRequest request, Pageable pageable) {
		List<GetEventResponse> content =
			queryFactory.select(new QGetEventResponse(
				event,
				eventPhoto.file))
			.from(event)
			.leftJoin(eventPhoto)
			.on(eventPhoto.event.eq(event), eventPhoto.givenOrder.eq(0))
			.where(event.store.id.eq(id),
				statusEq(request.getStatus()))
			.orderBy(event.createdDate.asc())
			.offset(pageable.getOffset())
			.limit(pageable.getPageSize())
			.fetch();

		JPAQuery<Long> count = queryFactory.select(event.count())
			.from(event)
			.where(event.store.id.eq(id),
				statusEq(request.getStatus()));

		return PageableExecutionUtils.getPage(content, pageable, count::fetchOne);
	}

	private BooleanExpression statusEq(EventStatus statusCond) {
		return statusCond != null ? event.status.eq(statusCond) : null;
	}
}
