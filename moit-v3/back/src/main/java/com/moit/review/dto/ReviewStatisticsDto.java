package com.moit.review.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReviewStatisticsDto {
	
	private Long id;
	
	private Integer rating;
	
	private Integer likesCount;
	
	private Integer viewsCount;
	
	private String isPublic;
	
	private LocalDateTime createdAt;
}
