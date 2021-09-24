package com.github.template.model.db.to;

import com.github.template.model.db.db.Book;
import lombok.Data;

import java.time.LocalDateTime;


@Data
public class ReviewDto {

    private Long id;

    private String text;

    private LocalDateTime reviewDate;

    private Short rating;

    private Book book;
}

