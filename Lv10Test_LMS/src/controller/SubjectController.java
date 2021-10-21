package controller;

import java.util.ArrayList;

import model.Subject;

public class SubjectController {
	
	private static SubjectController instance = new SubjectController();
	
	ArrayList<Subject> subjectList;

	private SubjectController() {
		subjectList = new ArrayList<Subject>();
		subjectList.add(new Subject("국어"));
		subjectList.add(new Subject("수학"));
		subjectList.add(new Subject("영어"));
		subjectList.add(new Subject("과학"));
		subjectList.add(new Subject("사회"));
	}
	
	public static SubjectController getInstance() {
		return instance; 
	}
	
	public Subject getSubject(int idx) {
		Subject subject = null;
		if(idx-1 >= 0 && idx-1 < subjectList.size()) {
			subject = subjectList.get(idx-1);
		}
		
		return subject;
	}
	public Subject getSubject(String subjectName) {
		Subject subject = null;
		for(Subject sub : subjectList) {
			if(sub.getSubjectName().equals(subjectName)) {
				subject = sub;
			}
		}
		return subject;
	}

	public boolean setSubject(int subjectIdx) {
		if(checkSubject(subjectIdx)) {
			this.subjectList.add(subjectList.get(subjectIdx-1));
			return true;
		}
		return false;
	}
	
	private boolean checkSubject(int subjectIdx) {
		if(subjectIdx-1 >=0 && subjectIdx-1 < subjectList.size()) {
			return true;
		}
		return true;
	}
	
	public void printSubjectList() {
		System.out.println("[전체 과목 리스트]");
		int n = 1;
		for(Subject sub : this.subjectList) {
			System.out.println(n++ +"] "+ sub.getSubjectName());
		}
	}
	
}
