package blackWhitePuzzle;

import aima.core.search.framework.GoalTest;

public class BWPuzzleGoalTest implements GoalTest {
	BWPuzzleBoard goal_1 = new BWPuzzleBoard(
			new Piece[] { Piece.WHITE, Piece.WHITE, Piece.WHITE, Piece.HOLE, 
					Piece.BLACK, Piece.BLACK, Piece.BLACK });

	BWPuzzleBoard goal_2 = new BWPuzzleBoard(
			new Piece[] { Piece.HOLE, Piece.WHITE, Piece.WHITE,
					Piece.WHITE, Piece.BLACK, Piece.BLACK, Piece.BLACK });

	BWPuzzleBoard goal_3 = new BWPuzzleBoard(
			new Piece[] { Piece.WHITE, Piece.WHITE, Piece.WHITE, Piece.BLACK,
					Piece.BLACK, Piece.BLACK, Piece.HOLE });

	public boolean isGoalState(Object state) {
		BWPuzzleBoard board = (BWPuzzleBoard) state;
		if(board.equals(goal_1)){
			return true;
		}else if(board.equals(goal_2)){
			return true;
		}else if(board.equals(goal_3)){
			return true;
		}
		return false;
	}
}