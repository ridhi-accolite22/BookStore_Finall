package com.week3.BookStore.Service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.week3.BookStore.Model.Book;
import com.week3.BookStore.Model.Inventory;
import com.week3.BookStore.Repository.BookRepository;
import com.week3.BookStore.Repository.InventoryRepository;

@Service
@Transactional
public class InventoryServiceImpl implements InventoryService{
@Autowired
private InventoryRepository inventoryrepo;
@Autowired
private BookRepository bookrepo;	
	public Inventory createInventory(Inventory inv)
	{
		Inventory inventoryAdded=inventoryrepo.save(inv);
		for(int i=0;i<inventoryAdded.getBookCount();i++)
		{
			Book book=new Book();
			book.setClassId(inventoryAdded.getClassId());
			bookrepo.save(book);
			System.out.println(book);
		}
		return inventoryAdded;
	}
	public List<Inventory> getInventories()
	{
		return this.inventoryrepo.findAll();
	}
	public List<Inventory> getInventoryByBookName(String bookName)
	{
		List<Inventory> bookList=inventoryrepo.findInventoryByBookName(bookName);
		for (Inventory inventory : bookList) {
			if(inventory.getBookCount()==0)
				System.out.println("BOOK "+ inventory.getClassId()+" OUT OF STOCK");
		}
		return bookList;
	}
	public List<Inventory> getSortedInventoryByBookLikes()
	{
		return inventoryrepo.findSortedInventoryByBookLikes();
	}
}