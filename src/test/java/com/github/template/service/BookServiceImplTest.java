package com.github.template.service;

import com.github.template.mapper.BookMapper;
import com.github.template.model.db.db.Book;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Slf4j
class BookServiceImplTest extends AbstractServiceTest{
    @Autowired
    private BookService service;
    @Autowired
    private BookMapper mapper;

    @Test
    @Transactional
    void cacheTest() {
        Book book = service.create(mapper.entityToDto(new Book(6L, "book1", "title1", "author1",
                LocalDate.of(2021, 1, 1))));
        Book book2 = service.create(mapper.entityToDto(new Book(7L, "book2", "title2", "author2",
                LocalDate.of(2022, 2, 2))));

        getAndPrint(book.getId());
        getAndPrint(book2.getId());
        getAndPrint(book.getId());
        getAndPrint(book2.getId());
    }

    private void getAndPrint(Long id) {
        log.info("book found: {}", service.get(id));
    }

}