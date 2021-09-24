package com.github.template.model.db.db;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String author;

    private String title;

    private String genre;

    private LocalDate publishDate;

//    @OneToMany(fetch = FetchType.LAZY, mappedBy = "book")
//    @OnDelete(action = OnDeleteAction.CASCADE)
//    private List<Review> reviews;

}
