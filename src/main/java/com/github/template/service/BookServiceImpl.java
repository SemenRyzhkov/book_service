package com.github.template.service;


import com.github.template.mapper.BookMapper;
import com.github.template.model.db.db.Book;
import com.github.template.model.db.to.BookDto;
import com.github.template.repository.BookRepository;
import com.github.template.repository.ReviewRepository;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
@Slf4j
public class BookServiceImpl implements BookService {

    private final BookRepository repository;
    private final ReviewRepository reviewRepository;
    private final BookMapper mapper;

    @Override
    @Cacheable("books")
    public Page<BookDto> getAll(Pageable pageable) {
        return repository.findAll(pageable).map(mapper::entityToDto);
    }

    @Override
    @Cacheable("books")
    public BookDto get(long id) {
        log.info("getting book by id: {}", id);
        return mapper.entityToDto(repository.getOne(id));
    }

    @Override
    @CacheEvict(value = "books", allEntries = true)
    public Book create(@NonNull BookDto bookDto) {
       return repository.save(mapper.dtoToEntity(bookDto));
    }

    @Override
    @CacheEvict(value = "books", allEntries = true)
    public void update(@NonNull BookDto bookDto, long id) {
       repository.getOne(id);
       repository.save(mapper.dtoToEntity(bookDto));
    }

    @Override
    @Transactional
    @CacheEvict(value = "books", allEntries = true)
    public void delete(long id) {
        reviewRepository.deleteByBookId(id);
        repository.deleteById(id);
    }
}
