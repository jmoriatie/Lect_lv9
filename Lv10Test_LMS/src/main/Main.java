package main;

import java.util.Scanner;

import controller.SubjectController;
import controller.UserController;

// 시작 12:30
// 종료 16:30
// 경과 04:00

public class Main {
	public static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
		UserController us = UserController.getInstance();
		SubjectController sub = SubjectController.getInstance();
		
		Login login = new Login();
		Join join = new Join();
		
		while(true) {
			System.out.println("== 수강신청 ==");
			System.out.println("1. 가입");
			System.out.println("2. 탈퇴");
			System.out.println("3. 로그인");
			System.out.println("4. [확인용] 학생, 과목 조회");
			System.out.println("0. 종료");
			System.out.print(">> ");
			int sel = -1;
			try {
				sel = Integer.parseInt( sc.next() );
			}catch(Exception e) {
				e.printStackTrace();
			}
			
			if(sel == 1) {
				join.join();
			}
			else if(sel == 2) {
				join.delete();
			}
			else if(sel == 3) {
				login.run();
			}
			else if(sel == 4) {
				System.out.println("--- 확인용 조회 ---");
				us.printAllUser();
				System.out.println();
				sub.printSubjectList();
			}
			else if(sel == 0) {
				System.out.println("프로그램 종료");
				break;
			}
			
		}
		
	}
}
