package com.github.template.model.db.to;


import lombok.Data;

import java.time.LocalDate;

@Data
public class BookDto {

    private Long id;

    private String author;

    private String title;

    private String genre;

    private LocalDate publishDate;

}