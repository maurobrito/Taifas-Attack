package gameviews;

import interfaces.IGameWindow;
import interfaces.ISprite;

import java.awt.Point;

//manages the drawing of the et that appears on the screen dancing
public class MonsterView {
	private Point shiftedPosition;
	private long frameDelay = 200;
	private long frameTimeout = 0;
	private int frameCounter = 0;
	private ISprite[] frames;
	
	private static int offsetX = 16;
	private static int offsetY = 12;
	private boolean invertida = false;
	private float velocidade = 0.1f;
	//constructor
	public MonsterView(IGameWindow gameWindow) {
		frames = new ISprite[6];
		frames[0] = gameWindow.createSprite("assets/monsterSpriteSheet1.png");
		frames[1] = gameWindow.createSprite("assets/monsterSpriteSheet2.png");
		frames[2] = gameWindow.createSprite("assets/monsterSpriteSheet3.png");
		frames[3] = gameWindow.createSprite("assets/monsterSpriteSheet4.png");
		frames[4] = gameWindow.createSprite("assets/monsterSpriteSheet5.png");
		frames[5] = gameWindow.createSprite("assets/monsterSpriteSheet6.png");
		
		this.shiftedPosition = new Point(-300, 250);
	}
	
	public void draw(long deltaMilli) {
		this.shiftedPosition.x += velocidade * deltaMilli;
		if(shiftedPosition.x > 300 && !invertida) {
			velocidade = -0.1f;
			invertida = true;
		}
		
		frameTimeout += deltaMilli;
		if(frameTimeout > frameDelay) {
			frameCounter++;
			frameCounter %= 6;
			frameTimeout = 0;
		}
		
		frames[frameCounter].draw(shiftedPosition.x + offsetX, shiftedPosition.y + offsetY);
	}
}
