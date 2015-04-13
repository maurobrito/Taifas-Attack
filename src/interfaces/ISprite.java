package interfaces;

import java.awt.Point;

public interface ISprite {
	public void draw(int x, int y);
	public void draw(Point position);
	public void draw(int x, int y, double angle);
	public int getWidth();
	public int getHeight();
}
