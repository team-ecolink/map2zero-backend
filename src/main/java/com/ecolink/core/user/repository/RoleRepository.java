package com.ecolink.core.user.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ecolink.core.user.constant.RoleType;
import com.ecolink.core.user.domain.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
	@Query("select r from Role r "
		+ "where r.type = :type")
	Optional<Role> findByType(@Param("type") RoleType type);
}
