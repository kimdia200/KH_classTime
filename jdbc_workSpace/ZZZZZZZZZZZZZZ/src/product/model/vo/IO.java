package product.model.vo;

import java.sql.Date;

public class IO {
	private int ioNo;
	private String productID;
	private Date ioDate;
	private int amount;
	private String status;
	
	public IO() {
		super();
	}
	
	public IO(int ioNo, String productID, Date ioDate, int amount, String status) {
		super();
		this.ioNo = ioNo;
		this.productID = productID;
		this.ioDate = ioDate;
		this.amount = amount;
		this.status = status;
	}
	
	public int getIoNo() {
		return ioNo;
	}
	public void setIoNo(int ioNo) {
		this.ioNo = ioNo;
	}
	public String getProductID() {
		return productID;
	}
	public void setProductID(String productID) {
		this.productID = productID;
	}
	public Date getIoDate() {
		return ioDate;
	}
	public void setIoDate(Date ioDate) {
		this.ioDate = ioDate;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	@Override
	public String toString() {
		return "IO [ioNo=" + ioNo + ", productID=" + productID + ", ioDate=" + ioDate + ", amount=" + amount
				+ ", status=" + status + "]";
	}
}
