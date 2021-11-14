package main;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.LineBorder;

import login.LoginFrame;
import regist.JoinFrame;
import util.Util;

public class MainPanel extends Util{

	private static String id = "";
	
	private static JLabel message;
	
	private JButton join;
	private JButton login;
	private JButton logout;
		
	public MainPanel() {
		setLayout(null);
		setBounds(0,0,500,400);
		
		init();
		
		setVisible(true);
	}

	private void init() {
		join = new JButton("JOIN");
		login = new JButton("LOGIN");
		logout = new JButton("LOGOUT");
		message = new JLabel();
		
		join.setBounds(50, 50, 70, 50);
		login.setBounds(150, 50, 70, 50);
		logout.setBounds(250, 50, 70, 50);
		message.setBounds(150, 300, 200, 50);
		
		join.addActionListener(this);
		login.addActionListener(this);
		logout.addActionListener(this);
		
		add(join, 0);
		add(login, 1);
		add(logout, 2);

		add(message);
	}
	
	// JTable(Vector<?>, Vector<?>)
	// 1) 실데이터
	// 2) 컬럼이름
		
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == join) {
			if(id.equals("")) {
				System.out.println("[Join 열기]");
				MainFrame.joinFrame = new JoinFrame();
			}
			else {
				message.setText("로그인 중입니다");
			}
		}
		if(e.getSource() == login) {
			if(id.equals("")) {
				System.out.println("[Login 열기]");
				MainFrame.loginFrame = new LoginFrame();
			}
			else {
				message.setText("로그인 중입니다");
			}
		}
		if(e.getSource() == logout) {
			if(!id.equals("")) {
				System.out.println("[logout]");
				id = "";
				JOptionPane.showMessageDialog(null, "로그아웃되었습니다");
				message.setText("");
			}
			else {
				JOptionPane.showMessageDialog(null, "로그인된 ID가 없습니다");
			}
		}
	}
	
	public static void setUserInfo(String inId) {
		id = inId;
		message.setText(String.format("%s 님이 로그인 중입니다", id));
	}
}
