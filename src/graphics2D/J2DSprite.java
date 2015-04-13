package graphics2D;

import interfaces.ISprite;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.geom.AffineTransform;

//makes it easily available for us to draw a sprite where we want from the size we want
@SuppressWarnings("unused")
public class J2DSprite implements ISprite {
	/** The image to be drawn for this sprite */
	private Image image;
	/** The game window to which this sprite is going to be drawn */
	private J2DGameWindow window;
	
	public J2DSprite(J2DGameWindow window, Image image) {
		this.image = image;
		this.window = window;
	}
	
	
	@Override
	public void draw(int x, int y) {
		window.getDrawGraphics().drawImage(image, x - getWidth() / 2 , y - getHeight() / 2, null);
	}

	@Override
	public void draw(Point position) {
		draw(position.x, position.y);
	}
	
	@Override
	public void draw(int x, int y, double angle) {
		Graphics2D g = window.getDrawGraphics();
		Graphics2D g2d = (Graphics2D)g.create();
		g2d.rotate(angle, x, y);
		g2d.drawImage(image, x - getWidth() / 2 , y - getHeight() / 2, null);
	}
	
	@Override
	public int getWidth() {
		return image.getWidth(null);
	}


	@Override
	public int getHeight() {
		return image.getHeight(null);
	}
	
	@Override
	public String toString() {
		return "J2DSprite - Image: " + image.toString() + " window: " + window.getName();
	}


	
}
