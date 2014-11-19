import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ImageIcon;

public class Enemy extends Entity {

	private int startY;
	private int startX;
	private int damageCounter = 0;

	public Enemy(int x, int y) {
		super(x, y);
		startY = y;
		startX = x;
	}

	public void update() {
		y += 1;
		checkCollisions();
		checkOffScreen();
	}

	public void draw(Graphics2D g2d) {

		g2d.drawImage(getEnemyImg(), x, y, null);
	}

	public Image getEnemyImg() {
		ImageIcon ic = new ImageIcon("src/enemy1.png");
		return ic.getImage();
	}

	Random rand = new Random();
	Timer timer = new Timer();

	public void checkCollisions() {
		for (int i = 0; i < GameFrame.getMissileList().size(); i++) {
			Missile m = GameFrame.getMissileList().get(i);
			if (getBounds().intersects(m.getBounds())) {
				GameFrame.addBoom(new Boom(m.getX() - 40, m.getY() - 60));
				GameFrame.addScore(new Score(m.getX() - 40, m.getY() - 60));
				GameFrame.removeEnemy(this);
				GameFrame.removeMissile(m);
				GameFrame.score += 50;

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

		for (int k = 0; k < GameFrame.getLaserList().size(); k++) {
			Laser l = GameFrame.getLaserList().get(k);
			if (getBounds().intersects(l.getBounds())) {
				damageCounter++;
				GameFrame.removeLaser(l);
				if (damageCounter == 6) {
					GameFrame.addBoom(new Boom(l.getX() - 40, l.getY() - 60));
					GameFrame.addScore(new Score(l.getX() - 40, l.getY() - 60));
					GameFrame.removeEnemy(this);
					GameFrame.removeLaser(l);
					GameFrame.score += 50;

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
	}

	public void checkOffScreen() {
		if (y >= 525) {
			y = startY;
		}
		if (x >= 700) {
			x = startX;
		}
	}

	public Rectangle getBounds() {
		return new Rectangle(x - 5, y - 5, getEnemyImg().getWidth(null) - 5,
				getEnemyImg().getHeight(null) - 5);

	}
}
