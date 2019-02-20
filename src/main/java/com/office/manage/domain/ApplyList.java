package com.office.manage.domain;

import java.util.Date;

public class ApplyList {
	private int apply_id;
	private int apply_user_id;
	private String apply_user_truename;
	private String apply_user_name;
	private int apply_product_id;
	private String  apply_product_name;
	private int apply_num;
	private float apply_product_price;
	private String apply_time;
	private int apply_passman_id;
	private String apply_passman_name;
	private int apply_pass;


	public int getApply_id() {
		return apply_id;
	}
	public void setApply_id(int apply_id) {
		this.apply_id = apply_id;
	}
	public int getApply_user_id() {
		return apply_user_id;
	}
	public void setApply_user_id(int apply_user_id) {
		this.apply_user_id = apply_user_id;
	}
	public int getApply_product_id() {
		return apply_product_id;
	}
	public void setApply_product_id(int apply_product_id) {
		this.apply_product_id = apply_product_id;
	}
	public int getApply_num() {
		return apply_num;
	}
	public void setApply_num(int apply_num) {
		this.apply_num = apply_num;
	}
	public String getApply_time() {
		return apply_time;
	}
	public void setApply_time(String apply_time) {
		this.apply_time = apply_time;
	}
	public int getApply_passman_id() {
		return apply_passman_id;
	}
	public void setApply_passman_id(int apply_passman_id) {
		this.apply_passman_id = apply_passman_id;
	}
	public int getApply_pass() {
		return apply_pass;
	}
	public void setApply_pass(int apply_pass) {
		this.apply_pass = apply_pass;
	}

	public String getApply_user_truename() {
		return apply_user_truename;
	}

	public void setApply_user_truename(String apply_user_truename) {
		this.apply_user_truename = apply_user_truename;
	}

	public String getApply_product_name() {
		return apply_product_name;
	}

	public void setApply_product_name(String apply_product_name) {
		this.apply_product_name = apply_product_name;
	}

	public String getApply_passman_name() {
		return apply_passman_name;
	}

	public void setApply_passman_name(String apply_passman_name) {
		this.apply_passman_name = apply_passman_name;
	}

	public float getApply_product_price() {
		return apply_product_price;
	}

	public void setApply_product_price(float apply_product_price) {
		this.apply_product_price = apply_product_price;
	}

	public String getApply_user_name() {
		return apply_user_name;
	}

	public void setApply_user_name(String apply_user_name) {
		this.apply_user_name = apply_user_name;
	}
}
