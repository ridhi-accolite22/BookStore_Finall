package com.week3.BookStore.Service;

import java.util.List;

import com.week3.BookStore.Model.Book;
import com.week3.BookStore.Model.Inventory;

public interface InventoryService {
	
	Inventory createInventory(Inventory inventory);


	List<Inventory> getInventories();
	
	List<Inventory> getInventoryByBookName(String bookName);
	
	List<Inventory> getSortedInventoryByBookLikes();

}
