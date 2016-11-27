package blackWhitePuzzle;

import aima.core.search.framework.HeuristicFunction;

public class BWPuzzleMTH implements HeuristicFunction{

	@Override
	public double h(Object arg0) {
		BWPuzzlePiece[] p = ((BWPuzzleBoard) arg0).getState();
		int acc = 0;
		for(int i = 0; i < 3; i++){
			if(p[i] != BWPuzzlePiece.WHITE){
				acc++;
			}
		}
		if(p[3] != BWPuzzlePiece.HOLE){
			acc++;
		}
		for(int i = 4; i < 7; i++){
			if(p[i] != BWPuzzlePiece.BLACK){
				acc++;
			}
		}
		return acc;
	}

}
