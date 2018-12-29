package com.office.manage.domain;

import java.util.Date;

public class BorrowList {
	private int borrowinfo_id;
	private int borrowinfo_user_id;
	private int borrowinfo_product_id;
	private int borrowinfo_num;
	private float borrwoinfo_price;
	private Date borrowinfo_time;
	private boolean borrowinfo_return;
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
	public float getBorrwoinfo_price() {
		return borrwoinfo_price;
	}
	public void setBorrwoinfo_price(float borrwoinfo_price) {
		this.borrwoinfo_price = borrwoinfo_price;
	}
	public Date getBorrowinfo_time() {
		return borrowinfo_time;
	}
	public void setBorrowinfo_time(Date borrowinfo_time) {
		this.borrowinfo_time = borrowinfo_time;
	}
	public boolean isBorrowinfo_return() {
		return borrowinfo_return;
	}
	public void setBorrowinfo_return(boolean borrowinfo_return) {
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
	
	
}
