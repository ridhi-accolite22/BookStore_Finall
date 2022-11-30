package com.week3.BookStore.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.week3.BookStore.Model.Book;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Integer>{


    //List<Book> findAllByBookName(String BookName);
//    @Query("Select b from Book b where b.book_name=?1")
//    Book findByBookName(String BookName);
//

   // Book findByBookName(String bookName);
    //List<Book> findAllByOrderByLikes();
    //List<Book> findAllByOrderByAddedDate();


}
