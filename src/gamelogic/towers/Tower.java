package gamelogic.towers;

import gamelogic.TowerSystem;
import gamelogic.minions.Minion;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import util.Vector2D;

public class Tower {
	protected String name;
	private Vector2D position;
	private int range;
	private int attackPower;//amount of damage that the tower will inflict of damage
	private List<Minion> minionList;

	private List<Minion> onRangeMinions;//list of the minions that are on the range of the tower
	private int timeout;//sum of the time in which the tower is reloading
	private int towerReload;//time the tower takes to reload
	TowerSystem towerSystem;
	
	//tower constructor
	public Tower(Vector2D position, TowerSystem towerSystem) {
		setName("Tower1");
		this.position = new Vector2D(position.getX(), position.getY());
		range = 100;
		attackPower = 5;
		onRangeMinions = new ArrayList<Minion>();
		setTowerReload(500);
		setTimeout(0);
		this.towerSystem = towerSystem;
	}
	
	public void update(long deltaMillis) {
		onRangeMinions = new ArrayList<Minion>();
		for(Minion m : minionList){
			if(Distance(getPosition(), m.getPosition()) < getRange()){
				onRangeMinions.add(m);
			}
		}
		attack(deltaMillis);
	}
	
	public void attack(long deltaMillis){
		//TODO: Create a bullet, and add to bulletList we are going to receive
		if(timeout > towerReload && !onRangeMinions.isEmpty()){
			//send the minion that is going to receive the shot
			Bullet bullet = new Bullet(this.position, onRangeMinions.get(0), this );
			//create Bullet
			towerSystem.createBullet(bullet);
			timeout = 0;
		} else {
			timeout += deltaMillis;
		}
	}
	
	 
	public TowerSystem getTowerSystem() {
		return towerSystem;
	}

	public void setTowerSystem(TowerSystem towerSystem) {
		this.towerSystem = towerSystem;
	}

	//calculates the distance from the minion to the tower
	public float Distance(Point torre, Point minion){
		float distance;
		distance = (float) Math.sqrt(Math.pow((torre.getX()-minion.getX()),2) + Math.pow((torre.getY()-minion.getY()),2));
		return distance;
	}
	
	public int getTimeout() {
		return timeout;
	}

	public void setTimeout(int timeout) {
		this.timeout = timeout;
	}

	public int getTowerReload() {
		return towerReload;
	}

	public void setTowerReload(int towerReload) {
		this.towerReload = towerReload;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}

	public Point getPosition() {
		return position.clamp();
	}

	public int getRange() {
		return range;
	}
	
	public void setRange(int range) {
		this.range = range;
	}

	public void setMinionsList(List<Minion> minionList){
		this.minionList = minionList;
	}
	
	public List<Minion> getOnRangeMinions(){
		return onRangeMinions;
	}
	
	public int getAttackPower() {
		return attackPower;
	}

	public void setAttackPower(int attackPower) {
		this.attackPower = attackPower;
	}
	
}
