package interfaces;

import java.awt.Font;

public interface IGameWindow {

	/**
	 * Set the title of the game window
	 * 
	 * @param title
	 *            The new title for the game window
	 */
	public void setTitle(String title);

	/**
	 * Set the game display resolution
	 * 
	 * @param x
	 *            The new x resolution of the display
	 * @param y
	 *            The new y resolution of the display
	 */
	public void setResolution(int x, int y);

	/**
	 * Start the game window rendering the display
	 */
	public void startRendering();

	public void setGame(IGame game);

	public ILabel createLabel(String text, Font font);
	public ISprite createSprite(String ref);
	public IPoint createPoint();
	public ILine createLine();
	public IRect createRect();
	public ICircle createCircle();
	public void endGame(int score);
}
