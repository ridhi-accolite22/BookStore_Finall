package com.week3.BookStore.Service;

import com.week3.BookStore.Model.Book;
import com.week3.BookStore.Model.Review;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface ReviewService {
    Review addReview(Review reviews1);
    //Book likeBook(int bookID, String userName);
    List<Review> getAllReviews();
    List<Review> getAllReviewsOfBook(String bookName);
    Review reportReview(String bookName, String userName);
}
