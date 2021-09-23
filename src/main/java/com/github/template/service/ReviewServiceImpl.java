package com.github.template.service;

import com.github.template.exception.NotFoundException;
import com.github.template.mapper.ReviewMapper;
import com.github.template.model.db.db.Book;
import com.github.template.model.db.db.Review;
import com.github.template.model.db.to.ReviewDto;
import com.github.template.repository.BookRepository;
import com.github.template.repository.ReviewRepository;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class ReviewServiceImpl implements ReviewService {
    private final ReviewRepository reviewRepository;
    private final BookRepository bookRepository;
    private final ReviewMapper reviewMapper;

    @Override
    public Page<ReviewDto> getAll(long bookId, Pageable pageable) {
        List<ReviewDto> reviews = reviewMapper.reviewsToReviewsDto(reviewRepository.findAllByBookId(bookId));
        return new PageImpl<>(reviews, pageable, reviews.size());
    }

    @Override
    public ReviewDto get(long id, long bookId) {
        Review review = reviewRepository.getByIdAndBookId(id, bookId).orElseThrow(
                () -> new NotFoundException("Review not found"));
        return reviewMapper.entityToDto(review);
    }

    @Override
    public void create(@NonNull ReviewDto reviewDto, long bookId) {
        Book book = bookRepository.findById(bookId).orElse(null);
        if (book != null) {
            reviewDto.setReviewDate(LocalDateTime.now());
            reviewDto.setBook(book);
            reviewRepository.save(reviewMapper.dtoToEntity(reviewDto));
        } else throw new NotFoundException("Book not found");
    }

    @Override
    public void update(@NonNull ReviewDto reviewDto, long id, long bookId) {
        Book book = bookRepository.findById(bookId).orElse(null);
        if (book != null) {
            reviewRepository.findById(id).map(newReview -> {
                newReview.setText(reviewDto.getText());
                newReview.setReviewDate(LocalDateTime.now());
                newReview.setRating(reviewDto.getRating());
                newReview.setBook(reviewDto.getBook());
                return reviewRepository.save(newReview);
            }).orElseGet(() -> {
                Review review = reviewMapper.dtoToEntity(reviewDto);
                review.setId(id);
                return reviewRepository.save(review);
            });
        }
    }

    @Override
    public void delete(long id, long bookId) {
        reviewRepository.deleteByIdAndBookId(id, bookId);
    }
}
