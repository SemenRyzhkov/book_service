package com.github.template.mapper;

import com.github.template.model.db.db.Book;
import com.github.template.model.db.to.BookDto;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BookMapper {

//    BookMapper BOOK_MAPPER = Mappers.getMapper(BookMapper.class);

    BookDto entityToDto(Book book);

    @InheritInverseConfiguration
    Book dtoToEntity(BookDto dto);

    List<BookDto> booksToBooksDto(List<Book>books);

    @InheritInverseConfiguration
    List<Book> booksDtoToBooks(List<BookDto> dtos);
}
