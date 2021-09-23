package com.github.template.repository;

import com.github.template.model.db.db.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
public interface ReviewRepository extends JpaRepository<Review, Long> {

    @Query("SELECT r FROM Review r WHERE r.book.id=:bookId ORDER BY r.reviewDate DESC")
    List<Review> findAllByBookId(@Param("bookId") long bookId);

    Optional<Review> getByIdAndBookId(long id, long bookId);
//
//    List<Review> findAllByBookId(long bookId);

    void deleteByIdAndBookId(Long id, Long bookId);
}
