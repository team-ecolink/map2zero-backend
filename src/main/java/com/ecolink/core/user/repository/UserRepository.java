package com.ecolink.core.user.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ecolink.core.user.domain.User;

public interface UserRepository extends JpaRepository<User, Long> {

	@Query("select u from User u "
		+ "join fetch u.role "
		+ "join fetch u.avatars a "
		+ "join fetch a.photo p "
		+ "where u.email = :email")
	Optional<User> findByEmail(@Param("email") String email);

}
