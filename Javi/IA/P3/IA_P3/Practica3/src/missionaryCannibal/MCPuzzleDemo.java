package missionaryCannibal;

import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import aima.core.agent.Action;

public class MCPuzzleDemo {
	
	static MCPuzzleBoard basicBoard = new MCPuzzleBoard();

	protected static void printInstrumentation(Properties properties) {
		Iterator<Object> keys = properties.keySet().iterator();
		while (keys.hasNext()) {
			String key = (String) keys.next();
			String property = properties.getProperty(key);
			System.out.println(key + " : " + property);
		}

	}

	protected static void printActions(List<Action> actions) {
		for (int i = 0; i < actions.size(); i++) {
			String action = actions.get(i).toString();
			System.out.println(action);
		}
	}

}
