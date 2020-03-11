package bean;

public class showCart {
	private String p_sysFileName;
	private String c_code;
	private String p_name;
	private int c_qty;
	private int p_price;
	
	public String getP_name() {
		return p_name;
	}
	public void setP_name(String p_name) {
		this.p_name = p_name;
	}
	public int getC_qty() {
		return c_qty;
	}
	public void setC_qty(int c_qty) {
		this.c_qty = c_qty;
	}
	
	
	public String getP_sysFileName() {
		return p_sysFileName;
	}
	public void setP_sysFileName(String p_sysFileName) {
		this.p_sysFileName = p_sysFileName;
	}
	public String getC_code() {
		return c_code;
	}
	public void setC_code(String c_code) {
		this.c_code = c_code;
	}
	public int getP_price() {
		return p_price;
	}
	public void setP_price(int p_price) {
		this.p_price = p_price;
	}
}
