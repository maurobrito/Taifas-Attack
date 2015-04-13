package gamelogic.minions;

import gamelogic.scenary.Scenary;

//factory to creates minions depending on what they are
public class MinionFactory {
	public enum MinionType {
		TANKER, AGI, HEALER, TAIFAS
	}; 

	public static Minion createMinion(MinionType type, Scenary scenary){
		switch(type){
		case TANKER: return new MinionTanker(scenary.getStartPoint(), scenary.getPath());
		case AGI: return new MinionAgi(scenary.getStartPoint(), scenary.getPath());
		case HEALER: return new MinionHealer(scenary.getStartPoint(), scenary.getPath());
		case TAIFAS: return new MinionTaifas(scenary.getStartPoint(), scenary.getPath());
		}
		return null;
	}
}
