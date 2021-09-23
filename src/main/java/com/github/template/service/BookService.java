package com.github.template.service;

import com.github.template.model.db.db.Book;

import java.util.List;

public interface BookService {

    List<Book> getAll();

    Book get(long id);

    void create(Book book);

    void update(Book book, long id);

    void delete(long id);
}
