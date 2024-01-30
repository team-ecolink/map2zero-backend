package com.ecolink.core.store.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecolink.core.store.domain.SearchHistory;

public interface SearchHistoryRepository extends JpaRepository<SearchHistory, Long> {
}
