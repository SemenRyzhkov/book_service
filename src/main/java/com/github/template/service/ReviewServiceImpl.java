package com.github.template.service;

import com.github.template.exception.NotFoundException;
import com.github.template.model.db.db.Book;
import com.github.template.model.db.db.Review;
import com.github.template.repository.BookRepository;
import com.github.template.repository.ReviewRepository;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class ReviewServiceImpl implements ReviewService {
    private final ReviewRepository reviewRepository;
    private final BookRepository bookRepository;

    @Override
    public List<Review> getAll(long bookId) {
        return reviewRepository.findAllByBookId(bookId);
    }

    @Override
    public Review get(long id, long bookId) {
        return reviewRepository.getByIdAndBookId(id, bookId).orElseThrow(() -> new NotFoundException("Review not found"));
    }

    @Override
    public void create(@NonNull Review review, long bookId) {
        Book book = bookRepository.findById(bookId).orElse(null);
        if (book != null) {
            review.setReviewDate(LocalDateTime.now());
            review.setBook(book);
            reviewRepository.save(review);
        } else throw new NotFoundException("Book not found");
    }

    @Override
    public void update(@NonNull Review review, long id, long bookId) {
        Book book = bookRepository.findById(bookId).orElse(null);
        if (book != null) {
            reviewRepository.findById(id).map(newReview -> {
                newReview.setText(review.getText());
                newReview.setReviewDate(LocalDateTime.now());
                newReview.setRating(review.getRating());
                newReview.setBook(review.getBook());
                return reviewRepository.save(newReview);
            }).orElseGet(() -> {
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
