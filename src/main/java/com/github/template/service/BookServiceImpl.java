package com.github.template.service;


import com.github.template.exception.NotFoundException;
import com.github.template.mapper.BookMapper;
import com.github.template.model.db.db.Book;
import com.github.template.model.db.db.BookDto;
import com.github.template.repository.BookRepository;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository repository;
    private final BookMapper mapper;

    @Override
    public Page<BookDto> getAll(Pageable pageable) {
        List<BookDto> books = mapper.booksToBooksDto(repository.findAll());
        return new PageImpl<>(books, pageable, books.size());
    }

    @Override
    public BookDto get(long id) {
        Book book = repository.findById(id).orElseThrow(() -> new NotFoundException("Book not found"));
        return mapper.entityToDto(book);
    }

    @Override
    public void create(@NonNull BookDto bookDto) {
        repository.save(mapper.dtoToEntity(bookDto));
    }

    @Override
    public void update(@NonNull BookDto bookDto, long id) {
        repository.findById(id).map(newBook -> {
            newBook.setTitle(bookDto.getTitle());
            newBook.setAuthor(bookDto.getAuthor());
            newBook.setGenre(bookDto.getGenre());
            newBook.setPublishDate(bookDto.getPublishDate());
            return repository.save(newBook);
        }).orElseGet(() -> {
            Book book = mapper.dtoToEntity(bookDto);
            book.setId(id);
            return repository.save(book);
        });
    }

    @Override
    public void delete(long id) {
        repository.deleteById(id);
    }
}
