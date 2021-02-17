package product.model.vo;

import java.sql.Date;

public class ProductLog {
	private int no;
	private int productNo;
	private String product;
	private int quantity;
	private Date modification_date;
	
	public ProductLog() {
		super();
	}

	public ProductLog(int no, int productNo, String product, int quantity, Date modificationDate) {
		super();
		this.no = no;
		this.productNo = productNo;
		this.product = product;
		this.quantity = quantity;
		this.modification_date = modificationDate;
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public int getProduct_no() {
		return productNo;
	}

	public void setProduct_no(int product_no) {
		this.productNo = product_no;
	}

	public String getProduct() {
		return product;
	}

	public void setProduct(String product) {
		this.product = product;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Date getModification_date() {
		return modification_date;
	}

	public void setModification_date(Date modification_date) {
		this.modification_date = modification_date;
	}

	@Override
	public String toString() {
		return "ProductLog [no=" + no + ", product_no=" + productNo + ", product=" + product + ", quantity=" + quantity
				+ ", modification_date=" + modification_date + "]";
	}
}
