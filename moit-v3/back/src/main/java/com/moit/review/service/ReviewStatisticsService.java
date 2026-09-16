package com.moit.review.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.moit.review.dto.ReviewStatisticsDto;
import com.moit.review.entity.Review;
import com.moit.review.repository.ReviewRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly=true)
public class ReviewStatisticsService {

		private final ReviewRepository reviewRepository;
		
		public List<ReviewStatisticsDto> getReviewStatistics() {

	        List<Review> reviews = reviewRepository.findByDeleteYn('N');

	        return reviews.stream()
	                .map(this::convertToDto)
	                .collect(Collectors.toList());
	    }

	    private ReviewStatisticsDto convertToDto(Review review) {

	        return ReviewStatisticsDto.builder()
	                .id(review.getId())
	                .rating(review.getRating())
	                .likesCount(review.getLikesCount())
	                .viewsCount(review.getViewsCount())
	                .isPublic(review.getIsPublic())
	                .createdAt(review.getCreatedAt())
	                .build();
	    }
}
