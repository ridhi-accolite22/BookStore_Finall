package com.week3.BookStore.Repository;

import com.week3.BookStore.Model.BookRented;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRentedRepo extends JpaRepository<BookRented, Integer> {
    List<BookRented> findAllByUserNameAndStatus(String UserName, String Status);
    List<BookRented> findAllByUserName(String userName);
    BookRented findOneByUserNameAndBookName(String UserName, String BookName);
    BookRented findOneByUserNameAndBookNameAndStatus(String UserName, String BookName, String Status);
}