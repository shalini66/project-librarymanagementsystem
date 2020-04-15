package com.capgemini.librarymanagementsystemjdbc.dto;

import java.util.Date;

import lombok.Data;
@Data
public class BookBean {
	private String bookName;
	private int bookId;
	private String bookAuthor;
	private String bookCategory;
	private String bookPublisherName;
	private int bookCopies;
	private int bookCopyRight;
	private Date dateAdded;
	private String status;
	
}
