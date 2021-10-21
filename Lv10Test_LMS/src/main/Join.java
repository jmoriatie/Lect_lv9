package main;

import controller.UserController;
import model.User;

public class Join {
	
	public void join() {
		System.out.print("가입할 id입력:");
		String id = Main.sc.next();
		System.out.print("비밀번호 입력:");
		String password = Main.sc.next();
		System.out.print("이름 입력:");
		String name = Main.sc.next();
		User newUser = new User(id, password, name);
		if(UserController.getInstance().setUser(newUser)){
			System.out.println("가입 성공!");
		}
		else {
			System.out.println("이미 있는 id입니다");
		}
	}

	public void delete() {
		System.out.print("삭제할 id입력:");
		String id = Main.sc.next();
		System.out.print("비밀번호 입력:");
		String password = Main.sc.next();		
		if(UserController.getInstance().checkUser(id, password)) {
			if(UserController.getInstance().removeUser(id)) {
				System.out.println("삭제 성공!");
			}
			else System.out.println("삭제 실패");
		} else System.out.println("아이디 또는 비밀번호를 확인하세요");
	}
}
