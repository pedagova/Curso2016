package blackWhitePuzzle;

public enum Piece {
	WHITE("W", 2),
	BLACK("B", 1),
	HOLE("H", 0);
	
	private final String name;
	private final int value;
	
	Piece(String name, int value){
		this.name=name;
		this.value = value;
	}
	
	@Override
	public String toString(){
		return this.name;
	}
	
	public int getValue(){
		return this.value;
	}
	
}
