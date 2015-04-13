package gameviews;

import gamelogic.minions.Minion;
import gamelogic.towers.Bullet;
import interfaces.IGameWindow;

import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

//mediates the view of the bullets, draws them, removes them and updates them
@SuppressWarnings("unused")
public class BulletViewsMediator {

	List<BulletView> bulletViews;
	Map<Bullet, BulletView> views;
	private IGameWindow gameWindow;

	public BulletViewsMediator(IGameWindow gameWindow)
	{
		this.gameWindow = gameWindow;
		bulletViews = new LinkedList<BulletView>();
		views = new Hashtable<Bullet, BulletView>();
	}

	public void draw(long deltaMillis)
	{
		for (BulletView bV : bulletViews) {
			bV.draw(deltaMillis);
		}
	}

	public void addView(Bullet b) {
		BulletView bV = new BulletView(gameWindow);
		bV.attachBullet(b);
		bulletViews.add(bV);
		views.put(b, bV);
	}

	public void removeView(Bullet b) {
		BulletView bV = views.get(b);
		bulletViews.remove(bV);
		views.remove(b);
	}

}
