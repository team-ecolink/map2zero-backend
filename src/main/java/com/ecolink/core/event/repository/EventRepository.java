package com.ecolink.core.event.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecolink.core.event.domain.Event;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
}
