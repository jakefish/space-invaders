import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JButton;
import javax.swing.JFrame;

public class Main {

	public static void main(String[] args) {
		menu();

	}

	public static void startGame() {
		JFrame frame = new JFrame("Space Invaders");
		frame.setSize(800, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(true);
		frame.add(new GameFrame());
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
		
	}
	public static void menu(){
		final JFrame frame = new JFrame("Space Invaders");
		frame.setSize(800, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(true);
		frame.add(new MainMenu());
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);

		final JButton button = new JButton();
		button.setLayout(null);
		button.setLocation(20, 20);
		button.setSize(200, 200);
		button.setContentAreaFilled(false);
		
		button.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				System.out.println(evt);
				if(evt.getX() > 20 && evt.getX() < 220){
					frame.setVisible(true);
					frame.pack();
					shipScreen();
					
				}
				
			}
		});
		frame.add(button);
	}
	
	public static void shipScreen(){
		final JFrame frame = new JFrame("Space Invaders");
		frame.setSize(800, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(true);
		frame.add(new ShipScreen());
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);

		final JButton button = new JButton();
		button.setLayout(null);
		button.setLocation(20, 20);
		button.setSize(200, 200);
		button.setContentAreaFilled(false);
		
		button.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				System.out.println(evt.getXOnScreen());
				if(evt.getXOnScreen() > 20 && evt.getXOnScreen() < 220){
					System.out.println(evt);
					frame.setVisible(true);
					frame.pack();
					startGame();
					
				}
				
			}
		});
		frame.add(button);
	}
	
	public static void endGame(){
		JFrame frame = new JFrame("Space Invaders");
		frame.setSize(800, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(true);
		frame.add(new GameOver());
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {

			@Override
			public void run() {
				System.exit(0);
			}
		}, 1000);
	}
}
