/*
 * w.이승연
 * */

package JaBae.Gisa.Main;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import JaBae.Login.Main.LoginMain;
import JaBae.admin.Main.GisaMgt.GisaManagement;
import JaBae.admin.Main.Mgt.MemberManagement;
import JaBae.admin.Main.Mgt.SalesManagement;

import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.FlowLayout;

public class GisaMain extends JFrame {

	private JPanel contentPane;
	static String gisaId;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GisaMain frame = new GisaMain(gisaId);
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
	public GisaMain(String gisaId) {
		this.gisaId = gisaId;
		setTitle("자배 택배");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(900,650);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panelTop = new JPanel();
		contentPane.add(panelTop, BorderLayout.NORTH);
		panelTop.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JPanel panelLogo = new JPanel();
		panelLogo.setBorder(new EmptyBorder(60, 350, 0, 0));
		panelTop.add(panelLogo);
		
		JLabel lblLogo = new JLabel("자배 택배");
		panelLogo.add(lblLogo);
		lblLogo.setFont(new Font("돋움", Font.PLAIN, 30));
		
		JPanel panelLogout = new JPanel();
		panelLogout.setBorder(new EmptyBorder(0, 250, 60, 0));
		panelTop.add(panelLogout);
		
		JButton btnLogout = new JButton("로그아웃");
		panelLogout.add(btnLogout);
		btnLogout.addActionListener(new ActionListener() { //로그아웃
			public void actionPerformed(ActionEvent e) {
				int result = JOptionPane.showConfirmDialog(null, "로그아웃 하시겠습니까?", "로그아웃",
                        JOptionPane.OK_CANCEL_OPTION);

                if (result == 0) { //OK=0 , Cancel=2 리턴
                	LoginMain newWindow = new LoginMain();
    				newWindow.setVisible(true);
    				dispose();
                }
			}
		});
		
		JPanel panelBtn = new JPanel();
		panelBtn.setBorder(new EmptyBorder(130, 70, 130, 70));
		contentPane.add(panelBtn, BorderLayout.CENTER);
		panelBtn.setLayout(new GridLayout(0, 2, 30, 20));
		
		JButton btnLocalDelivery = new JButton("배송 수락");
		panelBtn.add(btnLocalDelivery);
		
		JButton btnMyDelivery = new JButton("내 배송 목록");
		
		panelBtn.add(btnMyDelivery);
		
		
		/***********************ActionListener*****************************************************/
		
		btnLocalDelivery.addActionListener(new ActionListener() {//
			public void actionPerformed(ActionEvent e) {
				
				try {
					LocalDeliveryList newWindow = new LocalDeliveryList(gisaId);
					newWindow.setVisible(true);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				
				dispose();
			}
		});
		
		btnMyDelivery.addActionListener(new ActionListener() { //매출관리 버튼
			public void actionPerformed(ActionEvent e) {
				try {
					MyDeliveryList newWindow = new MyDeliveryList(gisaId);
					newWindow.setVisible(true);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				
				dispose();
			}
		});
		//화면을 모니터 가운데 정렬
		setLocationRelativeTo(null);
	}

}
