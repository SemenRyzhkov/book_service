package com.github.template.service;

import com.github.template.model.db.db.BookDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BookService {

    Page<BookDto> getAll(Pageable pageable);

    BookDto get(long id);

    void create(BookDto book);

    void update(BookDto book, long id);

    void delete(long id);
}
