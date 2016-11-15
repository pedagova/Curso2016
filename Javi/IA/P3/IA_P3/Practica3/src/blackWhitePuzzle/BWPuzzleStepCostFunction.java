package blackWhitePuzzle;

import aima.core.agent.Action;
import aima.core.search.framework.StepCostFunction;

public class BWPuzzleStepCostFunction implements StepCostFunction {

	@Override
	public double c(Object arg0, Action arg1, Object arg2) {
		
		if(arg1.equals(BWPuzzleBoard.Iz[0])){
			return 1;
		}else if(arg1.equals(BWPuzzleBoard.Iz[1])){
			return 1;
		}else if(arg1.equals(BWPuzzleBoard.Iz[2])){
			return 2;
		}else if(arg1.equals(BWPuzzleBoard.Der[0])){
			return 1;
		}else if(arg1.equals(BWPuzzleBoard.Der[1])){
			return 1;
		}else if(arg1.equals(BWPuzzleBoard.Der[2])){
			return 2;
		}
		return 0;
	}

}
