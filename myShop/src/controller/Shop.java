package controller;

import java.util.ArrayList;
import java.util.Scanner;

import models.Cart;
import models.Item;
import models.User;

public class Shop {

	// 메인: 가입, 탈퇴, 로그인, 로그아웃, 관리자, 종료
	static Scanner sc = new Scanner(System.in);
	
	ItemManager im = ItemManager.instance;
	UserManager um = UserManager.instance;
	
	public static String logId = null;
	
	public static Shop instance = new Shop();

	private boolean run = true;
	public void mainMenu() {
		while(this.run) {
			menu();
		}
	}
	
	// 메뉴
	private void menu() {
		System.out.println("=== 랄라 shop ===");
		System.out.println("1. 가입");
		System.out.println("2. 탈퇴");
		System.out.println("3. 로그인");
		System.out.println("100. 관리자 모드");
		System.out.println("0. 종료");
		System.out.print(">> ");
		this.select();
	}
	
	// 메인메뉴선택
	private void select() {
		String input = sc.next();
		int sel = -1;
		try {
			sel = Integer.parseInt(input);
		} catch(Exception e) {
			System.out.println("선택 실패!");
			e.printStackTrace();
		}
		
		if(sel == 1) { 
			um.join();
		}
		else if(sel == 2) {
			um.delete();
		}
		else if(sel == 3) {
			this.loginSelect();
		}
		else if(sel == 100) {
			this.masterSelect();
		}
		else if(sel == 0) {
			System.out.println("시스템 종료");
			this.run = false;
		}
	}
	
	// 로그인 메뉴
	private void loginMenu() {
			System.out.println("### 로그인 메뉴 ###");
			System.out.println("[" +um.getUser(logId).getId()+ "님 로그인 중...]");
			System.out.println("1] 쇼핑");
			System.out.println("2] 상품목록");
			System.out.println("0] 로그아웃");
			System.out.print(">> ");
	}
	
	// 메뉴선택
	private void loginSelect() {
		um.login();
		while(logId != null) { // if의 효과도 같이 되려
			//id출력
			loginMenu();
			String input = sc.next();
			int sel = -1;
			try {
				sel = Integer.parseInt(input);
			} catch(Exception e) {
				System.out.println("선택 실패!");
				e.printStackTrace();
			}
			
			if(sel == 1) { 	// 쇼핑
				shoppingSelect();
			}
			else if(sel == 2) {
				im.printAllItemList();
			}
			else if(sel == 0) {
				um.logout(); // while문 종료 => 뒤로가기 같이 됨
			}
		}
	}
	
	// 쇼핑메뉴
	private void shoppingMenu() {
		System.out.println("--- 쇼핑 메뉴 ---");
		System.out.println("내 잔액 : " + um.getUser(logId).getMoney()+"원");
		System.out.println("1) 구입");
		System.out.println("2) 삭제");
		System.out.println("3) 내 장바구니");
		System.out.println("4) 입금");
		System.out.println("5) 전체 결제");
		System.out.println("0) 뒤로가기");
		System.out.print(">> ");
}

	// 메뉴선택
	private void shoppingSelect() {
		while(true) { // if의 효과도 같이 되려
			shoppingMenu();
			String input = sc.next();
			int sel = -1;
			try {
				sel = Integer.parseInt(input);
			} catch(Exception e) {
				System.out.println("선택 실패!");
				e.printStackTrace();
			}
			if(sel == 1) { // 구입
				im.shopping(logId);
			}
			else if(sel == 2) { // 삭제
				im.deleteItem(logId);
			}
			else if(sel == 3) { //  내 장바구니
				im.printMyCart(logId);				
			}
			else if(sel == 4) { // 입금
				um.insetMoney(logId);
			}
			else if(sel == 5) { // 전체결제
				im.payAll(logId);
			}
			else if(sel == 0) {
				break;
			}
		}
	}
	
	// ===============================================
	
	// 관리자 로그인
	private void masterMenu() {
		System.out.println("#### 관리자 모드 ####");
		System.out.println("1] 아이템 관리");
		System.out.println("2] 카테고리 관리");
		System.out.println("3] 장바구니 관리");
		System.out.println("4] 유저 관리");
		System.out.println("0] 뒤로가기");
		System.out.print(">> ");
}

	// 메뉴선택
	private void masterSelect() {
		while(true) {
			masterMenu();
			String input = sc.next();
			int sel = -1;
			try {
				sel = Integer.parseInt(input);
			} catch(Exception e) {
				System.out.println("선택 실패!");
				e.printStackTrace();
			}
			
			if(sel == 1) { 
				MasterItemSelect();
			}
			else if(sel == 2) {
				MasterCategorySelect();
			}
			else if(sel == 3) {
				MasterCartSelect();
			}
			else if(sel == 4) {
				MasterUserSelect();
			}
			else if(sel == 0) {
				break;
			}
		}
	}
	
	// 아이템 관리
	private void MasterItemMenu() {
		System.out.println("--- 아이템관리 모드 ---");
		System.out.println("1) 전체 아이템 출력");
		System.out.println("2) 아이템 추가");
		System.out.println("3) 아이템 삭제");
		System.out.println("0) 뒤로가기");
		System.out.print(">> ");
	}
	
	// 메뉴선택
	private void MasterItemSelect() {
		while(true) {
			MasterItemMenu();
			String input = sc.next();
			int sel = -1;
			try {
				sel = Integer.parseInt(input);
			} catch(Exception e) {
				System.out.println("선택 실패!");
				e.printStackTrace();
			}			
			if(sel == 1) { 
				im.printAllItemList();
			}
			else if(sel == 2) {
				im.addItem();
			}
			else if(sel == 3) {
				im.deleteItem();
			}
			else if(sel == 0) {
				break;
			}
		}
	}
	

	// 카테고리 관리
	private void MasterCategoryMenu() {
		System.out.println("--- 카테고리 관리 모드 ---");
		System.out.println("1) 전체 카테고리 출력");
		System.out.println("2) 카테고리 추가");
		System.out.println("3) 카테고리 삭제");
		System.out.println("0) 뒤로가기");
		System.out.print(">> ");
	}
	
	// 메뉴선택
	private void MasterCategorySelect() {
		while(true) {
			MasterCategoryMenu();
			String input = sc.next();
			int sel = -1;
			try {
				sel = Integer.parseInt(input);
			} catch(Exception e) {
				System.out.println("선택 실패!");
				e.printStackTrace();
			}			
			if(sel == 1) { 
				im.printAllCategory();
			}
			else if(sel == 2) {
				im.addCategory();
			}
			else if(sel == 3) {
				im.deleteCategory();
			}
			else if(sel == 0) {
				break;
			}
		}
	}
	// 장바구니 관리 ==> 유저를 선택해서  해당 유저 장바구니 관리
//	[1.전체장바구니] [2. 유저 아이템추가] [3.유저 아이템삭제] [0.뒤로가기]
	// 장바구니 관리
	private void MasterCartyMenu() {
		System.out.println("--- 장바구니 관리 모드 ---");
		System.out.println("1) 전체 장바구니 출력");
		System.out.println("2) 장바구니 추가");
		System.out.println("3) 장바구니 삭제");
		System.out.println("0) 뒤로가기");
		System.out.print(">> ");
	}
	
	// 메뉴선택
	private void MasterCartSelect() {
		while(true) {
			MasterCartyMenu();
			String input = sc.next();
			int sel = -1;
			try {
				sel = Integer.parseInt(input);
			} catch(Exception e) {
				System.out.println("선택 실패!");
				e.printStackTrace();
			}			
			if(sel == 1) { 
				im.printAllCart();
			}
			else if(sel == 2) {
				im.addCart();
			}
			else if(sel == 3) {
				im.deleteCart();
			}
			else if(sel == 0) {
				break;
			}
		}
	}
	
	// 유저 관리
	private void MasterUserMenu() {
		System.out.println("--- 유저관리 모드 ---");
		System.out.println("1) 전체 유저 출력");
		System.out.println("2) 유저 추가");
		System.out.println("3) 유저 삭제");
		System.out.println("0) 뒤로가기");
		System.out.print(">> ");
}

	// 메뉴선택
	private void MasterUserSelect() {
		while(true) {
			MasterUserMenu();
			String input = sc.next();
			int sel = -1;
			try {
				sel = Integer.parseInt(input);
			} catch(Exception e) {
				System.out.println("선택 실패!");
				e.printStackTrace();
			}
			if(sel == 1) { 
				um.printAllUser();
			}
			else if(sel == 2) {
				um.join();
			}
			else if(sel == 3) {
				um.delete();
			}
			else if(sel == 0) {
				break;
			}
		}
	}

	
}
