package blackWhitePuzzle;

import aima.core.search.framework.GoalTest;

public class BWPuzzleGoalTest implements GoalTest {
	BWPuzzleBoard goal_1 = new BWPuzzleBoard(
			new BWPuzzlePiece[] { BWPuzzlePiece.WHITE, BWPuzzlePiece.WHITE, BWPuzzlePiece.WHITE, BWPuzzlePiece.HOLE, 
					BWPuzzlePiece.BLACK, BWPuzzlePiece.BLACK, BWPuzzlePiece.BLACK });
	
	public boolean isGoalState(Object state) {
		BWPuzzleBoard board = (BWPuzzleBoard) state;
		if(board.equals(goal_1))
			return true;
		return false;
	}
}