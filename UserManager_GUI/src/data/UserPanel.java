package data;

import java.awt.Color;
import java.util.Vector;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.LineBorder;

import main.MainFrame;

public class UserPanel extends JPanel{
	
//	JLabel userInfo;
	public Vector<String> colName;
	JTable table;

	public UserPanel() {
		setLayout(null);
		setBounds(0,0,500,900);
		setVisible(true);
//		userInfo = new JLabel();
//		userInfo.setBounds(0, 0, 500,900);
//		userInfo.setVerticalAlignment(JLabel.TOP);
//		userInfo.setText("------- 회원목록 -------");
//		printData();	
//		add(userInfo);
		setTable();
		add(table);
	}
	// 테이블설계로 변경
	public void setTable() {
				
		colName = new Vector<String>();
		
		this.colName.add("id");
		this.colName.add("pw");
		this.colName.add("name");
		
		table = new JTable(MainFrame.users, colName);
		this.table.setBounds(0, 20, 500, 800);
		table.setGridColor(Color.black);
		table.setBorder(new LineBorder(Color.red));
		
		table.setCellEditor(null); // 에디터
		table.setDragEnabled(true); // 드래그 가능
		
		table.setCellSelectionEnabled(true); // 가져다 대면 거기를 포커싱
	}
	
	// 기존설계
//	public void printData() {
//		if(MainFrame.users.size() != 0) {
//			String info = "<html>------- 회원목록 -------<br>"; 
//			for(Vector<String> user : MainFrame.users) {
//				System.out.printf("[id:%s][pw:%s][name:%s]", user.get(0), user.get(1), user.get(2));
//				
//				info += String.format("[id:%s][pw:%s][name:%s]", user.get(0), user.get(1), user.get(2));
//				if(MainFrame.users.indexOf(user) != MainFrame.users.size()-1) info += "<br>";
//			}
//			info += "<br>--------------------</html>";
//			userInfo.setText(info);
//		}
//	}
	
}
