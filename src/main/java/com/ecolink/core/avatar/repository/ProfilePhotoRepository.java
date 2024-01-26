package com.ecolink.core.avatar.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecolink.core.avatar.domain.ProfilePhoto;

public interface ProfilePhotoRepository extends JpaRepository<ProfilePhoto, Long> {
}
