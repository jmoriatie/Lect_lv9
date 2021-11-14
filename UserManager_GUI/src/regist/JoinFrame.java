package regist;

import javax.swing.JFrame;

public class JoinFrame extends JFrame{
	// 아이디 패스워드 이름
	public JoinFrame() {
		super("회원가입");
		setLayout(null);
		setBounds(100,100,300,210);
		
		add(new JoinPanel());
		
		setVisible(true);
		revalidate();
	}
}
