package com.ecolink.core.review.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.ecolink.core.avatar.domain.Avatar;
import com.ecolink.core.avatar.service.AvatarService;
import com.ecolink.core.common.error.ErrorCode;
import com.ecolink.core.common.error.exception.PhotoLimitExceededException;
import com.ecolink.core.file.constant.FilePath;
import com.ecolink.core.file.service.MultiPhotoService;
import com.ecolink.core.review.domain.Review;
import com.ecolink.core.review.domain.ReviewPhoto;
import com.ecolink.core.review.domain.ReviewTag;
import com.ecolink.core.review.dto.request.CreateReviewRequest;
import com.ecolink.core.store.domain.Store;
import com.ecolink.core.store.service.StoreService;
import com.ecolink.core.tag.constant.TagCategory;
import com.ecolink.core.tag.domain.Tag;
import com.ecolink.core.tag.service.TagService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class ReviewCreateService {

	private final ReviewService reviewService;
	private final StoreService storeService;
	private final AvatarService avatarService;
	private final ReviewTagService reviewTagService;
	private final TagService tagService;
	private final MultiPhotoService multiPhotoService;

	@Transactional
	public void createReview(CreateReviewRequest request, List<MultipartFile> files, Long avatarId) {

		Store store = storeService.getById(request.getStoreId());
		Avatar writer = avatarService.getById(avatarId);

		Review review = new Review(request, store, writer);
		reviewService.saveReview(review);

		if (request.getTagIds() != null) {
			List<Tag> tags = request.getTagIds().stream()
				.map(tagService::getById)
				.toList();

			tags.forEach(tag -> {
				tagService.validateCategory(tag.getId(), TagCategory.REVIEW);
				reviewTagService.saveReviewTag(new ReviewTag(review, tag));
			});
		}

		store.addReviewCount();
		store.addTotalScore(request.getScore());

		if (files != null && files.size() > 5)
			throw new PhotoLimitExceededException(ErrorCode.PHOTO_LIMIT_EXCEEDED);
		if (files != null)
			multiPhotoService.addPhotos(files, review, ReviewPhoto::of, FilePath.REVIEW_PHOTO);
	}
}
