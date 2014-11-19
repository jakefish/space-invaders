import java.awt.Color;
import java.awt.Font; 
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

public class GameFrame extends JPanel implements ActionListener {

	Timer mainTimer;
	Player player;
	boolean gameOver = false;
	

	int enemyCount = 0;
	int asteroidCount = 2;
	static int level = 1;
	static int score = 0;
	static int lives = 3;
	static int missileCount = 6 * level;

	static ArrayList<Enemy> enemies = new ArrayList<Enemy>();
	static ArrayList<Missile> missiles = new ArrayList<Missile>();
	static ArrayList<Boss> bosses = new ArrayList<Boss>();
	static ArrayList<Boom> booms = new ArrayList<Boom>();
	static ArrayList<Laser> lasers = new ArrayList<Laser>();
	static ArrayList<Score> scores = new ArrayList<Score>();
	static ArrayList<Asteroid> asteroids = new ArrayList<Asteroid>();
	Random rand = new Random();
	
	
	

	public GameFrame() {

		setFocusable(true);

		player = new Player(400, 490);
		addKeyListener(new KeyAdapt(player));

		mainTimer = new Timer(10, this);
		mainTimer.start();

		startGame();
	}

	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2d = (Graphics2D) g;
		
			ImageIcon ib = new ImageIcon("src/BackGround.jpg");
			g2d.drawImage(ib.getImage(), 0, 0, null);


		g2d.setColor(Color.WHITE);
		g.setFont(new Font("Calibri", Font.BOLD, 30));
		g.drawString("Level: " + level , 10, 50);
		
		//g2d.setColor(Color.RED);
		//g.setFont(new Font("Calibri", Font.BOLD, 100));
		//g.drawString("Game Over" , 200, 250);
		
		g2d.setColor(Color.WHITE);
		g.setFont(new Font("Calibri", Font.BOLD, 30));
		g.drawString("Lives: " + lives , 10, 515);
		
		g2d.setColor(Color.WHITE);
		g.setFont(new Font("Calibri", Font.BOLD, 30));
		g.drawString("Remaining Missiles: " + missileCount , 10, 545);
		
		g.setFont(new Font("Calibri", Font.BOLD, 30));
		g.drawString("Score: " + score , 650, 50);
	
		if(gameOver == true){
		g2d.setColor(Color.RED);
		g.setFont(new Font("Calibri", Font.BOLD, 100));
		g.drawString("Game Over", 165, 250);
		}
		
		player.draw(g2d);

		for (int i = 0; i < enemies.size(); i++) {
			Enemy tempEnemy = enemies.get(i);
			tempEnemy.draw(g2d);
		}

		for (int i = 0; i < missiles.size(); i++) {
			Missile m = missiles.get(i);
			m.draw(g2d);
		}

		for (int i = 0; i < bosses.size(); i++) {
			Boss b = bosses.get(i);
			b.draw(g2d);
		}

		for (int i = 0; i < booms.size(); i++) {
			Boom bm = booms.get(i);
			bm.draw(g2d);
			
		}
		for (int i = 0; i < scores.size(); i++) {
			Score s = scores.get(i);
			s.draw(g2d);
			
		}
		for (int i = 0; i < lasers.size(); i++) {
			Laser l = lasers.get(i);
			l.draw(g2d);
			
		}
		for (int i = 0; i < asteroids.size(); i++) {
			Asteroid a = asteroids.get(i);
			a.draw(g2d);
		}

		Enemy e = new Enemy(200, 400);
	}

	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		player.update();
		for (int i = 0; i < enemies.size(); i++) {
			Enemy tempEnemy = enemies.get(i);
			tempEnemy.update();
		}

		for (int i = 0; i < missiles.size(); i++) {
			Missile m = missiles.get(i);
			m.update();
		}

		for (int i = 0; i < bosses.size(); i++) {
			Boss b = bosses.get(i);
			b.update();
		}

		for (int i = 0; i < booms.size(); i++) {
			Boom bm = booms.get(i);
			bm.update();
		}
		
		for (int i = 0; i < scores.size(); i++) {
			Score score = scores.get(i);
			score.update();
		}
		for (int i = 0; i < lasers.size(); i++) {
			Laser l = lasers.get(i);
			l.update();
		}
		for (int i = 0; i < asteroids.size(); i++) {
			Asteroid a = asteroids.get(i);
			a.update();
		}

		checkEnd();

		repaint();

	}

	public static void addEnemy(Enemy e) {
		enemies.add(e);
	}

	public static void removeEnemy(Enemy e) {
		enemies.remove(e);
	}

	public static void addBoss(Boss b) {
		bosses.add(b);
	}

	public static void removeBoss(Boss b) {
		bosses.remove(b);
	}

	public static ArrayList<Boss> getBossList() {
		return bosses;
	}

	public static ArrayList<Enemy> getEnemyList() {
		return enemies;

	}

	public static void addMissile(Missile m) {
		missiles.add(m);
	}

	public static void removeMissile(Missile m) {
		missiles.remove(m);
	}

	public static ArrayList<Missile> getMissileList() {
		return missiles;
	}

	public static void addBoom(Boom bm) {
		booms.add(bm);
	}

	public static void removeBoom(Boom bm) {
		booms.remove(bm);
	}

	public static ArrayList<Boom> getBoomList() {
		return booms;
	}
	public static void addScore(Score s){
		scores.add(s);
	}
	public static void removeScore(Score s){
		scores.remove(s);
	}
	public static ArrayList<Score> getScoreList(){
		return scores;
	}
	
	public static void addLaser(Laser l){
		lasers.add(l);
	}
	public static void removeLaser(Laser l){
		lasers.remove(l);
	}
	public static ArrayList<Laser> getLaserList(){
		return lasers;
	}
	public static void addAsteroid(Asteroid a){
		asteroids.add(a);
	}
	public static void removeAsteroid(Asteroid a){
		asteroids.remove(a);
	}
	public static ArrayList<Asteroid> getAsteroidList(){
		return asteroids;
	}
	public void startGame() {
		booms.clear();
		
		
		if (level % 2 == 0) {
			addBoss(new Boss(rand.nextInt(500), -10 + -rand.nextInt(600)));
			

		} else {
			enemyCount = level * 5;
			asteroidCount++;
			for (int i = 0; i < enemyCount; i++) {
				addEnemy(new Enemy(rand.nextInt(500), -10 + -rand.nextInt(600)));

			}
			for (int i = 0; i < asteroidCount; i++) {
				addAsteroid(new Asteroid(rand.nextInt(500), -10 + -rand.nextInt(600)));

			}
		}
	}

	public void checkEnd() {
		if (enemies.size() == 0 && bosses.size() == 0) {
			level++;
			enemies.clear();
			asteroids.clear();
			missiles.clear();
			booms.clear();
			missileCount = 6*level;


			JOptionPane.showMessageDialog(null,
					"Good work, you have completed level " + (level - 1)
					+ ". Let's move onto the next one.");
			startGame();
		}
	}
	

}
