package com.github.template.controller;


import com.github.template.model.db.db.Review;
import com.github.template.service.ReviewService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(value = ReviewController.REST_URL)
@Slf4j
public class ReviewController {
    static final String REST_URL = "/api/books";

    private final ReviewService service;

    @GetMapping("/{bookId}/review")
    public List<Review> getAll(@PathVariable long bookId){
        log.info("getAllReview for book {}", bookId);
        return service.getAll(bookId);
    }

    @GetMapping("/{bookId}/review/{id}")
    public Review get(@PathVariable long id, @PathVariable long bookId){
        log.info("get review {} for book {}", id, bookId);
        return service.get(id, bookId);
    }

    @PostMapping("/{bookId}/review")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void create(@RequestBody Review review, @PathVariable long bookId){
        log.info("create review{} for book{}", review, bookId);
        service.create(review, bookId);
    }

    @PutMapping("/{bookId}/review/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@RequestBody Review review, @PathVariable long bookId, @PathVariable long id){
        log.info("update review{} for book{}", review, id);
        service.update(review, id, bookId);
    }

    @DeleteMapping("/{bookId}/review/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable long bookId, @PathVariable long id){
        log.info("delete review{} for book", id, bookId);
        service.delete(id, bookId);
    }
}
