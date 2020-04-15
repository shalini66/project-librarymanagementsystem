package com.capgemini.librarymanagementsystemhibernate.dao;

import java.util.LinkedList;
import java.util.List;

import com.capgemini.librarymanagementsystemhibernate.dto.BookBean;
import com.capgemini.librarymanagementsystemhibernate.dto.BookIssueDetails;
import com.capgemini.librarymanagementsystemhibernate.dto.BorrowedBooks;
import com.capgemini.librarymanagementsystemhibernate.dto.RequestDetails;
import com.capgemini.librarymanagementsystemhibernate.dto.UsersBean;

public interface UsersDAO {
	boolean register(UsersBean info);
	UsersBean auth(String email, String password);
	boolean addBook(BookBean book);
	BookBean searchBookByTitle(String bname);
	BookBean searchBookByAuthor(String bAuthor);
	BookBean searchBookById(int bid);
	boolean updateBook(BookBean bean);
	boolean removeBook(int bid);
	LinkedList<Integer> getBookIds();
	LinkedList<BookBean> getBooksInfo();
	List<UsersBean> showUsers();
	boolean request(int uId, int bId);
	List<RequestDetails> showRequest();
	boolean issueBook(int bId,int uId);
	//BookBean borrowBook(int bId);
	boolean returnBook(int bId, int uId);
	List<BorrowedBooks> borrowedBook(int uId);
	LinkedList<BookIssueDetails> bookHistoryDetails(int uId);

}
