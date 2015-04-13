package gamelogic.minions;

import java.util.List;

import util.Vector2D;

//minion that has more life but it's slower
public class MinionTanker extends Minion {

	public MinionTanker(Vector2D startPosition, List<Vector2D> path) {
		super(startPosition, path);
		setName("Tanker");
		setLife(70);
		setBaseSpeed(0.07f);
		setActualSpeed(getBaseSpeed());
	}
}
