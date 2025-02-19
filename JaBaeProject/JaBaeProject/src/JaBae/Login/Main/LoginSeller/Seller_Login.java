/*
 * w.이승연
 * */

package JaBae.Login.Main.LoginSeller;

import static javax.swing.JOptionPane.showMessageDialog;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import JaBae.Customer.Main.Cus_selectView;
import JaBae.Login.DAO.LoginDAO;
import JaBae.Login.Main.LoginMain;
import JaBae.Seller.Seller_Main;
import JaBae.admin.Main.AdminMain;

import javax.swing.JLabel;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;

public class Seller_Login extends JFrame {

	private JPanel contentPane;
	private JTextField tfId;
	private JPasswordField tfPwd;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Seller_Login frame = new Seller_Login();
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
	public Seller_Login() {
		setTitle("자배 택배");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 437, 477);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panelTop = new JPanel();
		contentPane.add(panelTop, BorderLayout.NORTH);
		panelTop.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JPanel panelBack = new JPanel();
		panelBack.setBorder(new EmptyBorder(0, 0, 100, 20));
		panelTop.add(panelBack);
		
		JButton btnBack = new JButton("←");
		panelBack.add(btnBack);
		
		JPanel panelLogo = new JPanel();
		panelLogo.setBorder(new EmptyBorder(60, 0, 0, 70));
		panelTop.add(panelLogo);
		
		JLabel lblLogo = new JLabel("자배 택배");
		panelLogo.add(lblLogo);
		lblLogo.setFont(new Font("굴림", Font.PLAIN, 56));
		
		JPanel panelBtn = new JPanel();
		panelBtn.setBorder(new EmptyBorder(0, 0, 60, 0));
		contentPane.add(panelBtn, BorderLayout.SOUTH);
		panelBtn.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 5));
		
		JButton btnFindInfo = new JButton("아이디/비밀번호찾기");
		panelBtn.add(btnFindInfo);
		
		JButton btnSignUp = new JButton("회원가입");
		panelBtn.add(btnSignUp);
		
		JPanel panelLogin = new JPanel();
		panelLogin.setBorder(new EmptyBorder(50, 90, 20, 90));
		contentPane.add(panelLogin, BorderLayout.CENTER);
		panelLogin.setLayout(new BorderLayout(0, 0));
		
		JPanel panelLoginInput = new JPanel();
		panelLogin.add(panelLoginInput);
		panelLoginInput.setLayout(new GridLayout(0, 2, -60, 20));
		
		JLabel lblId = new JLabel("아이디");
		lblId.setFont(new Font("굴림", Font.PLAIN, 17));
		panelLoginInput.add(lblId);
		
		tfId = new JTextField();
		panelLoginInput.add(tfId);
		tfId.setColumns(20);
		
		JLabel lblPwd = new JLabel("비밀번호");
		lblPwd.setFont(new Font("굴림", Font.PLAIN, 17));
		panelLoginInput.add(lblPwd);
		
		tfPwd = new JPasswordField();
		panelLoginInput.add(tfPwd);
		
		JPanel panelLoginBtn = new JPanel();
		panelLoginBtn.setBorder(new EmptyBorder(20, 0, 0, 0));
		panelLogin.add(panelLoginBtn, BorderLayout.SOUTH);
		panelLoginBtn.setLayout(new GridLayout(0, 1, 0, 0));
		
		JButton btnLogin = new JButton("로그인");
		panelLoginBtn.add(btnLogin);
		setLocationRelativeTo(null);
		
		
		/*****************************************ActionListener**********/
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String id = tfId.getText().trim();
				String pwd = tfPwd.getText().trim();
				
				LoginDAO sdao = new LoginDAO();
				
				boolean check = sdao.loginSeller(id, pwd);
				if (check == true) {
					Seller_Main sellerMain = new Seller_Main();
					sellerMain.setVisible(true);
					dispose();

				} else {
					showMessageDialog(null, "판매자 로그인 실패!");
					return;
				}
				
			}
		});
		
		btnFindInfo.addActionListener(new ActionListener() { //아이디 비밀번호 찾기
			public void actionPerformed(ActionEvent e) {
				Seller_FindInfo newwin = new Seller_FindInfo();
				newwin.setVisible(true);

			}
		});
		
		btnSignUp.addActionListener(new ActionListener() { //회원가입
			public void actionPerformed(ActionEvent e) {
				Seller_SignUp newwin = new Seller_SignUp();
				newwin.setVisible(true);

			}
		});
		
		
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoginMain newwin = new LoginMain();
				newwin.setVisible(true);
				dispose();
			}
		});
		
		//화면을 모니터 가운데 정렬
		setLocationRelativeTo(null);
	}

}
