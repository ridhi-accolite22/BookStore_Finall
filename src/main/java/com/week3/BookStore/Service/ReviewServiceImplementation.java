package com.week3.BookStore.Service;

import com.sun.xml.bind.annotation.OverrideAnnotationOf;
import com.week3.BookStore.Exceptions.NoObjectFoundException;
import com.week3.BookStore.Model.Book;
import com.week3.BookStore.Model.BookRented;
import com.week3.BookStore.Model.Review;
import com.week3.BookStore.Model.User;
import com.week3.BookStore.Repository.BookRentedRepo;
import com.week3.BookStore.Repository.BookRepository;
import com.week3.BookStore.Repository.ReviewRepo;
import com.week3.BookStore.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReviewServiceImplementation implements ReviewService {
    @Autowired
    private ReviewRepo reviewsRepo1;
    @Autowired
    private UserRepository userRepo1;
    @Autowired
    private BookRentedRepo bookRentedRepo1;

    @Autowired
    private BookRepository booksRepo1;

    @Override
    public Review addReview(Review reviews1){

        Optional<BookRented> isRentedBookObj = Optional.ofNullable(bookRentedRepo1.findOneByUserNameAndBookName(reviews1.getUserName(), reviews1.getBookName()));
        if(isRentedBookObj.isPresent()){
            return reviewsRepo1.save(reviews1);
        }
        else{
            throw new NoObjectFoundException("user doent have this book");
        }

    }

//    @Override
//
//    public Book likeBook(String bookName, String userName){
//
//        Book book = booksRepo1.findByBookName(bookName);
//        book.setLikes(book.getLikes()+1);
//        booksRepo1.save(book);
//        return book;
//    }

    @Override
    public List<Review> getAllReviews(){
        return reviewsRepo1.findAllByStatus("Active");
    }
    @Override
    public List<Review> getAllReviewsOfBook(String bookName){
        return reviewsRepo1.findAllByBookNameAndStatus(bookName, "Active");
    }


    @Override
    public Review reportReview(String bookName, String userName){
        Optional<Review> reviewObj = Optional.ofNullable(reviewsRepo1.findOneByBookNameAndUserName(bookName, userName));
        if(reviewObj.isPresent()){
            Review review = reviewObj.get();
            review.setStrikes(review.getStrikes()+1);
            User user1 = userRepo1.findByUserName(userName);
            if(review.getStrikes() == 3){
                review.setStatus("Disabled");

                user1.setUserStatus(false);
                userRepo1.save(user1);

            }
            reviewsRepo1.save(review);
            userRepo1.save(user1);
            return review;
        }
        else {
            throw new NoObjectFoundException("Review does not exist on book"+bookName+" by user "+userName);
        }
    }
}

