package missionaryCannibal;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

import aima.core.search.framework.GraphSearch;
import aima.core.search.framework.Problem;
import aima.core.search.framework.Search;
import aima.core.search.framework.SearchAgent;
import aima.core.search.informed.AStarSearch;
import aima.core.search.informed.GreedyBestFirstSearch;
import aima.core.search.local.SimulatedAnnealingSearch;

public class MCPuzzleReportedSearchDemo extends MCPuzzleDemo{

	public static void main(String[] args) throws FileNotFoundException {
		System.setOut(new PrintStream(new FileOutputStream("MC_reported_salida_normal.txt")));
		System.setErr(new PrintStream(new FileOutputStream("MC_reported_salida_error.txt")));
		
		missionaryCannibalGreedyBestFirstHeu1Demo();
		missionaryCannibalGreedyBestFirstHeu2Demo();
		missionaryCannibalAStarHeu1Demo();
		missionaryCannibalAStarHeu2Demo();
		missionaryCannibalSimulatedAnnealingDemo();
	}
	
	private static void missionaryCannibalGreedyBestFirstHeu1Demo() {
	System.out
			.println("\nMissionary Cannibal Greedy Best First Search (MCPuzzleHeuristic1)-->");
	try {
		Problem problem = new Problem(basicBoard, MCPuzzleFunctionFactory
				.getActionsFunction(), MCPuzzleFunctionFactory
				.getResultFunction(), new MCPuzzleGoalTest());
		Search search = new GreedyBestFirstSearch(new GraphSearch(),
				new MCPuzzleHeuristic1());
		SearchAgent agent = new SearchAgent(problem, search);
		printActions(agent.getActions());
		printInstrumentation(agent.getInstrumentation());
	} catch (Exception e) {
		e.printStackTrace();
	}

}

private static void missionaryCannibalGreedyBestFirstHeu2Demo() {
	System.out
			.println("\nMissionary Cannibal Greedy Best First Search (MCPuzzleHeuristic2)-->");
	try {
		Problem problem = new Problem(basicBoard, MCPuzzleFunctionFactory
				.getActionsFunction(), MCPuzzleFunctionFactory
				.getResultFunction(), new MCPuzzleGoalTest());
		Search search = new GreedyBestFirstSearch(new GraphSearch(),
				new MCPuzzleHeuristic2());
		SearchAgent agent = new SearchAgent(problem, search);
		printActions(agent.getActions());
		printInstrumentation(agent.getInstrumentation());
	} catch (Exception e) {
		e.printStackTrace();
	}

}

private static void missionaryCannibalAStarHeu1Demo() {
	System.out
			.println("\nMissionary Cannibal AStar Search (MCPuzzleHeuristic1)-->");
	try {
		Problem problem = new Problem(basicBoard, MCPuzzleFunctionFactory
				.getActionsFunction(), MCPuzzleFunctionFactory
				.getResultFunction(), new MCPuzzleGoalTest());
		Search search = new AStarSearch(new GraphSearch(),
				new MCPuzzleHeuristic1());
		SearchAgent agent = new SearchAgent(problem, search);
		printActions(agent.getActions());
		printInstrumentation(agent.getInstrumentation());
	} catch (Exception e) {
		e.printStackTrace();
	}

}

private static void missionaryCannibalSimulatedAnnealingDemo() {
	System.out.println("\nMissionary Cannibal Simulated Annealing  Search -->");
	try {
		Problem problem = new Problem(basicBoard, MCPuzzleFunctionFactory
				.getActionsFunction(), MCPuzzleFunctionFactory
				.getResultFunction(), new MCPuzzleGoalTest());
		SimulatedAnnealingSearch search = new SimulatedAnnealingSearch(
				new MCPuzzleHeuristic1());
		SearchAgent agent = new SearchAgent(problem, search);
		printActions(agent.getActions());
		System.out.println("Search Outcome=" + search.getOutcome());
		System.out.println("Final State=\n" + search.getLastSearchState());
		printInstrumentation(agent.getInstrumentation());
	} catch (Exception e) {
		e.printStackTrace();
	}
}

private static void missionaryCannibalAStarHeu2Demo() {
	System.out
			.println("\nMissionary Cannibal AStar Search (MCPuzzleHeuristic2)-->");
	try {
		Problem problem = new Problem(basicBoard, MCPuzzleFunctionFactory
				.getActionsFunction(), MCPuzzleFunctionFactory
				.getResultFunction(), new MCPuzzleGoalTest());
		Search search = new AStarSearch(new GraphSearch(),
				new MCPuzzleHeuristic2());
		SearchAgent agent = new SearchAgent(problem, search);
		printActions(agent.getActions());
		printInstrumentation(agent.getInstrumentation());
	} catch (Exception e) {
		e.printStackTrace();
	}

}
}
