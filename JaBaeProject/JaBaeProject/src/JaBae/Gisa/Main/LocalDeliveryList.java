/*
 * w.이승연
 * */

package JaBae.Gisa.Main;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import JaBae.Gisa.DAO.LocalDeliveryDAO;
import JaBae.Gisa.Event.LocalDeliveryEventClass;
import JaBae.Gisa.VO.GisaVO;
import JaBae.Login.Main.LoginMain;
import JaBae.admin.VO.MemberMgtCustomerVO;

import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class LocalDeliveryList extends JFrame {

	private JPanel contentPane;
	private static String gisaId;
	private JTable tableDList;
	private Vector titleDList;
	DefaultTableModel modelDList;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LocalDeliveryList frame = new LocalDeliveryList(gisaId);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws SQLException 
	 */
	public LocalDeliveryList(String gisaId) throws SQLException {
		this.gisaId = gisaId;
		System.out.println(gisaId);
		setTitle("자배 택배");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(900,650);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnBack = new JButton("←");
		btnBack.setBounds(12, 10, 55, 23);
		contentPane.add(btnBack);
		
		JLabel lblTitle = new JLabel("배송수락");
		lblTitle.setBounds(406, 30, 57, 15);
		contentPane.add(lblTitle);
		
		tableDList = new JTable();
		tableDList.setBounds(0, 0, 1, 1);
		
		titleDList = new Vector<>();
		titleDList.add("송장 번호"); titleDList.add("물품 명");
		titleDList.add("배송 주소"); titleDList.add("출발일");
		
		modelDList = new DefaultTableModel(titleDList, 0) {
        public boolean isCellEditable(int rowIndex, int mColIndex) {
                return false;
            }
        };
		
		JPanel panel = new JPanel();
		panel.setBounds(12, 118, 860, 432);
		contentPane.add(panel);
		
		JScrollPane spDList = new JScrollPane();
		panel.add(spDList);
		tableDList = new JTable(modelDList);
		tableDList.getTableHeader().setReorderingAllowed(false); 
		
		spDList.setViewportView(tableDList);
		
		JButton btnAccept = new JButton("수락");
		btnAccept.setBounds(396, 560, 97, 23);
		contentPane.add(btnAccept);
		
		/******************************table안에 값 넣음*************************/
		LocalDeliveryDAO ldDAO = new LocalDeliveryDAO();
		ArrayList<GisaVO> dList = ldDAO.loadDList(gisaId);
		
		modelDList.setNumRows(0); //테이블 초기화
		
		for(GisaVO getDList : dList) {  //리스트 불러옴
			modelDList.addRow(new Object[] { getDList.getOrderNo(), getDList.getObjectName(), 
					getDList.getAddrOrderer(), getDList.getStartDate()});
		}
		
		
		/*****************************************ActionListener******/
		btnAccept.addActionListener(new LocalDeliveryEventClass(tableDList, gisaId, modelDList));
		
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GisaMain newwin = new GisaMain(gisaId);
				newwin.setVisible(true);
				dispose();
			}
		});
		
		//화면을 모니터 가운데 정렬
		setLocationRelativeTo(null);
	}

}
