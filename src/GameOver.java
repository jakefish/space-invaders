import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class GameOver extends JPanel implements ActionListener {
	public void update() {

	}

	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2d = (Graphics2D) g;

		ImageIcon ib = new ImageIcon("src/BackGround.jpg");
		g2d.drawImage(ib.getImage(), 0, 0, null);

		g2d.setColor(Color.RED);
		g.setFont(new Font("Calibri", Font.BOLD, 100));
		g.drawString("Game Over", 175, 280);

	}

	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub

	}
}
