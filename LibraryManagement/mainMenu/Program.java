package com.sunbeam.bookstore.mainmenu;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.sunbeam.bookstore.entity.Book;
import com.sunbeam.bookstore.entity.Member;
import com.sunbeam.bookstore.util.DbUtil;

public class Program {
	public static void exitSystem() {
		System.out.println("Exit....!");
	}
	
	public static void addMember(Scanner sc) {
		Member member = new Member();
		member.accept(sc);
		String sql = "insert into member(name,email,password,membershipType) values(?,?,?,?)";
		try(Connection connection = DbUtil.getConnection()){
			try(PreparedStatement insertStatement = connection.prepareStatement(sql)){
				insertStatement.setString(1,member.getName());
				insertStatement.setString(2,member.getEmail());
				insertStatement.setString(3,member.getPassword());
				insertStatement.setString(4,member.getMembershipType());
				insertStatement.executeUpdate();
				System.out.println("User registered successfully....");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public static void addBook(Scanner sc) {
		Book book = new Book();
		book.accept(sc);
		String sql = "insert into book(title,author,price) values(?,?,?)";
		try(Connection connection = DbUtil.getConnection()){
			try(PreparedStatement insertStatement = connection.prepareStatement(sql)){
				insertStatement.setString(1, book.getTitle());
				insertStatement.setString(2, book.getAuthor());
				insertStatement.setDouble(3, book.getPrice());
				insertStatement.executeUpdate();
				System.out.println("Book added successfully....");
			} 
		} catch (SQLException e) {
			e.printStackTrace();
		}
			
	}
	public static void displayBooks() {
		String sql = "select * from book";
		try(Connection connection = DbUtil.getConnection()){
			try(PreparedStatement selectStatement = connection.prepareStatement(sql)){
				ResultSet rs = selectStatement.executeQuery();
				List<Book> bookList = new ArrayList<Book>();
				while(rs.next()) {
					Book book = new Book();
					book.setBookid(rs.getInt(1));
					book.setTitle(rs.getString(2));
					book.setAuthor(rs.getString(3));
					book.setPrice(rs.getDouble(4));
					bookList.add(book);
				}
				bookList.forEach(b -> System.out.println(b));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public static Member loginPage(Scanner sc) {
		Member member = new Member();
		String sql = "select * from member where email = ? and password = ?";
		System.out.print("Enter the email : ");
		member.setEmail(sc.next());
		System.out.print("Enter the password : ");
		member.setPassword(sc.next());
		
		try(Connection connection = DbUtil.getConnection()){
			try(PreparedStatement selectStatement = connection.prepareStatement(sql)){
				selectStatement.setString(1,member.getEmail());
				selectStatement.setString(2,member.getPassword());
				ResultSet rs = selectStatement.executeQuery();
				if(rs.next()) {
					member.setMemberid(rs.getInt(1));
					member.setName(rs.getString(2));
					member.setMembershipType(rs.getString(5));
					return member;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	public static void borrowBook(Scanner sc, int memberid) {
		displayBooks();
		String sql = "insert into orders(bookid,memberid) values(?,?)";
		System.out.print("Enter the book id to borrow book : ");
		int bookid = sc.nextInt();
		try(Connection connection = DbUtil.getConnection()){
			try(PreparedStatement insertStatement = connection.prepareStatement(sql)){
				insertStatement.setInt(1, bookid);
				insertStatement.setInt(2, memberid);
				insertStatement.executeUpdate();
				System.out.println("Book is borrowed..!");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	} 
	
	public static void borrowedHistory(int memberid) {
		String sql = " select b.title,b.author,b.price from book b inner join orders o on b.bookid = o.bookid where o.memberid = ?";
		try(Connection connection = DbUtil.getConnection()){
			try(PreparedStatement selectStatement = connection.prepareStatement(sql)){
				selectStatement.setInt(1, memberid);
				ResultSet rs =selectStatement.executeQuery();
				while(rs.next()) {
					System.out.print(rs.getString(1) + " - ");
					System.out.print(rs.getString(2) + " - ");
					System.out.print(rs.getDouble(3));
					System.out.println();
				}
			} 
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static int menu(Scanner sc) {
		System.out.println("*-*-*-*-*-*-*-*-*-*-*-*-MENU-*-*-*-*-*-*-*-*-*-*-*-*");
		System.out.println("0. Exit");
		System.out.println("1. Register a new member");
		System.out.println("2. Add a new book");
		System.out.println("3. Display all books");
		System.out.println("4. Member Login");
		System.out.println("5. Borrow a book");
		System.out.println("6. View borrowed books history for logged-in member");
		System.out.println("7. Logout");
		System.out.print("Enter the choice : ");
		int choice = sc.nextInt();
		System.out.println("*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*");
		return choice;
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Member member = null;
		int choice;
		while((choice = menu(sc))!=0) {
			switch(choice) {
			case 0:
				exitSystem();
				break;
				
			case 1:
				addMember(sc);
				break;
				
			case 2:
				addBook(sc);
				break;
				
			case 3:
				displayBooks();
				break;
				
			case 4:
				if(member != null) {
					System.out.println("A member is already logged in. Please logout first.");
				} else {
					member = loginPage(sc);
					if(member != null) {
						System.out.println("Login Successful...");
					} else {
						System.out.println("Invalid credentials...");
					}
				}
				break;

				
			case 5:
				if(member != null) {
					borrowBook(sc,member.getMemberid());
				}
				else {
					System.out.println("You need to login first..");
				}
				break;
				
			case 6:
				if(member != null) {
					borrowedHistory(member.getMemberid());
				}
				else {
					System.out.println("You need to login first..");
				}
				
				break;
				
			case 7:
				member = null;
				System.out.println("Logout Successful");
				break;
				
			default:
				System.out.println("Invalid choice....");
				break;
			}
		}
		
	}

}
