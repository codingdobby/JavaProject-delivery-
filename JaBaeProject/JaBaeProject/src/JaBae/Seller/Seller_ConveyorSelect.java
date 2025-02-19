package JaBae.Seller;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import JaBae.Login.Main.LoginMain;

import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Font;

public class Seller_ConveyorSelect extends JFrame {
	
	Seller_ConveyorSelectDAO scsdao = new Seller_ConveyorSelectDAO();

	private JPanel contentPane;
	private JTable table;
	private JTextField txt_in1, txt_in2, txt_in3, txt_in4, txt_in5;
	private JTextField txt_selid;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Seller_ConveyorSelect frame = new Seller_ConveyorSelect();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}// main method

	/**
	 * Create the frame.
	 */
	public Seller_ConveyorSelect() {
		setFocusTraversalPolicyProvider(true);
		setResizable(false);
		setTitle("자배 택배");
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(550, 200, 900, 650);
		getContentPane().setLayout(null);
		
		String colNames[] = {"송장 번호","상품 종류","받는 사람","받는 PH","받는 주소","출발 일자","도착 일자"};
		DefaultTableModel model = new DefaultTableModel(colNames, 0); // 7개
		JTable ta_LoL = new JTable(model);
		
		int widths[] = {50, 70, 50, 70, 80, 80, 80}; // 테이블의 칸 당 크기 설정
		for(int i=0; i<7; i++) { // 테이블 칸이 7개니까.
			TableColumn column = ta_LoL.getColumnModel().getColumn(i);
			column.setPreferredWidth(widths[i]);
		}
		
		JLabel la_ConSelect_Name = new JLabel("송장 조회 및 수정"); // 송장 조회 및 수정 프레임 이름
		la_ConSelect_Name.setFont(new Font("굴림", Font.PLAIN, 26));
		la_ConSelect_Name.setBounds(15, 10, 230, 40);
		getContentPane().add(la_ConSelect_Name);
		
		JLabel lb_selid = new JLabel("조회하고자 하는 ID"); // 조회할 id 입력 라벨
		lb_selid.setFont(new Font("굴림", Font.PLAIN, 16));
		lb_selid.setBounds(15, 75, 142, 26);
		getContentPane().add(lb_selid);
		
		txt_selid = new JTextField(); // 조회하는 id 입력 텍필
		txt_selid.setBounds(169, 79, 116, 21);
		getContentPane().add(txt_selid);
		txt_selid.setColumns(20);
		
		JScrollPane scp_LoL = new JScrollPane(ta_LoL); // JTable가 붙어있는 스크롤패널
		scp_LoL.setBounds(15, 171, 860, 220);
		getContentPane().add(scp_LoL);
		
		JLabel lb_underline = new JLabel("-------------------------------------------- 수정 정보 입력 -------------------------------------------------");
		lb_underline.setFont(new Font("굴림", Font.PLAIN, 16)); // 그냥 칸 나누는 용도의 라벨
		lb_underline.setBounds(15, 420, 860, 20);
		getContentPane().add(lb_underline);
		
		JLabel lb_under1 = new JLabel("송장번호에 맞는 4개의 정보를 수정 하실 수 있습니다.");
		lb_under1.setFont(new Font("굴림", Font.PLAIN, 14)); // 수정 설명 라벨
		lb_under1.setBounds(254, 450, 354, 15);
		getContentPane().add(lb_under1);
		
		JLabel lb_under2 = new JLabel("수정하지 않으실 정보도 기입 해주셔야합니다.");
		lb_under2.setFont(new Font("굴림", Font.PLAIN, 14));
		lb_under2.setBounds(254, 478, 354, 15);
		getContentPane().add(lb_under2);
		
		JLabel la_Con_number = new JLabel("송장 번호"); // where 조건절이 되는 송장번호 라벨
		la_Con_number.setBounds(55, 478, 55, 15);
		getContentPane().add(la_Con_number);
		txt_in1 = new JTextField();					// where 조건절로 입력하게 되는 송장번호 텍필
		txt_in1.setBounds(122, 475, 100, 20);
		getContentPane().add(txt_in1);
		txt_in1.setColumns(9);
		
		JLabel la_Con_Cons = new JLabel("상품 종류"); 	// 수정 상품종류 라벨
		la_Con_Cons.setBounds(55, 521, 62, 15);
		getContentPane().add(la_Con_Cons);
		txt_in2 = new JTextField();					// 수정 시 상품종류를 입력할 텍필
		txt_in2.setBounds(122, 518, 100, 20);
		getContentPane().add(txt_in2);
		txt_in2.setColumns(30);
		
		JLabel la_Con_Sday = new JLabel("받는 사람");	// 수정 받는 사람 라벨
		la_Con_Sday.setBounds(255, 521, 62, 15);
		getContentPane().add(la_Con_Sday);
		txt_in3 =  new JTextField();				// 수정 시 받는 사람을 입력할 텍필
		txt_in3.setBounds(322, 518, 100, 20);
		getContentPane().add(txt_in3);
		txt_in3.setColumns(20);
		
		JLabel la_Con_Eday = new JLabel("받는 주소"); // 수정 받는 주소 라벨
		la_Con_Eday.setBounds(655, 519, 61, 15);
		getContentPane().add(la_Con_Eday);
		txt_in4 = new JTextField();					// 수정 시 받는 주소를 입력할 텍필
		txt_in4.setBounds(722, 518, 100, 20);
		getContentPane().add(txt_in4);
		txt_in4.setColumns(50);
		
		JLabel la_Con_Ph = new JLabel("받는 PH");		// 수정 받는 ph 라벨
		la_Con_Ph.setBounds(455, 521, 57, 15);
		getContentPane().add(la_Con_Ph);
		txt_in5 = new JTextField();					// 수정 시 받는 ph를 입력할 텍필
		txt_in5.setBounds(522, 518, 100, 20);
		getContentPane().add(txt_in5);
		txt_in5.setColumns(15);
		
		
		JButton btn_Logout = new JButton("로그아웃"); // 로그아웃 버튼 LogMain으로 이동.
		btn_Logout.setBounds(761, 14, 114, 42);
		getContentPane().add(btn_Logout);
		
		JButton btn_Con_Select = new JButton("송장 조회"); // 송장 조회 버튼. select();
		btn_Con_Select.setBounds(15, 111, 110, 50);
		getContentPane().add(btn_Con_Select);
		btn_Con_Select.setFont(new Font("굴림", Font.PLAIN, 12)); 

		JButton btn_Con_Update = new JButton("수정 적용"); // 수정 사항 입력이 끝나면 적용 하는 버튼. update();
		btn_Con_Update.setBounds(15, 561, 110, 50);
		getContentPane().add(btn_Con_Update);
		btn_Con_Update.setFont(new Font("굴림", Font.PLAIN, 12));
		
		JButton btn_Reset = new JButton("초기화"); // 텍스트 필드값 전부 초기화하는 버튼
		btn_Reset.setBounds(384, 561, 110, 50);
		getContentPane().add(btn_Reset);
		
		JButton btn_Main_Go = new JButton("돌아가기"); // 판매자 메인으로 돌아가는 버튼
		btn_Main_Go.setBounds(765, 561, 110, 50);
		getContentPane().add(btn_Main_Go);

		
//		---------------------------------------------------------------------------------------
//		--------------------------------- 버튼 기능동작 --------------------------------------------
//		---------------------------------------------------------------------------------------	
		
//		로그아웃 버튼. 송장조회 → 로그인
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

//		입력받은 id에 대한 조회 버튼.
		btn_Con_Select.addActionListener(new Seller_ConveyorSelectDAO(ta_LoL,txt_selid));

//		수정사항 적용버튼. id를 where 조건절로 지정하여 같은 열의 4개의 값 조정이 가능.
		btn_Con_Update.addActionListener(new Seller_ConveyorSelectDAO(ta_LoL, txt_in1, txt_in2, txt_in3, txt_in4,txt_in5));
		
//		텍스트 필드 리셋 버튼
		btn_Reset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txt_selid.setText("");
				txt_in1.setText("");
				txt_in2.setText("");
				txt_in3.setText("");
				txt_in4.setText("");
				txt_in5.setText("");
			}
		});
		
//		메인화면 버튼. 송장조회 → 판매자 메인
		btn_Main_Go.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new Seller_Main();
			}
		});
		
		//화면을 모니터 가운데 정렬
		setLocationRelativeTo(null);

	}
}
