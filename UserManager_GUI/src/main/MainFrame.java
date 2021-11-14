package main;

import java.awt.Color;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.LineBorder;

import data.JoinUsers;
import data.UsersFile;
import login.LoginFrame;
import regist.JoinFrame;

public class MainFrame extends JFrame{
	MainPanel p;
	
	public static JoinUsers usersInfo; 
	public static UsersFile fileControl;
	public static JoinFrame joinFrame = null;
	public static LoginFrame loginFrame = null;
	public static Vector<Vector<String>> users;

	public MainFrame() {
		super("메인화면");
		setLayout(null);
		setBounds(100,100,500,400);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	
		init();
		
		p = new MainPanel();
		add(p);
		
		fileControl.load();
		
		setVisible(true);
		revalidate();
	}
	
	private void init() {
		fileControl = new UsersFile();
		users = new Vector<Vector<String>>();
		usersInfo = new JoinUsers();
		

	}
	
	
	public static void main(String[] args) {
		new MainFrame();
	}
	
	
}
