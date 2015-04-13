package gamelogic;

import java.awt.Color;
import java.awt.Font;

import gamelogic.minions.MinionFactory.MinionType;
import gamelogic.scenary.Scenary;
import gamelogic.scenary.ScenaryFactory;
import gameviews.BulletViewsMediator;
import gameviews.MinionViewsMediator;
import gameviews.ScenaryView;
import gameviews.ScoreView;
import gameviews.TowerViewsMediator;
import interfaces.IGame;
import interfaces.IGameWindow;
import interfaces.ILabel;

@SuppressWarnings("unused")
public class Game implements IGame {
	// References
	private IGameWindow gameWindow;
	
	// Systems
	private MinionSystem minionSystem;
	private MinionViewsMediator minionViews;
	
	private ScenarySystem scenarySystem;
	private ScenaryView scenaryView;
	
	private TowerSystem towerSystem;
	private TowerViewsMediator towerViews;
	
	private ScoreView scoreView;
	// Time
	private long startTime;
	
	// FPS
	private long lastFPSUpdateTime;
	private int fps;
	private ILabel fpsLabel;

	private BulletViewsMediator bulletViews;
	
	// menu
	MinionType[] minions; 
	
	private int score;
	
	public Game(IGameWindow gameWindow, MinionType[] minions) {
		this.gameWindow = gameWindow;
		this.minions = minions;
	}

	@Override
	public void init() {
		// Initialize Systems
		scenarySystem = new ScenarySystem();
		
		Scenary s = ScenaryFactory.createScenary("assets/Map.txt");
		scenarySystem.setScenary(s);
		
		scenaryView = new ScenaryView(gameWindow, s);
		scenarySystem.setView(scenaryView);
		
		minionSystem = new MinionSystem();
		minionSystem.setMinionsList(minions);
		minionViews = new MinionViewsMediator(gameWindow);
		minionSystem.setMediator(minionViews);
		minionSystem.setScenary(s);
		
		// SET MINIONSYSTEM DEPENDENCY ON SCENARYSYSTEM
		scenarySystem.setMinionSystem(minionSystem);
		
		towerSystem = new TowerSystem(minionSystem);
		towerViews = new TowerViewsMediator(gameWindow);
		//minionMenu = new MinionMenu(minionSystem);
		
		towerSystem.setMediator(towerViews);
		towerSystem.setScenary(s);
		
		bulletViews = new BulletViewsMediator(gameWindow);
		towerSystem.setMediator(bulletViews);
		
		towerSystem.createTower();
		
		minionSystem.startSpawning();
		
		scoreView = new ScoreView(gameWindow);
		// Create FPS label
		fpsLabel = gameWindow.createLabel("FPS: 0", new Font("Century Gothic", Font.PLAIN, 12));
		fpsLabel.setColor(Color.BLACK);
		
		// Start time counters
		startTime = System.nanoTime();
	}

	public MinionSystem getMinionSystem() {
		return minionSystem;
	}

	@Override
	public void update(long deltaMillis) {
		lastFPSUpdateTime += deltaMillis;
		fps++;
		
		// Show FPS on title bar
		if (lastFPSUpdateTime >= 1000) {
			fpsLabel.setText("FPS: " + fps);
			lastFPSUpdateTime = 0;
			fps = 0;
		}
		
		minionSystem.update(deltaMillis);
		score = minionSystem.getScore();
		towerSystem.update(deltaMillis);
		scenarySystem.update(deltaMillis);
		score += scenarySystem.getArrivalScore();
		
		if(minionSystem.GetGameIsOver()) {
			gameWindow.endGame(score);
		}
		// Limit FPS to 100
		try {
			Thread.sleep(10);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	//draws the scenary, the towers, the minions and the bullets
	@Override
	public void draw(long deltaMillis) {
		fpsLabel.draw(2, 12);
		scenaryView.draw(deltaMillis);
		towerViews.draw(deltaMillis);
		minionViews.draw(deltaMillis);
		bulletViews.draw(deltaMillis);
		scoreView.draw(score);
	}
}
