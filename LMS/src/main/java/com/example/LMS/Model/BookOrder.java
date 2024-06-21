package com.example.LMS.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Data
public class BookOrder {
	@Id
	@Getter @Setter
	@Column(name = "idbook")
	int idbook;
	@Getter @Setter
	@Column(name = "quantity")
	int quantity;
	@Getter @Setter
	@Column(name = "bookname")
	String bookname;
}
