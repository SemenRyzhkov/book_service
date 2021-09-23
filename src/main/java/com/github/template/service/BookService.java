package com.github.template.service;

import com.github.template.model.db.db.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BookService {

    Page<Book> getAll(Pageable pageable);

    Book get(long id);

    void create(Book book);

    void update(Book book, long id);

    void delete(long id);
}
