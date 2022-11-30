package com.week3.BookStore.Service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import com.week3.BookStore.Model.*;
import com.week3.BookStore.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Transactional
@Service
public class TransactionServiceImpl implements TransactionService{

	@Autowired
	private TransactionRepository transactionrepo;
	@Autowired
	private WalletRepository walletrepo;
	@Autowired
	BookRepository bookrepo;
	@Autowired
	private UserRepository userrepo;
	@Autowired
	private BookRentedRepo bookRentedRepo1;
	@Autowired
	Wallet walletUpdated;
	@Autowired
	Book bookAssigned;
	@Autowired
	BookRented bookrent;

	@Autowired
	User user;
	@Autowired
	private InventoryRepository invrepo;
	public Transaction createTransactionBorrow(int classId, Transaction transaction)
	{
		List<Book> BookList=transactionrepo.findBookByClassId(classId);
		System.out.println("BOOK LIST : "+BookList);
		int bookSet=0;
		for (Book book : BookList) {
			if(!(book.isBookStatus()))
			{
				System.out.println("BOOK STATUS "+book.getBookId()+book.isBookStatus());
				bookSet=1;
				transaction.setBookId(book.getBookId());
				bookAssigned=book;
				break;
			}
		}
		if(bookSet==0)
		{
			System.out.println("No books available! NO STOCK!");
			return null;
		}
		System.out.println("BOOK ASSIGNED: "+bookAssigned);
		System.out.println(transaction.getUserId());
		Optional<User> user=userrepo.findById(transaction.getUserId());
		System.out.println(user.isPresent());
		//System.out.println(user);
		if(user.isPresent())
		{
			User userObj= user.get();
			System.out.println(userObj.isUserStatus());
			transaction.setBookId(bookAssigned.getBookId());
			int amount=transactionrepo.findWalletBalanceByUserId(userObj.getUserId());
			Inventory inv=transactionrepo.findInventoryByClassId(transactionrepo.findClassIdByBookId(transaction.getBookId()));
			List<Transaction> transRecord=transactionrepo.findTransactionByUserId(userObj.getUserId());
			//System.out.println(transRecord);
			//System.out.println(transRecord.size());
			if(transRecord.size()>2)
			{
				System.out.println("BOOK LIMIT EXCEEDED "+userObj.getUserName()+" PLEASE RETURN PREVIOUS BOOKS FOR FURTHER BORROW!");
				return null;
			}
			for (Transaction trans : transRecord) {
				int prevClassId=transactionrepo.findClassIdByBookId(trans.getBookId());
				if(trans.getDateReturned()==null&&transactionrepo.findClassIdByBookId(transaction.getBookId())==prevClassId)
				{
					System.out.println("CANNOT BORROW THE SAME BOOK AGAIN");
					return null;
				}
			}
			if(userObj.isUserStatus()&&amount>=(0.2*inv.getBookPrice()))
			{
				if(inv.getBookCount()==0)
				{
					System.out.println("BOOK OUT OF STOCK!");
					return null;
				}
				transaction.setDeposit((int)(0.2*inv.getBookPrice()));
				transaction.setRefundBalance((int)(0.1*inv.getBookPrice()));
				Wallet userWallet=transactionrepo.findWalletByUserId(userObj.getUserId());
				walletUpdated.setBalance(amount-transaction.getDeposit());
				walletUpdated.setWalletId(userWallet.getWalletId());
				walletUpdated.setUserId(userWallet.getUserId());
				walletrepo.save(walletUpdated);
				bookrent.setUserId(userWallet.getUserId());
				bookrent.setUserName(user.get().getUserName());
				bookrent.setBookId(bookAssigned.getBookId());
				bookrent.setBookName(inv.getBookName());
				bookRentedRepo1.save(bookrent);
				inv.setBookCount(inv.getBookCount()-1);
				invrepo.save(inv);
				bookAssigned.setBookStatus(true);
				bookrepo.save(bookAssigned);
				Transaction transactionAdded=transactionrepo.save(transaction);
				return transactionAdded;
			}
			else if(amount<(0.2*inv.getBookPrice()))
					System.out.println("INSUFFICIENT BALANCE! PLEASE RECHARGE YOUR WALLET "+userObj.getUserName());
			else
			{
				System.out.println("USER "+userObj.getUserId()+"CANNOT BORROW! USER SUSPENDED!");
			}
		}
		return null;
	}
	public List<Transaction> getUserTransactions(int userId)
	{
		return transactionrepo.findTransactionByUserId(userId);
	}
}
