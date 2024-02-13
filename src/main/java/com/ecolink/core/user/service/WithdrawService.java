package com.ecolink.core.user.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ecolink.core.avatar.repository.AvatarRepository;
import com.ecolink.core.bookmark.repository.BookmarkRepository;
import com.ecolink.core.common.error.ErrorCode;
import com.ecolink.core.common.error.exception.ManagerForbiddenException;
import com.ecolink.core.like.repository.ReviewLikeRepository;
import com.ecolink.core.review.repository.ReviewRepository;
import com.ecolink.core.review.repository.ReviewTagRepository;
import com.ecolink.core.store.repository.SearchHistoryRepository;
import com.ecolink.core.user.constant.RoleType;
import com.ecolink.core.user.domain.User;
import com.ecolink.core.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class WithdrawService {

	private final UserRepository userRepository;
	private final AvatarRepository avatarRepository;
	private final ReviewRepository reviewRepository;
	private final ReviewLikeRepository reviewLikeRepository;
	private final ReviewTagRepository reviewTagRepository;
	private final BookmarkRepository bookmarkRepository;
	private final SearchHistoryRepository searchHistoryRepository;
	private final UserService userService;
	private final RoleService roleService;

	@Transactional
	public void withdraw(Long userId) {
		User user = userService.getUserGraphById(userId);
		if (user.getRole().getType().equals(RoleType.MANAGER)) {
			throw new ManagerForbiddenException(ErrorCode.MANAGER_WITHDRAWAL_DENIED);
		}
		user.withdraw(roleService.getWithdrawnUserRole());
	}

	@Transactional
	public void delete(User user) {
		user.getAvatars().forEach(avatar -> {
			reviewTagRepository.deleteAllInBatch(reviewTagRepository.findAllByAvatarId(avatar.getId()));
			reviewLikeRepository.deleteAllInBatch(reviewLikeRepository.findAllByAvatarId(avatar.getId()));
			reviewRepository.deleteAllInBatch(reviewRepository.findAllByAvatarId(avatar.getId()));
			bookmarkRepository.deleteAllInBatch(bookmarkRepository.findAllByAvatarId(avatar.getId()));
			searchHistoryRepository.deleteAllInBatch(searchHistoryRepository.findAllByAvatarId(avatar.getId()));
		});

		avatarRepository.deleteAllInBatch(user.getAvatars());
		userRepository.delete(user);
	}
}
