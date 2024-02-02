package com.ecolink.core.manager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ecolink.core.manager.constant.ManagerStatus;
import com.ecolink.core.manager.domain.Manager;

public interface ManagerRepository extends JpaRepository<Manager, Long> {

	@Query("select (count(m) > 0) from Manager m "
		+ "where m.user.id = :id "
		+ "and m.status = :status ")
	boolean existsByUserAndStatus(@Param("id") Long id,@Param("status") ManagerStatus status);

}
