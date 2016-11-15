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

			for(Action a : BWPuzzleBoard.Iz){
				if(board.canMoveHole(a)){
					actions.add(a);
				}
			}
			for(Action a : BWPuzzleBoard.Der){
				if(board.canMoveHole(a)){
					actions.add(a);
				}
			}
			
			return actions;
		}
	}

	private static class EPResultFunction implements ResultFunction {
		public Object result(Object s, Action a) {
			BWPuzzleBoard board = (BWPuzzleBoard) s;

			if(a.equals(BWPuzzleBoard.Iz[0]) && 
					board.canMoveHole(BWPuzzleBoard.Iz[0])){
				BWPuzzleBoard newBoard = new BWPuzzleBoard(board);
				newBoard.moveIz();
				return newBoard;
			}
			else if(a.equals(BWPuzzleBoard.Iz[1]) && 
					board.canMoveHole(BWPuzzleBoard.Iz[1])){
				BWPuzzleBoard newBoard = new BWPuzzleBoard(board);
				newBoard.moveIzIz();
				return newBoard;
			}
			else if(a.equals(BWPuzzleBoard.Iz[2]) && 
					board.canMoveHole(BWPuzzleBoard.Iz[2])){
				BWPuzzleBoard newBoard = new BWPuzzleBoard(board);
				newBoard.moveIzIzIz();
				return newBoard;
			}
			else if(a.equals(BWPuzzleBoard.Der[0]) && 
					board.canMoveHole(BWPuzzleBoard.Der[0])){
				BWPuzzleBoard newBoard = new BWPuzzleBoard(board);
				newBoard.moveDer();
				return newBoard;
			}
			else if(a.equals(BWPuzzleBoard.Der[1]) && 
					board.canMoveHole(BWPuzzleBoard.Der[1])){
				BWPuzzleBoard newBoard = new BWPuzzleBoard(board);
				newBoard.moveDerDer();
				return newBoard;
			}
			else if(a.equals(BWPuzzleBoard.Der[2]) && 
					board.canMoveHole(BWPuzzleBoard.Der[2])){
				BWPuzzleBoard newBoard = new BWPuzzleBoard(board);
				newBoard.moveDerDerDer();
				return newBoard;
			}
			
			// The Action is not understood or is a NoOp
			// the result will be the current state.
			return s;
		}
	}
}