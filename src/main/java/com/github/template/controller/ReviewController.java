package com.github.template.controller;


import com.github.template.model.db.to.ReviewDto;
import com.github.template.service.ReviewService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping(value = ReviewController.REST_URL)
@Slf4j
public class ReviewController {
    static final String REST_URL = "/api/books";

    private final ReviewService service;

    @GetMapping("/{bookId}/review")
    public Page<ReviewDto> getAll(@PathVariable long bookId,
                                  @PageableDefault Pageable pageable) {
        log.info("getAllReview for book {}", bookId);
        return service.getAll(bookId, pageable);
    }

    @GetMapping("/{bookId}/review/{id}")
    public ReviewDto get(@PathVariable long id, @PathVariable long bookId) {
        log.info("get review {} for book {}", id, bookId);
        return service.get(id, bookId);
    }

    @PostMapping("/{bookId}/review")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void create(@RequestBody ReviewDto reviewDto, @PathVariable long bookId) {
        log.info("create review{} for book{}", reviewDto, bookId);
        service.create(reviewDto, bookId);
    }

    @PutMapping("/{bookId}/review/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@RequestBody ReviewDto reviewDto, @PathVariable long bookId, @PathVariable long id) {
        log.info("update review{} for book{}", reviewDto, id);
        service.update(reviewDto, id, bookId);
    }

    @DeleteMapping("/{bookId}/review/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable long bookId, @PathVariable long id) {
        log.info("delete review{} for book{}", id, bookId);
        service.delete(id, bookId);
    }
}
