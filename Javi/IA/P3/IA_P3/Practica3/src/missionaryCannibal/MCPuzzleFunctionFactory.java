package missionaryCannibal;

import java.util.LinkedHashSet;
import java.util.Set;

import aima.core.agent.Action;
import aima.core.search.framework.ActionsFunction;
import aima.core.search.framework.ResultFunction;

/**
 * @author Ravi Mohan
 * @author Ciaran O'Reilly
 */
public class MCPuzzleFunctionFactory {
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
			MCPuzzleBoard board = (MCPuzzleBoard) state;

			Set<Action> actions = new LinkedHashSet<Action>();

			if (board.canMoveBoat(MCPuzzleBoard.MOVE_C)) {
				actions.add(MCPuzzleBoard.MOVE_C);
			}
			if (board.canMoveBoat(MCPuzzleBoard.MOVE_CC)) {
				actions.add(MCPuzzleBoard.MOVE_CC);
			}
			if (board.canMoveBoat(MCPuzzleBoard.MOVE_M)) {
				actions.add(MCPuzzleBoard.MOVE_M);
			}
			if (board.canMoveBoat(MCPuzzleBoard.MOVE_MC)) {
				actions.add(MCPuzzleBoard.MOVE_MC);
			}
			if (board.canMoveBoat(MCPuzzleBoard.MOVE_MM)) {
				actions.add(MCPuzzleBoard.MOVE_MM);
			}

			return actions;
		}
	}

	private static class EPResultFunction implements ResultFunction {
		public Object result(Object s, Action a) {
			MCPuzzleBoard board = (MCPuzzleBoard) s;

			if (MCPuzzleBoard.MOVE_C.equals(a)
					&& board.canMoveBoat(MCPuzzleBoard.MOVE_C)) {
				MCPuzzleBoard newBoard = new MCPuzzleBoard(board);
				newBoard.moveC();
				return newBoard;
			} else if (MCPuzzleBoard.MOVE_CC.equals(a)
					&& board.canMoveBoat(MCPuzzleBoard.MOVE_CC)) {
				MCPuzzleBoard newBoard = new MCPuzzleBoard(board);
				newBoard.moveCC();
				return newBoard;
			} else if (MCPuzzleBoard.MOVE_M.equals(a)
					&& board.canMoveBoat(MCPuzzleBoard.MOVE_M)) {
				MCPuzzleBoard newBoard = new MCPuzzleBoard(board);
				newBoard.moveM();
				return newBoard;
			} else if (MCPuzzleBoard.MOVE_MC.equals(a)
					&& board.canMoveBoat(MCPuzzleBoard.MOVE_MC)) {
				MCPuzzleBoard newBoard = new MCPuzzleBoard(board);
				newBoard.moveMC();
				return newBoard;
			}
			else if (MCPuzzleBoard.MOVE_MM.equals(a)
					&& board.canMoveBoat(MCPuzzleBoard.MOVE_MM)) {
				MCPuzzleBoard newBoard = new MCPuzzleBoard(board);
				newBoard.moveMM();
				return newBoard;
			}
			// The Action is not understood or is a NoOp
			// the result will be the current state.
			return s;
		}
	}
}