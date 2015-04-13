package gamelogic;

import java.util.LinkedList;
import java.util.List;

import gamelogic.minions.*;
import gamelogic.minions.MinionFactory.MinionType;
import gamelogic.scenary.Scenary;
import gameviews.MinionViewsMediator;

//manages the minions
public class MinionSystem {
	private List<Minion> minionsList, minionsListWaiting;
	private Minion lastAdded = null;
	@SuppressWarnings("unused")
	private MinionFactory factory;
	private MinionViewsMediator mediator;

	private Scenary scenary = null;

	private MinionType[] type = {MinionType.TANKER, MinionType.AGI, MinionType.TANKER};
	private Minion lastMinion;
	
	private int deadScore = 0;
	private boolean gameIsOver = false;
	
	//constructor
	public MinionSystem() {
		minionsList = new LinkedList<>();
		minionsListWaiting = new LinkedList<>();
		factory = new MinionFactory();
	}

	//updates the list of minions that are put on the screen
	public void update(long deltaMilli) {
		if (lastAdded == null){
			lastAdded = minionsListWaiting.remove(0);
			minionsList.add(lastAdded);
			if(mediator != null){
				mediator.addView(lastAdded);
			}
		}
		//check so it doesn't put one minion on top of the other
		else if(lastAdded.getTotalCaminhado() > 150 && !minionsListWaiting.isEmpty()){
			lastAdded = minionsListWaiting.remove(0);
			minionsList.add(lastAdded);
			if(mediator != null){
				mediator.addView(lastAdded);
			}
		}
		lastMinion = null;
		for (Minion m : minionsList) {
			m.update(deltaMilli, lastMinion);
			lastMinion = m;
		}
		
		if(minionsList.size() == 0 && minionsListWaiting.size() == 0) {
			gameIsOver = true;
		}
	}
	
	public void startSpawning() {
		for(int i = 0; i < type.length; i++){
			minionsListWaiting.add(MinionFactory.createMinion(type[i], scenary));
		}
	}

	public  List<Minion> getMinionsList(){
		return minionsList;
	}
	
	public void removeMinion(Minion m) {
		minionsList.remove(m);
		deadScore += (int)m.getTotalCaminhado();
		mediator.removeView(m);
	}
	
	public void setMediator(MinionViewsMediator mediator) {
		this.mediator = mediator;
	}

	public void setScenary(Scenary scenary) {
		this.scenary = scenary;
	}
	
	public void setMinionsList(MinionType[] minions){
		type = minions;
	}

	public int getScore() {
		int total = 0;
		total += deadScore;
		for(Minion m : minionsList) {
			total += (int)m.getTotalCaminhado();
		}
		return total;
	}

	public boolean GetGameIsOver() {
		return gameIsOver;
	}
}
