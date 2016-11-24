package blackWhitePuzzle;

import aima.core.search.framework.HeuristicFunction;

public class HeuManh implements HeuristicFunction {

	@Override
	public double h(Object arg0) {
		Piece[] pieces = ((BWPuzzleBoard)arg0).getState();
		double acc = 0;
		boolean marks[] = new boolean[7];
		for (int i = 0; i < 3; i++) {
			if (pieces[i] == Piece.BLACK) {
				for (int j = 3; j < 7; j++) {
					if (pieces[j] == Piece.WHITE && !marks[j]) {
						acc += (j - i > 2) ? 2 * (j - i) : 1;
						marks[j] = true;
						break;
					}
				}
			}
		}
		return acc;
	}
}
