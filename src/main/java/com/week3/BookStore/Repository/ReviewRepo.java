package com.week3.BookStore.Repository;

import com.week3.BookStore.Model.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepo extends JpaRepository<Review, Long> {
    List<Review> findAllByBookNameAndStatus(String BookName, String Status);
    List<Review> findAllByStatus(String Status);
    Review findOneByBookNameAndUserName(String BookName, String UserName);
}
