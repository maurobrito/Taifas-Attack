package gameviews;

import gamelogic.towers.Bullet;
import gamelogic.towers.Tower;
import interfaces.IGameWindow;

import java.util.LinkedList;
import java.util.List;

//mediates the view of the towers, adds them and updates them
public class TowerViewsMediator {
	List<TowerView> towerViews;
	private IGameWindow gameWindow;
	
	public TowerViewsMediator(IGameWindow gameWindow)
	{
		this.gameWindow = gameWindow;
		towerViews = new LinkedList<TowerView>();
	}
	
	public void draw(long deltaMillis)
	{
		for (TowerView mV : towerViews) {
			mV.draw(deltaMillis);
		}
	}
	
	public void addView(Tower m) {
		TowerView mV = new TowerView(gameWindow);
		mV.attachTower(m);
		towerViews.add(mV);
	}
}
