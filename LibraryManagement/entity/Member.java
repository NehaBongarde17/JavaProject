package com.sunbeam.bookstore.entity;

import java.util.Scanner;

public class Member {
	private int memberid;
	private String name;
	private String email;
	private String password;
	private String membershipType;
	
	public Member() {
		
	}

	public Member(int memberid, String name, String email, String password, String membershipType) {
		this.memberid = memberid;
		this.name = name;
		this.email = email;
		this.password = password;
		this.membershipType = membershipType;
	}

	public int getMemberid() {
		return memberid;
	}

	public void setMemberid(int memberid) {
		this.memberid = memberid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getMembershipType() {
		return membershipType;
	}

	public void setMembershipType(String membershipType) {
		this.membershipType = membershipType;
	}
	@Override
	public String toString() {
		return "Member [ memberid = "+memberid+", name = "+name+", email = "+email+", password = "+password+", membershipType = "+membershipType+"]";
	}
	public void accept(Scanner sc) {
		System.out.print("Enter the name : ");
		name = sc.next();
		System.out.print("Enter the email : ");
		email = sc.next();
		System.out.print("Enter the password : ");
		password = sc.next();
		System.out.print("Enter the membershipType : ");
		membershipType = sc.next();
	}
	
}
