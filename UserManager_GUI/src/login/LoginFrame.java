package login;

import javax.swing.JFrame;

import regist.JoinPanel;

public class LoginFrame extends JFrame {
	public LoginFrame() {
		super("로그인");
		setLayout(null);
		setBounds(100,100,300,210);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		add(new LoginPanel());
		
		setVisible(true);
		revalidate();
	}
}
