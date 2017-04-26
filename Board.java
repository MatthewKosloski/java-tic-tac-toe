//********************************************************************
// Board.java
//
// A class that interacts and manages a 2-D array of Tiles.
//********************************************************************

import java.util.regex.*;

public class Board {

	public final int BOARD_SIZE, BOARD_TILE_QUANTITY;
	public final char X_PIECE, O_PIECE, EMPTY_PIECE;
	private Tile[][] tiles;

	public Board(int boardSize, int boardTileQuantity, char xPiece, char oPiece, char emptyPiece) {
		BOARD_SIZE = boardSize;
		BOARD_TILE_QUANTITY = boardTileQuantity;
		X_PIECE = xPiece;
		O_PIECE = oPiece;
		EMPTY_PIECE = emptyPiece;

		tiles = new Tile[BOARD_SIZE][BOARD_SIZE];
		populate();
	}

	/*
	 * returns a tile by converting index value to row and column
	 */
	public Tile getTile(int index) {
		int row = (int) Math.floor(index / BOARD_SIZE);
		int col = (int) Math.floor(index % BOARD_SIZE);
		return tiles[row][col];
	}

	/*
	 * returns whether or not the tile with the index is empty
	 */
	public boolean isTileEmpty(int index) {
		return getTile(index).isEmpty();
	}

	/*
	 * returns a 1-D array of tiles
	 */
	public Tile[] getTiles() {
		Tile[] tiles = new Tile[BOARD_TILE_QUANTITY];

		for(int i = 0; i < BOARD_TILE_QUANTITY; i++) tiles[i] = getTile(i);

		return tiles;
	}

	/*
	 * populates the board with empty tiles
	 */
	public void populate() {
		for(int rows = 0; rows < BOARD_SIZE; rows++) {
			for(int cols = 0; cols < BOARD_SIZE; cols++) {
				tiles[rows][cols] = new Tile(EMPTY_PIECE);
			}
		}

		// Set tile indices (between 0 and [BOARD_TILE_QUANTITY - 1])
		for(int i = 0; i < getTiles().length; i++) {
			getTiles()[i].setIndex(i);
		}

	}

	/*
	 * returns a 1-D array of empty tiles
	 */
	public Tile[] getAvailableTiles() {

		// count number of empty/available tiles
		int amountAvailable = 0;
		for(int i = 0; i < BOARD_TILE_QUANTITY; i++) {
			if(isTileEmpty(i)) {
				amountAvailable++;
			}
		}

		Tile[] available = new Tile[amountAvailable];

		int count = 0;
		for(int i = 0; i < BOARD_TILE_QUANTITY; i++) {
			if(isTileEmpty(i)) {
				available[count] = getTile(i); 
				count++;
			}
		}

		return available;
	}

	/*
	 * returns a random index of an available tile
	 */
	public int getRandomTileIndex() {
		int randomNumber = (int) Math.floor(Math.random() * getAvailableTiles().length);
		Tile randomTile = getAvailableTiles()[randomNumber];
		return randomTile.getIndex();
	}

	/*
	 * applies a character value to a tile
	 */
	public void setSymbol(int index, char symbol) {
		getTile(index).setSymbol(symbol);
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

	private String[] getRowSymbols() {
		String[] symbols = new String[BOARD_SIZE];

		int count = 0;
		for(int i = 0; i < BOARD_SIZE; i++) {
			String row = "";
			for(int j = 0; j < BOARD_SIZE; j++) {
				row += getTiles()[count].getSymbol() + "";
				count++;
			}
			symbols[i] = row;
		}

		return symbols;
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