package bean;

import java.util.Date;

public class Product {
	//상품빈
	//한꺼번에 담아 가기 위해 자바 빈 생성
	private String p_code; //상품 등록시에는 필요없지만 셀렉트시 필요함 pk
	private Date p_date; //위와 동문 pk는 XX  타임 스탬프로 쓰면댐
	private String p_id; //상품 등록자
	private String p_kind; //new or best
	private int p_price; //상품 가격
	private int p_qty; //상품 수량
	private String p_name; //상품 이름
	private String p_contents; //상품 상세 설명
	private String p_oriFileName; //상품이미지 원래 파일명
	private String p_sysFileName; //바뀐 파일명
	public String getP_code() {
		return p_code;
	}
	public void setP_code(String p_code) {
		this.p_code = p_code;
	}
	public Date getP_date() {
		return p_date;
	}
	public void setP_date(Date p_date) {
		this.p_date = p_date;
	}
	public String getP_id() {
		return p_id;
	}
	public void setP_id(String p_id) {
		this.p_id = p_id;
	}
	public String getP_kind() {
		return p_kind;
	}
	public void setP_kind(String p_kind) {
		this.p_kind = p_kind;
	}
	public int getP_price() {
		return p_price;
	}
	public void setP_price(int p_price) {
		this.p_price = p_price;
	}
	public int getP_qty() {
		return p_qty;
	}
	public void setP_qty(int p_qty) {
		this.p_qty = p_qty;
	}
	public String getP_name() {
		return p_name;
	}
	public void setP_name(String p_name) {
		this.p_name = p_name;
	}
	public String getP_contents() {
		return p_contents;
	}
	public void setP_contents(String p_contents) {
		this.p_contents = p_contents;
	}
	public String getP_oriFileName() {
		return p_oriFileName;
	}
	public void setP_oriFileName(String p_oriFileName) {
		this.p_oriFileName = p_oriFileName;
	}
	public String getP_sysFileName() {
		return p_sysFileName;
	}
	public void setP_sysFileName(String p_sysFileName) {
		this.p_sysFileName = p_sysFileName;
	}
	
}
