package JaBae.Customer.Main;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import java.awt.Font;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Vector;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import JaBae.Customer.DAO.SearchDAO;
import JaBae.Customer.VO.SearchVO;

import java.awt.FlowLayout;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.EmptyBorder;
import java.awt.SystemColor;

public class OrderListView extends JFrame {
	Vector v;
	Vector cols;
	DefaultTableModel model;
	JScrollPane pane;
	private JTextField tfDate;
	private JTextField tfToday;
	private JTable JTable;
	private static String toss;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					OrderListView window = new OrderListView(toss);
					window.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public OrderListView(String toss) {
		this.toss = toss;
		setTitle("자배 택배");
		setBackground(Color.WHITE);
		setBounds(100, 100, 900, 650);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		JPanel p1 = new JPanel();
		p1.setBackground(Color.WHITE);
		getContentPane().add(p1, BorderLayout.NORTH);
		p1.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new EmptyBorder(30, 10, 30, 0));
		panel_2.setBackground(SystemColor.control);
		p1.add(panel_2, BorderLayout.WEST);
		
		JButton btnBack = new JButton("←");
		panel_2.add(btnBack);
		
		JPanel panel_3 = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel_3.getLayout();
		flowLayout.setVgap(20);
		panel_3.setBackground(SystemColor.control);
		p1.add(panel_3, BorderLayout.CENTER);
		
				JLabel latop = new JLabel("주문 조회");
				panel_3.add(latop);
				latop.setFont(new Font("맑은 고딕", Font.PLAIN, 30));
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				Cus_selectView backView = new Cus_selectView(toss);
				backView.setVisible(true);
				
			}
		});

		JPanel p2 = new JPanel();
		getContentPane().add(p2, BorderLayout.CENTER);
		p2.setLayout(new BorderLayout(0, 0));

		JPanel pSelect = new JPanel();
		pSelect.setBackground(Color.WHITE);
		p2.add(pSelect, BorderLayout.NORTH);
		pSelect.setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.control);
		pSelect.add(panel, BorderLayout.NORTH);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		// 한달 전 구하기
		JButton btnNewButton = new JButton("1개월");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
				Calendar cal = Calendar.getInstance();
				cal.add(Calendar.MONTH, -1);
				tfDate.setText(format1.format(cal.getTime()));
				tfDate.setHorizontalAlignment(JTextField.CENTER);

			}
		});
		panel.add(btnNewButton);
		System.out.println("주문내역 조회 : " + toss);

		JButton button = new JButton("3개월");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
				Calendar cal = Calendar.getInstance();
				cal.add(Calendar.MONTH, -3);
				tfDate.setText(format1.format(cal.getTime()));
				tfDate.setHorizontalAlignment(JTextField.CENTER);

			}
		});
		panel.add(button);

		JButton button_1 = new JButton("6개월");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
				Calendar cal = Calendar.getInstance();
				cal.add(Calendar.MONTH, -6);
				tfDate.setText(format1.format(cal.getTime()));
				tfDate.setHorizontalAlignment(JTextField.CENTER);
			}
		});
		panel.add(button_1);

		JButton button_2 = new JButton("12개월");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
				Calendar cal = Calendar.getInstance();
				cal.add(Calendar.YEAR, -1);
				tfDate.setText(format1.format(cal.getTime()));
				tfDate.setHorizontalAlignment(JTextField.CENTER);

			}
		});
		panel.add(button_2);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(SystemColor.control);
		pSelect.add(panel_1, BorderLayout.SOUTH);
		panel_1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		tfDate = new JTextField();
		panel_1.add(tfDate);
		tfDate.setColumns(10);

		JLabel lblNewLabel = new JLabel("~");
		lblNewLabel.setFont(new Font("돋움체", Font.BOLD, 20));
		panel_1.add(lblNewLabel);

		tfToday = new JTextField();
		panel_1.add(tfToday);
		tfToday.setColumns(10);

		JButton btnNewButton_1 = new JButton("조회");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SearchDAO dao = new SearchDAO();
				
				

				String firstDate = tfDate.getText();
				
				dao.getOrder(toss, firstDate);
				
				model.setRowCount(0);
				DefaultTableModel model = new DefaultTableModel(dao.getOrder(toss,firstDate), getColumn());

				JTable.setModel(model);

				DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();
				dtcr.setHorizontalAlignment(SwingConstants.CENTER);

				TableColumnModel tcm = JTable.getColumnModel();
				for (int i = 0; i < tcm.getColumnCount(); i++) {
					tcm.getColumn(i).setCellRenderer(dtcr);
				}

			}
		});
		panel_1.add(btnNewButton_1);

		// 오늘 날짜 구하기
		SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");

		Date time = new Date();
		String time1 = format1.format(time);

		tfToday.setText(time1);
		tfToday.setHorizontalAlignment(JTextField.CENTER);

		// 테이블 관련
		JPanel pTable = new JPanel();
		pTable.setBackground(Color.WHITE);
		model = new DefaultTableModel(v, cols) {

			public boolean isCellEditable(int i, int c) {
				return false;
			}

		};

		pTable.setLayout(new BorderLayout(0, 0));
		JScrollPane pane = new JScrollPane();
		pTable.add(pane);

		JScrollPane scrollPane = new JScrollPane();
		pane.setViewportView(scrollPane);

		JTable = new JTable();
		SearchDAO dao = new SearchDAO();

		System.out.println("v=" + v);
		cols = getColumn();

		model = new DefaultTableModel(v, cols) {

			public boolean isCellEditable(int i, int c) {
				return false;
			}

		};

		JTable = new JTable(model) {
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		JTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		JTable.getTableHeader().setReorderingAllowed(false);// 컬럼들 이동 금지
		JTable.getTableHeader().setResizingAllowed(false); // 헤더 셀 크기 조절 금지

		/****************************/

		scrollPane.setViewportView(JTable);
		p2.add(pTable, BorderLayout.CENTER);
		setLocationRelativeTo(null);
	}


	// JTable의 컬럼
	public Vector getColumn() {
		Vector col = new Vector();
		col.add("송장번호");
		col.add("고객이름");
		col.add("전화번호");
		col.add("지역");
		col.add("기사이름");
		col.add("출발일");
		col.add("도착일");

		return col;
	}// getColumn


}
