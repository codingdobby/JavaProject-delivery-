package JaBae.Seller;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import JaBae.Login.Main.LoginMain;
import JaBae.admin.VO.GisaVO;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;

import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.Random;
import java.awt.event.ActionEvent;

public class Seller_Conveyor extends JFrame {
	
	Seller_ConveyorDAO scdao = new Seller_ConveyorDAO();
	
	private JTextField txt_Cus_Phone;
	private JTextField txt_Cus_Address2;
	private JTextField txt_Cus_Name;
	private JTextField txt_Cus_Jung;
	private JTextField txt_Sel_Id;
	private int localIndex;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Seller_Conveyor frame = new Seller_Conveyor();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	@SuppressWarnings("unlikely-arg-type")
	public Seller_Conveyor() {
		setTitle("자배 택배");
		setVisible(true); // 눈에 보이게 설정
		setTitle("Seller_Conveyor"); // 프레임 이름
		setResizable(false); // 사이즈 재조정 불가
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // x 버튼 활성화
		setBounds(550, 200, 900, 650); // 사이즈 및 나타나는 위치 설정
		getContentPane().setLayout(null); // 레이아웃 없음.
		
		JLabel la_Conveyor_Name = new JLabel("송장 등록"); //송장등록 라벨.
		la_Conveyor_Name.setFont(new Font("굴림", Font.PLAIN, 26));
		la_Conveyor_Name.setBounds(15, 14, 124, 38);
		getContentPane().add(la_Conveyor_Name);
		
		JLabel la_Cus_Orderno1 = new JLabel("송장 번호   : "); // 송장 번호 주석 라벨.
		la_Cus_Orderno1.setFont(new Font("굴림", Font.PLAIN, 16));
		la_Cus_Orderno1.setBounds(15, 100, 98, 15);
		getContentPane().add(la_Cus_Orderno1);
		
		JLabel lb_Cus_Orderno2 = new JLabel(""); // 송장 번호 출력 라벨.
		lb_Cus_Orderno2.setBounds(125, 100, 140, 15);
		getContentPane().add(lb_Cus_Orderno2);
		
		JLabel la_Cus_Jung = new JLabel("상품 종류"); // 상품 종류 라벨.
		la_Cus_Jung.setFont(new Font("굴림", Font.PLAIN, 16));
		la_Cus_Jung.setBounds(15, 160, 69, 15);
		getContentPane().add(la_Cus_Jung);
		
		JLabel la_Cus_Name = new JLabel("받는 사람"); // 받는사람 라벨.
		la_Cus_Name.setFont(new Font("굴림", Font.PLAIN, 16));
		la_Cus_Name.setBounds(15, 220, 69, 15);
		getContentPane().add(la_Cus_Name);
		
		JLabel la_Cus_Phone = new JLabel("받는 P.H"); // 받는 사람 휴대폰 번호 라벨
		la_Cus_Phone.setFont(new Font("굴림", Font.PLAIN, 16));
		la_Cus_Phone.setBounds(15, 280, 69, 15);
		getContentPane().add(la_Cus_Phone);
		
		JLabel la_Cus_Address = new JLabel("받는 지역"); // 받는 주소 라벨
		la_Cus_Address.setFont(new Font("굴림", Font.PLAIN, 16));
		la_Cus_Address.setBounds(15, 340, 69, 15);
		getContentPane().add(la_Cus_Address);
		
		JLabel la_Cus_Address2 = new JLabel("상세주소"); // 상세주소 라벨
		la_Cus_Address2.setFont(new Font("굴림", Font.PLAIN, 16));
		la_Cus_Address2.setBounds(15, 400, 69, 15);
		getContentPane().add(la_Cus_Address2);
		
		JLabel la_sel_Id = new JLabel("사용자 ID"); // 사용자 ID 라벨
		la_sel_Id.setFont(new Font("굴림", Font.PLAIN, 16));
		la_sel_Id.setBounds(15, 460, 69, 15);
		getContentPane().add(la_sel_Id);
		
		txt_Cus_Jung = new JTextField(); // 받는 물건 종류 텍필
		txt_Cus_Jung.setColumns(30);
		txt_Cus_Jung.setBounds(125, 158, 250, 21);
		getContentPane().add(txt_Cus_Jung);
		
		txt_Cus_Name = new JTextField(); // 받는 사람 텍필
		txt_Cus_Name.setColumns(20);
		txt_Cus_Name.setBounds(125, 218, 250, 21);
		getContentPane().add(txt_Cus_Name);
		
		txt_Cus_Phone = new JTextField(); // 받는 사람 휴대폰 번호 텍스트필드
		txt_Cus_Phone.setColumns(15);
		txt_Cus_Phone.setBounds(125, 278, 250, 21);
		getContentPane().add(txt_Cus_Phone);
		
		JComboBox cbboxLocal = new JComboBox();
		cbboxLocal.setBounds(198, 336, 95, 24);
		getContentPane().add(cbboxLocal);
		cbboxLocal.addItem("지역을 선택해주세요");
		cbboxLocal.removeAllItems();
		
		txt_Cus_Address2 = new JTextField(); // 받는 주소 상세 텍스트필드
		txt_Cus_Address2.setColumns(50);
		txt_Cus_Address2.setBounds(125, 398, 250, 21);
		getContentPane().add(txt_Cus_Address2);
		
		txt_Sel_Id = new JTextField(); // 등록하는 사람 ID
		txt_Sel_Id.setColumns(20);
		txt_Sel_Id.setBounds(125, 458, 250, 21);
		getContentPane().add(txt_Sel_Id);
		
		JButton btn_Con_Insert = new JButton("송장 등록"); // 송장등록 버튼.
		btn_Con_Insert.setBounds(430, 365, 120, 50);
		getContentPane().add(btn_Con_Insert);
		
		JButton btn_Ft_On = new JButton("정액권 사용"); // 정액권 사용 버튼
		btn_Ft_On.setBounds(630, 365, 120, 50);
		getContentPane().add(btn_Ft_On);
		
		JButton btn_reset = new JButton("초기화"); // 텍스트 필드 초기화 버튼
		btn_reset.setBounds(430, 525, 120, 50);
		getContentPane().add(btn_reset);
		
		JButton btn_Main_Go = new JButton("돌아가기");  // 메인화면으로 가는 버튼
		btn_Main_Go.setBounds(630, 525, 120, 50);
		getContentPane().add(btn_Main_Go);
		
		JButton btn_Logout = new JButton("로그아웃"); // 로그인 화면으로 가는 버튼
		btn_Logout.setBounds(768, 10, 114, 42);
		getContentPane().add(btn_Logout);
		
		JLabel lb_ps1 = new JLabel("송장 번호는 송장 등록 후에 나타납니다."); // 설명1
		lb_ps1.setFont(new Font("굴림", Font.PLAIN, 16));
		lb_ps1.setBounds(430, 120, 452, 15);
		getContentPane().add(lb_ps1);
		
		JLabel lb_ps2 = new JLabel("정액권 및 송장 등록 전, ID확인을 부탁드립니다."); // 설명2
		lb_ps2.setFont(new Font("굴림", Font.PLAIN, 16));
		lb_ps2.setBounds(430, 160, 452, 15);
		getContentPane().add(lb_ps2);
		
		JLabel lb_ps3 = new JLabel("입력칸을 비우시고 싶다면 초기화 버튼을 눌러주세요."); // 설명 3
		lb_ps3.setFont(new Font("굴림", Font.PLAIN, 16));
		lb_ps3.setBounds(430, 200, 452, 15);
		getContentPane().add(lb_ps3);

		//체크 박스안의 내용 등록
		ArrayList<GisaVO> localList = new ArrayList<GisaVO>();
		try {
			localList = scdao.getLocalList();
			int localIndex = 0;
			
			for(GisaVO local : localList) {
				cbboxLocal.addItem(local.getLocal());
				localIndex++;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
//		---------------------------------------------------------------------------------------
//		--------------------------------- 버튼 기능동작 --------------------------------------------
//		---------------------------------------------------------------------------------------	
		
//		초기화 버튼. 모든 텍스트 필드 공백처리.
		btn_reset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lb_Cus_Orderno2.setText("");
				txt_Cus_Jung.setText("");
				txt_Cus_Name.setText("");
				txt_Cus_Phone.setText("");
				txt_Cus_Address2.setText("");
				txt_Sel_Id.setText("");
			}
		});
		
//		돌아가기 버튼. 송장등록 → 메인화면
		btn_Main_Go.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new Seller_Main();
			}
		});
		
//		로그아웃 버튼. 송장등록 → 로그인화면
		btn_Logout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoginMain newWindow = new LoginMain();
				
				int ans = JOptionPane.showConfirmDialog(null, "정말 로그아웃 하시겠습니까?", "로그아웃", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE);
				if(ans == JOptionPane.CLOSED_OPTION) {
					
				} else if(ans == JOptionPane.YES_OPTION) {
					newWindow.setFocusable(true);
					newWindow.setVisible(true);
					dispose();
				} else {
					
				}
			}
		});
		
//		송장등록 버튼. JTField 정보를 DAO로 던진뒤 DAO에서 DB로 Insert 하기위한 버튼.
		btn_Con_Insert.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				
				int a;
				Random r = new Random();
				a = r.nextInt(999999999)+1; // 랜덤 송장번호. 억의 자리까지 나옴.
				String RandomNumbering = Integer.toString(a);
				

				String orderno = RandomNumbering; // 9억자리의 랜덤 매소드 r을 지정한 변수를 다시 orederno에 지정.
				String jung = txt_Cus_Jung.getText();
				String name = txt_Cus_Name.getText();
				String ph = txt_Cus_Phone.getText();
				String addres = cbboxLocal.getSelectedItem() + " " + txt_Cus_Address2.getText(); // 상세주소
				String selid = txt_Sel_Id.getText();
				int locno = cbboxLocal.getSelectedIndex()+1;
// index는 0부터 시작하며 테이블은 1부터 시작하기에 에러가 발생하는 것을 막기위해서 'index+1'하여 1부터 시작하도록 넣음.
				
				scdao.Insert(orderno, jung, name, ph, addres, selid, locno);


//				lb_Cus_Orderno2.setText(""); // 송장번호는 등록 후에 나오기에 insert 실행 후 초기화하면 못 보고 지워짐
				txt_Cus_Jung.setText("");
				txt_Cus_Name.setText("");
				txt_Cus_Phone.setText("");
				txt_Cus_Address2.setText("");
				txt_Sel_Id.setText("");
				lb_Cus_Orderno2.setText(RandomNumbering);
			}
		});

//		==============================================================================================================
		
//		정액권 사용 버튼.
		btn_Ft_On.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
			String selid = txt_Sel_Id.getText().toString().trim();
			
			try {
				scdao.ftuse(selid);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
					
				
				
//	버튼 클릭시 DB의 ID와 일치하는 열의 Ft를 1개 차감하는 버튼. 별도의 가격을 차감한다던가 없이 ft만 차감함.
//	id입력 칸이 공백이면 파업창 호출. (미구현)
			}
		});
		
		//화면을 모니터 가운데 정렬
		setLocationRelativeTo(null);
		
	}
}
