package data;

import javax.swing.JFrame;
import javax.swing.JScrollPane;


public class JoinUsers extends JFrame{
	public UserPanel userPanel;
	private JScrollPane sp; // 프레임에 스크롤 생성 

	
	public JoinUsers() {
		super("가입인원");
		setLayout(null);
		setBounds(700,100,500,900);
		userPanel = new UserPanel();
		add(userPanel);
		
		setScroll();
		
		setVisible(true);
		revalidate();
	}
	
	private void setScroll() {
		// scrollPane => 프레임에 스크롤 생성
		sp = new JScrollPane();
		add(sp);
	}
	
	public UserPanel getUserPanel() {
		return this.userPanel;
	}
}

