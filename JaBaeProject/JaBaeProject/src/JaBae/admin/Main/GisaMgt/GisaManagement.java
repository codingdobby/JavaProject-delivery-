/*
 * w.이승연
 * */

package JaBae.admin.Main.GisaMgt;

import static javax.swing.JOptionPane.showMessageDialog;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.util.Vector;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.JButton;

import JaBae.admin.EventAction.GisaEventAction.GisaEventClass;
import JaBae.admin.Main.AdminMain;

import java.awt.Component;
import javax.swing.JCheckBox;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class GisaManagement extends JFrame {

	private JPanel contentPane;
	private JTextField tfId;
	private JTextField tfName;
	private JTextField tfHiredate;
	private JTextField tfLocal;
	private JTable tableGisa;
	
	Vector titleGisa = null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GisaManagement frame = new GisaManagement();
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
	public GisaManagement() {
		
		setTitle("자배 택배");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(900,650);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panelNorth = new JPanel();
		contentPane.add(panelNorth, BorderLayout.NORTH);
		panelNorth.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JPanel panelSearch = new JPanel();
		panelNorth.add(panelSearch);
		panelSearch.setLayout(new BorderLayout(0, 0));
		
		JPanel panelSearch1 = new JPanel();
		panelSearch.add(panelSearch1);
		panelSearch1.setBorder(new EmptyBorder(-15, 100, 0, 80));
		panelSearch1.setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanel panelSearchTop = new JPanel();
		panelSearch1.add(panelSearchTop);
		
		JPanel panelBack = new JPanel();
		panelSearchTop.add(panelBack);
		panelBack.setBorder(new EmptyBorder(5, 0, 10, 85));
		
		JButton btnBack = new JButton("←");
		
		panelBack.add(btnBack);
		
		/*************************************************************/
		
		JPanel panelInput1 = new JPanel();
		panelInput1.setBorder(new EmptyBorder(20, 95, 0, 0));
		panelSearchTop.add(panelInput1);
		
		JPanel panelId = new JPanel();
		panelId.setBorder(new EmptyBorder(0, 0, 0, 20));
		panelInput1.add(panelId);
		
		JLabel lblId = new JLabel("아이디");
		panelId.add(lblId);
		
		tfId = new JTextField();
		panelId.add(tfId);
		tfId.setColumns(10);
		
		JPanel panelName = new JPanel();
		panelInput1.add(panelName);
		
		JLabel lblName = new JLabel("이름");
		panelName.add(lblName);
		
		tfName = new JTextField();
		panelName.add(tfName);
		tfName.setColumns(10);
		
		/******************************************************************************************/

		JPanel panelSearchBottom = new JPanel();
		panelSearchBottom.setBorder(new EmptyBorder(0, 240, 0, 0));
		panelSearch1.add(panelSearchBottom);
		
		JPanel panelHiredate = new JPanel();
		panelHiredate.setBorder(new EmptyBorder(0, 0, 0, 20));
		panelSearchBottom.add(panelHiredate);
		
		JLabel lblHiredate = new JLabel("입사일");
		panelHiredate.add(lblHiredate);
		
		tfHiredate = new JTextField();
		panelHiredate.add(tfHiredate);
		tfHiredate.setColumns(10);
		
		JPanel panelArea = new JPanel();
		panelSearchBottom.add(panelArea);
		
		JLabel lblArea = new JLabel("지역");
		panelArea.add(lblArea);
		
		tfLocal = new JTextField();
		panelArea.add(tfLocal);
		tfLocal.setColumns(10);
		
		JPanel panelSearch2 = new JPanel();
		panelSearch2.setBorder(new EmptyBorder(-15, 0, 0, 160));
		panelSearch.add(panelSearch2, BorderLayout.EAST);
		panelSearch2.setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanel panelCheck = new JPanel();
		panelCheck.setBorder(new EmptyBorder(30, 0, 0, 0));
		panelSearch2.add(panelCheck);
		
		/******************************************************************************************/
		
		JCheckBox chckbxExcudeReg = new JCheckBox("퇴사한 사람 제외");
		panelCheck.add(chckbxExcudeReg);
		
		JPanel panel = new JPanel();
		panelSearch2.add(panel);
		
		JButton btnSearch = new JButton("검색");
		
		
		
		
		panel.add(btnSearch);
		
		/******************************************************************************************/
		
		JPanel panelCenter = new JPanel();
		contentPane.add(panelCenter, BorderLayout.CENTER);
		
		tableGisa = new JTable();
		panelCenter.setLayout(new GridLayout(0, 1, 0, 0));
		
		JScrollPane spGisaTable = new JScrollPane();
		panelCenter.add(spGisaTable);
		
		titleGisa = new Vector<>();
		titleGisa.add("아이디"); titleGisa.add("이름"); 
		titleGisa.add("전화번호"); titleGisa.add("입사일"); 
		titleGisa.add("퇴사일"); titleGisa.add("지역");
		DefaultTableModel model = new DefaultTableModel(titleGisa, 0) {
        public boolean isCellEditable(int rowIndex, int mColIndex) {
                return false;
            }
        };
		tableGisa = new JTable(model);
		tableGisa.getTableHeader().setReorderingAllowed(false); 

		//테이블 가운데 정렬
		DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();
		dtcr.setHorizontalAlignment(SwingConstants.CENTER);
		
		TableColumnModel tcm = tableGisa.getColumnModel();
		for(int i =0; i < tcm.getColumnCount(); i++) {
			tcm.getColumn(i).setCellRenderer(dtcr);
		}
		
		spGisaTable.setViewportView(tableGisa);
		
		/******************************************************************************************/
		
		JPanel panelSouth = new JPanel();
		contentPane.add(panelSouth, BorderLayout.SOUTH);
		
		JButton btnInsertGisa = new JButton("택배 기사 등록");
		panelSouth.add(btnInsertGisa);
		
		JButton btnUpdateGisa = new JButton("택배 기사 수정");
		panelSouth.add(btnUpdateGisa);
		
		/**********************ActionListener******************************************************/
		
		btnBack.addActionListener(new ActionListener() { //뒤로가기
			public void actionPerformed(ActionEvent e) {
				AdminMain newWindow = new AdminMain();
				newWindow.setVisible(true);
				dispose();
			}
		});
		
		addWindowListener(new WindowAdapter() { //창이 열리면 검색버튼에 포커스 주기
			@Override
			public void windowOpened(WindowEvent e) {
				btnSearch.requestFocus();
			}
		});
		
		
		btnSearch.addKeyListener(new GisaEventClass(model, chckbxExcudeReg, tfName, tfId, tfHiredate, tfLocal));
		
		btnSearch.addActionListener(new GisaEventClass(model, chckbxExcudeReg, tfName, tfId, tfHiredate, tfLocal));//검색 버튼
	
		btnInsertGisa.addActionListener(new ActionListener() {//기사 등록
			public void actionPerformed(ActionEvent arg0) {
				GisaJoined newWindow = new GisaJoined("");
				newWindow.setVisible(true);
				dispose();
			}
		});
		
		btnUpdateGisa.addActionListener(new ActionListener() {//기사 수정
			public void actionPerformed(ActionEvent e) {
				int selectRow = tableGisa.getSelectedRow();
				if(selectRow ==-1) showMessageDialog(null, "변경할 기사를 선택해주세요!");
				else {
					Object id = tableGisa.getValueAt(selectRow, 0);
					GisaJoined newWindow = new GisaJoined(id.toString());
					newWindow.setVisible(true);
					dispose();
				}
			}
		});
		
		setLocationRelativeTo(null);
	}

}
