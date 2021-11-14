package login;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.util.Vector;

import javax.swing.JOptionPane;

import main.Contoroller;
import main.MainFrame;
import main.MainPanel;
import util.Util;

public class LoginPanel extends Util{

	Contoroller con = new Contoroller();
	
	public LoginPanel() {
		setLayout(null);
		setBounds(0,0,400,300);
		setBackground(Color.LIGHT_GRAY);
		addKeyListener(this);

		add(con.getSubmit());
		con.getSubmit().addKeyListener(this);
		con.getSubmit().addActionListener(this);
		
		add(con.getIdLab());
		add(con.getPasswordLab());
		add(con.getMessageLab());
		
		add(con.getId());
		add(con.getPassword());
		
		setVisible(true);
		revalidate();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == con.getSubmit()) {
			String id = con.getId().getText();
			String password = con.getPassword().getText();
			
			// 빈칸없나 확인
			boolean check = true;
			if (id.equals("") || password.equals("")) {
				check = false;
			}
			if (!check) {
				// 모든 정보를 입력하세요
				System.out.println("[정보 누락]");
				JOptionPane.showMessageDialog(null, "아이디/비밀번호 입력");
				con.setMessageLab("정보입력 필요");
			} 
			else {
				// 유저만들어서 체크
				Vector<String> userData = new Vector<String>();
				userData.add(id);
				userData.add(password);
				
				if(con.checkLogin(userData)) {
					System.out.println("[로그인 성공]");
					con.setMessageLab("로그인 성공");	
					JOptionPane.showMessageDialog(null, "로그인 성공");
					MainFrame.loginFrame.dispose();
				}
				else {
					System.out.println("[로그인 실패]");
					con.setMessageLab("로그인 실패");
				}
			}
		}
	}
}
