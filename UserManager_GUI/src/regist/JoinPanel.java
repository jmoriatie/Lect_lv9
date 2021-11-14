package regist;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.Vector;


import main.Contoroller;
import main.MainFrame;
import util.Util;

public class JoinPanel extends Util{
	
	Contoroller con = new Contoroller();
	
	public JoinPanel() {
		setLayout(null);
		setBounds(0,0,400,300);
		setBackground(Color.LIGHT_GRAY);
		addKeyListener(this);

//		add(con.getWriteArea());
		add(con.getSubmit());
//		con.getWriteArea().addKeyListener(this);
		con.getSubmit().addKeyListener(this);
		con.getSubmit().addActionListener(this);
		
		for(int i=0; i<con.labels.size(); i++) {
			add(con.labels.get(i));
		}
		add(con.getId());
		add(con.getPassword());
		add(con.getName());
		
		setVisible(true);
		revalidate();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == con.getSubmit()) {
			String id = con.getId().getText();
			String password = con.getPassword().getText();
			String name = con.getName().getText();
//			String write = con.getWriteArea().getText();
			
			// 빈칸없나 확인
			boolean check = true;
			if (id.equals("") || password.equals("") || name.equals("")) {
				check = false;
			}

			if (!check) {
				// 모든 정보를 입력하세요
				System.out.println("[정보 누락]");
				con.setMessageLab("추가 정보입력 필요");
			} 
			else {
				// 유저만들어서 넣기
				Vector<String> userData = new Vector<String>();
				userData.add(id);
				userData.add(password);
				userData.add(name);
				
				if (con.setUserData(userData)) {
					System.out.println("[회원가입 완료]");
					con.setMessageLab("회원가입 성공");
					MainFrame.fileControl.save(); // 세이브
				}
				else {
					System.out.println("[회원가입 실패]_중복회원");
					con.setMessageLab("회원가입 실패");
				}
			}
		}
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {			
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}
