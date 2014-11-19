import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.util.Random;

import javax.swing.ImageIcon;

public class Score extends Entity {
	private int startY;
	private int startX;

	public Score(int x, int y) {
		super(x, y);

	}

	public void update() {
	}

	public void draw(Graphics2D g2d) {

		g2d.drawString("+ 50", x +10, y+10 );
	}

}
