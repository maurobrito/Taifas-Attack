package gamelogic.minions;

import java.awt.Point;
import java.util.Iterator;
import java.util.List;

import util.Vector2D;

public class Minion {
	protected String name;
	private Vector2D position;
	@SuppressWarnings("unused")
	private List<Vector2D> path;
	private Iterator<Vector2D> pathIterator;
	private Vector2D pathNode;
	private float baseSpeed = 0;
	private float actualSpeed = 0;
	private int life = 0;
	private int cure = 0;
	private float totalCaminhado = 0;
	private double angle = 0;
	private int timeout = 0;
	private int cureSleep = 0;

	//constructor
	public Minion(Vector2D startPosition, List<Vector2D> path) {
		setName("Base");
		this.position = new Vector2D(startPosition.getX(), startPosition.getY());
		this.path = path;
		this.pathIterator = path.iterator();
		this.pathNode = pathIterator.next();		
		calculateAngle();
	}

	//updates the minions and checks so the minions walk on a line and don't pass one another even if the minion behind is faster
	public void update(long deltaMillis, Minion lastMinion) {
		
		if(this.name == "Healer"){
			if(timeout > cureSleep){
				this.setLife(this.getLife() + cure);
				timeout = 0;
			}
			else{
				timeout += deltaMillis;
			}
		}
		
		if(lastMinion != null){
			if(Distance(this.position, lastMinion.position) < 80 &&
					this.getActualSpeed() >= lastMinion.getActualSpeed()){
				this.setActualSpeed(lastMinion.getActualSpeed());
			} else  {
				this.setActualSpeed(this.getBaseSpeed());
			}
			
		}else  {
			this.setActualSpeed(this.getBaseSpeed());
		}
		
		if(pathNode.getX() > position.getX()) {
			position.setX(position.getX() + getActualSpeed()*deltaMillis);
			if(position.getX() > pathNode.getX()){
				totalCaminhado += position.getX() - pathNode.getX();
				position.setX(pathNode.getX());
			} else {
				totalCaminhado += getActualSpeed()*deltaMillis;
			}
		} else if(pathNode.getX() < position.getX()) {
			position.setX(position.getX() - getActualSpeed()*deltaMillis);
			if(position.getX() < pathNode.getX()){
				totalCaminhado += pathNode.getX() - position.getX();
				position.setX(pathNode.getX());
			} else {
				totalCaminhado += getActualSpeed()*deltaMillis;
			}
		}

		if(pathNode.getY() > position.getY()) {
			position.setY(position.getY() + getActualSpeed()*deltaMillis);
			if(position.getY() > pathNode.getY()){
				totalCaminhado += position.getY() - pathNode.getY();
				position.setY(pathNode.getY());
			} else {
				totalCaminhado += getActualSpeed()*deltaMillis;
			}
		} else if(pathNode.getY() < position.getY()) {
			position.setY(position.getY() - getActualSpeed()*deltaMillis);
			if(position.getY() < pathNode.getY()){
				totalCaminhado += pathNode.getY() - position.getY();
				position.setY(pathNode.getY());
			} else {
				totalCaminhado += getActualSpeed()*deltaMillis;
			}
		}

		if(pathNode.getX() == position.getX() && pathNode.getY() == position.getY()) {
			if(pathIterator.hasNext()) {
				pathNode = pathIterator.next();
				calculateAngle();
			}
		}
	}
	
	public float Distance(Vector2D minion, Vector2D lastMinion){
		float distancia;
		distancia = (float) (Math.abs(minion.getX() - lastMinion.getX()) + Math.abs(minion.getY() - lastMinion.getY()));
		return distancia;
	}

	public Point getPosition() {
		return position.clamp();
	}

	public int getLife() {
		return life;
	}

	public void setLife(int life) {
		this.life = life;
	}

	public float getBaseSpeed() {
		return baseSpeed;
	}

	public void setBaseSpeed(float speed) {
		this.baseSpeed = speed;
	}

	public float getActualSpeed(){
		return actualSpeed;
	}

	public void setActualSpeed(float speed) {
		this.actualSpeed = speed;
	}

	protected void setName(String name) {
		this.name = name;
	}

	public float getTotalCaminhado() {
		return totalCaminhado;
	}

	public void setTotalCaminhado(float totalCaminhado) {
		this.totalCaminhado = totalCaminhado;
	}
	private void calculateAngle() {
		angle = (-1)*Math.atan2(pathNode.getX() - position.getX(), pathNode.getY() - position.getY()) + (Math.PI);
		
	}
	public double getAngle() {
		return angle;
	}

	public int getCure() {
		return cure;
	}

	public void setCure(int cure) {
		this.cure = cure;
	}

	public int getTimeout() {
		return timeout;
	}

	public void setTimeout(int timeout) {
		this.timeout = timeout;
	}

	public int getCureSleep() {
		return cureSleep;
	}

	public void setCureSleep(int cureSleep) {
		this.cureSleep = cureSleep;
	}
	
	
}
