package main;

import controller.UserController;

public class Login {
	String log = "";
	
	public void run() {
		while(true) {
			// 비로그인
			if(log.equals("")) {
				if(!login()) {
					System.out.println("아이디 또는 비밀번호를 확인하세요");
					continue;
				}
			}
			
			// 로그인시
			viewMenu();
			int sel = -1;
			try {
				sel = Integer.parseInt(Main.sc.next());
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			if(sel == 1) { // 학생정보변경
				UserController.getInstance().updateUserInfo(log);
			}
			else if(sel == 2) { // 수강신청
				UserController.getInstance().updateSubject(log);
			}
			else if(sel == 0) {
				this.log = "";
				System.out.println("로그아웃");
				break;
			}
			
		}
	}
	
	public boolean login() {
		System.out.println("====로그인====");
		System.out.print("ID:");
		String id = Main.sc.next();
		
		System.out.print("PASSWORD:");
		String password = Main.sc.next();
		if(UserController.getInstance().checkUser(id, password)) {
			this.log = id;
			return true;
		}
		return false;
	}
	
	public void viewMenu() {
		System.out.printf("[%s님 로그인 중]\n", this.log);
		System.out.println("1] 학생정보 확인 및 변경");
		System.out.println("2] 수강과목 신청 및 변경");
		System.out.println("0] 로그아웃");
		System.out.print(">> ");
	}
	
	
}
