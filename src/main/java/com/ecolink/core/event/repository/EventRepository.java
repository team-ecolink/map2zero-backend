package com.ecolink.core.event.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ecolink.core.event.domain.Event;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {

	@Query("select e from Event e "
		+ "join fetch e.store "
		+ "left join fetch e.eventPhotos "
		+ "where e.store.id=:id and "
		+ "e.status='ACTIVE' "
		+ "order by e.startDate")
	Page<Event> findAllByStore(@Param("id") Long id, Pageable pageable);
}
