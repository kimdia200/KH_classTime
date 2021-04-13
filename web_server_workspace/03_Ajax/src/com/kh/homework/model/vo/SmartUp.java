package com.kh.homework.model.vo;

import java.sql.Date;

public class SmartUp extends Smart{
	private int rank;

	public SmartUp() {
		super();
	}

	public SmartUp(String pname, int amount, Date pdate, int rank) {
		super(pname, amount, pdate);
		this.rank = rank;
	}

	public SmartUp(String pname, int amount, Date pdate) {
		super(pname, amount, pdate);
	}

	public SmartUp(String pname, int amount) {
		super(pname, amount);
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	@Override
	public String toString() {
		return rank+","+super.getPname()+","+super.getAmount();
	}
	
}
