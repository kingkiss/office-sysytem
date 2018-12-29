package com.office.manage.domain;

import java.util.Date;

public class ApplyList {
	private int apply_id;
	private int apply_user_id;
	private int apply_product_id;
	private int apply_num;
	private Date apply_time;
	private int apply_passman_id;
	private boolean apply_pass;
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
	public Date getApply_time() {
		return apply_time;
	}
	public void setApply_time(Date apply_time) {
		this.apply_time = apply_time;
	}
	public int getApply_passman_id() {
		return apply_passman_id;
	}
	public void setApply_passman_id(int apply_passman_id) {
		this.apply_passman_id = apply_passman_id;
	}
	public boolean isApply_pass() {
		return apply_pass;
	}
	public void setApply_pass(boolean apply_pass) {
		this.apply_pass = apply_pass;
	}
	
	
}
