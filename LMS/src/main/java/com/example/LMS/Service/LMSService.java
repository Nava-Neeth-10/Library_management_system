package com.example.LMS.Service;


import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.LMS.Model.Books;
import com.example.LMS.Repository.BooksRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class LMSService {
	
	@Autowired
	BooksRepository booksrepo;

	public List<Books> displayBooks() {
		return booksrepo.displayBooks();
	}

	public void addBooks(String bookname,String author, String genre) throws Exception {
		try {
		System.out.println(bookname+" "+author+" "+genre);
		booksrepo.insertBook(bookname,author,genre);
		}
		catch(Exception e) {
			throw new Exception("Exception in addBooks() method");
		}
		
	}

	public List<Books> findbook(String searchword) {
		return booksrepo.findBook(searchword);
	}

	public String checkout(String bookname) {
		String availability= booksrepo.findavailaibility(bookname);
		if("A".equals(availability)) {
			booksrepo.checkout(bookname);
			return "Book checked out";
		}
		return "Book unavailable";
	}

	public String checkin(String bookname) {
		try {			
			String dueDate=booksrepo.getdueDate(bookname);
			double dueAmt=calculateDue(dueDate);
				
			if(dueAmt<=0.0) {
				booksrepo.checkin(bookname);
				return "Book returned";
			}
			else {
				return "Due amount to be paid (Fine amount: 5rs/day) :"+ dueAmt;	
			}
			
		}
		catch (Exception e) {
			e.printStackTrace();
			return "Exception in CheckIn() method";
		}
	}

	private double calculateDue(String dueDate) throws Exception {
		try {
			if(dueDate!=null) {
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
				LocalDate date=LocalDate.parse(dueDate,formatter); 
				Period period= Period.between(date, LocalDate.now());
				int dueDays=period.getDays();
				return (dueDays*5.0);
			}
			return 0.0;
		}
		catch (Exception e) {
			throw new Exception("Exception in CheckIn() method");
		}
	}

	public List<Books> findBooksByGenre(String genre) {
		return booksrepo.findByGenre(genre);
	}

}
