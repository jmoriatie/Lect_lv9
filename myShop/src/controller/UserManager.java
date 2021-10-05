package controller;

import java.util.ArrayList;
import java.util.Scanner;

import models.User;

public class UserManager {
		
	public static UserManager instance = new UserManager();
	
	ArrayList<User> users;
	
	// 메인: 가입, 탈퇴, 로그인, 로그아웃, 관리자, 종료
	// 관리자 : 유저 전체 출력
	
	private UserManager() {
		users = new ArrayList<User>();
	}

	// 가입
	public void join() {
		System.out.print("가입할 ID입력: ");
		String input = Shop.sc.next();
		if(this.getUser(input) == null) {
			users.add(new User(input, 5000));		
			System.out.println("["+input + "님 가입 성공]");
		} else System.out.println("[이미 존재하는 ID입니다]");
	}
	
	// get User + 중복체크
	public User getUser(String inId) {
		User user = null;
		for(User u : users) {
			if(u.getId().equals(inId)) {
				user = u;
			}
		}
		return user;
	}
	
	// 탈퇴
	public void delete() {
		System.out.print("탈퇴할 ID입력: ");
		String input = Shop.sc.next();
		User delUser = this.getUser(input);
		if(delUser != null) {
			users.remove(delUser);	
			System.out.println("[정상 탈퇴되었습니다]");
		} else System.out.println("[없는 ID입니다]");
	}
	
	// 로그인
	public void login() {
		System.out.print("ID입력: ");
		String input = Shop.sc.next();
		User loginId = this.getUser(input);
		if(loginId != null) {
			Shop.logId = loginId.getId(); // static으로 하는 것이 나은 방법?
			System.out.println("[로그인 되었습니다]");
		} else System.out.println("[없는 ID입니다]");
	}
	
	// 로그아웃
	public void logout() {
		if(Shop.logId != null) {
			Shop.logId = null;
		} else System.out.println("[로그아웃 상태입니다]");
	}
	
	// user 전체 출력
	public void printAllUser() {
		if(users.size() != 0) {
			System.out.println("[전체 유저]");
			for(User user : users) {
				System.out.printf(" ㄴ id: %s, 보유액: %d원\n",user.getId(), user.getMoney());
			}
		} else System.out.println("[저장된 회원이 없습니다]");
	}
	
	// id 입력 -> 한명 정보출력
	public void getUserInfo(String userId) {
		User user = this.getUser(userId);
		if(user != null) {
			System.out.println("["+ userId +"님 회원정보]");
			System.out.printf(" ㄴ id: %s, money: %d\n",user.getId(), user.getMoney());
		}else System.out.println("[없는 회원입니다]");
	}
	
	// 입금 메서드
	public void insetMoney(String userId) {
		System.out.println("입금 금액 입력: ");
		User user = getUser(userId);
		String input = Shop.sc.next();
		int inMon = -1;
		try {
			inMon = Integer.parseInt(input);
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		if(inMon > 0) {
			user.setMoney( user.getMoney() + inMon );
			System.out.printf("[%d원 입금 완료]\n", inMon);
		}
	}
}
