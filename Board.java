//********************************************************************
// Board.java
//
// A class that encapsulates the tiles.
// This is an aggregate object, for it contains references to 
// Tiles as instance data.
//********************************************************************

import java.util.regex.*;

public class Board {

	public final int BOARD_SIZE, BOARD_TILE_QUANTITY;
	private String pieceRegex = "";
	private Tile[][] tiles;

	// Constructor: Initially populates the board with empty tiles
	public Board(int boardSize, char xPiece, char oPiece) {
		pieceRegex = String.format("[%1$s%2$s]", xPiece, oPiece);
		BOARD_SIZE = boardSize;
		BOARD_TILE_QUANTITY = (int) Math.pow(BOARD_SIZE, 2);
		tiles = new Tile[BOARD_SIZE][BOARD_SIZE];
		populate();
	}

	// Populates the board with empty tiles
	public void populate() {
		for(int rows = 0; rows < BOARD_SIZE; rows++) {
			for(int cols = 0; cols < BOARD_SIZE; cols++) {
				tiles[rows][cols] = new Tile();
			}
		}

		// Set tile indices (0-BOARD_TILE_QUANTITY - 1)
		for(int i = 0; i < getRows().length; i++) {
			getRows()[i].setIndex(i);
		}

	}

	/*
		Returns a Tile from a provided index value 
		between 0 and BOARD_TILE_QUANTITY - 1.

		Example:
		If BOARD_SIZE is 3 (3 rows and 3 columns),
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
			getRowSymbols(), getColumnSymbols(), getDiagonalSymbols()
		);

		String result = "";

		for(int i = 0; i < lines.length; i++) {
			result += lines[i] + " ";
		}

		return result;
	}

	private Tile[] getRows() {
		Tile[] rows = new Tile[BOARD_TILE_QUANTITY];

		int count = 0;
		for(int row = 0; row < BOARD_SIZE; row++) {
			for(int col = 0; col < BOARD_SIZE; col++) {
				rows[count] = tiles[row][col];
				count++;
			}
		}

		return rows;
	}

	private String[] getRowSymbols() {
		String[] symbols = new String[BOARD_SIZE];

		int count = 0;
		for(int i = 0; i < BOARD_SIZE; i++) {
			String row = "";
			for(int j = 0; j < BOARD_SIZE; j++) {
				row += getRows()[count].getSymbol() + "";
				count++;
			}
			symbols[i] = row;
		}

		return symbols;
	}

	public int[] getRowSums() {
		int[] rowSums = new int[BOARD_SIZE];

		for(int i = 0; i < getRowSymbols().length; i++) {
			Pattern p = Pattern.compile(pieceRegex);
      		Matcher m = p.matcher(getRowSymbols()[i]);
			int count = 0;
			while(m.find()) count++;
			rowSums[i] = count;
		}

		return rowSums;
	}

	public Tile[] getTilesFromRow(int row) {
		Tile[] rowTiles = new Tile[BOARD_SIZE];

		for(int col = 0; col < BOARD_SIZE; col++) {
			rowTiles[col] = tiles[row][col];
		}

		return rowTiles;
	}

	private Tile[] getColumns() {
		Tile[] cols = new Tile[BOARD_TILE_QUANTITY];

		int count = 0;
		for(int col = 0; col < BOARD_SIZE; col++) {
			for(int row = 0; row < BOARD_SIZE; row++) {
				cols[count] = tiles[row][col];
				count++;
			}
		}

		return cols;
	}

	private String[] getColumnSymbols() {
		String[] symbols = new String[BOARD_SIZE];

		int count = 0;
		for(int i = 0; i < BOARD_SIZE; i++) {
			String column = "";
			for(int j = 0; j < BOARD_SIZE; j++) {
				column += getColumns()[count].getSymbol() + "";
				count++;
			}
			symbols[i] = column;
		}

		return symbols;
	}

	public int[] getColumnSums() {
		int[] colSums = new int[BOARD_SIZE];

		for(int i = 0; i < getRowSymbols().length; i++) {
			Pattern p = Pattern.compile(pieceRegex);
      		Matcher m = p.matcher(getColumnSymbols()[i]);
			int count = 0;
			while(m.find()) count++;
			colSums[i] = count;
		}

		return colSums;
	}

	public Tile[] getTilesFromColumn(int col) {
		Tile[] columnTiles = new Tile[BOARD_SIZE];

		for(int row = 0; row < BOARD_SIZE; row++) {
			columnTiles[row] = tiles[row][col];
		}

		return columnTiles;
	}

	private Tile[] getTopLeftDiagonals() {
		Tile[] topLeftDiagonals = new Tile[BOARD_SIZE];

		for(int i = 0; i < BOARD_SIZE; i++) {
			topLeftDiagonals[i] = tiles[i][i];
		}

		return topLeftDiagonals;
	}

	private Tile[] getTopRightDiagonals() {
		Tile[] topRightDiagonals = new Tile[BOARD_SIZE];
		int col = BOARD_SIZE - 1;

		for(int row = 0; row < BOARD_SIZE; row++) {
			topRightDiagonals[row] = tiles[row][col];
			col--;
		}

		return topRightDiagonals;
	}

	private Tile[] getDiagonals() {
		Tile[] diagonals = new Tile[BOARD_SIZE * 2];

		int count = 0;

		// diagonal from top-left to bottom-right
		for(int i = 0; i < getTopLeftDiagonals().length; i++) {
			diagonals[count] = getTopLeftDiagonals()[i];
			count++;
		}

		// diagonal from top-right to bottom-left
		for(int i = 0; i < getTopRightDiagonals().length; i++) {
			diagonals[count] = getTopRightDiagonals()[i];
			count++;
		}

		return diagonals;
	}

	private String[] getDiagonalSymbols() {
		String[] symbols = new String[2];

		int count = 0;
		for(int i = 0; i < 2; i++) {
			String diagonal = "";
			for(int j = 0; j < BOARD_SIZE; j++) {
				diagonal += getDiagonals()[count].getSymbol() + "";
				count++;
			}
			symbols[i] = diagonal;
		}

		return symbols;
	}

	public int[] getDiagonalSums() {
		int[] diagonalSums = new int[2];

		for(int i = 0; i < getDiagonalSymbols().length; i++) {
			Pattern p = Pattern.compile(pieceRegex);
      		Matcher m = p.matcher(getDiagonalSymbols()[i]);
			int count = 0;
			while(m.find()) count++;
			diagonalSums[i] = count;
		}

		return diagonalSums;
	}

	public Tile[] getTilesFromDiagonal(int diagonal) {
		if(diagonal == 0) {
			return getTopLeftDiagonals();
		} else {
			return getTopRightDiagonals();
		}
	}

	public String toString() {
		String result = "";
		for(int rows = 0; rows < BOARD_SIZE; rows++) {
			for(int cols = 0; cols < BOARD_SIZE; cols++) {
				result += tiles[rows][cols] + " ";
				if(cols == BOARD_SIZE - 1) {
					result += "\n";
				}
			}
		}
		return result;
	}

}