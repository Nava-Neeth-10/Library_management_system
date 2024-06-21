package com.example.LMS.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.LMS.Model.Books;
import com.example.LMS.Service.OrderService;
import com.google.gson.Gson;

@RestController
@RequestMapping("/order")
public class OrderController {
	@Autowired
	OrderService orderservice;
	@Autowired
	Gson gson;
	
	@GetMapping("/checkAvailability")
	public String checkAvailability(@RequestParam String bookname){
		List<Books> list=orderservice.checkAvailability(bookname);
		String jsonBookList = gson.toJson(list);
		return jsonBookList;	
	}
	
	@GetMapping("/orderBook")
	public String orderBook(@RequestParam int id,@RequestParam int quantity,@RequestParam String bookname) throws Exception {
		try {
		orderservice.orderbook(id,quantity,bookname);
		return "Order placed";
		}
		catch(Exception e) {
			return e.getMessage();
		}
	}

}
