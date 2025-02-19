package JaBae.Customer.Main;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import JaBae.Customer.DAO.MemberDAO;
import JaBae.Customer.VO.MemberVO;
import JaBae.Login.Main.LoginMain;
import JaBae.Login.Main.LoginCustomer.Cus_Login;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.FlowLayout;
import javax.swing.JButton;
import java.awt.GridLayout;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Cus_selectView extends JFrame {
	JLabel test;
	private JPanel contentPane;
	JPanel panel_top;
	private static String toss;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Cus_selectView frame = new Cus_selectView(toss);
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

	public Cus_selectView(String toss) {
		this.toss = toss;

		setTitle("자배 택배");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 900, 650);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		panel_top = new JPanel();
		contentPane.add(panel_top, BorderLayout.NORTH);
		Cus_Login test1 = new Cus_Login();
		System.out.println(toss);
		panel_top.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_label = new JPanel();
		panel_label.setBorder(new EmptyBorder(0, 100, 0, 0));
		FlowLayout fl_panel_label = (FlowLayout) panel_label.getLayout();
		fl_panel_label.setVgap(20);
		panel_top.add(panel_label, BorderLayout.CENTER);

		JLabel lb_top = new JLabel("택배 관리 시스템(고객용)");
		panel_label.add(lb_top);
		lb_top.setFont(new Font("돋움", Font.PLAIN, 30));
		
		JPanel panel_btn = new JPanel();
		panel_btn.setBorder(new EmptyBorder(20, 0, 0, 20));
		panel_top.add(panel_btn, BorderLayout.EAST);
		
		JButton btnLogout = new JButton("로그아웃");
		btnLogout.addActionListener(new ActionListener() {
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
		panel_btn.add(btnLogout);

		JPanel panel_center = new JPanel();
		contentPane.add(panel_center, BorderLayout.CENTER);
		panel_center.setLayout(new BorderLayout(0, 0));

		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new EmptyBorder(100, 30, 100, 50));
		panel_center.add(panel_2, BorderLayout.CENTER);
		panel_2.setLayout(new GridLayout(0, 3, 30, 20));

		JButton btnInfo = new JButton("회원 정보 관리");
		btnInfo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				Cus_Info CI = new Cus_Info(toss);
				CI.setVisible(true);

			}
		});
		btnInfo.setFont(new Font("돋움", Font.PLAIN, 20));
		panel_2.add(btnInfo);

		// 송장 조회
		JButton btnSong = new JButton("송장 조회");
		btnSong.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				Delivery_Select ds = new Delivery_Select(toss);
				ds.setVisible(true);
				dispose();

			}
		});
		btnSong.setFont(new Font("돋움", Font.PLAIN, 20));
		panel_2.add(btnSong);

		JButton btnOrder = new JButton("주문 내역 조회");
		btnOrder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				OrderListView olv = new OrderListView(toss);
				olv.setVisible(true);
				dispose();

			}
		});
		btnOrder.setFont(new Font("돋움", Font.PLAIN, 20));
		panel_2.add(btnOrder);

		JPanel panel_3 = new JPanel();
		panel_center.add(panel_3, BorderLayout.NORTH);

		JLabel test_1 = new JLabel();
		panel_3.add(test_1);
		test_1.setText(toss);

		JLabel lblNewLabel = new JLabel("고객님 환영합니다!");
		panel_3.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("보유 포인트");
		panel_3.add(lblNewLabel_1);

		// 화면 전환시 바로 포인트 조회

		JLabel lb_point = new JLabel("");

		MemberDAO mdo = new MemberDAO();
		MemberVO vo = mdo.getPoint(toss);

		String Point = Integer.toString(vo.getPoint());

		lb_point.setText(Point);

		panel_3.add(lb_point);
		setLocationRelativeTo(null);
	}
}
