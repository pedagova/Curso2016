package blackWhitePuzzle;

/*import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
*/
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import aima.core.agent.Action;
import aima.core.search.framework.GraphSearch;
import aima.core.search.framework.Problem;
import aima.core.search.framework.Search;
import aima.core.search.framework.SearchAgent;
import aima.core.search.informed.AStarSearch;
import aima.core.search.uninformed.DepthLimitedSearch;
import aima.core.search.uninformed.IterativeDeepeningSearch;

public class BWPuzzleDemo {
	static BWPuzzleBoard board1 = new BWPuzzleBoard(
			new Piece[] { Piece.BLACK, Piece.BLACK, Piece.BLACK, Piece.HOLE, Piece.WHITE, Piece.WHITE, Piece.WHITE });;
	
	/*static BWPuzzleBoard board1 = new BWPuzzleBoard(
			new Piece[] { Piece.WHITE, Piece.BLACK, Piece.WHITE, Piece.HOLE, Piece.WHITE, Piece.BLACK, Piece.BLACK });

	/*static BWPuzzleBoard extreme = new BWPuzzleBoard(
			new Piece[] { Piece.WHITE, Piece.BLACK, Piece.HOLE, Piece.BLACK, Piece.WHITE, Piece.WHITE, Piece.BLACK });
	*/
	public static void main(String[] args) {
		try {
			System.setOut(new PrintStream(new FileOutputStream("salida_normal.txt")));
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		//System.setErr(new PrintStream(new FileOutputStream("salida_error.txt")));
		bwPuzzleDLSDemo();
		//bwPuzzleIDLSDemo();
		//bwPuzzleGreedyBestFirstDemo();
		//wPuzzleGreedyBestFirstManhattanDemo();
		bwPuzzleAStarDemo();
		//bwPuzzleAStarManhattanDemo();
		//bwPuzzleSimulatedAnnealingDemo();
	}

	private static void bwPuzzleDLSDemo(){
		
		
		
		System.out.println("\nBlackWhitePuzzleDemo recursive DLS (9) -->");
		try {
			Problem problem = new Problem(board1, BWPuzzleFunctionFactory.getActionsFunction(),
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
			Piece w = Piece.WHITE, b = Piece.BLACK, h = Piece.HOLE;
			Piece[] pi = {b,w,b,w,b,h,w};
			BWPuzzleBoard p = new BWPuzzleBoard(pi);			
			Problem problem = new Problem(p, BWPuzzleFunctionFactory.getActionsFunction(),
					BWPuzzleFunctionFactory.getResultFunction(), new BWPuzzleGoalTest(), new BWPuzzleStepCostFunction());
			Search search = new IterativeDeepeningSearch();
			SearchAgent agent = new SearchAgent(problem, search);
			printActions(agent.getActions());
			printInstrumentation(agent.getInstrumentation());
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/*private static void bwPuzzleGreedyBestFirstDemo() {
		System.out.println("\nBlackWhitePuzzleDemo Greedy Best First Search (MisplacedTileHeursitic)-->");
		try {
			Problem problem = new Problem(boardWithThreeMoveSolution, BWPuzzleFunctionFactory.getActionsFunction(),
					BWPuzzleFunctionFactory.getResultFunction(), new BWPuzzleGoalTest());
			Search search = new GreedyBestFirstSearch(new GraphSearch(), new MisplacedTilleHeuristicFunction());
			SearchAgent agent = new SearchAgent(problem, search);
			printActions(agent.getActions());
			printInstrumentation(agent.getInstrumentation());
		} catch (Exception e) {
			e.printStackTrace();
		}

	}*/

	/*private static void bwPuzzleGreedyBestFirstManhattanDemo() {
		System.out.println("\nBlackWhitePuzzleDemo Greedy Best First Search (ManhattanHeursitic)-->");
		try {
			Problem problem = new Problem(boardWithThreeMoveSolution, BWPuzzleFunctionFactory.getActionsFunction(),
					BWPuzzleFunctionFactory.getResultFunction(), new BWPuzzleGoalTest());
			Search search = new GreedyBestFirstSearch(new GraphSearch(), new ManhattanHeuristicFunction());
			SearchAgent agent = new SearchAgent(problem, search);
			printActions(agent.getActions());
			printInstrumentation(agent.getInstrumentation());
		} catch (Exception e) {
			e.printStackTrace();
		}

	}*/

	private static void bwPuzzleAStarDemo() {
		System.out.println("\nBlackWhitePuzzleDemo AStar Search (MisplacedTileHeursitic)-->");
		try {
			
			Piece w = Piece.WHITE, b = Piece.BLACK, h = Piece.HOLE;
			Piece[] pi = {b,w,b,w,b,h,w};
			BWPuzzleBoard p = new BWPuzzleBoard(pi);
			
			Problem problem = new Problem(p, BWPuzzleFunctionFactory.getActionsFunction(),
					BWPuzzleFunctionFactory.getResultFunction(), new BWPuzzleGoalTest());
			Search search = new AStarSearch(new GraphSearch(), new SuperHeroeHoleM());
			SearchAgent agent = new SearchAgent(problem, search);
			printActions(agent.getActions());
			printInstrumentation(agent.getInstrumentation());
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/*private static void bwPuzzleSimulatedAnnealingDemo() {
		System.out.println("\nBlackWhitePuzzleDemo Simulated Annealing  Search -->");
		try {
			Problem problem = new Problem(random1, BWPuzzleFunctionFactory.getActionsFunction(),
					BWPuzzleFunctionFactory.getResultFunction(), new BWPuzzleGoalTest());
			SimulatedAnnealingSearch search = new SimulatedAnnealingSearch(new ManhattanHeuristicFunction());
			SearchAgent agent = new SearchAgent(problem, search);
			printActions(agent.getActions());
			System.out.println("Search Outcome=" + search.getOutcome());
			System.out.println("Final State=\n" + search.getLastSearchState());
			printInstrumentation(agent.getInstrumentation());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}*/

	/*private static void bwPuzzleAStarManhattanDemo() {
		System.out.println("\nBlackWhitePuzzleDemo AStar Search (ManhattanHeursitic)-->");
		try {
			Problem problem = new Problem(random1, BWPuzzleFunctionFactory.getActionsFunction(),
					BWPuzzleFunctionFactory.getResultFunction(), new BWPuzzleGoalTest());
			Search search = new AStarSearch(new GraphSearch(), new ManhattanHeuristicFunction());
			SearchAgent agent = new SearchAgent(problem, search);
			printActions(agent.getActions());
			printInstrumentation(agent.getInstrumentation());
		} catch (Exception e) {
			e.printStackTrace();
		}

	}*/

	private static void printInstrumentation(Properties properties) {
		Iterator<Object> keys = properties.keySet().iterator();
		while (keys.hasNext()) {
			String key = (String) keys.next();
			String property = properties.getProperty(key);
			System.out.println(key + " : " + property);
		}

	}

	private static void printActions(List<Action> actions) {
		for (int i = 0; i < actions.size(); i++) {
			String action = actions.get(i).toString();
			System.out.println(action);
		}
	}

}
