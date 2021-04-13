package com.kh.homework.model.vo;

import java.sql.Date;

public class Smart {
	private String pname;
	private int amount;
	private Date pdate;
	public Smart() {
		super();
	}
	
	public Smart(String pname, int amount) {
		super();
		this.pname = pname;
		this.amount = amount;
	}

	public Smart(String pname, int amount, Date pdate) {
		super();
		this.pname = pname;
		this.amount = amount;
		this.pdate = pdate;
	}
	public String getPname() {
		return pname;
	}
	public void setPname(String pname) {
		this.pname = pname;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public Date getPdate() {
		return pdate;
	}
	public void setPdate(Date pdate) {
		this.pdate = pdate;
	}
	@Override
	public String toString() {
		return pname+","+amount+","+pdate;
	}
}
