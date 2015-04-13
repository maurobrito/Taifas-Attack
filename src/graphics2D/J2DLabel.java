package graphics2D;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;

import interfaces.ILabel;

//makes it easily available for us to draw a label where we want from the size we want
public class J2DLabel implements ILabel {
	private String text;
	private Font font;
	private Color color;
	private J2DGameWindow window;
	
	public J2DLabel(J2DGameWindow window, String text, Font font) {
		this.text = text;
		this.font = font;
		this.window = window; 
	}
	
	@Override
	public void draw(int x, int y) {
		Graphics2D g = window.getDrawGraphics();
		g.setFont(font);
		g.setColor(color);
		g.drawString(text, x, y);
	}

	@Override
	public void setText(String text) {
		this.text = text;
	}

	@Override
	public void setColor(Color color) {
		this.color = color;
	}

	@Override
	public void drawCentered(int x, int y) {
		Graphics2D g = window.getDrawGraphics();
		
		g.setFont(font);
		g.setColor(color);
		
		FontMetrics fm = g.getFontMetrics();
	    int xCenter = fm.stringWidth(text) / 2;
	    int yCenter = fm.getAscent() - ((fm.getAscent() + fm.getDescent()) / 2);
		g.drawString(text, x - xCenter, y + yCenter);
		
	}

}
