package gamelogic;

import java.util.ArrayList;
import java.util.List;

import gamelogic.minions.Minion;
import gamelogic.scenary.Scenary;
import gameviews.ScenaryView;

public class ScenarySystem {
	@SuppressWarnings("unused")
	private ScenaryView view;
	private Scenary scenary;
	private MinionSystem minionSystem;
	
	private int arrivalScore = 0;

	//updates what appears on the scenary
	public void update(long deltaMillis) {
		List<Minion> toBeRemoved = new ArrayList<>();
		
		for(Minion m : minionSystem.getMinionsList()) {
			if(m.getPosition().equals(scenary.getEndPoint().clamp())) {
				toBeRemoved.add(m);
			}
		}
		
		for(Minion m : toBeRemoved) {
			arrivalScore += 1000;
			minionSystem.removeMinion(m);
		}
	}
	
	public void setView(ScenaryView view) {
		this.view = view;
	}
	
	public void setScenary(Scenary scenary) {
		this.scenary = scenary;
	}
	
	public void setMinionSystem(MinionSystem system) {
		this.minionSystem = system;
	}
	
	public int getArrivalScore() {
		return arrivalScore;
	}
}
