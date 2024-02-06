package com.ecolink.core.store.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecolink.core.store.domain.SearchHistory;

public interface SearchHistoryRepository extends JpaRepository<SearchHistory, Long> {

	List<SearchHistory> findByAvatarIdOrderByCreatedDateDesc(Long avatarId);

	int countByAvatarId(Long avatarId);

	List<SearchHistory> findAllByAvatarIdOrderByCreatedDate(Long avatarId);

	List<SearchHistory> findAllByAvatarId(Long avatarId);

	List<SearchHistory> findAllByWordAndAvatarId(String word, Long avatarId);
}
