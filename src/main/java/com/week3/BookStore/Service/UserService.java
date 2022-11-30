package com.week3.BookStore.Service;

import java.util.List;

import com.week3.BookStore.Model.User;

public interface UserService {
	
	User createUser(User user);

	User updateUser(User user);

	List<User> getUsers();

	User suspendUserById(int userid);

}
