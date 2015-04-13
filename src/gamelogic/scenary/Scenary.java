package gamelogic.scenary;

import java.util.List;

import util.Vector2D;

//has all the info about the scenary
public class Scenary {
	private MapObject map;
	private Vector2D startPoint;
	private Vector2D endPoint;
	private List<Vector2D> path;
	
	public Scenary(){
		
	}
	
	public void setMap(MapObject map) {
		this.map = map;
	}
	
	public void setStartPoint(Vector2D startPoint) {
		this.startPoint = startPoint;
	}
	
	public void setEndPoint(Vector2D endPoint) {
		this.endPoint = endPoint;
	}
	
	public void setPath(List<Vector2D> path) {
		this.path = path;
	}

	public Vector2D getStartPoint() {
		return startPoint;
	}
	
	public List<Vector2D> getPath() {
		return path;
	}

	public MapObject getMap() {
		return map;
	}

	public Vector2D getEndPoint() {
		return endPoint;
	}
}
