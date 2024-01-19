package com.ecolink.core.avatar.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ecolink.core.avatar.domain.Avatar;

public interface AvatarRepository extends JpaRepository<Avatar, Long> {

	@Query("select a from Avatar a "
		+ "join fetch a.photo p "
		+ "join fetch a.user u "
		+ "join fetch u.role r "
		+ "where a.id = :id")
	Optional<Avatar> findUserGraphById(@Param("id") Long id);

}
