package missionaryCannibal;

import aima.core.agent.Action;
import aima.core.agent.impl.DynamicAction;

public class MissionaryCannibalBoard {
	// Different kind of moves: "M Missionary C Cannibal"
	public static Action MOVE_MC = new DynamicAction("MC");

	public static Action MOVE_CC = new DynamicAction("CC");

	public static Action MOVE_MM = new DynamicAction("MM");

	public static Action MOVE_C = new DynamicAction("C");

	public static Action MOVE_M = new DynamicAction("M");

	// State defined by: (nº Missionaries, nº Cannibals, edge value boat)
	// "0 Left 1 Right"
	private int[] state;

	public MissionaryCannibalBoard() {
		state = new int[] { 3, 3, 0 };
	}

	public int[] getState() {
		return state;
	}
	
	//State Modifiers
	
	public void moveMC() {
		moveBoat();
		state[0] = (boatLeft()) ? state[0] - 1 : state[0] + 1;
		state[1] = (boatLeft()) ? state[1] - 1 : state[1] + 1;
	}

	public void moveCC() {
		moveBoat();
		state[1] = (boatLeft()) ? state[1] - 2 : state[1] + 2;
	}

	public void moveMM() {
		moveBoat();
		state[0] = (boatLeft()) ? state[0] - 2 : state[0] + 2;
	}

	public void moveC() {
		moveBoat();
		state[1] = (boatLeft()) ? state[1] - 1 : state[1] + 1;
	}

	public void moveM() {
		moveBoat();
		state[0] = (boatLeft()) ? state[0] - 1 : state[0] + 1;
	}
	// END State modifiers
	
	//Move Pre-conditions
	public boolean canMoveBoat(Action where){
		
		if(where.equals(MOVE_C)){
			
			if(boatLeft()){
				if(state[1] == 0){return false;}
			}	
			else if(state[1] == 3){return false;}
			
		}else if(where.equals(MOVE_CC)){
			
			if(boatLeft()){
				if(state[1] > 1){return false;}
			}	
			else if(state[1] < 3){return false;}
			
		}else if(where.equals(MOVE_MC)){
			
			if(boatLeft()){
				if(state[1] == 0 || state[0] == 0){return false;}
			}	
			else if(state[1] == 3 || state[0] == 3){return false;}
			
		}
		else if(where.equals(MOVE_MM)){
			
			if(boatLeft()){
				if(state[0] > 1){return false;}
			}	
			else if(state[0] < 3){return false;}
			
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
		
		for(int i = 0; i < 3; i++){if(state[i] != ((MissionaryCannibalBoard)o).getElem(i)) return false;}
		
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
								+ "   " + state[2] + "   " + state[1] + "   " + ((boatLeft()) ? "L" : "R");
	}
	
	//Private Methods
	
	private void moveBoat() {state[2] = boatLeft() ? 1: 0;}
	
	private boolean boatLeft() {return state[2] == 0;}
	
	private int getElem(int i){
		return state[i];		
	}
}

