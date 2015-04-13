package graphics2D;

import java.awt.Color;
import java.awt.Graphics2D;

import interfaces.IPoint;

//makes it easily available for us to draw a point where we want from the size we want
public class J2DPoint implements IPoint{
	private Color color;
	private J2DGameWindow window;
	
	public J2DPoint(J2DGameWindow window) {
		this.window = window; 
	}
	
	@Override
	public void draw(int x, int y) {
		Graphics2D g = window.getDrawGraphics();
		g.setColor(color);
		g.fillOval(x-2, y-2, 4, 4);
		g.drawOval(x-4, y-4, 8, 8);
	}

	@Override
	public void setColor(Color color) {
		this.color = color;
	}
}
