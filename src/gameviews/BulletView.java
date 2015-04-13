package gameviews;

import java.awt.Color;
import java.awt.Point;

import gamelogic.minions.Minion;
import gamelogic.towers.Bullet;
import gamelogic.towers.Tower;
import interfaces.ICircle;
import interfaces.IGameWindow;
import interfaces.ILine;
import interfaces.IPoint;
import interfaces.ISprite;

//draws the bullet on the screen
@SuppressWarnings("unused")
public class BulletView {
	private ISprite sprite;
	private Bullet attachedBullet;
	private Point shiftedPosition;
	private IGameWindow gameWindow;
	
	private static int offsetX = 16;
	private static int offsetY = 12;
	
	//constructor
	public BulletView(IGameWindow gameWindow) {
		this.gameWindow = gameWindow;
		sprite = gameWindow.createSprite("assets/Bulleticus.png");
		this.shiftedPosition = new Point();
	}
	
	public void draw(long deltaMilli) {
		shiftedPosition.x = attachedBullet.getPosition().x + offsetX;
		shiftedPosition.y = attachedBullet.getPosition().y + offsetY;
		sprite.draw(shiftedPosition);
	}
	
	
	public void attachBullet(Bullet b) {
		this.attachedBullet = b;
	}
}
