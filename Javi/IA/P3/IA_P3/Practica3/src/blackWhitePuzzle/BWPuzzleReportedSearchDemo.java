package blackWhitePuzzle;

import aima.core.search.framework.GraphSearch;
import aima.core.search.framework.Problem;
import aima.core.search.framework.Search;
import aima.core.search.framework.SearchAgent;
import aima.core.search.informed.AStarSearch;
import aima.core.search.informed.GreedyBestFirstSearch;
import aima.core.search.local.SimulatedAnnealingSearch;

public class BWPuzzleReportedSearchDemo extends BWPuzzleDemo {
	public static void main(String[] args) {
		bwPuzzleGreedyHeu1Demo();
		bwPuzzleGreedyHeu2Demo();
		bwPuzzleAStarHeu1Demo();
		bwPuzzleAStarHeu2Demo();
		bwPuzzleSimulatedAnnealingDemo();
	}

	private static void bwPuzzleGreedyHeu1Demo() {
		System.out.println("\nBlackWhitePuzzleDemo Greedy Best First Search (BWHeuristic1)-->");
		try {
			Problem problem = new Problem(BWPuzzleDemo.board1, BWPuzzleFunctionFactory.getActionsFunction(),
					BWPuzzleFunctionFactory.getResultFunction(), new BWPuzzleGoalTest());
			Search search = new GreedyBestFirstSearch(new GraphSearch(), new BWPuzzleHeuristic1());
			SearchAgent agent = new SearchAgent(problem, search);
			printActions(agent.getActions());
			printInstrumentation(agent.getInstrumentation());
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private static void bwPuzzleGreedyHeu2Demo() {
		System.out.println("\nBlackWhitePuzzleDemo Greedy Best First Search (BWHeuristic2)-->");
		try {
			Problem problem = new Problem(BWPuzzleDemo.board1, BWPuzzleFunctionFactory.getActionsFunction(),
					BWPuzzleFunctionFactory.getResultFunction(), new BWPuzzleGoalTest());
			Search search = new GreedyBestFirstSearch(new GraphSearch(), new BWPuzzleHeuristic2());
			SearchAgent agent = new SearchAgent(problem, search);
			printActions(agent.getActions());
			printInstrumentation(agent.getInstrumentation());
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private static void bwPuzzleAStarHeu1Demo() {
		System.out.println("\nBlackWhitePuzzleDemo AStar Search (BWHeuristic1)-->");
		try {
			Problem problem = new Problem(BWPuzzleDemo.board1, BWPuzzleFunctionFactory.getActionsFunction(),
					BWPuzzleFunctionFactory.getResultFunction(), new BWPuzzleGoalTest());
			Search search = new AStarSearch(new GraphSearch(), new BWPuzzleHeuristic1());
			SearchAgent agent = new SearchAgent(problem, search);
			printActions(agent.getActions());
			printInstrumentation(agent.getInstrumentation());
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private static void bwPuzzleSimulatedAnnealingDemo() {
		System.out.println("\nBlackWhitePuzzleDemo Simulated Annealing  Search -->");
		try {
			Problem problem = new Problem(BWPuzzleDemo.board1, BWPuzzleFunctionFactory.getActionsFunction(),
					BWPuzzleFunctionFactory.getResultFunction(), new BWPuzzleGoalTest());
			SimulatedAnnealingSearch search = new SimulatedAnnealingSearch(new BWPuzzleHeuristic2());
			SearchAgent agent = new SearchAgent(problem, search);
			printActions(agent.getActions());
			System.out.println("Search Outcome=" + search.getOutcome());
			System.out.println("Final State=\n" + search.getLastSearchState());
			printInstrumentation(agent.getInstrumentation());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void bwPuzzleAStarHeu2Demo() {
		System.out.println("\nBlackWhitePuzzleDemo AStar Search (BWHeuristic2)-->");
		try {
			Problem problem = new Problem(BWPuzzleDemo.board1, BWPuzzleFunctionFactory.getActionsFunction(),
					BWPuzzleFunctionFactory.getResultFunction(), new BWPuzzleGoalTest());
			Search search = new AStarSearch(new GraphSearch(), new BWPuzzleHeuristic2());
			SearchAgent agent = new SearchAgent(problem, search);
			printActions(agent.getActions());
			printInstrumentation(agent.getInstrumentation());
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
