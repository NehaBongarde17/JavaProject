package com.sunbeam.bookstore.entity;

import java.util.Scanner;

public class Book {
	private int bookid;
	private String title;
	private String author;
	private double price;
	
	public Book() {
		
	}

	public Book(int bookid, String title, String author, double price) {
		this.bookid = bookid;
		this.title = title;
		this.author = author;
		this.price = price;
	}

	public int getBookid() {
		return bookid;
	}

	public void setBookid(int bookid) {
		this.bookid = bookid;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
	@Override
	public String toString() {
		return "[Bookid = "+bookid+", Title = "+title+", Author = "+author+", Price = "+price+"]";
	}
	public void accept(Scanner sc) {
		sc.nextLine();
		System.out.print("Enter the title of book : ");
		title = sc.nextLine();
		System.out.print("Enter the author : ");
		author = sc.nextLine();
		//sc.nextLine();
		System.out.print("Enter the price : ");
		price = sc.nextDouble();
	}
	
}
