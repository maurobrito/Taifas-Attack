package gamelogic.minions;

import java.util.List;

import util.Vector2D;

public class MinionTaifas extends Minion {

	public MinionTaifas(Vector2D startPosition, List<Vector2D> path) {
		super(startPosition, path);
		setName("Taifas");
		setLife(10);
		setBaseSpeed(0.03f);
		setActualSpeed(getBaseSpeed());
	}

}
