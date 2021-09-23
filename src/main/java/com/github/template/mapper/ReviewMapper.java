package com.github.template.mapper;

import com.github.template.model.db.db.Review;
import com.github.template.model.db.to.ReviewDto;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ReviewMapper {

    ReviewMapper REVIEW_MAPPER = Mappers.getMapper(ReviewMapper.class);

    ReviewDto entityToDto(Review review);

    @InheritInverseConfiguration
    Review dtoToEntity(ReviewDto dto);

    List<ReviewDto> reviewsToReviewsDto(List<Review>books);

    @InheritInverseConfiguration
    List<Review> reviewsDtoToReviews(List<ReviewDto> dtos);
}
