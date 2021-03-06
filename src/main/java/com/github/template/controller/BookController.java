package com.github.template.controller;

import com.github.template.model.db.to.BookDto;
import com.github.template.service.BookService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping(value = BookController.REST_URL)
@Slf4j
public class BookController {
    static final String REST_URL = "/api/books";

    private final BookService service;

    @GetMapping
    public Page<BookDto> getAll(
            @PageableDefault Pageable pageable){
        log.info("getAll");
        return service.getAll(pageable);
    }

    @GetMapping("/{id}")
    public BookDto get(@PathVariable long id){
        log.info("get book {}", id);
        return service.get(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void create(@RequestBody BookDto bookDto){
        log.info("create book{}", bookDto);
        service.create(bookDto);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@RequestBody BookDto bookDto, @PathVariable long id){
        log.info("update book{}", bookDto);
        service.update(bookDto, id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable long id){
        log.info("delete book{}", id);
        service.delete(id);
    }
}
