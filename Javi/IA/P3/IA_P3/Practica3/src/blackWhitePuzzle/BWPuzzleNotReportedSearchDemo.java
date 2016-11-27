package blackWhitePuzzle;

import aima.core.search.framework.Problem;
import aima.core.search.framework.Search;
import aima.core.search.framework.SearchAgent;
import aima.core.search.uninformed.DepthLimitedSearch;
import aima.core.search.uninformed.IterativeDeepeningSearch;

public class BWPuzzleNotReportedSearchDemo extends BWPuzzleDemo{
	public static void main(String[] args) {
		bwPuzzleDLSDemo();
		bwPuzzleIDLSDemo();
	}

	private static void bwPuzzleDLSDemo(){
		System.out.println("\nBlackWhitePuzzleDemo recursive DLS (9) -->");
		try {
			Problem problem = new Problem(BWPuzzleDemo.board1, BWPuzzleFunctionFactory.getActionsFunction(),
					BWPuzzleFunctionFactory.getResultFunction(), new BWPuzzleGoalTest(), new BWPuzzleStepCostFunction());
			Search search = new DepthLimitedSearch(12);
			SearchAgent agent = new SearchAgent(problem, search);
			printActions(agent.getActions());
			printInstrumentation(agent.getInstrumentation());
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private static void bwPuzzleIDLSDemo() {
		System.out.println("\nBlackWhitePuzzleDemo Iterative DLS -->");
		try {
			Problem problem = new Problem(BWPuzzleDemo.board1, BWPuzzleFunctionFactory.getActionsFunction(),
					BWPuzzleFunctionFactory.getResultFunction(), new BWPuzzleGoalTest(), new BWPuzzleStepCostFunction());
			Search search = new IterativeDeepeningSearch();
			SearchAgent agent = new SearchAgent(problem, search);
			printActions(agent.getActions());
			printInstrumentation(agent.getInstrumentation());
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
