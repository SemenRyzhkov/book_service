package com.github.template.service;

import com.github.template.model.db.to.ReviewDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ReviewService {

    Page<ReviewDto> getAll(long bookId, Pageable pageable);

    ReviewDto get(long id, long bookId);

    void create(ReviewDto review, long bookId);

    void update(ReviewDto review, long id, long bookId);

    void delete(long id, long bookId);
}
