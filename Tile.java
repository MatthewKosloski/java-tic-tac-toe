//********************************************************************
// Tile.java
//
// A class that represents an individual tile on the board.
//********************************************************************

public class Tile {

	private final char EMPTY;
	private int index;
	private char symbol;

	public Tile(char emptySymbol) {
		// initially set the tile to empty state
		EMPTY = emptySymbol;
		setSymbol(emptySymbol);
	}

	// Set the tile's symbol
	public void setSymbol(char symbol) {
		this.symbol = symbol;
	}

	// Get the tile's symbol
	public char getSymbol() {
		return symbol;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public int getIndex() {
		return index;
	}

	// Check if the tile is empty
	public boolean isEmpty() {
		return symbol == EMPTY;
	}

	public String toString() {
		return symbol + "";
	}

}