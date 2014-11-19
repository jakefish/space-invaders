import java.awt.Graphics; 
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ImageIcon;

public class Boss extends Entity {
	Random generator = new Random();
	int max = 1;
	int min = 1;
	int bossHealth = 2;
	int randomNum = generator.nextInt((max-min) + 1) + min;
	int startY;
	int startX;
	int yVelocity = 1;
	int xVelocity = 1;

	public Boss(int x, int y) {
		super(x, y);
		startY = y;
		startX = x;
	}

	public void update() {

		checkCollisions();
		checkOffScreen();

		switch (randomNum) {
		case 1:
			
			if (y == 300){
				while( y != 0){
					y += -yVelocity;
				}
			}
			else{
				y += yVelocity;
			}
			
			if (x == 750){
				while (x != 0){
					x += -xVelocity;
				}
			}
			else{
				x += xVelocity;
			}
			

		}
		checkCollisions();
		checkOffScreen();
	}

	public void draw(Graphics2D g2d) {

		g2d.drawImage(getEnemyImg(), x, y, null);
	}

	public Image getEnemyImg() {
		ImageIcon ic = new ImageIcon("src/boss.png");
		return ic.getImage();
	}

	private int damageCounter = 0;
	Timer timer = new Timer();
	public void checkCollisions() {
		
		for (int k = 0; k < GameFrame.getLaserList().size(); k++) {
			Laser l = GameFrame.getLaserList().get(k);
			if (getBounds().intersects(l.getBounds())) {
				damageCounter++;
				GameFrame.removeLaser(l);
				if (damageCounter == 25) {
					GameFrame.addBoom(new Boom(l.getX() - 40, l.getY() - 60));
					GameFrame.addScore(new Score(l.getX() - 40, l.getY() - 60));
					GameFrame.removeBoss(this);
					GameFrame.removeLaser(l);
					GameFrame.score += 1000;

					timer.schedule(new TimerTask() {

						@Override
						public void run() {
							for (int j = 0; j < GameFrame.getScoreList().size(); j++) {
								Score s = GameFrame.getScoreList().get(j);
								GameFrame.removeScore(s);
							}
							for (int k = 0; k < GameFrame.getBoomList().size(); k++) {
								Boom bm = GameFrame.getBoomList().get(k);
								GameFrame.removeBoom(bm);
							}

						}
					}, 400);

				}
			}
		}
		
		for (int i = 0; i < GameFrame.getMissileList().size(); i++) {
			Missile m = GameFrame.getMissileList().get(i);
			if (getBounds().intersects(m.getBounds())) {
				bossHealth--;
				if (bossHealth == 0){
				GameFrame.removeBoss(this);
				}


				GameFrame.removeMissile(m);
				GameFrame.booms.clear();
				GameFrame.score += 1000;
				GameFrame.addBoom(new Boom(m.getX() -40, m.getY()- 60));
				for(int j = 0; i <GameFrame.getBoomList().size(); i++){
					Boom bm = GameFrame.getBoomList().get(j);
				}	

			}
		}
	}

	public void checkOffScreen() {


	}

	public Rectangle getBounds() {
		return new Rectangle(x, y, getEnemyImg().getWidth(null) - 5, getEnemyImg()
				.getHeight(null)-5);

	}
}
