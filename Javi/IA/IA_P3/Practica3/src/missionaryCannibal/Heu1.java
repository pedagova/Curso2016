package missionaryCannibal;

import aima.core.search.framework.HeuristicFunction;

/**
 * @author Ravi Mohan
 * 
 */
public class Heu1 implements HeuristicFunction {

	public double h(Object state) {
		MissionaryCannibalBoard board = (MissionaryCannibalBoard)state;
		int[] st = board.getState();
		return (st[0] + st[1]) * 2 + st[2];
	}
	
}