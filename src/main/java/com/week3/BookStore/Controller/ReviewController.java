package com.week3.BookStore.Controller;


import com.week3.BookStore.Model.Review;
import com.week3.BookStore.Service.ReviewService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;


import java.util.List;
@RestController
public class ReviewController {
    @Autowired
    private ReviewService reviewService1;
    @GetMapping(path = "/getAllReviews")
    private ResponseEntity<List<Review>> getAllReviews(){
        return ResponseEntity.ok().body(this.reviewService1.getAllReviews());
    }

    @GetMapping(path = "/getAllReviewsOfBook/{bookName}")
    private ResponseEntity<List<Review>> getAllReviewsOfBook(@PathVariable String bookName){
        return ResponseEntity.ok().body(this.reviewService1.getAllReviewsOfBook(bookName));
    }

    @PostMapping(path = "/addReview")
    private ResponseEntity<Review> addReview(@RequestBody Review reviews1) {
        return ResponseEntity.ok().body(this.reviewService1.addReview(reviews1));
    }
//    @PostMapping(path = "/likeBook/{bookName}/{userName}")
//    private ResponseEntity<Book> likeBook(@PathVariable String bookName, @PathVariable String userName) {
//        return ResponseEntity.ok().body(this.reviewService1.likeBook(bookName,userName));
//    }

    @PostMapping(path = "/reportReview/{bookName}/{userName}")
    private ResponseEntity<Review> reportReview(@PathVariable String bookName, @PathVariable String userName) {
        return ResponseEntity.ok().body(this.reviewService1.reportReview(bookName,userName));
    }
}
