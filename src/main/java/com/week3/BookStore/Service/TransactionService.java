package com.week3.BookStore.Service;

import java.util.List;

import com.week3.BookStore.Model.Inventory;
import com.week3.BookStore.Model.Transaction;

public interface TransactionService {

	Transaction createTransactionBorrow(int classId, Transaction transaction);
	
	//Transaction createTransactionReturn(Transaction transaction);

	List<Transaction> getUserTransactions(int userId);

}
