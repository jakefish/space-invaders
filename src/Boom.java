import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ImageIcon;

public class Boom extends Enemy {
	private int startY;
	private int startX;
	

	public Boom(int x, int y) {
		super(x, y);
	
	}

	public void update() {
		checkCollisions();
	}

	public void draw(Graphics2D g2d) {

		g2d.drawImage(getBoomImg(), x, y, null);
	}

	public Image getBoomImg() {
		final ImageIcon ic = new ImageIcon("src/Boom.gif");
		Timer timer = new Timer();
		
		timer.schedule(new TimerTask() {

			public void run() {
				ic.getImage().flush();
				
			}
		}, 400);
		
		return ic.getImage();
	}

	public void checkCollisions() {
		
	}

	public void checkOffScreen() {
		if (y >= 650) {
			y = startY;
		}
		if (x >= 550) {
			x = startX;
		}

	}

	public Rectangle getBounds() {
		return new Rectangle(x, y, getEnemyImg().getWidth(null), getEnemyImg()
				.getHeight(null));

	}
}
