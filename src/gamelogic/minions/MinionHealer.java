package gamelogic.minions;

import java.util.List;

import util.Vector2D;

public class MinionHealer extends Minion {

	public MinionHealer(Vector2D startPosition, List<Vector2D> path) {
		super(startPosition, path);
		setName("Healer");
		setLife(30);
		setCure(1);
		setCureSleep(700);
		setBaseSpeed(0.12f);
		setActualSpeed(getBaseSpeed());
	}

}
