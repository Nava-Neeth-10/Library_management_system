package com.example.LMS.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.LMS.Model.Books;
import com.example.LMS.Service.LMSService;
import com.google.gson.Gson;

@RestController
@RequestMapping("/lms")
public class LMSController {
	
	@Autowired
	LMSService lmsservice;
	@Autowired
	Gson gson;
	
	@GetMapping("/allBooks")
	public String displayBooks(){
		List<Books> list=lmsservice.displayBooks();
		String jsonBookList = gson.toJson(list);
		return jsonBookList;	
	}
	
	@PutMapping("/addBooks")
	public void addBooks(@RequestParam String bookname,@RequestParam String author,@RequestParam String genre) throws Exception {
		lmsservice.addBooks(bookname,author,genre);
	}
	
	@GetMapping("/findBook")
	public String findBook (@RequestParam String searchword) {
		List<Books> list=lmsservice.findbook(searchword);
		String jsonBookList = gson.toJson(list);
		return jsonBookList;
	}
	
	@GetMapping("/Checkout")
	public String checkout (@RequestParam String bookname) {
		return lmsservice.checkout(bookname);	
	}
	
	@PutMapping("/Checkin")
	public String checkin (@RequestParam String bookname) {
		return lmsservice.checkin(bookname);	
	}
	
	@GetMapping("/findByGenre")
	public String findBooksByGenre(@RequestParam String genre){
		List<Books> list=lmsservice.findBooksByGenre(genre);
		String jsonBookList = gson.toJson(list);
		return jsonBookList;	
	}
}
