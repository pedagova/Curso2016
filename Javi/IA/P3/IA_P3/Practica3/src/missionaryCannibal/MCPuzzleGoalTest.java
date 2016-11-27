package missionaryCannibal;

import aima.core.search.framework.GoalTest;

/**
 * @author Ravi Mohan
 * 
 */
public class MCPuzzleGoalTest implements GoalTest {
	
	MCPuzzleBoard goal = new MCPuzzleBoard(new int[] { 0, 0, 1});
	 
	public boolean isGoalState(Object state) {
		return ((MCPuzzleBoard) state).equals(goal);
	}
}
