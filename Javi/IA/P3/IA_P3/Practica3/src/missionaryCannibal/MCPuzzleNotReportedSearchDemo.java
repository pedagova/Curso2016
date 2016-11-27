package missionaryCannibal;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

import aima.core.search.framework.Problem;
import aima.core.search.framework.Search;
import aima.core.search.framework.SearchAgent;
import aima.core.search.uninformed.DepthLimitedSearch;
import aima.core.search.uninformed.IterativeDeepeningSearch;

public class MCPuzzleNotReportedSearchDemo extends MCPuzzleDemo{

	public static void main(String[] args) throws FileNotFoundException {
		System.setOut(new PrintStream(new FileOutputStream("MC_not_reported_salida_normal.txt")));
		System.setErr(new PrintStream(new FileOutputStream("MC_not_reported_salida_error.txt")));
		missionaryCannibalDSLDemo();
		missionaryCannibalIDLSDemo();
	}
	
	private static void missionaryCannibalDSLDemo() {
		System.out.println("\nMissionary Cannibal recursive DLS (3) -->");
		try {
			Problem problem = new Problem(basicBoard, MCPuzzleFunctionFactory
					.getActionsFunction(), MCPuzzleFunctionFactory
					.getResultFunction(), new MCPuzzleGoalTest());
			Search search = new DepthLimitedSearch(17);
			SearchAgent agent = new SearchAgent(problem, search);
			printActions(agent.getActions());
			printInstrumentation(agent.getInstrumentation());
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private static void missionaryCannibalIDLSDemo() {
		System.out.println("\nMissionary Cannibal Iterative DLS -->");
		try {
			Problem problem = new Problem(basicBoard, MCPuzzleFunctionFactory
					.getActionsFunction(), MCPuzzleFunctionFactory
					.getResultFunction(), new MCPuzzleGoalTest());
			Search search = new IterativeDeepeningSearch();
			SearchAgent agent = new SearchAgent(problem, search);
			printActions(agent.getActions());
			printInstrumentation(agent.getInstrumentation());
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
