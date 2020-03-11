package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MemberDao {
	Connection con;
	PreparedStatement pstmt;
	ResultSet rs;
	
	public MemberDao() {
		con=JdbcUtil.getConnection();
	}
	public void close() {
		JdbcUtil.close(con, rs, pstmt);
	}

	public int access(String id, String pw) {
		String sql = "SELECT * FROM M WHERE ID=?";
		int result = -1;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setNString(1, id);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				if (rs.getNString("PW").equals(pw)) {
					result = 1;// 모두 일치
				} else {
					result = 0; // 비번불일치
				}
			} else { // 아이디 불일치
				result = -1;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
}
