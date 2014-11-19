import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;

public class Laser extends Entity {

	public Laser(int x, int y) {
		super(x, y);

	}

	public void update() {
		y -= 3;
	}

	public void draw(Graphics2D g2d) {
		g2d.drawImage(getBulletImg(), x, y, null);
	}

	public Image getBulletImg() {
		ImageIcon ic = new ImageIcon("src/bullet.png");
		return ic.getImage();
	}

	public Rectangle getBounds() {
		return new Rectangle(x, y, getBulletImg().getWidth(null),
				getBulletImg().getHeight(null));

	}

}
