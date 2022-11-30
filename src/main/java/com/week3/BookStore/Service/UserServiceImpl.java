package com.week3.BookStore.Service;

import java.nio.file.FileAlreadyExistsException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import com.week3.BookStore.Exceptions.NoObjectFoundException;
import com.week3.BookStore.Model.Book;
//import com.week3.BookStore.Model.Inventory;
import com.week3.BookStore.Model.User;
import com.week3.BookStore.Model.Wallet;
import com.week3.BookStore.Repository.InventoryRepository;
import com.week3.BookStore.Repository.UserRepository;
import com.week3.BookStore.Repository.WalletRepository;

@Service
public class UserServiceImpl implements UserService{

@Autowired
private UserRepository userrepo;
@Autowired
private WalletRepository walletrepo;
@Autowired
private Wallet wallet;
	public User createUser(User user)
	{
			user.setUserStatus(true);
			User userAdded=userrepo.save(user);
			wallet.setDefaultBalance();
			wallet.setUserId(user.getUserId());
			walletrepo.save(wallet);
			return userAdded;
	}
	public User updateUser(User user)
	{
		Optional<User> userObj=this.userrepo.findById(user.getUserId());
		
		if(userObj.isPresent())
		{
			User userUpdate=userObj.get();
			//userrepo.updateUserByID(user.getUserName(),user.getUserEmail(),user.getUserPhNo(),user.getUserId());
			userUpdate.setUserId(user.getUserId());
			userUpdate.setUserName(user.getUserName());
			userUpdate.setUserEmail(user.getUserEmail());
			userUpdate.setUserPhNo(user.getUserPhNo());
			userrepo.save(userUpdate);
			return userUpdate;
			
		}
		else
		{
			throw new NoObjectFoundException("No user with ID : "+user.getUserId());
		}
	}

	public List<User> getUsers()
	{
		return this.userrepo.findAll();
	}

	
	public User suspendUserById(int userid)
	{
		Optional<User> userObj=this.userrepo.findById(userid);
		
		if(userObj.isPresent())
		{
			User userSuspend=userObj.get();
			userSuspend.setUserStatus(false);
			userrepo.save(userSuspend);
			return userSuspend;
		}
		else
		{
			throw new NoObjectFoundException("No user with ID : "+userid);
		}
	}

	
}
