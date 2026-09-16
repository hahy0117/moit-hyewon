package com.moit.review.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.moit.review.dto.ReviewStatisticsDto;
import com.moit.review.service.ReviewStatisticsService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@Tag(name = "Review Statistics Api", description = "리뷰 통계 관련 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/reviews/statistics")
public class ReviewStatisticsController {

    private final ReviewStatisticsService reviewStatisticsService;

    @Operation(
        summary = "리뷰 통계 데이터 조회",
        description = "삭제되지 않은 리뷰의 통계 분석용 데이터를 조회합니다."
    )
    @GetMapping
    public ResponseEntity<List<ReviewStatisticsDto>> getReviewStatistics() {

        List<ReviewStatisticsDto> statistics =
                reviewStatisticsService.getReviewStatistics();

        return ResponseEntity.ok(statistics);
    }
}