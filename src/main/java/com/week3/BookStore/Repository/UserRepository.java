package com.week3.BookStore.Repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.week3.BookStore.Model.User;

public interface UserRepository extends JpaRepository<User, Integer>{


    User findByUserName(String userName);


}
