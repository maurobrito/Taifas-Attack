package graphics2D;

import interfaces.ILine;

import java.awt.Color;
import java.awt.Graphics2D;

//makes it easily available for us to draw a line where we want from the size we want
public class J2DLine implements ILine {
	private Color color;
	private J2DGameWindow window;
	
	public J2DLine(J2DGameWindow window) {
		this.window = window; 
	}
	
	@Override
	public void draw(int x1, int y1, int x2, int y2) {
		Graphics2D g = window.getDrawGraphics();
		g.setColor(color);
		g.drawLine(x1, y1, x2, y2);
	}


	@Override
	public void setColor(Color color) {
		this.color = color;
	}
}
