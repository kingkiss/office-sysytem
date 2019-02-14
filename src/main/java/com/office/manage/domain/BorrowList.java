package com.office.manage.domain;

import java.util.Date;

public class BorrowList {
	private int borrowinfo_id;
	private int borrowinfo_user_id;
	private String borrowinfo_user_truename;
	private int borrowinfo_product_id;
	private String borrowinfo_product_name;
	private int borrowinfo_num;
	private float borrowinfo_product_price;
	private String borrowinfo_time;
	private int borrowinfo_return;
	private int borrowinfo_return_num;
	private float borrowinfo_missprice;
	public int getBorrowinfo_id() {
		return borrowinfo_id;
	}
	public void setBorrowinfo_id(int borrowinfo_id) {
		this.borrowinfo_id = borrowinfo_id;
	}
	public int getBorrowinfo_user_id() {
		return borrowinfo_user_id;
	}
	public void setBorrowinfo_user_id(int borrowinfo_user_id) {
		this.borrowinfo_user_id = borrowinfo_user_id;
	}
	public int getBorrowinfo_product_id() {
		return borrowinfo_product_id;
	}
	public void setBorrowinfo_product_id(int borrowinfo_product_id) {
		this.borrowinfo_product_id = borrowinfo_product_id;
	}
	public int getBorrowinfo_num() {
		return borrowinfo_num;
	}
	public void setBorrowinfo_num(int borrowinfo_num) {
		this.borrowinfo_num = borrowinfo_num;
	}
	public float getBorrowinfo_product_price() {
		return borrowinfo_product_price;
	}
	public void setBorrwoinfo_product_price(float borrowinfo_product_price) {
		this.borrowinfo_product_price = borrowinfo_product_price;
	}
	public String getBorrowinfo_time() {
		return borrowinfo_time;
	}
	public void setBorrowinfo_time(String borrowinfo_time) {
		this.borrowinfo_time = borrowinfo_time;
	}
	public int getBorrowinfo_return() {
		return borrowinfo_return;
	}
	public void setBorrowinfo_return(int borrowinfo_return) {
		this.borrowinfo_return = borrowinfo_return;
	}
	public int getBorrowinfo_return_num() {
		return borrowinfo_return_num;
	}
	public void setBorrowinfo_return_num(int borrowinfo_return_num) {
		this.borrowinfo_return_num = borrowinfo_return_num;
	}
	public float getBorrowinfo_missprice() {
		return borrowinfo_missprice;
	}
	public void setBorrowinfo_missprice(float borrowinfo_missprice) {
		this.borrowinfo_missprice = borrowinfo_missprice;
	}

	public String getBorrowinfo_user_truename() {
		return borrowinfo_user_truename;
	}

	public void setBorrowinfo_user_truename(String borrowinfo_user_truename) {
		this.borrowinfo_user_truename = borrowinfo_user_truename;
	}

	public String getBorrowinfo_product_name() {
		return borrowinfo_product_name;
	}

	public void setBorrowinfo_product_name(String borrowinfo_product_name) {
		this.borrowinfo_product_name = borrowinfo_product_name;
	}
}
