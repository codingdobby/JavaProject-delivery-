/*
 * w.이승연
 * */

package JaBae.Login.Main;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;

import JaBae.Customer.Be_Cus.Be_Cus_OrderView;
import JaBae.Login.Main.LoginCustomer.Cus_Login;
import JaBae.Login.Main.LoginGisa.Gisa_Login;
import JaBae.Login.Main.LoginSeller.Seller_Login;

import javax.swing.JButton;
import java.awt.GridLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginMain extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginMain frame = new LoginMain();
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
	public LoginMain() {
		//룩앤필
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (InstantiationException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IllegalAccessException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (UnsupportedLookAndFeelException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		setTitle("자배 택배");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null); 
		setSize(900,650);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		panel.setBorder(new EmptyBorder(50, 0, 0, 0));
		panel.setBackground(SystemColor.menu);
		contentPane.add(panel, BorderLayout.NORTH);
		
		JLabel laILogo = new JLabel("자배 택배");
		laILogo.setFont(new Font("굴림", Font.PLAIN, 56));
		panel.add(laILogo);
		
		/**********************************************************************************/
		
		JPanel LoginPanel = new JPanel();
		contentPane.add(LoginPanel, BorderLayout.CENTER);
		LoginPanel.setBorder(new EmptyBorder(100, 50, 140, 50));
		LoginPanel.setBackground(SystemColor.menu);
		LoginPanel.setLayout(new GridLayout(0, 4, 20, 0));
		
		JButton btnCustomerLogin = new JButton("회원 로그인");
		
		LoginPanel.add(btnCustomerLogin);
		
		JButton btnGuestLogin = new JButton("비회원 송장 조회");
		LoginPanel.add(btnGuestLogin);
		
		JButton btnSellerLogin = new JButton("판매자 로그인");
		
		LoginPanel.add(btnSellerLogin);
		
		JButton btnGisaLogin = new JButton("택배 기사 로그인");
		
		LoginPanel.add(btnGisaLogin);
		
		
		/*********************************버튼 클릭********************************************/
		
		btnCustomerLogin.addActionListener(new ActionListener() { //회원 로그인
			public void actionPerformed(ActionEvent e) {
				Cus_Login newWindow = new Cus_Login();
				newWindow.setVisible(true);
				dispose();
			}
		});
		
		btnGuestLogin.addActionListener(new ActionListener() { //비회원 송장 조회
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Be_Cus_OrderView be_cust = new Be_Cus_OrderView();
				be_cust.setVisible(true);
				dispose();
				
			}
		});
		
		btnSellerLogin.addActionListener(new ActionListener() {//판매자 로그인
			public void actionPerformed(ActionEvent e) {
				Seller_Login newWindow = new Seller_Login();
				newWindow.setFocusable(true);
				newWindow.setVisible(true);
				dispose();
			}
		});
		
		btnGisaLogin.addActionListener(new ActionListener() { //택배 기사 로그인
			public void actionPerformed(ActionEvent e) {
				Gisa_Login newWindow = new Gisa_Login();
				newWindow.setVisible(true);
				
			}
		});
		setLocationRelativeTo(null);
	}

}
