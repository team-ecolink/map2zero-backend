package com.ecolink.core.tag.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ecolink.core.tag.constant.TagCategory;
import com.ecolink.core.tag.domain.Tag;

public interface TagRepository extends JpaRepository<Tag, Long> {

	@Query("select t from Tag t "
		+ "where t.category = :category")
	List<Tag> findByCategory(@Param("category") TagCategory category);

}
