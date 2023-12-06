package com.example.finalprojectbootcamp.mapper;

import com.example.finalprojectbootcamp.core.entities.Expert;
import com.example.finalprojectbootcamp.core.entities.RateAndReview;
import com.example.finalprojectbootcamp.dto.ExpertDto;
import com.example.finalprojectbootcamp.dto.RateAndReviewDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface RateAndReviewMapper {
    RateAndReviewMapper INSTANCE = Mappers.getMapper(RateAndReviewMapper.class) ;
    RateAndReviewDto toRateAndReviewDto (RateAndReview rateAndReview) ;
    RateAndReview toRateAndReview (RateAndReviewDto rateAndReviewDto) ;
    List<RateAndReviewDto> toRateAndReviewDtoAsList (List<RateAndReview> rateAndReviews) ;
    List<RateAndReview> toRateAndReviewAsList (List<RateAndReviewDto> rateAndReviewDtoS) ;
}
