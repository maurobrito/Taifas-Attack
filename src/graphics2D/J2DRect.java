package graphics2D;

import java.awt.Color;
import java.awt.Graphics2D;

import interfaces.IRect;

//makes it easily available for us to draw a rectangle where we want from the size we want
public class J2DRect implements IRect {
	private Color color;
	private J2DGameWindow window;
	
	public J2DRect(J2DGameWindow window) {
		this.window = window; 
	}
	
	@Override
	public void draw(int x, int y, int width, int height) {
		Graphics2D g = window.getDrawGraphics();
		g.setColor(color);
		g.drawRect(x, y, width, height);
	}


	@Override
	public void setColor(Color color) {
		this.color = color;
	}
}
