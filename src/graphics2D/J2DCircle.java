package graphics2D;

import java.awt.Color;
import java.awt.Graphics2D;

import interfaces.ICircle;

//makes it easily available for us to draw a circle where we want from the size we want
public class J2DCircle implements ICircle {
	private Color color;
	private J2DGameWindow window;
	
	public J2DCircle(J2DGameWindow window) {
		this.window = window; 
	}
	

	@Override
	public void draw(int x, int y, int radius) {
		Graphics2D g = window.getDrawGraphics();
		g.setColor(color);
		g.drawOval(x-radius,y-radius, 2*radius, 2*radius);
	}

	@Override
	public void setColor(Color color) {
		this.color = color;
	}

}
