package blackWhitePuzzle;

import aima.core.search.framework.HeuristicFunction;

public class BWPuzzleHeuristic2 implements HeuristicFunction {

	@Override
	public double h(Object arg0) {
		BWPuzzlePiece[] pieces = ((BWPuzzleBoard) arg0).getState();
		double acc = 0;
		boolean marks[] = new boolean[7];

		double posBlack = 0, posWhite= 0, posHole= 0;
		
		for(int i = 0; i < 7; i++){
			if(pieces[i] == BWPuzzlePiece.HOLE) {
				posHole = i;
				break;
				}
		}
		
		for (int j = 0; j < 3; j++) {
			if (pieces[j] == BWPuzzlePiece.BLACK) {
					marks[j] = true;
					posBlack = j;
				}
			for (int i = 3; i < 7; i++) {
				if (pieces[i] == BWPuzzlePiece.WHITE && !marks[i]) {
					marks[i] = true;
					posWhite = i;
					break;
				}
			}
			acc += Math.abs(posBlack - posWhite) + Math.abs(posHole - posWhite);
		}
		return acc;
	}
}