package gameviews;

import java.awt.Color;
import java.awt.Font;
import java.awt.Point;

import util.Globals;
import gamelogic.minions.Minion;
import interfaces.IGameWindow;
import interfaces.ILabel;
import interfaces.IPoint;
import interfaces.ISprite;

//all the info about the minion will be here
@SuppressWarnings("unused")
public class MinionView {
	private Minion attachedMinion;
	private Point shiftedPosition;
	private IPoint position;
	private ILabel hpLbl;
	private ILabel spdLbl;
	private ILabel ttlCaminhadoLbl;
	private long frameDelay = 50;
	private long frameTimeout = 0;
	private int frameCounter = 0;
	private ISprite[] frames;
	
	private static int offsetX = 16;
	private static int offsetY = 12;
	private IGameWindow gameWindow;
	
	//minionView constructor
	public MinionView(IGameWindow gameWindow) {
		this.gameWindow = gameWindow;
		frames = new ISprite[12];
		
		this.position = gameWindow.createPoint();
		this.shiftedPosition = new Point();
		hpLbl = gameWindow.createLabel("", new Font("Century Gothic", Font.PLAIN, 12));
		spdLbl = gameWindow.createLabel("", new Font("Century Gothic", Font.PLAIN, 12));
		ttlCaminhadoLbl = gameWindow.createLabel("", new Font("Century Gothic", Font.PLAIN, 12));
	}
	
	//draws on the screen info about the minion
	public void draw(long deltaMilli) {
		
		for(int i=1; i<=frames.length; i++){
			if(attachedMinion.getBaseSpeed() == 0.2f){
				frames[i-1] = gameWindow.createSprite("assets/AgiSprite" + i + ".png");
			}
			else if(attachedMinion.getBaseSpeed() == 0.07f){
				frames[i-1] = gameWindow.createSprite("assets/TankerSprite" + i + ".png");
			}
			else if(attachedMinion.getBaseSpeed() == 0.12f){
				frames[i-1] = gameWindow.createSprite("assets/HealerSprite" + i + ".png");
			}
			else if(attachedMinion.getBaseSpeed() == 0.03f){
				frames[i-1] = gameWindow.createSprite("assets/TaifasSprite" + i + ".png");
			}
		}
		
		frameTimeout += deltaMilli;
		if(frameTimeout > frameDelay) {
			frameCounter++;
			if(attachedMinion.getAngle() == 0){
				frameCounter %=3;
				frameCounter += 9;
			}
			else if(attachedMinion.getAngle() == Math.PI/2){
				frameCounter %=3;
				frameCounter += 6;
			}
			else if(attachedMinion.getAngle() == Math.PI){
				frameCounter %=3;
				frameCounter += 0;
			}
			else if(attachedMinion.getAngle() == Math.PI*3/2){
				frameCounter %=3;
				frameCounter += 3;
			}
			frameTimeout = 0;
		}
		
		shiftedPosition.x = attachedMinion.getPosition().x;
		shiftedPosition.y = attachedMinion.getPosition().y - 10;
		frames[frameCounter].draw(shiftedPosition.x + offsetX, shiftedPosition.y + offsetY);
		
		if(Globals.DEBUG) {
			position.setColor(Color.WHITE);
			position.draw(attachedMinion.getPosition().x + offsetX, attachedMinion.getPosition().y + offsetY);
		
			hpLbl.setText("HP: " + attachedMinion.getLife());
			hpLbl.drawCentered(attachedMinion.getPosition().x + offsetX, attachedMinion.getPosition().y - 15 + offsetY);
		
			spdLbl.setText("SPD: " + attachedMinion.getActualSpeed());
			spdLbl.drawCentered(attachedMinion.getPosition().x + offsetX, attachedMinion.getPosition().y + 15 + offsetY);
		
			ttlCaminhadoLbl.setText("TC: " + attachedMinion.getTotalCaminhado());
			ttlCaminhadoLbl.drawCentered(attachedMinion.getPosition().x + offsetX, attachedMinion.getPosition().y + 30 + offsetY);
		}
	}
	
	public void attachMinion(Minion m) {
		this.attachedMinion = m;
	}
}
