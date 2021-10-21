package controller;

import java.util.HashMap;
import java.util.Map;

import main.Main;
import model.Subject;
import model.User;

public class UserController {
	
	private static UserController instance = new UserController();
	
	Map<String, User> users = new HashMap<String, User>();
	
	private UserController() {
		setUser( new User("first", "sss", "나일등") );
		setUser( new User("sec", "ttt", "이번이번") );
		setUser( new User("thr", "ddd", "삼삼이") );
		getUser("first").setSubject( SubjectController.getInstance().getSubject(1) );
		getUser("first").setSubject( SubjectController.getInstance().getSubject(2) );
		getUser("thr").setSubject( SubjectController.getInstance().getSubject(4) );
	}
	
	public static UserController getInstance() {
		return instance;
	}
	
	public User getUser(String userId) {
		User user = null;
		for(Object u : this.users.keySet()) {
			if(userId.equals(u)) {
				user = this.users.get(u);
			}
		}
		return user;
	}
	
	public boolean setUser(User user) {
		if(checkJoin(user)) {
			this.users.put(user.getUserId(), user);
			return true;
		}
		return false;
	}
	
	private boolean checkJoin(User user) {
		for(Object u : this.users.keySet()) {
			if(user.getUserId().equals(u)) {
				return false;
			}
		}
		return true;
	}
	
	public boolean checkUser(String id, String pw) {
		for(String u : this.users.keySet()) {
			if(u.equals(id)) {
				if(users.get(id).getUserPassword().equals(pw)) {
					return true;
				}
			}
		}
		return false;
	}
	
	public boolean updateUser() {
		boolean check = false;
		return check;
	}
	
	public boolean updateSubject() {
		boolean check = false;
		// subjectController로 연결할 수 있도록 작성
		return check;
	}
	
	public void printAllUser() {
		System.out.println("[유저리스트]");
		int n = 1;
		for(Object key : this.users.keySet()) {
			User user = this.users.get(key);
			System.out.printf("[id: %s][pw: %s][name: %s]\n", user.getUserId(),user.getUserPassword(),user.getUserName());
			System.out.print("ㄴ 신청과목:");
			for(Subject sub : user.getSubjects()) {
				System.out.printf("[%s]",sub.getSubjectName());
			}
			System.out.println();
		}
	}
	
	public void updateUserInfo(String log) {
		System.out.println("1) 비밀번호 변경");
		System.out.println("2) 이름 변경");
		System.out.print(">> ");
		int sel = Main.sc.nextInt();
		if(sel == 1) {
			System.out.print("변경할 비밀번호 입력: ");
			String pw = Main.sc.next();
			users.get(log).setUserPassword(pw);
			System.out.println("비밀번호 변경 완료");
		}
		else if(sel == 2) {
			System.out.print("변경할 이름 입력: ");
			String name = Main.sc.next();
			users.get(log).setUserName(name);
			System.out.println("이름 변경 완료");
		}
	}
	
	public void updateSubject(String log) {
		System.out.println("1) 과목추가 ");
		System.out.println("2) 과목삭제 ");
		System.out.print(">> ");
		int sel = Main.sc.nextInt();
		if(sel == 1) {
			SubjectController.getInstance().printSubjectList();
			System.out.print("과목번호 선택: ");
			int idx = Main.sc.nextInt();
			this.users.get(log).setSubject(SubjectController.getInstance().getSubject(idx));
			System.out.printf("[%s]과목 추가 완료\n",SubjectController.getInstance().getSubject(idx).getSubjectName());
		}
		else if(sel == 2) {
			this.users.get(log).printSubjects();
			System.out.print("과목번호 선택: ");
			int idx = Main.sc.nextInt();
			if(idx >= 0 && idx < this.users.get(log).getSubjects().size()) {
				System.out.printf("[%s]과목 삭제 완료\n",SubjectController.getInstance().getSubject(idx).getSubjectName());
				this.users.get(log).getSubjects().remove(idx-1);
			} else System.out.println("과목 삭제 실패");
		}		
	}
	
	public boolean removeUser(String id) {
		for(String u : users.keySet()) {
			if(u.equals(id)) {
				users.remove(u);
				return true;
			}
		}
		return false;
	}
}
