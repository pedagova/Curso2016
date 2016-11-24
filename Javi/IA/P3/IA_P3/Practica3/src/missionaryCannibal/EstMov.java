package missionaryCannibal;

import aima.core.search.framework.HeuristicFunction;

public class EstMov implements HeuristicFunction {

	@Override
	public double h(Object arg0) {
		MissionaryCannibalBoard board = (MissionaryCannibalBoard)arg0;
		int[] st = board.getState();
		return st[0] * 3 + st[1] * 6 ;
	}

}
