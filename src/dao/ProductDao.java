package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.Product;

public class ProductDao {
	Connection con;
	PreparedStatement pstmt;
	ResultSet rs;

	public ProductDao() {
		con = JdbcUtil.getConnection();
	}

	public void close() {
		JdbcUtil.close(con, rs, pstmt);
	}

	public boolean insertProduct(Product product) {
		String sql="INSERT INTO PRODUCT(PRODUCT_CODE,PRODUCT_SELLER,"
				+ "PRODUCT_NAME,PRODUCT_UNIT_PRICE,PRODUCT_REGISTERED_QUANTITY,"
				+ "PRODUCT_CONTENTS,PRODUCT_DATE,PRODUCT_ORIGIANL_FILE_NAME,"
				+ "PRODUCT_SYS_FILE_NAME) "
				+ "VALUES(?||P_SEQ.NEXTVAL,?,?,?,?,?,SYSDATE,?,?)";
			try {
				pstmt=con.prepareStatement(sql);
				pstmt.setNString(1, product.getP_kind());
				pstmt.setNString(2, product.getP_id());
				pstmt.setNString(3, product.getP_name());
				pstmt.setInt(4, product.getP_price());
				pstmt.setInt(5, product.getP_qty());
				pstmt.setNString(6, product.getP_contents());
				pstmt.setNString(7, product.getP_oriFileName());
				pstmt.setNString(8, product.getP_sysFileName());
				int result=pstmt.executeUpdate();
				if(result!=0) {
					System.out.println("insert성공");
					return true;
				}else {
					System.out.println("등록 실패");
				}
			} catch (SQLException e) {
				System.out.println("등록 예외");
				e.printStackTrace();
			}
		
		return false;
	}

	public List<Product> getItemList(String kind) {
		String sql="SELECT *FROM P WHERE P_CODE LIKE '%'||?||'%'";
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setNString(1, kind);
			rs=pstmt.executeQuery();
			List<Product> pList=new ArrayList<Product>();
			while(rs.next()) {
				Product product=new Product();
				product.setP_code(rs.getNString("P_CODE"));
				product.setP_sysFileName(rs.getNString("P_SYSFILENAME"));
				pList.add(product);
				//나머지는 필요할경우
			}
			return pList;
		} catch (SQLException e) {
			System.out.println(" new best 검색 예외");
			e.printStackTrace();
		}
		return null;
	}


	public Product getAjaxDetail(String code) {
		System.out.println(code);
		String sql="SELECT * FROM P WHERE P_CODE=?";
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setNString(1, code);
			rs=pstmt.executeQuery();
			Product p=null;
			while(rs.next()) {
				p=new Product();
				p.setP_code(rs.getNString("P_CODE"));
				p.setP_contents(rs.getNString("P_CONTENTS"));
				p.setP_id(rs.getNString("P_ID"));
				p.setP_name(rs.getNString("P_NAME"));
				p.setP_price(rs.getInt("P_PRICE"));
				p.setP_qty(rs.getInt("P_QTY"));
				p.setP_date(rs.getTimestamp("P_DATE"));
			}
			return p;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
		
	}

	public Product prodetail(String code) {
		String sql="SELECT * FROM P WHERE P_CODE=?";
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setNString(1, code);
			rs=pstmt.executeQuery();
			Product p=null;
			if(rs.next()) {
				p=new Product();
				p.setP_code(rs.getNString("P_CODE"));
				p.setP_contents(rs.getNString("P_CONTENTS"));
				p.setP_id(rs.getNString("P_ID"));
				p.setP_name(rs.getNString("P_NAME"));
				p.setP_price(rs.getInt("P_PRICE"));
				p.setP_qty(rs.getInt("P_QTY"));
				p.setP_date(rs.getTimestamp("P_DATE"));
				p.setP_sysFileName(rs.getNString("P_SYSFILENAME"));
			}
			return p;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}


}
