package gameviews;

import gamelogic.minions.Minion;
import interfaces.IGameWindow;
import interfaces.ISprite;

import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

//mediates the views of the minions, adds them, updates them and removes them
@SuppressWarnings("unused")
public class MinionViewsMediator {
	List<MinionView> minionViews;
	Map<Minion, MinionView> views;
	private IGameWindow gameWindow;
	
	public MinionViewsMediator(IGameWindow gameWindow)
	{
		this.gameWindow = gameWindow;
		minionViews = new LinkedList<MinionView>();
		views = new Hashtable<Minion, MinionView>();
	}
	
	public void draw(long deltaMillis)
	{
		for (MinionView mV : minionViews) {
			mV.draw(deltaMillis);
		}
	}

	public void addView(Minion m) {
		MinionView mV = new MinionView(gameWindow);
		mV.attachMinion(m);
		minionViews.add(mV);
		views.put(m, mV);
	}

	public void removeView(Minion m) {
		MinionView mV = views.get(m);
		minionViews.remove(mV);
		views.remove(m);
	}
}
