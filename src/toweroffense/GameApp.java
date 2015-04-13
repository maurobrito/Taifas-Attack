package toweroffense;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import minionMenu.MinionMenu;
import gamelogic.Game;
import gamelogic.minions.MinionFactory.MinionType;
import graphics2D.J2DGameWindow;
import interfaces.IGame;
import interfaces.IGameWindow;

@SuppressWarnings("unused")
public final class GameApp{
	//private ResourceManager resManager;
	private IGameWindow gameWindow;
	private IGame game;
	private MinionMenu minionMenu;
	private MinionType[] minions;
	
	void init() {
		//resManager = new ResourceManager("assets/");
		/*gameWindow = new J2DGameWindow();
		gameWindow.setTitle("Taifas Attack");
		
		game = new Game(gameWindow, minions);
		game.init();*/
		
		
		//gameWindow.
		minionMenu = new MinionMenu();
		
		
		/*gameWindow.setGame(game);
		gameWindow.startRendering();*/
	}

	public static void main(String[] args) {
		GameApp gameApp = new GameApp();
		gameApp.init();
	}

}
