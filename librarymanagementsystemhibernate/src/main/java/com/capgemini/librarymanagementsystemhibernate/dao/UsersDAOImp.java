package com.capgemini.librarymanagementsystemhibernate.dao;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Parameter;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import com.capgemini.librarymanagementsystemhibernate.dto.BookBean;
import com.capgemini.librarymanagementsystemhibernate.dto.BookIssueDetails;
import com.capgemini.librarymanagementsystemhibernate.dto.BorrowedBooks;
import com.capgemini.librarymanagementsystemhibernate.dto.RequestBean;
import com.capgemini.librarymanagementsystemhibernate.dto.RequestDetails;
import com.capgemini.librarymanagementsystemhibernate.dto.UsersBean;
import com.capgemini.librarymanagementsystemhibernate.exception.UsersException;

public class UsersDAOImp implements UsersDAO{
	EntityManagerFactory factory = null;
	EntityManager manager = null;
	EntityTransaction transaction = null;
	public boolean register(UsersBean info) {

		try {
			factory = Persistence.createEntityManagerFactory("TestPersistence");
			manager = factory.createEntityManager();
			transaction = manager.getTransaction();
			transaction.begin();
			UsersBean bean = manager.find(UsersBean.class, info.getEmail());
			if(bean== null) {
				System.out.println("Record Saved");
			} else {
				throw new UsersException("User already Exist");
			}
			manager.persist(info);
			transaction.commit();
		} catch (Exception e) {

			e.printStackTrace();
			transaction.rollback();
		}
		manager.close();
		factory.close();
		return false;
	}

	public UsersBean auth(String email, String password) {
		try {
			factory = Persistence.createEntityManagerFactory("TestPersistence");
			manager = factory.createEntityManager();
			transaction = manager.getTransaction();
			transaction.begin();
			UsersBean bean2= manager.find(UsersBean.class, email);
			bean2.setEmail(email);
			manager.persist(bean2);
			if(bean2.getPassword().equals(password)) {
				return bean2;
			}
			transaction.commit();
		} catch (Exception e) {

			e.printStackTrace();
			transaction.rollback();
		}
		manager.close();
		factory.close();
		return null;
	}

	public boolean addBook(BookBean book) {
		try {
			factory = Persistence.createEntityManagerFactory("TestPersistence");
			manager = factory.createEntityManager();
			transaction = manager.getTransaction();
			transaction.begin();
			BookBean bean = manager.find(BookBean.class, book.getBid());
			manager.persist(book);
			transaction.commit();
		} catch (Exception e) {

			e.printStackTrace();
			transaction.rollback();
		}
		manager.close();
		factory.close();
		return false;
	}

	public BookBean searchBookByTitle(String bname) {
		EntityManagerFactory factory = null;
		EntityManager manager = null;
		EntityTransaction transaction = null;
		try {
			factory = Persistence.createEntityManagerFactory("TestPersistence");
			manager = factory.createEntityManager();
			transaction = manager.getTransaction();
			transaction.begin();

			String jpql = "select * from bookbean m where m.bname =:mbname";
			TypedQuery<BookBean> query = (TypedQuery<BookBean>) manager.createNativeQuery(jpql, BookBean.class);
			query.setParameter("mbname", bname);
			List<BookBean> recordList = query.getResultList();
			Iterator it = recordList.iterator();
			while (it.hasNext()){
				BookBean bean = (BookBean) it.next();
				System.out.println("----------------------");
				System.out.print("Id:---"+bean.getBid());
				System.out.print(" Name:---"+bean.getBname());
				System.out.println(" Author:---"+bean.getAuthor());
				System.out.println("PublisherName:---"+bean.getPublishername());
				System.out.println("Category:---"+bean.getCategory());
			}
			transaction.commit();

		} catch (Exception e) {

			e.printStackTrace();
			transaction.rollback();
		}
		manager.close();
		factory.close();
		return null;
	}

	public BookBean searchBookByAuthor(String bAuthor) {
		EntityManagerFactory factory = null;
		EntityManager manager = null;
		EntityTransaction transaction = null;
		try {
			factory = Persistence.createEntityManagerFactory("TestPersistence");
			manager = factory.createEntityManager();
			transaction = manager.getTransaction();
			transaction.begin();

			String jpql = "select * from bookbean m where m.author =:mauthor";
			TypedQuery<BookBean> query = (TypedQuery<BookBean>) manager.createNativeQuery(jpql, BookBean.class);
			query.setParameter("mauthor", bAuthor);
			List<BookBean> recordList = query.getResultList();
			Iterator it = recordList.iterator();
			while (it.hasNext()){
				BookBean bean = (BookBean) it.next();
				System.out.println("----------------------");
				System.out.print("Id:---"+bean.getBid());
				System.out.print(" Name:---"+bean.getBname());
				System.out.println(" Author:---"+bean.getAuthor());
				System.out.println("PublisherName:---"+bean.getPublishername());
				System.out.println("Category:---"+bean.getCategory());
			}
			transaction.commit();
		} catch (Exception e) {

			e.printStackTrace();
			transaction.rollback();
		}
		manager.close();
		factory.close();
		return null;
	}
	public BookBean searchBookById(int bid) {

		try {
			factory = Persistence.createEntityManagerFactory("TestPersistence");
			manager = factory.createEntityManager();
			transaction = manager.getTransaction();
			transaction.begin();
			BookBean bean = manager.find(BookBean.class, bid);
			manager.persist(bean);
			transaction.commit();
			return bean;
		} catch (Exception e) {

			e.printStackTrace();
			transaction.rollback();
		}
		manager.close();
		factory.close();
		return null;
	}

	public boolean updateBook(BookBean bean) {

		try {
			factory = Persistence.createEntityManagerFactory("TestPersistence");
			manager = factory.createEntityManager();
			transaction = manager.getTransaction();
			transaction.begin();
			BookBean bean2 = manager.find(BookBean.class, bean.getBid());
			bean2.setBname(bean.getBname());
			manager.persist(bean);
			System.out.println("updated");
			transaction.commit();

		} catch (Exception e) {
			e.printStackTrace();
			transaction.rollback();
		}
		return false;
	}

	public boolean removeBook(int bid) {

		try {
			factory = Persistence.createEntityManagerFactory("TestPersistence");
			manager = factory.createEntityManager();
			transaction = manager.getTransaction();
			transaction.begin();
			BookBean bean = manager.find(BookBean.class, bid);
			bean.setBid(bid);
			manager.remove(bean);
			transaction.commit();

		} catch (Exception e) {
			e.printStackTrace();
			transaction.rollback();
		}
		manager.close();
		factory.close();
		return false;
	}

	public LinkedList<Integer> getBookIds() {

		try {
			factory = Persistence.createEntityManagerFactory("TestPersistence");
			manager = factory.createEntityManager();
			transaction = manager.getTransaction();
			transaction.begin();
			String jpql = "select * from bookbean";
			Query query =  manager.createNativeQuery(jpql, BookBean.class);
			List<Integer> recordList = query.getResultList();
			Iterator it = recordList.iterator();
			while (it.hasNext()){
				BookBean bean = (BookBean) it.next();
				System.out.println("----------------------");
				System.out.println("Id:---"+bean.getBid());
			}
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			transaction.rollback();
		}
		manager.close();
		factory.close();
		return null;
	}

	public LinkedList<BookBean> getBooksInfo() {

		try {
			factory = Persistence.createEntityManagerFactory("TestPersistence");
			manager = factory.createEntityManager();
			transaction = manager.getTransaction();
			transaction.begin();
			String jpql = "select * from bookbean";
			TypedQuery<BookBean> query = (TypedQuery<BookBean>) manager.createNativeQuery(jpql, BookBean.class);
			List<BookBean> recordList = query.getResultList();
			Iterator it = recordList.iterator();
			while (it.hasNext()){
				BookBean bean = (BookBean) it.next();
				System.out.println("----------------------");
				System.out.print("Id:---"+bean.getBid());
				System.out.print(" Name:---"+bean.getBname());
				System.out.println(" Author:---"+bean.getAuthor());
				System.out.println("PublisherName:---"+bean.getPublishername());
				System.out.println("Category:---"+bean.getCategory());
			}
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			transaction.rollback();
		}
		manager.close();
		factory.close();
		return null;
	}

	public List<UsersBean> showUsers() {

		try {
			factory = Persistence.createEntityManagerFactory("TestPersistence");
			manager = factory.createEntityManager();
			transaction = manager.getTransaction();
			transaction.begin();
			String jpql = "select * from usersbean";
			TypedQuery<UsersBean> query = (TypedQuery<UsersBean>) manager.createNativeQuery(jpql, UsersBean.class);
			List<UsersBean> recordList = query.getResultList();
			Iterator it = recordList.iterator();
			while (it.hasNext()){
				UsersBean bean = (UsersBean) it.next();
				System.out.println("----------------------");
				System.out.print("Id:---"+bean.getId());
				System.out.print(" Name:---"+bean.getName());
				System.out.println(" Email:---"+bean.getEmail());
				System.out.println("Mobile:---"+bean.getMobile());
				System.out.println("Role:---"+bean.getRole());
			}
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			transaction.rollback();
		}
		manager.close();
		factory.close();
		return null;
	}

	public boolean request(int uId, int bId) {
		try {
			factory = Persistence.createEntityManagerFactory("TestPersistence");
			manager = factory.createEntityManager();
			transaction = manager.getTransaction();
			transaction.begin();
			String jpql = "select count(*) as b.uId from borrowed_books b where b.uId=:buId and b.bId=:bbId and b.email=:(select u.email from users u where u.uId=:uuId)";
			TypedQuery<BorrowedBooks> query = (TypedQuery<BorrowedBooks>) manager.createNativeQuery(jpql, BorrowedBooks.class);
			query.setParameter("buId", uId);
			query.setParameter("bbId", bId);
			query.setParameter("uuId", uId);
			List<BorrowedBooks> books = query.getResultList();
			Iterator<BorrowedBooks> iterator = books.iterator();
			if(iterator.hasNext()) {
				String sql2 = "select b.uId from borrowed_books b";
				Query query2 = manager.createNativeQuery(sql2);
				Parameter<?> id = query2.getParameter("uId");
				if(id == null) {
					String sql3 = "select count(*) as b.userId from book_issue_details b where b.userId=:buserId";
					Query query3 = manager.createNativeQuery(sql3, BookIssueDetails.class);
					query3.setParameter("buserId", uId);
//					List<BookIssueDetails> issueDetails = query3.getResultList();4
//					Iterator<BookIssueDetails> iterator2 = issueDetails.iterator();
//					if(iterator2.hasNext()) {
//						String sql4 = 
//					}
				}
			}


			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			transaction.rollback();
		}
		manager.close();
		factory.close();

		return false;
	}

	public List<RequestDetails> showRequest() {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean issueBook(int bId, int uId) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean returnBook(int bId, int uId) {
		// TODO Auto-generated method stub
		return false;
	}

	public List<BorrowedBooks> borrowedBook(int uId) {
		// TODO Auto-generated method stub
		return null;
	}

	public LinkedList<BookIssueDetails> bookHistoryDetails(int uId) {
		// TODO Auto-generated method stub
		return null;
	}

}