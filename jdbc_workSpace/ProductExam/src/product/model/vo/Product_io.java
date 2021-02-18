package product.model.vo;

import java.sql.Date;

public class Product_io {
	private int ioNo;
	private String product_id;
	private Date ioDate;
	private int amount;
	private String status;
	
	public Product_io() {
		super();
	}
	public Product_io(int ioNo, String product_id, Date ioDate, int amount, String status) {
		super();
		this.ioNo = ioNo;
		this.product_id = product_id;
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
	public String getProduct_id() {
		return product_id;
	}
	public void setProduct_id(String product_id) {
		this.product_id = product_id;
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
		return "Product_io [ioNo=" + ioNo + ", product_id=" + product_id + ", ioDate=" + ioDate + ", amount=" + amount
				+ ", status=" + status + "]";
	}
}
