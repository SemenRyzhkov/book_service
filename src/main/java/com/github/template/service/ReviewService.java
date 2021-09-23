package com.github.template.service;

import com.github.template.model.db.db.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ReviewService {

    Page<Review> getAll(long bookId, Pageable pageable);

    Review get(long id, long bookId);

    void create(Review review, long bookId);

    void update(Review review, long id, long bookId);

    void delete(long id, long bookId);
}
