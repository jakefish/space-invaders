import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Player extends Entity {
	int velX;
	int velY;
	int speed = 4;
	int ySpeed = 2;
	int missileCount = 0;
	private int startY;
	private int startX;

	public Player(int x, int y) {
		super(x, y);
		startY = y;
		startX = x;

	}

	public void update() {

		x += velX;
		y += velY;

		checkCollisions();
		checkOffScreen();
	}

	public void draw(Graphics2D g2d) {
		g2d.drawImage(getPlayerIMG(), x, y, null);
		// g2d.draw(getBounds());
	}

	public Image getPlayerIMG() {

		ImageIcon ic = new ImageIcon("src/newShip.png");
		return ic.getImage();
	}

	boolean missileKeyPressed = false;
	boolean laserKeyPressed = false;

	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();

		if (key == KeyEvent.VK_LEFT) {
			velX = -speed;
		} else if (key == KeyEvent.VK_RIGHT) {
			velX = speed;
		} else if (key == KeyEvent.VK_UP) {
			velY = -ySpeed;
		} else if (key == KeyEvent.VK_DOWN) {
			velY = ySpeed;
		} else if (key == KeyEvent.VK_F) {
			if (!laserKeyPressed) {
				GameFrame.addLaser(new Laser(x, y));
				GameFrame.addLaser(new Laser(x+57, y));
			}
			laserKeyPressed = true;
		}

		else if (key == KeyEvent.VK_SPACE) {
			if (!missileKeyPressed) {
				if(GameFrame.missileCount > 0){
				GameFrame.addMissile(new Missile(x+30, y-10));
				GameFrame.missileCount = GameFrame.missileCount - 1;
				}
			
			}
			missileKeyPressed = true;

		} else if (key == KeyEvent.VK_SHIFT) {
			speed = speed * 2;
		} else if (key == KeyEvent.VK_A) {
			velX = -speed;
		} else if (key == KeyEvent.VK_D) {
			velX = speed;
		} else if (key == KeyEvent.VK_W) {
			velY = -ySpeed;
		} else if (key == KeyEvent.VK_S) {
			velY = ySpeed;
		}
	}

	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		if (key == KeyEvent.VK_LEFT) {
			velX = 0;
		} else if (key == KeyEvent.VK_RIGHT) {
			velX = 0;
		} else if (key == KeyEvent.VK_UP) {
			velY = 0;
		} else if (key == KeyEvent.VK_DOWN) {
			velY = 0;
		} else if (key == KeyEvent.VK_F) {
			laserKeyPressed = false;
		}

		else if (key == KeyEvent.VK_SPACE) {
			missileKeyPressed = false;
		} else if (key == KeyEvent.VK_SHIFT) {
			speed = 2;
		} else if (key == KeyEvent.VK_A) {
			velX = 0;
		} else if (key == KeyEvent.VK_D) {
			velX = 0;
		} else if (key == KeyEvent.VK_W) {
			velY = 0;
		} else if (key == KeyEvent.VK_S) {
			velY = 0;
		}
	}

	public void checkCollisions() {
		ArrayList<Enemy> enemies = GameFrame.getEnemyList();
		ArrayList<Boss> boss = GameFrame.getBossList();
		ArrayList<Asteroid> ast1 = GameFrame.getAsteroidList();
		if (GameFrame.level % 2 != 0) {
			for (int i = 0; i < enemies.size(); i++) {
				Enemy tempEnemy = enemies.get(i);
				if (getBounds().intersects(tempEnemy.getBounds())) {
					GameFrame.lives -= 1;
					y=490;
					x=400;
				}

			}
			for (int k = 0; k < ast1.size(); k++) {
				Asteroid ast = ast1.get(k);

				if (getBounds().intersects(ast.getBounds())) {
					GameFrame.lives -= 1;
					y=490;
					x=400;
				}

			}
			
			if(GameFrame.lives == 0){
				GameFrame.lives = 3;
				Main.endGame();
			
				
				}
		} else {
			for (int j = 0; j < boss.size(); j++) {
				Boss tempBoss = boss.get(j);
				if (getBounds().intersects(tempBoss.getBounds())) {
					//JOptionPane.showMessageDialog(null, "Game Over");
					//System.exit(0);
				}
			}
		}
	}

	public Rectangle getBounds() {
		return new Rectangle(x, y, getPlayerIMG().getWidth(null),
				getPlayerIMG().getHeight(null));

	}

	public void checkOffScreen() {
		if (y >= 525) {
			y = 525;
		}
		if (y <= -10) {
			y = -10;
		}
		if (x >= 750) {
			x = -10;
		}
		if (x <= -15) {
			x = 750;
		}
	}

}
