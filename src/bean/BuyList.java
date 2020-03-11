package bean;

import java.util.Date;

public class BuyList {
	private String b_num;
	private int b_total;
	private String b_id;
	public String getB_num() {
		return b_num;
	}
	public void setB_num(String b_num) {
		this.b_num = b_num;
	}
	public int getB_total() {
		return b_total;
	}
	public void setB_total(int b_total) {
		this.b_total = b_total;
	}
	public String getB_id() {
		return b_id;
	}
	public void setB_id(String b_id) {
		this.b_id = b_id;
	}
	public Date getB_date() {
		return b_date;
	}
	public void setB_date(Date b_date) {
		this.b_date = b_date;
	}
	private Date b_date;
}
