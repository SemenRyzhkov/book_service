package com.github.template.service;


import com.github.template.mapper.BookMapper;
import com.github.template.model.db.db.Book;
import com.github.template.model.db.to.BookDto;
import com.github.template.repository.BookRepository;
import com.github.template.repository.ReviewRepository;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository repository;
    private final ReviewRepository reviewRepository;
    private final BookMapper mapper;

    @Override
    public Page<BookDto> getAll(Pageable pageable) {
        return repository.findAll(pageable).map(mapper::entityToDto);
    }

    @Override
    public BookDto get(long id) {
        Book book = repository.getOne(id);
        return mapper.entityToDto(book);
    }

    @Override
    public void create(@NonNull BookDto bookDto) {
        repository.save(mapper.dtoToEntity(bookDto));
    }

    @Override
    public void update(@NonNull BookDto bookDto, long id) {
        repository.findById(id)
                .map(user -> {
                    bookDto.setId(id);
                    return repository.save(mapper.dtoToEntity(bookDto));
                })
                .orElseGet(() -> {
                    Book book = mapper.dtoToEntity(bookDto);
                    book.setId(id);
                    return repository.save(book);
                });
    }

    @Override
    @Transactional
    public void delete(long id) {
        reviewRepository.deleteByBookId(id);
        repository.deleteById(id);
    }
}
