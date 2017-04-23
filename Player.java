//********************************************************************
// Player.java
//
// --
//********************************************************************

public class Player {
	protected Board board;
	protected char symbol;

	public Player(Board board, char symbol) {
		this.board = board;
		this.symbol = symbol;
	}

	public char getSymbol() {
		return symbol;
	}

	public void setSymbol(char symbol) {
		this.symbol = Character.toUpperCase(symbol);
	}

	public void placeSymbol(int index) {
		Tile t = board.getTile(index);
		t.setSymbol(symbol);
	}

	public String toString() {
		return symbol + "";
	}
}