/*
 * w.이승연
 * */

package JaBae.Gisa.Event;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import JaBae.Gisa.DAO.LocalDeliveryDAO;
import JaBae.Gisa.Main.LocalDeliveryList;

public class LocalDeliveryEventClass implements ActionListener {
	
	private JTable tableDList;
	private String gisaId;
	private DefaultTableModel modelDList;

	public LocalDeliveryEventClass(JTable tableDList, String gisaId, DefaultTableModel modelDList) {
		this.tableDList = tableDList;
		this.gisaId = gisaId;
		this.modelDList = modelDList;
	}
	
	public void updateGisaId() throws NumberFormatException, SQLException {
		int selectRow = tableDList.getSelectedRow();
		LocalDeliveryDAO ldDAO = new LocalDeliveryDAO();
		
		if(selectRow ==-1) JOptionPane.showMessageDialog(null, "값을 선택해주세요");
		else {
			Object orderNo = tableDList.getValueAt(selectRow, 0);
			ldDAO.updateGisaId(gisaId, Integer.parseInt(orderNo.toString()));
			modelDList.removeRow(tableDList.getSelectedRow());
			JOptionPane.showMessageDialog(null, "배송 수락 완료!");
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("수락")) {
			try {
				updateGisaId();
				
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}
	}
	

}
