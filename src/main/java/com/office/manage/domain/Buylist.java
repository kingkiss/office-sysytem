package com.office.manage.domain;

import javax.xml.crypto.Data;

public class Buylist {
	private int buyinfo_id;
	private int buyinfo_product_id;
	private int buyinfo_user_id;
	private int buyinfo_num;
	private float buyinfo_allprice;
	private Data buyinfo_time;
	public int getBuyinfo_id() {
		return buyinfo_id;
	}
	public void setBuyinfo_id(int buyinfo_id) {
		this.buyinfo_id = buyinfo_id;
	}
	public int getBuyinfo_product_id() {
		return buyinfo_product_id;
	}
	public void setBuyinfo_product_id(int buyinfo_product_id) {
		this.buyinfo_product_id = buyinfo_product_id;
	}
	public int getBuyinfo_user_id() {
		return buyinfo_user_id;
	}
	public void setBuyinfo_user_id(int buyinfo_user_id) {
		this.buyinfo_user_id = buyinfo_user_id;
	}
	public int getBuyinfo_num() {
		return buyinfo_num;
	}
	public void setBuyinfo_num(int buyinfo_num) {
		this.buyinfo_num = buyinfo_num;
	}
	public float getBuyinfo_allprice() {
		return buyinfo_allprice;
	}
	public void setBuyinfo_allprice(float buyinfo_allprice) {
		this.buyinfo_allprice = buyinfo_allprice;
	}
	public Data getBuyinfo_time() {
		return buyinfo_time;
	}
	public void setBuyinfo_time(Data buyinfo_time) {
		this.buyinfo_time = buyinfo_time;
	}
	
	
}
