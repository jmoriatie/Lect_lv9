package data;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Vector;

import main.MainFrame;

public class UsersFile {
	String fileName = "users";
	File file = new File(fileName);
	
	FileWriter fw;
	FileReader fr;
	BufferedReader br;
	
	public void save() {
		String w = "";
		try {
			fw = new FileWriter(file);
			for(int i=0; i<MainFrame.users.size(); i++) {
				for(int j=0; j<3; j++) {
					w += MainFrame.users.get(i).get(j);
					if(j != MainFrame.users.get(i).size()-1) w+="/";
				}
				if(i != MainFrame.users.size()-1 ) w+="\n";
			}
			fw.write(w);
			System.out.println(w);
			System.out.println("[파일세이브 성공]");
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void load() {
		if(file.exists()) {
			try {
				fr = new FileReader(file);
				br = new BufferedReader(fr);
				
				int usersCnt = 0;
				while(br.readLine() != null) {
					usersCnt++;
				}
				
				fr = new FileReader(file);
				br = new BufferedReader(fr);
				for(int i=0; i<usersCnt; i++) {
					String temp[] = br.readLine().split("/");
					Vector<String> tempArr = new Vector<String>();
					tempArr.add(temp[0]);
					tempArr.add(temp[1]);
					tempArr.add(temp[2]);
					MainFrame.users.add(tempArr);
				}
				System.out.println("[불러오기 성공]");
				br.close();
			} catch (Exception e) {
				e.printStackTrace();
			} 
		}
	}
}
