package com.example.LMS.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.example.LMS.Model.BookOrder;

import jakarta.transaction.Transactional;

public interface BookOrderRepository extends JpaRepository<BookOrder,Integer>{
	
	@Modifying
	@Transactional
	@Query(value="Insert into lms.book_order(idbook,quantity,bookname) values (?1,?2,?3)",nativeQuery = true)
	void orderbook(int id, int quantity, String bookname);

}
