package JaBae.Seller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

import JaBae.admin.VO.GisaVO;

public class Seller_ConveyorDAO {
	
	ArrayList<GisaVO> localList = new ArrayList<GisaVO>();
	GisaVO gisaVO = new GisaVO();

	private static Connection conn; //id, pwd, ulr
	private static Statement st; //sql execute
	private static ResultSet rs; //결과물
	private static PreparedStatement ps;
	
	private JTextField txt_Sel_Id;
	
//	DB 접속 메소드
	void connectDB() throws SQLException {
		String url = "jdbc:oracle:thin:@localhost:1521/xe"; //2.url설정
		String id = "sample";
		String pwd = "sample";
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");//1.드라이버 로딩
			
			conn = DriverManager.getConnection(url, id, pwd); //3.db연결 접속 확인
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch(SQLException e) {
		}
		
		st = conn.createStatement();
		
	}
	
//	ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ DB 연결해제 메소드 ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ
	void closeDB() throws SQLException {
		if(rs != null) rs.close();
		if(ps != null) ps.close();
		if(st != null) st.close();
		if(conn!= null) conn.close();
	}
	
// ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ 지역 목록을 가져오는 메서드ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ

	public ArrayList<GisaVO> getLocalList() throws SQLException {

		connectDB();

		String sql = "select loc from loc";
		ps = conn.prepareStatement(sql);
		rs = ps.executeQuery();
		
		while (rs.next()) {
			gisaVO = new GisaVO();
			gisaVO.setLocal(rs.getString("loc"));
			localList.add(gisaVO);
		}
		closeDB();
		return localList;
	}
	
// ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ 송장 등록 ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ 
	public void Insert (String orderno, String jung,String name,String ph,String addres, String selid, int locno) {
		
		try {
			connectDB();
		} catch (SQLException e) {
			System.out.println("DB연결 실패");
		}
		
		if(orderno.equals("")) {
			JOptionPane.showMessageDialog(null, "송장등록 실패.\n송장 번호 재확인이 필요합니다.");
		} else if(jung.equals("")) {
			JOptionPane.showMessageDialog(null, "송장등록 실패.\n상품 종류(명)이 비어있습니다.");
		} else if(name.equals("")) {
			JOptionPane.showMessageDialog(null, "송장등록 실패.\n받으실 분의 성함이 비어있습니다.");
		} else if(ph.equals("")) {
			JOptionPane.showMessageDialog(null, "송장등록 실패.\n받으실 분의 연락처가 비어있습니다.");
		} else if(addres.equals("")) {
			JOptionPane.showMessageDialog(null, "송장등록 실패.\n받으실 분의 주소가 비어있습니다.");
		} else if(selid.equals("")) {
			JOptionPane.showMessageDialog(null, "사용자 ID를 입력해주시길 바랍니다.");
		} else if(locno == 0) {
			JOptionPane.showMessageDialog(null, "송장등록 실패.\n지역 설정이 필요합니다.");
		}
		
		String sql = "insert into delivery_select (ORDER_NO, OBJECT_NAME, NAME, TEL, ADDR, START_DATE, END_DATE , SELLER_ID_DS_FK, GISA_ID_DS_FK, L_NO_DS_FK) "
				                + "values (?, ?, ?, ?, ?, sysdate, null, ?, null, ?)";
//										   ▲  ▲  ▲  ▲  ▲				 ▲		  ▲ 
//										   1  2  3  4  5				 6		  7
		try {//							   |  |  |  |  |				 |		  |
		ps = conn.prepareStatement(sql);// |  |  |  |  |				 |		  |
		ps.setString(1, orderno);// order_no  |  |  |  |				 |		  |
		ps.setString(2, jung);// object_name--┘	 |  |  |				 |		  |
		ps.setString(3, name);// name ㅡ ㅡ ㅡ ㅡ ㅡ ㅡ┘	|  |				 |		  |
		ps.setString(4, ph);//	 tel ㅡ ㅡ ㅡ ㅡ ㅡ ㅡ ㅡ ㅡ ┘  |				 |		  |
		ps.setString(5, addres);// addr ㅡ ㅡ ㅡ ㅡ ㅡ ㅡ ㅡ ㅡ ┘ 				 |		  |
		ps.setString(6, selid);// seller_id_ds_kf ㅡ ㅡ ㅡ ㅡ ㅡ ㅡ ㅡ ㅡ ㅡ ㅡ ㅡ ㅡ-┘		  |
		ps.setInt(7, locno);// l_no_ds_fk ㅡ ㅡ ㅡ ㅡ ㅡ ㅡ ㅡ ㅡ ㅡ ㅡ ㅡ ㅡ ㅡ ㅡ ㅡ ㅡ ㅡ ㅡ ㅡ ㅡ ㅡ ┘
		} catch(SQLException e1) {
			System.out.println("SQL입력 실패");
		}
		
		
			try {
				rs = ps.executeQuery();
				JOptionPane.showMessageDialog(null, "송장등록 완료\n" + "송장번호 : " + orderno);
			} catch (SQLException e) {
			}
		
			try {
				closeDB();
			} catch (SQLException e) {
				System.out.println("DB종료 실패");
			}
		
	} 
	
// ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ
// ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ 정액권 사용 ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ
	public void ftuse(String selid) throws SQLException {
		
			connectDB();
		
		if(selid.equals("")) {
			JOptionPane.showMessageDialog(null, "사용자 ID를 입력해주시길 바랍니다.");
		} else {
//			sell_id 와 일치하는 열의 freeticketcnt를 1개 차감.
			String sql = "update seller set freeticketcnt = (freeticketcnt - 1) where sell_id = ? and freeticketcnt > 0";
//																								▲
				//																				1
				ps = conn.prepareStatement(sql);//												|
				ps.setString(1, selid); // SELL_ID ㅡ ㅡ ㅡ ㅡ ㅡ ㅡ ㅡ ㅡ ㅡ ㅡ ㅡ ㅡ ㅡ ㅡ ㅡ ㅡ ㅡ ㅡ ㅡ ㅡ ㅡ ㅡ ㅡ ㅡ ┘
				rs = ps.executeQuery();
				JOptionPane.showMessageDialog(null, selid + "님의 정액권 1개를 차감하였습니다.");
		}
		
			closeDB();
		
	}
	
// ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ
	
	public static void main(String[] args) {
		
	}
}

