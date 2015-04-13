package gamelogic.minions;

import java.util.List;

import util.Vector2D;

//minion that has less life but it's faster
public class MinionAgi extends Minion {

	public MinionAgi(Vector2D startPosition, List<Vector2D> path) {
		super(startPosition, path);
		setName("Agi");
		setLife(40);
		setBaseSpeed(0.2f);
		setActualSpeed(getBaseSpeed());
	}

}
