package main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public class FileController {
	FileWriter fw = null;
	FileReader fr = null;
	BufferedReader br = null;
	
	String fileName = "list.txt";
	
	File file = new File(fileName);
	
	public static FileController instance = new FileController(); 
	
	private FileController() {
		
	}
	
	public void save() {
		
	}
	
	public void load() {
		
	}


	
}
