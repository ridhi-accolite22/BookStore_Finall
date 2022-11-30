package com.week3.BookStore.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.week3.BookStore.Model.Inventory;
import com.week3.BookStore.Model.Transaction;

public interface InventoryRepository extends JpaRepository<Inventory, Integer>{

	@Query("Select i from Inventory i where i.bookName=?1")
	List<Inventory> findInventoryByBookName(String bookName);
	
	@Query("Select i from Inventory i order by bookLikes desc")
	List<Inventory> findSortedInventoryByBookLikes();

}
