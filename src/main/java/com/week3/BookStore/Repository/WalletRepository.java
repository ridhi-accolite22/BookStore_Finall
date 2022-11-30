package com.week3.BookStore.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.week3.BookStore.Model.Wallet;

public interface WalletRepository extends JpaRepository<Wallet, Integer>{

}
