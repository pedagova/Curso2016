package missionaryCannibal;

import aima.core.agent.Action;
import aima.core.agent.impl.DynamicAction;

public class MCPuzzleBoard {
	// Different kind of moves: "M Missionary C Cannibal"
	public static Action MOVE_MC = new DynamicAction("MC");

	public static Action MOVE_CC = new DynamicAction("CC");

	public static Action MOVE_MM = new DynamicAction("MM");

	public static Action MOVE_C = new DynamicAction("C");

	public static Action MOVE_M = new DynamicAction("M");

	// State defined by: (nº Missionaries, nº Cannibals, edge value boat)
	// "0 Left 1 Right"
	private int[] state;
	
	public MCPuzzleBoard() {
		state = new int[] { 3, 3, 0 };
	}

	public MCPuzzleBoard(MCPuzzleBoard board) {
		this(board.getState());
	}

	public MCPuzzleBoard(int[] is) {
		state = new int[] {is[0], is[1], is[2]};
	}

	public MCPuzzleBoard(int n1, int n2, int n3) {
		state = new int[] {n1,n2,n3};
	}

	public int[] getState() {
		return state;
	}
	
	//State Modifiers
	
	public void moveMC() {
		state[0] += (boatLeft()) ? -1 : +1;
		state[1] += (boatLeft()) ? -1 : +1;
		moveBoat();
	}

	public void moveCC() {
		state[1] += (boatLeft()) ? -2 : +2;
		moveBoat();
	}

	public void moveMM() {
		state[0] += (boatLeft()) ? -2 : +2;
		moveBoat();
	}

	public void moveC() {
		state[1] += (boatLeft()) ? -1 : +1;
		moveBoat();
	}

	public void moveM() {
		state[0] += (boatLeft()) ? -1 : +1;
		moveBoat();
	}
	// END State modifiers
	
	//Move Pre-conditions
	public boolean canMoveBoat(Action where){
		
		if(!(state[0] == 3 || state[0] == 0) && state[0] != state[1]) return false;
		
		//if(state[0] != 0 && state[0] != state[1]) return false;
		
		if(where.equals(MOVE_C)){
			
			if(boatLeft()){
				if(state[1] == 0){return false;}
			}	
			else if(state[1] == 3){return false;}
			
		}else if(where.equals(MOVE_CC)){
			
			if(boatLeft()){
				if(state[1] < 2){return false;}
			}	
			else if(state[1] >= 2){return false;}
			
		}else if(where.equals(MOVE_MC)){
			
			if(boatLeft()){
				if(state[1] == 0 && state[0] == 0){return false;}
			}	
			else if(state[1] == 3 && state[0] == 3){return false;}
			
		}
		else if(where.equals(MOVE_MM)){
			
			if(boatLeft()){
				if(state[0] < 2){return false;}
			}	
			else if(state[0] >= 2){return false;}
			
		}
		else if(where.equals(MOVE_M)){
			
			if(boatLeft()){

				if(state[0] == 0){return false;}
			}	
			else if(state[0] == 3){return false;}
			
		}
		return true;
	}
	//END Move Pre-conditions
	
	//Comparation Overrides
	@Override
	public boolean equals(Object o){
		
		if(this == o) return true;
		
		if(o == null || this.getClass() != o.getClass()) return false; 
		
		for(int i = 0; i < 3; i++){if(state[i] != ((MCPuzzleBoard)o).getElem(i)) return false;}
		
		return true;
	}
	
	@Override
	public int hashCode(){
		
		int result = 0;
		
		for(int i = 0; i < 3; i++){
			result = result * (int)Math.pow(10, i) + state[i];
		}
		return result;
	}
	//END Comparation Overrides
	
	@Override
	public String toString(){return "   M    C   B   \n"
								+ "   " + state[0] + "   " + state[1] + "   " + ((boatLeft()) ? "L" : "R");
	}
	
	//Private Methods
	
	private void moveBoat() {state[2] = boatLeft() ? 1: 0;}
	
	private boolean boatLeft() {return state[2] == 0;}
	
	private int getElem(int i){
		return state[i];		
	}
}

