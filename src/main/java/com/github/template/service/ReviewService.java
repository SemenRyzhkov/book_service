package com.github.template.service;

import com.github.template.model.db.db.Review;

import java.util.List;

public interface ReviewService {

    List<Review> getAll(long bookId);

    Review get(long id, long bookId);

    void create(Review review, long bookId);

    void update(Review review, long id, long bookId);

    void delete(long id, long bookId);
}
