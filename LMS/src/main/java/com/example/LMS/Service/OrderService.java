package com.example.LMS.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.LMS.Model.Books;
import com.example.LMS.Repository.BookOrderRepository;
import com.example.LMS.Repository.BooksRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class OrderService {

	@Autowired
	BooksRepository booksrepo;
	@Autowired
	BookOrderRepository bookorderrepo;
	
	public List<Books> checkAvailability(String bookname) {
		return booksrepo.checkAvailability(bookname);
		
	}
	public void orderbook(int id, int quantity, String bookname) throws Exception {
		try {
			List<Books> validBook= booksrepo.findBook(bookname);
			if(validBook.size()>0) {
				bookorderrepo.orderbook(id,quantity,bookname);
			}
			else {
				throw new Exception("Invalid book ordered");
			}
		}
		catch(Exception e) {
			throw new Exception(e.getMessage());
		}
		
		
	}

}
