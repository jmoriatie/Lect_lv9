package basic;

class PlayGame extends Thread{
	
	boolean play = true;

	@Override
	public void run() {
//		super.run();
		while(this.play) {
			System.out.println("신나게 게임을 하는 중");
			try {
				sleep(300);
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	}
}

class PlayMusic implements Runnable{
	
	boolean play = true;
	
	@Override
	public void run() {
		while(this.play) {
			System.out.println("음악이 흐르고...");
			try {
				Thread.sleep(300);
			} catch (Exception e) {
				// TODO: handle exception
			}		
		}
	}
}

public class Ex03_Thread {
	public static void main(String[] args) {
		// 스레드 thread
		// sleep()
		// ㄴ ms 단위로 실행에 딜레이를 줄 수 O
		// 멀티 쓰레드
		
		PlayGame game = new PlayGame();
		game.start(); // 스타트 메서드는 쓰레드의 기본인 듯
		
		for(int i=0; i<10; i++) {
			System.out.printf("<%d>\n", i);
			try {
				Thread.sleep(1000);
			} catch (Exception e) {
				// TODO: handle exception
			}
			if(i == 8) {
				System.out.println("앗 엄마가 나타났다!");
				game.play = false;
//				game.stop(); // 구버전
			}
		}
		
		Runnable music = new PlayMusic();
//		music.run(); // 단순히 Runnable을 실행하는건 start가 아님 		
//		ㄴ 결국 동작은 쓰레드로 쓰긴 해야함
//		ㄴ 쓰는 이유는 자바 다중상속 불가하지만, Runnable 인터페이스를 통해서 다중 상속이 가능
//		ㄴ 이를 통해 확장성있는 코드 작성 가능

		Thread t = new Thread(music); // 쓰레드 객체를 선언해서, Runnable객체를 받쳐줌
		t.start(); // 기본 메서드인 start로 진행
		
		for(int i=0; i<10; i++) {
			System.out.printf("<%d>\n", i);
			try {
				Thread.sleep(1000);
			} catch (Exception e) {
				// TODO: handle exception
			}
			if(i == 8) {
				System.out.println("앗 엄마가 나타났다!");
				PlayMusic temp = (PlayMusic)music;
				temp.play = false;
//				t.stop();
			}
		}
	}
}
