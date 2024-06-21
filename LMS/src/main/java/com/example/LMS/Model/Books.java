package com.example.LMS.Model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Data

public class Books {
	@Id
	@Getter @Setter
	@Column(name = "is_bn_id")
	int isBnId;
	@Getter @Setter
	@Column(name = "book_name")
	String BookName;
	@Getter @Setter
	@Column(name = "author")
	String author;
	@Getter @Setter
	@Column(name = "genre")
	String genre;
	@Getter @Setter
	@Column(name = "availability")
	String availability;
	@Getter @Setter
	@Column(name = "dueDate")
	String dueDate;

}
