package com.capgemini.librarymanagementsystemjdbc.controller;

import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import com.capgemini.librarymanagementsystemjdbc.dto.BookBean;
import com.capgemini.librarymanagementsystemjdbc.dto.BookIssueDetails;
import com.capgemini.librarymanagementsystemjdbc.dto.BorrowedBooks;
import com.capgemini.librarymanagementsystemjdbc.dto.RequestDetails;
import com.capgemini.librarymanagementsystemjdbc.dto.UsersBean;
import com.capgemini.librarymanagementsystemjdbc.exception.LMSException;
import com.capgemini.librarymanagementsystemjdbc.factory.LibraryFactory;
import com.capgemini.librarymanagementsystemjdbc.service.UsersService;
import com.capgemini.librarymanagementsystemjdbc.validation.Validation;

public class Controller {
	public static void main(String[] args) {
		doReg();
	}
	public static void doReg() {
		boolean flag = false;

		int regId = 0; 
		String regFirstName = null; 
		String regLastName = null; 
		String regMobile = null;
		String regEmail = null;
		String regPassword = null;

		int addBookId = 0;
		String addBookName = null; 
		String addBookAuthorName = null; 
		String addBookCategory = null;
		String addBookPublisher = null;

		int updateBookId = 0;
		String updateBookName = null; 
		String updateBookAuthorName = null; 
		String updateBookCategory = null;
		String updateBookPublisher = null;

		boolean loginStatus = true;
		Validation validation = new Validation();
		Scanner scanner = new Scanner(System.in);
		do {
			System.out.println("----------WELCOME TO LIBRARY-----------");
			System.out.println("Press 1 to Register");
			System.out.println("Press 2 to Login");
			System.out.println("Press 3 to EXIT");
			System.out.println("---------------------------------------");

			UsersService service1= LibraryFactory.getUsersService();
				try {
					int choice = scanner.nextInt();
					switch(choice) {
					case 1:
						do {
							try {
								System.out.println("Enter ID :");
								regId = scanner.nextInt();
								validation.validatedId(regId);
								flag = true;
							} catch (InputMismatchException e) {
								flag = false;
								System.err.println("Id should contains only digits");
							} catch (LMSException e) {
								flag = false;
								System.err.println(e.getMessage());
							}
						} while (!flag);

						do {
							try {
								System.out.println("Enter First Name :");
								regFirstName = scanner.next();
								validation.validatedName(regFirstName);
								flag = true;
							} catch (InputMismatchException e) {
								flag = false;
								System.err.println("Name should contains only Alphabates");
							} catch (LMSException e) {
								flag = false;
								System.err.println(e.getMessage());
							}
						} while (!flag);

						do {
							try {
								System.out.println("Enter Email :");
								regEmail = scanner.next();
								validation.validatedEmail(regEmail);
								flag = true;
							} catch (InputMismatchException e) {
								flag = false;
								System.err.println("Email should be proper ");
							} catch (LMSException e) {
								flag = false;
								System.err.println(e.getMessage());
							}
						} while (!flag);

						do {
							try {
								System.out.println("Enter Password :");
								regPassword = scanner.next();
								validation.validatedPassword(regPassword);
								flag = true;
							} catch (InputMismatchException e) {
								flag = false;
								System.err.println("Enter correct Password ");
							} catch (LMSException e) {
								flag = false;
								System.err.println(e.getMessage());
							}
						} while (!flag);

						do {
							try {
								System.out.println("Enter Mobile :");
								regMobile = scanner.next();
								validation.validatedMobile(regMobile);
								flag = true;
							} catch (InputMismatchException e) {
								flag = false;
								System.err.println("Mobile Number  should contains only numbers");
							} catch (LMSException e) {
								flag = false;
								System.err.println(e.getMessage());
							}
						} while (!flag);
						System.out.println("Enter the role");
						String regRole = scanner.next();

						UsersBean ai = new UsersBean();
						ai.setId(regId);
						ai.setName(regFirstName);
						ai.setEmail(regEmail);
						ai.setPassword(regPassword);
						ai.setMobile(regMobile);
						ai.setRole(regRole);
						boolean check=service1.register(ai);
						if(check) {
							System.out.println("Registered");
						}else {
							System.out.println("Already user is registered");
						}
						break;
					case 2:
						System.out.println("Enter Email for Login");
						String email=scanner.next();
						System.out.println("Enter Password");
						String password=scanner.next();
						try {
							UsersBean loginInfo=service1.auth(email, password);
							if(loginInfo.getEmail().equals(email) && loginInfo.getPassword().equals(password)) {
								System.out.println("Logged In");
							}
							String role = loginInfo.getRole();
							if(role.equalsIgnoreCase("admin")) {
								do {
									try {
										System.out.println("------------------------------------");
										System.out.println("Press 1 to Add book");
										System.out.println("Press 2 to Update the book");
										System.out.println("Press 3 to Remove book");
										System.out.println("Press 4 to get All the Book Ids");
										System.out.println("Press 5 to Search the Book by Id");
										System.out.println("Press 6 to Search the Book by Author");
										System.out.println("Press 7 to Search the Book by Title");
										System.out.println("Press 8 to Get All the Books Information");
										System.out.println("Press 9 to Issue Book");
										System.out.println("Press 10 to Show Users");
										System.out.println("Press 11 to Show Requests");
										System.out.println("Press 12 to check user book history");
										System.out.println("Press 13 to Log out");
										System.out.println("------------------------------------");
										int choice1 = scanner.nextInt();
										switch (choice1) {
										case 1:

											do {
												try {
													System.out.println("Enter Book-Id:");
													addBookId=scanner.nextInt();
													validation.validatedId(addBookId);
													flag = true;
												} catch (InputMismatchException e) {
													flag = false;
													System.err.println("Id should contains only digits");
												} catch (LMSException e) {
													flag = false;
													System.err.println(e.getMessage());
												}
											} while (!flag);


											do {
												try {
													System.out.println("Enter Book Name :");
													addBookName=scanner.next();
													validation.validatedName(addBookName);
													flag = true;
												} catch (InputMismatchException e) {
													flag = false;
													System.err.println("Name should contains only Alphabates");
												} catch (LMSException e) {
													flag = false;
													System.err.println(e.getMessage());
												}
											} while (!flag);

											do {
												try {
													System.out.println("Enter Author Name :");
													addBookAuthorName=scanner.next();
													validation.validatedName(addBookAuthorName);
													flag = true;
												} catch (InputMismatchException e) {
													flag = false;
													System.err.println("Name should contains only Alphabates");
												} catch (LMSException e) {
													flag = false;
													System.err.println(e.getMessage());
												}
											} while (!flag);

											do {
												try {
													System.out.println("Enter Category Name :");
													addBookCategory=scanner.next();
													validation.validatedName(addBookCategory);
													flag = true;
												} catch (InputMismatchException e) {
													flag = false;
													System.err.println("Name should contains only Alphabates");
												} catch (LMSException e) {
													flag = false;
													System.err.println(e.getMessage());
												}
											} while (!flag);

											do {
												try {
													System.out.println("Enter Publisher Name :");
													addBookPublisher=scanner.next();
													validation.validatedName(addBookPublisher);
													flag = true;
												} catch (InputMismatchException e) {
													flag = false;
													System.err.println("Name should contains only Alphabates");
												} catch (LMSException e) {
													flag = false;
													System.err.println(e.getMessage());
												}
											} while (!flag);
											
											System.out.println("Enter no of copies");
											int copies = scanner.nextInt();
											System.out.println("Enter copyright year");
											int copyRight = scanner.nextInt();
											System.out.println("Enter status");
											String status = scanner.next();
											
											BookBean bi =new BookBean();
											bi.setBookId(addBookId);
											bi.setBookName(addBookName);
											bi.setBookAuthor(addBookAuthorName);
											bi.setBookCategory(addBookCategory);
											bi.setBookPublisherName(addBookPublisher);
											bi.setBookCopies(copies);
											bi.setBookCopyRight(copyRight);
											bi.setStatus(status);
											boolean check2=service1.addBook(bi);
											if(check2) {
												System.out.println("Added Book");
											}else {
												System.out.println("Book not added");
											}
											break;	
										case 2:
											do {
												try {
													System.out.println("Enter the updated id :");
													updateBookId=scanner.nextInt();
													validation.validatedId(updateBookId);
													flag = true;
												} catch (InputMismatchException e) {
													flag = false;
													System.err.println("Id should contains only digits");
												} catch (LMSException e) {
													flag = false;
													System.err.println(e.getMessage());
												}
											} while (!flag);


											do {
												try {
													System.out.println("Enter BookName to be Updated :");
													updateBookName=scanner.next();
													validation.validatedName(updateBookName);
													flag = true;
												} catch (InputMismatchException e) {
													flag = false;
													System.err.println("Name should contains only Alphabates");
												} catch (LMSException e) {
													flag = false;
													System.err.println(e.getMessage());
												}
											} while (!flag);

											do {
												try {
													System.out.println("Enter BookAuthor to be Updtaed :");
													updateBookAuthorName=scanner.next();
													validation.validatedName(updateBookAuthorName);
													flag = true;
												} catch (InputMismatchException e) {
													flag = false;
													System.err.println("Name should contains only Alphabates");
												} catch (LMSException e) {
													flag = false;
													System.err.println(e.getMessage());
												}
											} while (!flag);

//											do {
//												try {
//													System.out.println("Enter BookCategory to be Updtaed :");
//													updateBookCategory=scanner.next();
//													validation.validatedName(updateBookCategory);
//													flag = true;
//												} catch (InputMismatchException e) {
//													flag = false;
//													System.err.println("Name should contains only Alphabates");
//												} catch (LMSException e) {
//													flag = false;
//													System.err.println(e.getMessage());
//												}
//											} while (!flag);
//
//											do {
//												try {
//													System.out.println("Enter BookPublisher to be Updtaed :");
//													updateBookPublisher=scanner.next();
//													validation.validatedName(updateBookPublisher);
//													flag = true;
//												} catch (InputMismatchException e) {
//													flag = false;
//													System.err.println("Name should contains only Alphabates");
//												} catch (LMSException e) {
//													flag = false;
//													System.err.println(e.getMessage());
//												} 
//											} while (!flag);
//										
											BookBean bean2 = new BookBean();
											bean2.setBookId(updateBookId);
											bean2.setBookName(updateBookName);
											bean2.setBookAuthor(updateBookAuthorName);
//											bean2.setBookCategory(updateBookCategory);
//											bean2.setBookPublisherName(updateBookPublisher);
										
											boolean updated = service1.updateBook(bean2);
											if (updated) {
												System.out.println("Book is Updated");
											} else {
												System.out.println("Book is not Updated");
											}
											break;
										case 3:
											int removeId = 0;
											do {
												try {
													System.out.println("Enter Book-Id to Remove:");
													removeId=scanner.nextInt();
													validation.validatedId(removeId);
													flag = true;
												} catch (InputMismatchException e) {
													flag = false;
													System.err.println("Id should contains only digits");
												} catch (LMSException e) {
													flag = false;
													System.err.println(e.getMessage());
												}
											} while (!flag);
											
											
											boolean check3=service1.removeBook(removeId);
											if(check3) {
												System.out.println("Removed Book");
											}else {
												System.out.println("Book not removed");
											}
											break;

										case 4:
											LinkedList<BookBean> info = service1.getBookIds();
											for (BookBean bookBean : info) {

												if (bookBean != null) {
													System.out.println("-------------------------------------");
													System.out.println("Book_Id is-->"+bookBean.getBookId());
												} else {
													System.out.println("Books Ids is not present");
												}
											}
											break;
										case 5:
											int id = 0;
											do {
												try {
													System.out.println("Search the book by the Book-Id:");
													id=scanner.nextInt();
													validation.validatedId(id);
													flag = true;
												} catch (InputMismatchException e) {
													flag = false;
													System.err.println("Id should contains only digits");
												} catch (LMSException e) {
													flag = false;
													System.err.println(e.getMessage());
												}
											} while (!flag);

											BookBean bean4 = new BookBean();
											bean4.setBookId(id);
											List<BookBean> bookid = (List<BookBean>) service1.searchBookById(id);
											for (BookBean bookBean : bookid) {

												if (bookBean != null) {
													System.out.println("----------------------------------------");
													System.out.println("Book_Id is-->"+bookBean.getBookId());
													System.out.println("Book_Name is-->"+bookBean.getBookName());
													System.out.println("Book_Author is-->"+bookBean.getBookAuthor());
													System.out.println("Book_PublisherName is-->"+bookBean.getBookPublisherName());
													System.out.println("Book_Category is-->"+bookBean.getBookCategory());
													System.out.println("----------------------------------------");
												} else {
													System.out.println("No books are available written by this author");
												}
											}
											break;
										case 6:
											String author = null;
											do {
												try {
													System.out.println("Search the book by the Author Name:");
													author=scanner.next();
													validation.validatedName(author);
													flag = true;
												} catch (InputMismatchException e) {
													flag = false;
													System.err.println("Name should contains only Alphabates");
												} catch (LMSException e) {
													flag = false;
													System.err.println(e.getMessage());
												}
											} while (!flag);

											BookBean bean5 = new BookBean();
											bean5.setBookAuthor(author);

											List<BookBean> bookauthor = (List<BookBean>) service1.searchBookByAuthor(author);
											for (BookBean bookBean : bookauthor) {

												if (bookBean != null) {
													System.out.println("----------------------------------------");
													System.out.println("Book_Id is-->"+bookBean.getBookId());
													System.out.println("Book_Name is-->"+bookBean.getBookName());
													System.out.println("Book_Author is-->"+bookBean.getBookAuthor());
													System.out.println("Book_PublisherName is-->"+bookBean.getBookPublisherName());
													System.out.println("Book_Category is-->"+bookBean.getBookCategory());
													System.out.println("----------------------------------------");
												} else {
													System.out.println("No books are available written by this author");
												}
											}
											break;
										case 7:
											String title = null;
											do {
												try {
													System.out.println("  Search the book by the Book_Title :");
													title=scanner.next();
													validation.validatedName(title);
													flag = true;
												} catch (InputMismatchException e) {
													flag = false;
													System.err.println("Name should contains only Alphabates");
												} catch (LMSException e) {
													flag = false;
													System.err.println(e.getMessage());
												}
											} while (!flag);
											
											BookBean bean6 = new BookBean();
											bean6.setBookAuthor(title);
											List<BookBean> booktitle = (List<BookBean>) service1.searchBookByTitle(title);
											for (BookBean bookBean : booktitle) {	
												if (bookBean != null) {
													System.out.println("----------------------------------------");
													System.out.println("Book_Id is-->"+bookBean.getBookId());
													System.out.println("Book_Name is-->"+bookBean.getBookName());
													System.out.println("Book_Author is-->"+bookBean.getBookAuthor());
													System.out.println("Book_PublisherName is-->"+bookBean.getBookPublisherName());
													System.out.println("Book_Category is-->"+bookBean.getBookCategory());
													System.out.println("----------------------------------------");
												} else {
													System.out.println("No books are available with this title.");
												}
											}
											break;

										case 8:
											LinkedList<BookBean> info1 = service1.getBooksInfo();
											for (BookBean bookBean : info1) {

												if (bookBean != null) {
													System.out.println("-------------------------------------");
													System.out.println("Book_Id is-->"+bookBean.getBookId());
													System.out.println("Book_Name is-->"+bookBean.getBookName());
													System.out.println("Book_Author is-->"+bookBean.getBookAuthor());
													System.out.println("Book_PublisherName is-->"+bookBean.getBookPublisherName());
													System.out.println("Book_Category is-->"+bookBean.getBookCategory());
													System.out.println("No_Of_Copies are-->"+bookBean.getBookCopies());
													System.out.println("CopyRight year-->"+bookBean.getBookCopyRight());
													System.out.println("DateAdded-->"+bookBean.getDateAdded());
													System.out.println("Book Status-->"+bookBean.getStatus());
													System.out.println("----------------------------------------------");
												} else {
													System.out.println("Books info is not present");
												}
											}
											break;

										case 9:
											System.out.println("Enter Book Id");
											int issueId=scanner.nextInt();
											System.out.println("Enter User Id");
											int userId = scanner.nextInt();
											boolean check4=service1.issueBook(issueId,userId);
											if(check4) {
												System.out.println("-----------------------------------------------");
												System.out.println("Book Issued");
											}else {
												System.out.println("-----------------------------------------------");
												System.out.println("Book not issued");
											}
											break;
										case 10:
											try {
												System.out.println("--------Users of Library are------------");

												List<UsersBean> usersInfo = service1.showUsers();
												for (UsersBean usersBean : usersInfo) {
													if (usersBean != null) {
														System.out.println("---------------------------------------");
														System.out.println("User_Id is ---->"+usersBean.getId());
														System.out.println("User Firstname is ---->"+usersBean.getName());
														System.out.println("User Email is ---->"+usersBean.getEmail());
														System.out.println("User Mobile is ---->"+usersBean.getMobile());
														System.out.println("User Role is ---->"+usersBean.getRole());
														System.out.println("---------------------------------------");
													} else {
														System.out.println("No user are there");
													}
												}
											} catch (Exception e) {
												System.out.println("No Users present in library");
											}
											break;
										case 11:
											try {
												System.out.println("Requests for Books are :");
												System.out.println("-----------------------------");
												List<RequestDetails> requestInfos = service1.showRequest();
												for (RequestDetails infos : requestInfos) {
													System.out.println("---------------------------------------");
													System.out.println("User id ---------- " + infos.getUserId());
													System.out.println("User Name----------- " + infos.getUserName());
													System.out.println("Book Id ------" + infos.getBookId());
													System.out.println("Book Name------" + infos.getBookName());
													System.out.println("---------------------------------------");

												}
											} catch (Exception e) {
												System.out.println("no books present in library");
											}
											break;
										case 12:
											System.out.println("Enter the User Id");
											int user_Id = scanner.nextInt();

											List<BookIssueDetails> uid = service1.bookHistoryDetails(user_Id);
											for (BookIssueDetails issueDetails : uid) {
												if(issueDetails != null) {
													System.out.println("-----------------------------------------------");
													System.out.println("No of books Borrowed :"+issueDetails.getUserId());
												} else {
													System.out.println("-----------------------------------------------");
													System.out.println("Respective user hasn't borrowed any books");
												}
											}
											break;
										case 13:
											doReg();

										default:
											System.out.println("Invalid Choice");
											break;
										}
									}   catch (InputMismatchException ex)   {
										System.out.println("Incorrect entry. Please input only a positive integer.");
										scanner.nextLine();
									}
								}while(true);

							}
							else if(loginInfo.getRole().equals("student")) {
								do {
									try {
										System.out.println("------------------------------------");
										System.out.println("Press 1 to Get All the Book-Ids");
										System.out.println("Press 2 to Search Book by Book-Id");
										System.out.println("Press 3 to Search Book by Author");
										System.out.println("Press 4 to Search Book by Title");
										System.out.println("Press 5 to Get All the Books Info");
										System.out.println("Press 6 to Request Book");
										System.out.println("Press 7 to View the Books Borrowed");
										System.out.println("Press 8 to Return Book");
										System.out.println("Press 9 to Main");
										System.out.println("------------------------------------");

										int choice2 = scanner.nextInt();
										switch (choice2) {
										case 1:
											LinkedList<BookBean> info = service1.getBookIds();
											for (BookBean bookBean : info) {

												if (bookBean != null) {
													System.out.println("-------------------------------------");
													System.out.println("Book_Id is-->"+bookBean.getBookId());
												} else {
													System.out.println("Books Ids is not present");
												}
											}
											break;
										case 2:
											System.out.println("Search the Book by the Book-Id:");
											int id = scanner.nextInt();

											BookBean bean4 = new BookBean();
											bean4.setBookId(id);
											List<BookBean> bookid = (List<BookBean>) service1.searchBookById(id);
											for (BookBean bookBean : bookid) {

												if (bookBean != null) {
													System.out.println("----------------------------------------");
													System.out.println("Book_Id is-->"+bookBean.getBookId());
													System.out.println("Book_Name is-->"+bookBean.getBookName());
													System.out.println("Book_Author is-->"+bookBean.getBookAuthor());
													System.out.println("Book_PublisherName is-->"+bookBean.getBookPublisherName());
													System.out.println("Book_Category is-->"+bookBean.getBookCategory());
													//System.out.println("No_Of_Copies are-->"+bookBean.getCopies());
													System.out.println("----------------------------------------");
												} else {
													System.out.println("No books are available written by this author");
												}
											}
											break;
										case 3:
											System.out.println("Search the Book by the Author Name :");
											String author = scanner.next();

											BookBean bean2 = new BookBean();
											bean2.setBookAuthor(author);
											List<BookBean> bookauthor = (List<BookBean>) service1.searchBookByAuthor(author);
											for (BookBean bookBean : bookauthor) {

												if (bookBean != null) {
													System.out.println("---------------------------------------");
													System.out.println("Book_Id is-->"+bookBean.getBookId());
													System.out.println("Book_Name is-->"+bookBean.getBookName());
													System.out.println("Book_Author is-->"+bookBean.getBookAuthor());
													System.out.println("Book_PublisherName is-->"+bookBean.getBookPublisherName());
													System.out.println("Book_Category is-->"+bookBean.getBookCategory());
													System.out.println("No_Of_Copies are-->"+bookBean.getBookCopies());
													System.out.println("---------------------------------------");
												} else {
													System.out.println("No books are available written by this author");
												}
											}
											break;
										case 4:
											System.out.println("Search the book by the Book Title :");
											String btitle = scanner.next();

											BookBean bean3 = new BookBean();
											bean3.setBookAuthor(btitle);
											List<BookBean> booktitle = (List<BookBean>) service1.searchBookByAuthor(btitle);
											for (BookBean bookBean : booktitle) {	
												if (bookBean != null) {
													System.out.println("---------------------------------------");
													System.out.println("Book_Id is-->"+bookBean.getBookId());
													System.out.println("Book_Name is-->"+bookBean.getBookName());
													System.out.println("Book_Author is-->"+bookBean.getBookAuthor());
													System.out.println("Book_PublisherName is-->"+bookBean.getBookPublisherName());
													System.out.println("Book_Category is-->"+bookBean.getBookCategory());
													System.out.println("No_Of_Copies are-->"+bookBean.getBookCopies());
													System.out.println("---------------------------------------");
												} else {
													System.out.println("No books are available with this title.");
												}
											}
											break;
										case 5:
											LinkedList<BookBean> info1 = service1.getBooksInfo();
											for (BookBean bookBean : info1) {

												if (bookBean != null) {
													System.out.println("---------------------------------------");
													System.out.println("Book_Id is-->"+bookBean.getBookId());
													System.out.println("Book_Name is-->"+bookBean.getBookName());
													System.out.println("Book_Author is-->"+bookBean.getBookAuthor());
													System.out.println("Book_PublisherName is-->"+bookBean.getBookPublisherName());
													System.out.println("Book_Category is-->"+bookBean.getBookCategory());
													System.out.println("No_Of_Copies are-->"+bookBean.getBookCopies());
													System.out.println("---------------------------------------");
												} else {
													System.out.println("Books info is not presernt");
												}
											}
											break;
										case 6:
											System.out.println("Enter the Book Id:");
											int reqBookId= scanner.nextInt();
											System.out.println("Enter the user Id:");
											int reqUserId = scanner.nextInt();
											boolean requested = service1.request(reqUserId,reqBookId);
											if (requested != false) {
												System.out.println("-----------------------------------------------");
												System.out.println("Book is Requested");
											} else {
												System.out.println("-----------------------------------------------");
												System.out.println("Book is not Requested");
											}	
											break;
										case 7:
											System.out.println("Enter the user Id");
											int user_Id = scanner.nextInt();
											List<BorrowedBooks> borrowedBookList = service1.borrowedBook(user_Id);
											for (BorrowedBooks bookBean : borrowedBookList) {

												if (bookBean != null) {
													System.out.println("-----------------------------------------------");
													System.out.println("User_Id is-->"+bookBean.getBorrowedUserId());
													System.out.println("Book_Id is-->"+bookBean.getBorrowedBookId());
													System.out.println("Email Id is-->"+bookBean.getEmail());
												} else {
													System.out.println("-----------------------------------------------");
													System.out.println("No books are borrowed by the user");
												}
											}
											break;
										case 8:

											System.out.println("*****Returning a Book*****");
											System.out.println("Enter the Book-id to return :");
											int returnId= scanner.nextInt();
											System.out.println("Enter User-Id");
											int userId = scanner.nextInt();				
											boolean returned = service1.returnBook(returnId,userId);
											if (returned != false) {
												System.out.println("-----------------------------------------------");
												System.out.println("Book is Returned");
											} else {
												System.out.println("-----------------------------------------------");
												System.out.println("Book is not Returned");
											}	
											break;

										case 9:
											doReg();
										default:
											break;
										}
									}   catch (InputMismatchException ex)   {
										System.out.println("Incorrect entry. Please input only a positive integer.");
										scanner.nextLine();
									}
								} while(true);
							}

						}catch(Exception e) {
							System.out.println("Invalid Credentials");
							System.out.println("Try login again,Press 2 to login");
							//scanner.nextLine();
						}
						break;
					default:
						System.out.println("Exit");
						loginStatus = false;
					}
				} catch (InputMismatchException ex)   {
					System.out.println("Incorrect entry. Please input only a positive integer.");
					scanner.nextLine();
				}

			} while(loginStatus);
		//}while(true); //

	}
}
