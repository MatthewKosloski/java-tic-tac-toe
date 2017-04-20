//********************************************************************
// Board.java
//
// A class that encapsulates the tiles.
// This is an aggregate object, for it contains references to 
// Tiles as instance data.
//********************************************************************

import java.util.ArrayList;

public class Board {

	private final int BOARD_SIZE, BOARD_TILE_QUANTITY;
	private Tile[][] tiles;

	// Constructor: Initially populates the board with empty tiles
	public Board(int boardSize) {
		BOARD_SIZE = boardSize;
		BOARD_TILE_QUANTITY = (int) Math.pow(BOARD_SIZE, 2);
		tiles = new Tile[BOARD_SIZE][BOARD_SIZE];
		populate();
	}

	// Populates the board with empty tiles
	public void populate() {
		for(int rows = 0; rows < BOARD_SIZE; rows++) {
			for(int cols = 0; cols < BOARD_SIZE; cols++) {
				tiles[rows][cols] = new Tile('-');
			}
		}
	}

	public Tile[][] getAllTiles() {
		return tiles;
	}

	/*
		Returns a certain tile from a provided index value 
		between 0-(BOARD_TILE_QUANTITY - 1).

		Example:
		If a board has a size of 3 (3 rows and 3 columns),
		the index value should be between 0-8.
	*/
	public Tile getTile(int index) {
		int row = (int) Math.floor(index / BOARD_SIZE);
		int col = (int) Math.floor(index % BOARD_SIZE);
		return tiles[row][col];

	}

	public boolean isTileEmpty(int index) {
		if(index < 0 || index > (BOARD_TILE_QUANTITY - 1)) {
			return false;
		} else {
			return getTile(index).isEmpty();
		}
	}

	public boolean hasAvailableTiles() {
		boolean areAvailable = false;
		for(int rows = 0; rows < BOARD_SIZE; rows++) {
			for(int cols = 0; cols < BOARD_SIZE; cols++) {
				if(!areAvailable && tiles[rows][cols].isEmpty()) {
					areAvailable = true;
				}
			}
		}
		return areAvailable;
	}

 	// Will be removed soon.
	public int getAvailableTileIndex() {
		int index = MyUtils.range(0, 8);
		Tile tile = getTile(index);
		if(tile.isEmpty()) {
			return index;
		} else {
			return getAvailableTileIndex();
		}
	}

	/*
		Prints a string of the board's rows, columns, and 
		diagonals separated by spaces.
	
		Ex: XXX O-O -O- XO- X-O XO- X-- --X

	*/
	public String getBoardString() {
		String[] lines = MyUtils.mergeStringArrays(
			getRows(), getColums(), getDiagonals()
		);

		String result = "";

		for(int i = 0; i < lines.length; i++) {
			result += lines[i] + " ";
		}

		return result;
	}

	private String[] getRows() {
		String[] rowStrings = new String[BOARD_SIZE];
		for(int rows = 0; rows < BOARD_SIZE; rows++) {
			String rowString = "";
			for(int cols = 0; cols < BOARD_SIZE; cols++) {
				rowString += tiles[rows][cols].getSymbol() + "";
			}
			rowStrings[rows] = rowString;	
		}
		return rowStrings;
	}

	private String[] getColums() {
		String[] colStrings = new String[BOARD_SIZE];
		for(int rows = 0; rows < BOARD_SIZE; rows++) {
			String colString = "";
			for(int cols = 0; cols < BOARD_SIZE; cols++) {
				colString += tiles[cols][rows].getSymbol() + "";
			}
			colStrings[rows] = colString;	
		}
		return colStrings;
	}

	private String[] getDiagonals() {
		String[] diagonals = new String[2];

		String diagonalTopLeft = "", diagonalTopRight = "";

		// diagonal from top-left to bottom-right
		for(int i = 0; i < BOARD_SIZE; i++) {
			diagonalTopLeft += tiles[i][i].getSymbol() + "";
		}

		// diagonal from top-right to bottom-left
		for(int i = BOARD_SIZE - 1; i >= 0; i--) {
			diagonalTopRight += tiles[i][i].getSymbol() + "";
		}

		diagonals[0] = diagonalTopLeft;
		diagonals[1] = diagonalTopRight;

		return diagonals;
	}

	public String toString() {
		String result = "";
		for(int rows = 0; rows < BOARD_SIZE; rows++) {
			for(int cols = 0; cols < BOARD_SIZE; cols++) {
				result += tiles[rows][cols];
				if(cols == BOARD_SIZE - 1) {
					result += "\n";
				}
			}
		}
		return result;
	}

}