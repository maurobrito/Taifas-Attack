package gamelogic.towers;

import gamelogic.TowerSystem;
import util.Vector2D;

public class Tower2 extends Tower{

	public Tower2(Vector2D position, TowerSystem towerSystem) {
		super(position, towerSystem);
		setName("Tower2");
		setRange(200);
		setTowerReload(350);
		setAttackPower(3);
	}

}
