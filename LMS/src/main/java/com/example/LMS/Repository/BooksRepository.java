package com.example.LMS.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.LMS.Model.Books;

import jakarta.transaction.Transactional;

public interface BooksRepository extends JpaRepository<Books,Integer> {
	@Query(value="select * from lms.books",nativeQuery=true)
	public List<Books> displayBooks();

	
	
	@Query(value="select * from lms.books where author=?1 or book_name=?1",nativeQuery=true)
	public List<Books> findBook(String searchword);

	@Query(value="select availability from lms.books where book_name=?1",nativeQuery=true)
	public String findavailaibility(String bookname);

	@Modifying
	@Transactional
	@Query(value="update lms.books set availability='N' where book_name=?1",nativeQuery=true)
	public void checkout(String bookname);

	@Query(value="select due_date from lms.books where book_name=?1",nativeQuery=true)
	public String getdueDate(String bookname);
	
	@Modifying
	@Transactional
	@Query(value="update lms.books set availability='A',due_date= date_add(date(due_date),INTERVAL 30 DAY) where book_name=?1",nativeQuery=true)
	public void checkin(String bookname);

	@Query(value="select * from lms.books where genre=?1",nativeQuery=true)
	public List<Books> findByGenre(String genre);

	@Modifying
	@Transactional
	@Query(value="Insert into lms.books(book_name,author,genre,availability) values (?1,?2,?3,'A')",nativeQuery = true)
	public void insertBook(String bookname, String author, String genre);


	@Query(value="select * from lms.books where book_name=?1",nativeQuery=true)
	public List<Books> checkAvailability(String bookname);

	
}
