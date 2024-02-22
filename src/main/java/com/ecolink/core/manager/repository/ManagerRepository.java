package com.ecolink.core.manager.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ecolink.core.manager.constant.ManagerStatus;
import com.ecolink.core.manager.domain.Manager;
import com.ecolink.core.store.domain.Store;
import com.ecolink.core.user.domain.User;

public interface ManagerRepository extends JpaRepository<Manager, Long> {

	@Query("select m from Manager m "
		   + "left join fetch m.storeRegistrations sr "
		   + "left join fetch sr.store "
		   + "where m.user = :user")
	Optional<Manager> findByUser(@Param("user") User user);

	@Query("select (count(m) > 0) from Manager m "
		   + "where m.user.id = :id "
		   + "and m.status = :status ")
	boolean existsByUserAndStatus(@Param("id") Long id, @Param("status") ManagerStatus status);

	@Query("select s from Manager m "
		   + "left join m.storeRegistrations sr "
		   + "on sr.status = com.ecolink.core.manager.constant.RegistrationStatus.ACTIVE "
		   + "left join sr.store s "
		   + "where m.id = :managerId")
	List<Store> findStoresByManager(@Param("managerId") Long id);

}
