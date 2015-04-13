package junitsTests;

import gamelogic.minions.Minion;
import gamelogic.minions.MinionAgi;
import gamelogic.minions.MinionTanker;
import static org.junit.Assert.*;
import java.util.ArrayList;

import org.junit.Test;

import util.Vector2D;

public class MinionTest {

	@Test
	public void testUpdateSpeed(){
		Vector2D vectorStartPoint = new Vector2D(0, 0);
		Vector2D vectorEndPoint = new Vector2D(10, 0);
		
		ArrayList<Vector2D> path = new ArrayList<>();
		path.add(vectorStartPoint);
		path.add(vectorEndPoint);
		
		Minion m = new MinionAgi(vectorStartPoint, path);
		Minion m2 = new MinionTanker(vectorEndPoint, path);
		
		m.update(1000, m2);
		
		//testing if the minion behind is updating his speed if he reaches the one ahead
		assertEquals(m.getActualSpeed(), m2.getBaseSpeed(), 0.0001);
		
		m.update(1, null);
		//testing if he is updating his speed if the minion ahead dies
		assertEquals(m.getActualSpeed(), m.getBaseSpeed(), 0.0001);
	}
	
	@Test
	public void testUpdateNull(){
		Vector2D vectorStartPoint = new Vector2D(0, 0);
		Vector2D vectorEndPoint = new Vector2D(0, 0);
		
		ArrayList<Vector2D> path = new ArrayList<>();
		path.add(vectorStartPoint);
		path.add(vectorEndPoint);
		
		Minion m = new MinionAgi(vectorStartPoint, path);
		Minion m2 = null;
		
		m.update(10, m2);
		//testing if the minion constructor recognizes if the minion ahead is null
		assertEquals(m.getActualSpeed(), m.getBaseSpeed(), 0.000001);
		
	}
	
	@Test
	public void testUpdateTotalDistance(){
		Vector2D vectorStartPoint = new Vector2D(0, 0);
		Vector2D vectorEndPoint = new Vector2D(100, 0);
		
		ArrayList<Vector2D> path = new ArrayList<>();
		path.add(vectorStartPoint);
		path.add(vectorEndPoint);
		
		Minion m = new MinionAgi(vectorStartPoint, path);
		Minion m2 = null;
		
		m.update(20, m2);
		//testing if the total distance of the minion is updating
		assertEquals(2*m.getActualSpeed(), m.getTotalCaminhado(), 1);
		
	}
	

}
