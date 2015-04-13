package gamelogic;

import gamelogic.minions.Minion;
import gamelogic.minions.MinionAgi;
import gamelogic.scenary.Scenary;
import gamelogic.towers.Bullet;
import gamelogic.towers.Tower;
import gamelogic.towers.Tower2;
import gameviews.BulletViewsMediator;
import gameviews.MinionViewsMediator;
import gameviews.TowerViewsMediator;


import interfaces.ICircle;

import java.awt.Point;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import util.Vector2D;

@SuppressWarnings("unused")
public class TowerSystem {
	private List<Tower> towerList;
	private List<Bullet> bulletList;
	private List<Minion> minionList;
	private TowerViewsMediator towerMediator;
	private BulletViewsMediator bulletMediator;
	private MinionSystem minionSystem;
	private int tileSize = 64;
	
	private Scenary scenary = null;
	
	//constructor
	public TowerSystem(MinionSystem minionSystem) {
		towerList = new LinkedList<Tower>();
		bulletList = new LinkedList<>();
		this.minionSystem = minionSystem;
	}
	
	//updates the tower and the bullets of the towers
	public void update(long deltaMilli) {
		minionList = minionSystem.getMinionsList();
		for (Tower t : towerList) {
			t.setMinionsList(minionList);
			t.update(deltaMilli);
		}
		List<Bullet> toRemove = new ArrayList<>();
		if(bulletList != null){
			for(Bullet b : bulletList) {
				b.update(deltaMilli);
				if(b.toBeRemoved())
					toRemove.add(b);
					
			}
		}
		
		for(Bullet b : toRemove) {
			deleteBullet(b);
			
		}
	}

	//creates a new tower
	public void createTower() {
		if(scenary == null) {
			System.err.println("Scenary is null.");
			return;
		}
		
		Tower m = new Tower(new Vector2D(6*tileSize - tileSize/2, 5*tileSize - tileSize/2), this);
		Tower n = new Tower2(new Vector2D(9*tileSize - tileSize/2, 4*tileSize - tileSize/2), this);
		Tower p = new Tower(new Vector2D(3*tileSize - tileSize/2, 8*tileSize - tileSize/2), this);
		Tower t = new Tower2(new Vector2D(8*tileSize - tileSize/2, 7*tileSize - tileSize/2), this);
		towerList.add(m);
		towerList.add(n);
		towerList.add(p);
		towerList.add(t);
		if(towerMediator != null){
			towerMediator.addView(m);
			towerMediator.addView(n);
			towerMediator.addView(p);
			towerMediator.addView(t);
		}
	}
	
	//creates a new Bullet
	public void createBullet(Bullet bullet){
		
		if(scenary == null) {
			System.err.println("Scenary is null.");
			return;
		}
		
		bulletList.add(bullet);
		if(bulletMediator != null)
			bulletMediator.addView(bullet);
		
	}
	
	public void deleteMinion(Minion m){
		minionSystem.removeMinion(m);
	}
	
	public void deleteBullet(Bullet bullet){
		bulletMediator.removeView(bullet);
		bulletList.remove(bullet);
	}
	
	public void setMediator(TowerViewsMediator mediator) {
		this.towerMediator = mediator;
	}
	
	public void setMediator(BulletViewsMediator mediator) {
		this.bulletMediator = mediator;
	}
	
	public List<Tower> getTowerList() {
		return towerList;
	}

	public void setTowerList(List<Tower> towerList) {
		this.towerList = towerList;
	}

	public void setScenary(Scenary scenary) {
		this.scenary = scenary;
	}
}
