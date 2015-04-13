package interfaces;

import java.awt.Color;

public interface ILabel {
	public void draw(int x, int y);
	public void drawCentered(int x, int y);
	public void setText(String text);
	public void setColor(Color color);
}
