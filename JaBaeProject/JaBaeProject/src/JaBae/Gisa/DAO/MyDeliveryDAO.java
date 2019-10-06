package JaBae.Gisa.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import JaBae.Gisa.VO.GisaVO;

//import JaBae.Gisa.VO.GisaVO;


public class MyDeliveryDAO {
	private static Connection conn; // id, pwd, ulr
	private static Statement stmt; // sql execute
	private static ResultSet rs; // 결과물
	private static PreparedStatement pstmt;

	void sqlOpen() throws SQLException {
		String url = "jdbc:oracle:thin:@localhost:1521/xe"; // 2.url설정
		String id = "sample";
		String pwd = "sample";

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");// 1.드라이버 로딩
			System.out.println("jdbc 연결 성공");

			conn = DriverManager.getConnection(url, id, pwd); // 3.db연결 접속 확인
			System.out.println("oracle연결 성공");

		} catch (ClassNotFoundException e) {
			System.out.println("jdbc 연결 실패");
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("orcale연결 실패");
		}

		stmt = conn.createStatement();

	}

	void sqlClose() throws SQLException {
		if (rs != null)
			rs.close();
		if (pstmt != null)
			pstmt.close();
		if (stmt != null)
			stmt.close();
		if (conn != null)
			conn.close();
	}
	
	public ArrayList<GisaVO> loadDList(String gisaId) throws SQLException {
		sqlOpen();
		
		ArrayList<GisaVO> dList = new ArrayList<GisaVO>();
		GisaVO gisaVO = null;
		
		String sql = "SELECT ORDER_NO , OBJECT_NAME , NAME , ADDR , TEL  " + 
				"FROM DELIVERY_SELECT " + 
				"WHERE GISA_ID_"
				+ ""
				+ "DS_FK = ? "
				+ "and end_date is null";

		pstmt = conn.prepareStatement(sql);
		

		pstmt.setString(1, gisaId);
		
		rs = pstmt.executeQuery();

		while (rs.next()) {
			gisaVO = new GisaVO();
			gisaVO.setOrderNo(rs.getString(1));
			gisaVO.setObjectName(rs.getString(2));
			gisaVO.setNameOrderer(rs.getString(3));
			gisaVO.setAddrOrderer(rs.getString(4));
			gisaVO.setTelOrderer(rs.getString(5));


			
			dList.add(gisaVO);
		}
		
		sqlClose();
		
		return dList;
	}
	
	public void UpdatedComplete(int order_no) throws SQLException {
		sqlOpen();
		
		ArrayList<GisaVO> dList = new ArrayList<GisaVO>();
		GisaVO gisaVO = null;
		
		String sql = "\r\n" + 
				"Update delivery_select " + 
				"set end_date = sysdate " + 
				"where Order_no = ?";

		pstmt = conn.prepareStatement(sql);

		pstmt.setInt(1, order_no);
		
		rs = pstmt.executeQuery();
		sqlClose();
		
	}
}
