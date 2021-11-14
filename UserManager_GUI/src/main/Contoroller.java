package main;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import data.JoinUsers;
import data.UserData;

public class Contoroller {
	
	private JLabel idLab;
	private JLabel passwordLab;
	private JLabel nameLab;
	private JLabel writeAreaLab;
	private JLabel messageLab;
	
	private JTextField id;
	private JTextField password;
	private JTextField name;
//	private JTextArea writeArea;
	
	private JButton submit;

	// 버튼 1개, 텍스트 에어리어 1개
	public ArrayList<JLabel> labels;
	
	public Contoroller() {
		init();
		set();
	}
	
	private void init() {
		labels = new ArrayList<JLabel>();

		// 라벨
		idLab = new JLabel("아이디");
		passwordLab = new JLabel("비밀번호");
		nameLab = new JLabel("이름");
		messageLab = new JLabel();
//		writeAreaLab = new JLabel();
		
		// 텍스트필드, 텍스트에어리어
		id = new JTextField();
		password = new JTextField();
		name = new JTextField();
//		writeArea = new JTextArea();

//		id.setText("아이디를 입력하세요.");
//		password.setText("비밀번호를 입력하세요.");
//		name.setText("이름을 입력하세요.");
//		writeArea.setText("글 입력 공간입니다");
		// 버튼
		submit = new JButton();
	}
	
	private void set() {
		// 라벨
		idLab.setBounds(10, 10, 50, 30);
		passwordLab.setBounds(10, 50, 50, 30);
		nameLab.setBounds(10, 90, 50, 30);
		messageLab.setBounds(120, 125, 100, 20);

//		writeAreaLab.setBounds(10, 130, 50, 30);
		// 텍스트필드, 텍스트에어리어
		id.setBounds(70, 10, 200, 30);
		password.setBounds(70, 50, 200, 30);
		name.setBounds(70, 90, 200, 30);
//		writeArea.setBounds(70, 130, 200, 100);
		
//		id.setText("아이디를 입력하세요.");
//		password.setText("비밀번호를 입력하세요.");
//		name.setText("이름을 입력하세요.");
//		writeArea.setText("글 입력 공간입니다");

		// 버튼
		submit.setText("제출");
		submit.setBounds(130, 150, 50, 30);

		// 배열에 저장
		labels.add(idLab);
		labels.add(passwordLab);
		labels.add(nameLab);
		labels.add(messageLab);
//		labels.add(writeAreaLab);
	}

	public boolean setUserData(Vector<String> user) {
		boolean check = true;
		// 중복아이디
		if(MainFrame.users.size() != 0) {
			for(int i=0; i<MainFrame.users.size(); i++){
				if(MainFrame.users.get(i).get(0).equals(user.get(0))) check = false;
			}
		}
		if(check) {
			MainFrame.users.add(user); // 중복 아이디 없으면 저장
//			MainFrame.usersInfo.getUserPanel().printData();
		}
		return check;
	}
	
	public boolean checkLogin(Vector<String> user) {
		boolean check = false;
		
		if(MainFrame.users.size() != 0) {
			for(int i=0; i<MainFrame.users.size(); i++){
				if(MainFrame.users.get(i).get(0).equals(user.get(0))) {
					if(MainFrame.users.get(i).get(1).equals(user.get(1))) {
						MainPanel.setUserInfo(user.get(0));
						check = true;
//						MainFrame.usersInfo.getUserPanel().printData();
					}
				}
			}
		}
		
		return check;
	}
	
	public JTextField getId() {
		return id;
	}

	public void setId(JTextField id) {
		this.id = id;
	}

	public JTextField getPassword() {
		return password;
	}

	public void setPassword(JTextField password) {
		this.password = password;
	}

	public JTextField getName() {
		return name;
	}

	public void setName(JTextField name) {
		this.name = name;
	}

//	public JTextArea getWriteArea() {
//		return writeArea;
//	}
//
//	public void setWriteArea(JTextArea writeArea) {
//		this.writeArea = writeArea;
//	}

	public JButton getSubmit() {
		return submit;
	}

	public JLabel getIdLab() {
		return idLab;
	}

	public void setIdLab(JLabel idLab) {
		this.idLab = idLab;
	}

	public JLabel getPasswordLab() {
		return passwordLab;
	}

	public void setPasswordLab(JLabel passwordLab) {
		this.passwordLab = passwordLab;
	}

	public JLabel getNameLab() {
		return nameLab;
	}

	public void setNameLab(JLabel nameLab) {
		this.nameLab = nameLab;
	}

	public JLabel getWriteAreaLab() {
		return writeAreaLab;
	}

	public void setWriteAreaLab(JLabel writeAreaLab) {
		this.writeAreaLab = writeAreaLab;
	}

	public void setSubmit(JButton submit) {
		this.submit = submit;
	}

	public JLabel getMessageLab() {
		return messageLab;
	}

	public void setMessageLab(String massge) {
		if(massge.equals("회원가입 성공")) {
			messageLab.setForeground(Color.black);
			MainFrame.joinFrame.dispose();
		}
		else if(massge.equals("회원가입 실패") || massge.equals("추가 정보입력 필요")) {
			messageLab.setForeground(Color.red);
		}
		this.messageLab.setText(massge);
	}
}
