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
import blackWhitePuzzle.BWPuzzleBoard;
import blackWhitePuzzle.BWPuzzleFunctionFactory;
import blackWhitePuzzle.BWPuzzleGoalTest;
import blackWhitePuzzle.BWPuzzleHeuristic1;
import blackWhitePuzzle.BWPuzzleHeuristic2;
import blackWhitePuzzle.BWPuzzleMTH;
import blackWhitePuzzle.BWPuzzlePiece;
import blackWhitePuzzle.BWPuzzleStepCostFunction;

public class BWTest {

	public static void main(String[] args) {
		try {
			System.setOut(new PrintStream(new FileOutputStream("BW_search.txt")));
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		BWPuzzleBoard b;
		for(int i=0; i<200;i++){
			b = generateRandomBoard();
			System.out.println(bwPuzzleDLSDemo(b));
			System.out.println(bwPuzzleIDLSDemo(b));
			System.out.println(bwPuzzleGreedyBestFirstHeu1Demo(b));
			System.out.println(bwPuzzleGreedyBestFirstHeu2Demo(b));
			System.out.println(bwPuzzleAStarHeu1Demo(b));
			System.out.println(bwPuzzleAStarHeu2Demo(b));
			
			System.out.println(bwPuzzleGreedyBWPuzzleMTHDemo(b));
			System.out.println(bwPuzzleAStarBWPuzzleMTHDemo(b));
			System.out.println("\n");

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

	private static BWPuzzleBoard generateRandomBoard() {
		Random r = new Random();
		int n = r.nextInt(6);

		BWPuzzlePiece[] result = new BWPuzzlePiece[7];
		result[n] = BWPuzzlePiece.HOLE;
		for (int i = 0; i < 3; i++) {
			n = r.nextInt(6);
			if (result[n] != BWPuzzlePiece.BLACK && result[n] != BWPuzzlePiece.HOLE) {
				result[n] = BWPuzzlePiece.BLACK;
			} else {
				i--;
			}
		}

		for (int i = 0; i < 7; i++) {
			if (result[i] != BWPuzzlePiece.BLACK && result[i] != BWPuzzlePiece.HOLE) {
				result[i] = BWPuzzlePiece.WHITE;
			}
		}

		return new BWPuzzleBoard(result);
	}

	private static String bwPuzzleGreedyBestFirstHeu1Demo(BWPuzzleBoard b) {
		System.out.print("Voraz--BWPuzzleHeuristic1: ");
		try {
			Problem problem = new Problem(b, BWPuzzleFunctionFactory.getActionsFunction(),
					BWPuzzleFunctionFactory.getResultFunction(), new BWPuzzleGoalTest());
			Search search = new GreedyBestFirstSearch(new GraphSearch(), new BWPuzzleHeuristic1());
			SearchAgent agent = new SearchAgent(problem, search);
			return printInstrumentation(agent.getInstrumentation());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	private static String bwPuzzleGreedyBestFirstHeu2Demo(BWPuzzleBoard b) {
		System.out.print("Voraz--BWPuzzleHeuristic2: ");
		try {
			Problem problem = new Problem(b, BWPuzzleFunctionFactory.getActionsFunction(),
					BWPuzzleFunctionFactory.getResultFunction(), new BWPuzzleGoalTest());
			Search search = new GreedyBestFirstSearch(new GraphSearch(), new BWPuzzleHeuristic2());
			SearchAgent agent = new SearchAgent(problem, search);
			return printInstrumentation(agent.getInstrumentation());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}

	private static String bwPuzzleAStarHeu1Demo(BWPuzzleBoard b) {
		System.out.print("A*--BWPuzzleHeuristic1: ");
		try {
			Problem problem = new Problem(b, BWPuzzleFunctionFactory.getActionsFunction(),
					BWPuzzleFunctionFactory.getResultFunction(), new BWPuzzleGoalTest());
			Search search = new AStarSearch(new GraphSearch(), new BWPuzzleHeuristic1());
			SearchAgent agent = new SearchAgent(problem, search);
			return printInstrumentation(agent.getInstrumentation());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}

	private static String bwPuzzleAStarHeu2Demo(BWPuzzleBoard b) {
		System.out.print("A*--BWPuzzleHeuristic2: ");
		try {
			Problem problem = new Problem(b, BWPuzzleFunctionFactory.getActionsFunction(),
					BWPuzzleFunctionFactory.getResultFunction(), new BWPuzzleGoalTest());
			Search search = new AStarSearch(new GraphSearch(), new BWPuzzleHeuristic2());
			SearchAgent agent = new SearchAgent(problem, search);
			return printInstrumentation(agent.getInstrumentation());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}

	private static String bwPuzzleDLSDemo(BWPuzzleBoard b) {
		System.out.print("Recursive DLS: ");
		try {
			Problem problem = new Problem(b, BWPuzzleFunctionFactory.getActionsFunction(),
					BWPuzzleFunctionFactory.getResultFunction(), new BWPuzzleGoalTest(),
					new BWPuzzleStepCostFunction());
			Search search = new DepthLimitedSearch(12);
			SearchAgent agent = new SearchAgent(problem, search);
			return printInstrumentation(agent.getInstrumentation());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}

	private static String bwPuzzleIDLSDemo(BWPuzzleBoard b) {
		System.out.print("Iterative DLS: ");
		try {
			Problem problem = new Problem(b, BWPuzzleFunctionFactory.getActionsFunction(),
					BWPuzzleFunctionFactory.getResultFunction(), new BWPuzzleGoalTest(),
					new BWPuzzleStepCostFunction());
			Search search = new IterativeDeepeningSearch();
			SearchAgent agent = new SearchAgent(problem, search);
			return printInstrumentation(agent.getInstrumentation());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}
	
	private static String bwPuzzleGreedyBWPuzzleMTHDemo(BWPuzzleBoard b) {
		System.out.print("Voraz--BWPuzzleMTH: ");
		try {
			Problem problem = new Problem(b, BWPuzzleFunctionFactory.getActionsFunction(),
					BWPuzzleFunctionFactory.getResultFunction(), new BWPuzzleGoalTest());
			Search search = new GreedyBestFirstSearch(new GraphSearch(), new BWPuzzleMTH());
			SearchAgent agent = new SearchAgent(problem, search);
			return printInstrumentation(agent.getInstrumentation());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}
	
	private static String bwPuzzleAStarBWPuzzleMTHDemo(BWPuzzleBoard b) {
		System.out.print("A*--BWPuzzleMTH: ");
		try {
			Problem problem = new Problem(b, BWPuzzleFunctionFactory.getActionsFunction(),
					BWPuzzleFunctionFactory.getResultFunction(), new BWPuzzleGoalTest());
			Search search = new AStarSearch(new GraphSearch(), new BWPuzzleMTH());
			SearchAgent agent = new SearchAgent(problem, search);
			return printInstrumentation(agent.getInstrumentation());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}
}
