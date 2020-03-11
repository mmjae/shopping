package bean;

import java.util.Date;

public class BuyDetail {
	private String bd_sysFileName;
	private String bd_name;
	private String bd_code;
	private int bd_qty;
	private int bd_price;
	private int bd_tottot;
	private int bd_totPrice;
	private Date bd_date;

	public String getBd_sysFileName() {
		return bd_sysFileName;
	}

	public void setBd_sysFileName(String bd_sysFileName) {
		this.bd_sysFileName = bd_sysFileName;
	}

	public String getBd_name() {
		return bd_name;
	}

	public void setBd_name(String bd_name) {
		this.bd_name = bd_name;
	}

	public String getBd_code() {
		return bd_code;
	}

	public void setBd_code(String bd_code) {
		this.bd_code = bd_code;
	}

	public int getBd_qty() {
		return bd_qty;
	}

	public void setBd_qty(int bd_qty) {
		this.bd_qty = bd_qty;
	}

	public int getBd_price() {
		return bd_price;
	}

	public void setBd_price(int bd_price) {
		this.bd_price = bd_price;
	}

	public int getBd_tottot() {
		return bd_tottot;
	}

	public void setBd_tottot(int bd_tottot) {
		this.bd_tottot = bd_tottot;
	}

	
	public int getBd_totPrice() {
		return bd_totPrice;
	}

	public void setBd_totPrice(int bd_totPrice) {
		this.bd_totPrice = bd_totPrice;
	}

	public Date getBd_date() {
		return bd_date;
	}

	public void setBd_date(Date bd_date) {
		this.bd_date = bd_date;
	}

}
