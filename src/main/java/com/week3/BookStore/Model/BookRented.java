package com.week3.BookStore.Model;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Component
@Table(name = "bookRented")
public class BookRented {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer BorrowId;
    @Column(name = "userId")
    private Integer userId;
    @Column(name = "userName")
    private String  userName;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public String getBookUniqueId() {
        return bookUniqueId;
    }

    public void setBookUniqueId(String bookUniqueId) {
        this.bookUniqueId = bookUniqueId;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Column(name = "bookId")
    private Integer  bookId;
    @Column(name = "bookUniqueId")
    private String  bookUniqueId;
    @Column(name = "bookName")
    private String  bookName;
    @Column(name = "author")
    private String  author;
    @Column(name = "status")
    private String status;
    @CreationTimestamp
    private Date borrowDate;
    @UpdateTimestamp
    private  Date returnDate;
}

