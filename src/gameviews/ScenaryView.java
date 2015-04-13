package gameviews;

import gamelogic.scenary.Scenary;
import interfaces.IGame;
import interfaces.IGameWindow;
import interfaces.ILabel;
import interfaces.ILine;
import interfaces.IPoint;
import interfaces.IRect;
import interfaces.ISprite;

import java.awt.Color;
import java.awt.Font;
import java.util.HashMap;
import java.util.Map;

import util.Globals;
import util.Vector2D;

//gets the info and draws the map
@SuppressWarnings("unused")
public class ScenaryView {

	private MonsterView monster;
	
	private Map<Integer, ISprite> map;
	
	private IPoint startPoint;
	private IPoint endPoint;
	
	private String path = "assets/";
	private String[] tileNames = {"grass0.png", "grass1.png", "grass2.png", "grass3.png", "grass4.png", "grass5.png", "grass6.png", 
									"grass7.png", "path8.png", "path9.png", "path10.png", "path11.png", "path12.png", "path13.png"};
	private final int numberTiles = tileNames.length;
	private int tileSize;
	
	private Scenary scenary;
	//offset to adjust the position
	private static int offsetX = 16;
	private static int offsetY = 12;

	private IGameWindow gameWindow;
	
	public ScenaryView(IGameWindow gameWindow, Scenary scenary){
		this.gameWindow = gameWindow;
		monster = new MonsterView(gameWindow);
		startPoint = gameWindow.createPoint();
		endPoint = gameWindow.createPoint();
		
		this.scenary = scenary;
		for(int i=0; i<numberTiles; i++){
			tileNames[i] = path + tileNames[i];
		}
		
		map = new HashMap<Integer, ISprite>();
		for(int i=0; i<numberTiles; i++){
			map.put(i, gameWindow.createSprite(tileNames[i]));
		}
		
		tileSize = 64;
	}
	
	//draws the background map and the grid
	public void draw(long deltaMillis){
		for(int row = 0; row < scenary.getMap().height; row++){
			for(int col=0; col < scenary.getMap().width; col++){
				map.get(scenary.getMap().matrix[row][col]).draw(col*tileSize + offsetX + 32, row*tileSize + offsetY + 32);
				if(Globals.DEBUG) {
					IRect r = gameWindow.createRect();
					r.setColor(Color.GRAY);
					r.draw(col*tileSize + offsetX, row*tileSize + offsetY, tileSize - 1, tileSize-1);
				}
			}
		}
		if(Globals.DEBUG) {
			int x = (int)scenary.getStartPoint().getX();
			int y = (int)scenary.getStartPoint().getY();
			
			for(Vector2D v:scenary.getPath()){
				ILine l = gameWindow.createLine();
				l.setColor(Color.RED);
				l.draw(x + offsetX, y + offsetY, (int)v.getX() + offsetX, (int)v.getY() + offsetY);
				x = (int)v.getX();
				y = (int)v.getY();
			}
			
			startPoint.draw(scenary.getStartPoint().clamp().x + offsetX, scenary.getStartPoint().clamp().y + offsetY);
			endPoint.draw(scenary.getEndPoint().clamp().x + offsetX, scenary.getEndPoint().clamp().y + offsetY);
		}
		
		monster.draw(deltaMillis);
	}
}
