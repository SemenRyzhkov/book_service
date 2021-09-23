package com.github.template.controller;

import com.github.template.model.db.db.Book;
import com.github.template.service.BookService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(value = BookController.REST_URL)
@Slf4j
public class BookController {
    static final String REST_URL = "/api/books";

    private final BookService service;

    @GetMapping
    public List<Book> getAll(){
        log.info("getAll");
        return service.getAll();
    }

    @GetMapping("/{id}")
    public Book get(@PathVariable long id){
        log.info("get book {}", id);
        return service.get(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void create(@RequestBody Book book){
        log.info("create book{}", book);
        service.create(book);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@RequestBody Book book, @PathVariable long id){
        log.info("update book{}", book);
        service.update(book, id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable long id){
        log.info("delete book{}", id);
        service.delete(id);
    }
}
