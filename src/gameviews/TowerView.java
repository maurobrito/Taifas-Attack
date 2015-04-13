package gameviews;

import gamelogic.minions.Minion;
import gamelogic.towers.Tower;
import interfaces.ICircle;
import interfaces.IGameWindow;
import interfaces.ILabel;
import interfaces.ILine;
import interfaces.IPoint;
import interfaces.ISprite;

import java.awt.Color;
import java.awt.Font;
import java.awt.Point;

import util.Globals;

//is responsible for drawing each tower on the correct position
@SuppressWarnings("unused")
public class TowerView {
	private ISprite sprite;
	private Tower attachedTower;
	private Point shiftedPosition;
	private IGameWindow gameWindow;
	private IPoint position;
	private ICircle range;
	private ILine attack;
	
	private static int offsetX = 16;
	private static int offsetY = 12;
	
	public TowerView(IGameWindow gameWindow) {
		this.gameWindow = gameWindow;
		
		sprite = gameWindow.createSprite("assets/Tower.png");
		range = gameWindow.createCircle();
		this.position = gameWindow.createPoint();
		this.shiftedPosition = new Point();
	}
	
	public void draw(long deltaMilli) {
		shiftedPosition.x = attachedTower.getPosition().x + offsetX;
		shiftedPosition.y = attachedTower.getPosition().y + offsetY;
		sprite.draw(shiftedPosition);
		
		if(Globals.SHOW_RANGE) {
			range.setColor(Color.YELLOW);
			range.draw(offsetX + attachedTower.getPosition().x, offsetY + attachedTower.getPosition().y, attachedTower.getRange());
		}
		
		if(Globals.DEBUG) {
			position.setColor(Color.WHITE);
			position.draw(offsetX + attachedTower.getPosition().x, offsetY + attachedTower.getPosition().y);
			
			
			
			for(Minion m : attachedTower.getOnRangeMinions()){
				attack = gameWindow.createLine();
				attack.setColor(Color.CYAN);
				attack.draw(m.getPosition().x + offsetX, m.getPosition().y + offsetY, attachedTower.getPosition().x + offsetX, attachedTower.getPosition().y + offsetY);
			}
		}
	}
	
	public void attachTower(Tower m) {
		this.attachedTower = m;
		if(attachedTower.getName() == "Tower1"){
			sprite = gameWindow.createSprite("assets/Tower.png");
		}
		else if(attachedTower.getName() == "Tower2"){
			sprite = gameWindow.createSprite("assets/Tower2.png");
		}
	}
}
