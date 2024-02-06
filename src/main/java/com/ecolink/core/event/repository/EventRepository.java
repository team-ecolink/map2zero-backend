package com.ecolink.core.event.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ecolink.core.event.domain.Event;


@Repository
public interface EventRepository extends JpaRepository<Event, Long> {

	@Query("select e from Event e "
		+ "left join fetch e.eventPhotos "
		+ "where e.id = :id")
	Optional<Event> findGraphById(@Param("id") Long id);
}
