package com.github.template.repository;

import com.github.template.model.db.db.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional
public interface ReviewRepository extends JpaRepository<Review, Long> {

//    @Query("SELECT r FROM Review r WHERE r.book.id=:bookId ORDER BY r.rating DESC")
    Page<Review> findAllByBookId(@Param("bookId") long bookId, Pageable pageable);

    Optional<Review> getByIdAndBookId(long id, long bookId);

    @Modifying
    void deleteByBookId(Long bookId);

}
