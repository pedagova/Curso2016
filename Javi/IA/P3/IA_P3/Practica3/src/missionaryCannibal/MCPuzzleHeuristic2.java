package missionaryCannibal;

import aima.core.search.framework.HeuristicFunction;

/**
 * @author Ravi Mohan
 * 
 */
public class MCPuzzleHeuristic2 implements HeuristicFunction {

	public double h(Object state) {
		MCPuzzleBoard board = (MCPuzzleBoard)state;
		int[] st = board.getState();
		return (st[0] + st[1]) * 2 + st[2];
	}
	
}