package test;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.Iterator;
import java.util.Properties;
import java.util.Random;

import aima.core.search.framework.GraphSearch;
import aima.core.search.framework.Problem;
import aima.core.search.framework.Search;
import aima.core.search.framework.SearchAgent;
import aima.core.search.informed.AStarSearch;
import aima.core.search.informed.GreedyBestFirstSearch;
import aima.core.search.uninformed.DepthLimitedSearch;
import aima.core.search.uninformed.IterativeDeepeningSearch;
import missionaryCannibal.MCPuzzleBoard;
import missionaryCannibal.MCPuzzleFunctionFactory;
import missionaryCannibal.MCPuzzleGoalTest;
import missionaryCannibal.MCPuzzleHeuristic1;
import missionaryCannibal.MCPuzzleHeuristic2;

public class MCTest {

	public static void main(String[] args) {
		try {
			System.setOut(new PrintStream(new FileOutputStream("MC_search.txt")));
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		MCPuzzleBoard b;
		for (int i = 0; i < 200; i++) {
			b = generateRandomBoard();
			if (b == null) {
				i--;
			} else {
				System.out.println(mcPuzzleDLSDemo(b));
				System.out.println(mcPuzzleIDLSDemo(b));
				System.out.println(mcPuzzleGreedyBestFirstHeu1Demo(b));
				System.out.println(mcPuzzleGreedyBestFirstHeu2Demo(b));
				System.out.println(mcPuzzleAStarHeu1Demo(b));
				System.out.println(mcPuzzleAStarHeu2Demo(b));
				System.out.println("\n");
			}
		}
	}

	protected static String printInstrumentation(Properties properties) {
		Iterator<Object> keys = properties.keySet().iterator();
		while (keys.hasNext()) {
			String key = (String) keys.next();
			String property = properties.getProperty(key);
			if (key == "pathCost")
				return property;
		}
		return null;

	}

	private static MCPuzzleBoard generateRandomBoard() {
		Random r = new Random();
		int n1 = r.nextInt(4), n2 = r.nextInt(4), n3 = r.nextInt(2);

		if (n1 == 0 && n3 == 0) {
			return new MCPuzzleBoard(n1, n2, n3);
		}
		if (n1 == n2 && n1 != 0) {
			return new MCPuzzleBoard(n1, n2, n3);
		}
		if (n2 == 0 && n3 == 1) {
			return new MCPuzzleBoard(n1, n2, n3);
		}

		return null;
	}

	private static String mcPuzzleGreedyBestFirstHeu1Demo(MCPuzzleBoard b) {
		System.out.print("Voraz--MCPuzzleHeuristic1: ");
		try {
			Problem problem = new Problem(b, MCPuzzleFunctionFactory.getActionsFunction(),
					MCPuzzleFunctionFactory.getResultFunction(), new MCPuzzleGoalTest());
			Search search = new GreedyBestFirstSearch(new GraphSearch(), new MCPuzzleHeuristic1());
			SearchAgent agent = new SearchAgent(problem, search);
			return printInstrumentation(agent.getInstrumentation());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	private static String mcPuzzleGreedyBestFirstHeu2Demo(MCPuzzleBoard b) {
		System.out.print("Voraz--MCPuzzleHeuristic2: ");
		try {
			Problem problem = new Problem(b, MCPuzzleFunctionFactory.getActionsFunction(),
					MCPuzzleFunctionFactory.getResultFunction(), new MCPuzzleGoalTest());
			Search search = new GreedyBestFirstSearch(new GraphSearch(), new MCPuzzleHeuristic2());
			SearchAgent agent = new SearchAgent(problem, search);
			return printInstrumentation(agent.getInstrumentation());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}

	private static String mcPuzzleAStarHeu1Demo(MCPuzzleBoard b) {
		System.out.print("A*--MCPuzzleHeuristic1: ");
		try {
			Problem problem = new Problem(b, MCPuzzleFunctionFactory.getActionsFunction(),
					MCPuzzleFunctionFactory.getResultFunction(), new MCPuzzleGoalTest());
			Search search = new AStarSearch(new GraphSearch(), new MCPuzzleHeuristic1());
			SearchAgent agent = new SearchAgent(problem, search);
			return printInstrumentation(agent.getInstrumentation());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}

	private static String mcPuzzleAStarHeu2Demo(MCPuzzleBoard b) {
		System.out.print("A*--MCPuzzleHeuristic2: ");
		try {
			Problem problem = new Problem(b, MCPuzzleFunctionFactory.getActionsFunction(),
					MCPuzzleFunctionFactory.getResultFunction(), new MCPuzzleGoalTest());
			Search search = new AStarSearch(new GraphSearch(), new MCPuzzleHeuristic2());
			SearchAgent agent = new SearchAgent(problem, search);
			return printInstrumentation(agent.getInstrumentation());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}

	private static String mcPuzzleDLSDemo(MCPuzzleBoard b) {
		System.out.print("Recursive DLS: ");
		try {
			Problem problem = new Problem(b, MCPuzzleFunctionFactory.getActionsFunction(),
					MCPuzzleFunctionFactory.getResultFunction(), new MCPuzzleGoalTest());
			Search search = new DepthLimitedSearch(12);
			SearchAgent agent = new SearchAgent(problem, search);
			return printInstrumentation(agent.getInstrumentation());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}

	private static String mcPuzzleIDLSDemo(MCPuzzleBoard b) {
		System.out.print("Iterative DLS: ");
		try {
			Problem problem = new Problem(b, MCPuzzleFunctionFactory.getActionsFunction(),
					MCPuzzleFunctionFactory.getResultFunction(), new MCPuzzleGoalTest());
			Search search = new IterativeDeepeningSearch();
			SearchAgent agent = new SearchAgent(problem, search);
			return printInstrumentation(agent.getInstrumentation());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}

}
