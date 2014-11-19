import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JPanel;


public class Menu extends Player{

	public Menu(int x, int y) {
		super(x, y);
	
	}
	public void draw(Graphics2D g2d) {

		g2d.drawImage(getImage(), x, y, null);
	}

	
	
	public Image getImage(){
		
			ImageIcon im = new ImageIcon("src/Menu.jpg");
			return im.getImage();
			
	}
		
	

	public void update() {
		
		
	}
}
