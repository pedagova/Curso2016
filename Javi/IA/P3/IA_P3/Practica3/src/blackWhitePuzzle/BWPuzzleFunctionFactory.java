package blackWhitePuzzle;

import java.util.LinkedHashSet;
import java.util.Set;

import aima.core.agent.Action;
import aima.core.search.framework.ActionsFunction;
import aima.core.search.framework.ResultFunction;

public class BWPuzzleFunctionFactory {
	private static ActionsFunction _actionsFunction = null;
	private static ResultFunction _resultFunction = null;

	public static ActionsFunction getActionsFunction() {
		if (null == _actionsFunction) {
			_actionsFunction = new EPActionsFunction();
		}
		return _actionsFunction;
	}

	public static ResultFunction getResultFunction() {
		if (null == _resultFunction) {
			_resultFunction = new EPResultFunction();
		}
		return _resultFunction;
	}

	private static class EPActionsFunction implements ActionsFunction {
		public Set<Action> actions(Object state) {
			BWPuzzleBoard board = (BWPuzzleBoard) state;

			Set<Action> actions = new LinkedHashSet<Action>();

			if (board.canMovePiece(BWPuzzleBoard.WHITE_ADY, )) {
				actions.add(BWPuzzleBoard.WHITE_ADY);
			}
			if (board.canMovePiece(BWPuzzleBoard.WHITE_ONE)) {
				actions.add(BWPuzzleBoard.WHITE_ONE);
			}
			if (board.canMovePiece(BWPuzzleBoard.WHITE_TWO)) {
				actions.add(BWPuzzleBoard.WHITE_TWO);
			}
			

			return actions;
		}
	}

	private static class EPResultFunction implements ResultFunction {
		public Object result(Object s, Action a) {
			BWPuzzleBoard board = (BWPuzzleBoard) s;

			if (BWPuzzleBoard.WHITE_ADY.equals(a)
					&& board.canMovePiece(BWPuzzleBoard.WHITE_ADY)) {
				BWPuzzleBoard newBoard = new BWPuzzleBoard(board);
				newBoard.moveWhiteAdy();
				return newBoard;
			} else if (BWPuzzleBoard.WHITE_ONE.equals(a)
					&& board.canMovePiece(BWPuzzleBoard.WHITE_ONE)) {
				BWPuzzleBoard newBoard = new BWPuzzleBoard(board);
				newBoard.moveWhiteOne();
				return newBoard;
			} else if (BWPuzzleBoard.WHITE_TWO.equals(a)
					&& board.canMovePiece(BWPuzzleBoard.WHITE_TWO)) {
				BWPuzzleBoard newBoard = new BWPuzzleBoard(board);
				newBoard.moveWhiteTwo();
				return newBoard;
			} else if (BWPuzzleBoard.BLACK_ADY.equals(a)
					&& board.canMovePiece(BWPuzzleBoard.BLACK_ADY)) {
				BWPuzzleBoard newBoard = new BWPuzzleBoard(board);
				newBoard.moveBlackAdy();
				return newBoard;
			} else if (BWPuzzleBoard.BLACK_ONE.equals(a)
					&& board.canMovePiece(BWPuzzleBoard.BLACK_ONE)) {
				BWPuzzleBoard newBoard = new BWPuzzleBoard(board);
				newBoard.moveBlackOne();
				return newBoard;
			} else if (BWPuzzleBoard.BLACK_TWO.equals(a)
					&& board.canMovePiece(BWPuzzleBoard.BLACK_TWO)) {
				BWPuzzleBoard newBoard = new BWPuzzleBoard(board);
				newBoard.moveBlackTwo();
				return newBoard;
			} 

			// The Action is not understood or is a NoOp
			// the result will be the current state.
			return s;
		}
	}
}