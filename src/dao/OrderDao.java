package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.BuyDetail;
import bean.BuyList;
import bean.Cart;

import bean.showCart;

public class OrderDao {
	Connection con;
	PreparedStatement pstmt;
	ResultSet rs;
	int cnt=1;
	
	public OrderDao() {
		con=JdbcUtil.getConnection();
	}
	public void close() {
		JdbcUtil.close(con, rs, pstmt);
	}
	public boolean cart(Cart c) {
		//코드가 중복되면 업데이트를 해야되고 코드가 중복이 안되면 인서트
		String sql="INSERT INTO C VALUES(?,?,?)";

		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setNString(1, c.getC_id());
			pstmt.setNString(2, c.getC_code());
			pstmt.setInt(3, c.getC_qty());
			int result=pstmt.executeUpdate();
			if(result!=0) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
		
	}
	public List<showCart> showcart(String c_id) {
		List<showCart> showCartList=null;
		showCart sc=null;
		String sql="SELECT * FROM C INNER JOIN P ON C.C_CODE=P.P_CODE WHERE C_ID=?";
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setNString(1, c_id);
			rs=pstmt.executeQuery();
			showCartList=new ArrayList<showCart>();
			while(rs.next()) {
				sc=new showCart();
				//사진 코드번호 제품이름 수량(변경) 총가격 
				sc.setP_sysFileName(rs.getNString("P_SYSFILENAME"));
				sc.setC_code(rs.getNString("C_CODE"));
				sc.setP_name(rs.getNString("P_NAME"));
				sc.setC_qty(rs.getInt("C_QTY"));
				sc.setP_price(rs.getInt("P_PRICE"));
				showCartList.add(sc);
			}
			return showCartList;
		} catch (SQLException e) {
			System.out.println("쇼카드 예외");
			e.printStackTrace();
		}
		
		return null;
	}
	public int check(String c_code, String c_id) {
		String sql="SELECT * FROM C WHERE C_CODE=? AND C_ID=?";
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setNString(1, c_code);
			pstmt.setNString(2, c_id);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				System.out.println("검색 성공 업데이트");
				return 1;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("검색 없음 인설트");
		return 0;
	}
	public boolean cartUpdate(Cart c) {
		String sql="UPDATE (SELECT c_qty FROM C WHERE C_CODE=?) set C_QTY=C_QTY+?";
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setNString(1, c.getC_code());
			pstmt.setInt(2, c.getC_qty());
			int result=pstmt.executeUpdate();
			if(result!=0) {
				System.out.println("디비 업데이트 성공");
				return true;
			}
		} catch (SQLException e) {
			System.out.println("디비 업데이트 예외");
			e.printStackTrace();
		}
		System.out.println("디비 업데이트 실패");
		return false;
	}
	public boolean buy(String sum, String b_id) {
		String sql="";
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setNString(1, sum);
			pstmt.setNString(2, b_id);
			int result=pstmt.executeUpdate();
			if(result!=0) {
				System.out.println("구매 테이블 insert 성공");

				return true;
			}
		} catch (SQLException e) {
			System.out.println("구매 테이블 insert 예외");
			e.printStackTrace();
		}
		System.out.println("구매 테이블 insert 실패");
		return false;
	}
	public List<BuyList> buyList(String b_id) {
		List<BuyList> bList=null;
		String sql="SELECT*FROM B WHERE B_ID=?";
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setNString(1, b_id);
			rs=pstmt.executeQuery();
			bList=new ArrayList<BuyList>();
			while(rs.next()) {
				BuyList bl= new BuyList();
				bl.setB_num(rs.getNString("B_NUM"));
				bl.setB_total(rs.getInt("B_TOTAL"));
				bl.setB_id(rs.getNString("B_ID"));
				bl.setB_date(rs.getTimestamp("B_DATE"));
				bList.add(bl);
			}
			System.out.println("구매목록 불러오기 성공");
			return bList;
		} catch (SQLException e) {
			System.out.println("구매목록 불러오기 예외");
			e.printStackTrace();
		}
		return null;
	}//메소드 end
	public boolean move() {
		String sql="INSERT INTO BUY (B_ID,B_CODE,B_QTY) SELECT C_ID,C_CODE,C_QTY CONTENTS FROM cart";
		try {
			pstmt=con.prepareStatement(sql);
			int result=pstmt.executeUpdate();
			if(result!=0) {
				System.out.println("옮기기 성공");
				return true;
			}
		} catch (SQLException e) {
			System.out.println("옮기기 예외");
			e.printStackTrace();
		}
		System.out.println("옮기기 실패");
		return false;
	}
	public boolean cartDel(String b_id) {
		String sql="DELETE FROM c where C_ID=?";
		
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setNString(1, b_id);
			int result=pstmt.executeUpdate();
			if(result!=0) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	public List<BuyDetail> buyDetail(String orderNum) {
		String sql="SELECT * from bd INNER JOIN p on bd.bd_CODE=p.p_CODE \r\n" + 
				"INNER JOIN b on bd.BD_ID=b.B_ID where b_num=?";
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setNString(1, orderNum);
			rs=pstmt.executeQuery();
			List<BuyDetail> bdList= new ArrayList<BuyDetail>();
			while(rs.next()) {
				BuyDetail bd= new BuyDetail();
				bd.setBd_sysFileName(rs.getNString("P_SYSFILENAME"));
				bd.setBd_name(rs.getNString("P_NAME"));
				bd.setBd_code(rs.getNString("P_CODE"));
				bd.setBd_qty(rs.getInt("BD_QTY"));
				bd.setBd_price(rs.getInt("P_PRICE"));
				bd.setBd_totPrice(rs.getInt("BD_QTY")*rs.getInt("P_PRICE"));
				bd.setBd_date(rs.getDate("B_DATE"));
				bd.setBd_tottot(rs.getInt("B_TOTAL"));
				bdList.add(bd);
			}
			System.out.println("제발 성공");
			return bdList;
		} catch (SQLException e) {
			System.out.println("예외다ㅡㅡ");
			e.printStackTrace();
		}
		System.out.println("에반데?");
		return null;
	}
}
