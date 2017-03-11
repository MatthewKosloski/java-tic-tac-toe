//********************************************************************
// Player.java
//
// A class representing an individual player.  It should be
// instantiated with two arguments: the board object and a symbol.
//********************************************************************

public class Player {

	private Board board;
	private char symbol;

	public Player(Board board, char symbol) {
		this.board = board;
		this.symbol = symbol;
	}

	public placeSymbol(int index) {
		board.getTile(index).setSymbol(symbol);
	}

	public String toString() {
		return symbol + "";
	}

}