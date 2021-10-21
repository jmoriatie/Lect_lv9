package model;

import java.util.ArrayList;

public class User {
	private String userId;
	private String userPassword;
	private String userName;
	private ArrayList<Subject> subjects;
	
	public User(String userId, String userPassword, String userName) {
		this.userId = userId;
		this.userPassword = userPassword;
		this.userName = userName;
		subjects = new ArrayList<Subject>();
	}
	
	public String getUserId() {
		return this.userId;
	}

	
	public String getUserPassword() {
		return this.userPassword;
	}
	
	public String getUserName() {
		return this.userName;
	}
	
	public ArrayList<Subject> getSubjects() {
		return this.subjects;
	}
	
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public void setSubjects(ArrayList<Subject> subjects) {
		this.subjects = subjects;
	}
	
	public void setSubject(Subject subject) {
		this.subjects.add(subject);
	}
	
	public void printSubjects() {
		int n = 1;
		for(Subject sub : subjects) {
			System.out.printf("%d] %s\n",n++,sub.getSubjectName());
		}
	}
}
