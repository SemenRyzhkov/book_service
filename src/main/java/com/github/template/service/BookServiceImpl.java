package com.github.template.service;


import com.github.template.exception.NotFoundException;
import com.github.template.model.db.db.Book;
import com.github.template.repository.BookRepository;
import lombok.NonNull;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository repository;

    public BookServiceImpl(BookRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Book> getAll() {
        return repository.findAll();
    }

    @Override
    public Book get(long id) {
        return repository.findById(id).orElseThrow(() -> new NotFoundException("Book not found"));
    }

    @Override
    public void create(@NonNull Book book) {
        repository.save(book);
    }

    @Override
    public void update(@NonNull Book book, long id) {
        repository.findById(id).map(newBook -> {
            newBook.setTitle(book.getTitle());
            newBook.setAuthor(book.getAuthor());
            newBook.setGenre(book.getGenre());
            newBook.setPublishDate(book.getPublishDate());
            return repository.save(newBook);
        }).orElseGet(() -> {
            book.setId(id);
            return repository.save(book);
        });
    }

    @Override
    public void delete(long id) {
        repository.deleteById(id);
    }
}
