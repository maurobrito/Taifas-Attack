package gamelogic.towers;

import gamelogic.minions.Minion;

import java.awt.Point;

import util.Vector2D;

public class Bullet {
	protected String name;
	private Vector2D position;
	private Minion minion;
	private float speed;
	private Vector2D distance;
	private Tower tower;
	
	private boolean toBeRemoved = false;
	//bullet constructor
	public Bullet(Vector2D position, Minion minion, Tower tower) {
		setName("Base");
		this.position = new Vector2D(position.getX(), position.getY());
		this.minion = minion;
		this.speed = 0.5f;
		this.tower = tower;
	}
	
	//updates the bullet
	public void update(long deltaMillis) {
		updatePosition();
		checkColision();
		
	}
	
	//updates bullet position
	public void updatePosition(){
		
		this.distance = getVectorDistance();
		this.position.setX( this.position.getX() - distance.getX() * speed);
		this.position.setY( this.position.getY() - distance.getY() * speed);
		
	}
	
	//check to see if the bullet hit the minion
	public void checkColision(){
		if(Math.abs(distance.getX()) < 40 &&  Math.abs(distance.getY()) < 40 ){
			//tower.getTowerSystem().deleteBullet(this);
			toBeRemoved = true;
			minion.setLife(minion.getLife() - tower.getAttackPower());
			
			if(minion.getLife() <= 0){
				tower.getTowerSystem().deleteMinion(minion);
			}
		}
	}
	
	//calculates the distance from the bullet to the minion
	public Vector2D getVectorDistance(){
		Vector2D distance = new Vector2D(0, 0);
		
		distance.setX((float) (this.position.getX() - minion.getPosition().getX()));
		distance.setY((float) (this.position.getY() - minion.getPosition().getY()));
		return distance;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
	
	public Point getPosition() {
		return position.clamp();
	}

	public boolean toBeRemoved() {
		return toBeRemoved;
	}
}
