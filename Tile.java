//********************************************************************
// Tile.java
//
// A class that represents an individual tile on the grid.
//********************************************************************

public class Tile {

	// instance data
	private char symbol;

	// Constructor: Sets the tile's symbol based off a provided character parameter (X or O)
	public Tile(char symbol) {
		setSymbol(symbol);
	}

	// Set the tile's symbol
	public void setSymbol(char symbol) {
		this.symbol = symbol;
	}

	// Get the tile's symbol
	public char getSymbol() {
		return symbol;
	}

	// Check if the tile is empty
	public boolean isEmpty() {
		return symbol == '-';
	}

	public String toString() {
		return symbol + " ";
	}

}