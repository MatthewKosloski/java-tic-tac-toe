// Test file (not part of program)

import java.util.*;
import java.util.regex.*;
import java.util.stream.*;

public class A {

	public final int BOARD_SIZE = 3;
	public final int BOARD_TILE_QUANTITY;
	public final String PIECE_REGEX = "[XO]";
	private Tile[][] tiles;

	public A() {			
		BOARD_TILE_QUANTITY = (int) Math.pow(BOARD_SIZE, 2);
		tiles = new Tile[BOARD_SIZE][BOARD_SIZE];


		// add tiles to 2-D array
		char[] symbols = {'X', 'O', '-'};
		for(int rows = 0; rows < BOARD_SIZE; rows++) {
			for(int cols = 0; cols < BOARD_SIZE; cols++) {
				Tile t = new Tile();
				t.setSymbol(symbols[(int)Math.floor(Math.random() * symbols.length)]);
				tiles[rows][cols] = t;
			}
		}

		// Set tile indices (0-BOARD_TILE_QUANTITY - 1)
		for(int i = 0; i < getRows().length; i++) {
			getRows()[i].setIndex(i);
		}

		String result = "";
		for(int rows = 0; rows < BOARD_SIZE; rows++) {
			for(int cols = 0; cols < BOARD_SIZE; cols++) {
				result += tiles[rows][cols] + " ";
				if(cols == BOARD_SIZE - 1) {
					result += "\n";
				}
			}
		}

		System.out.println(result);

		// // for(int i = 0; i < BOARD_SIZE; i++) {
		// // 	for(int j = 0; j < BOARD_SIZE; j++) {
		// // 		System.out.println(getRows()[i][j]);
		// // 	}
		// // }

		// System.out.println("Row sums:");
		// for(int i = 0; i < getRowSums().length; i++) {
		// 	System.out.println(getRowSums()[i]);
		// }

		// System.out.println("Column sums:");
		// for(int i = 0; i < getColumnSums().length; i++) {
		// 	System.out.println(getColumnSums()[i]);
		// }

		// System.out.println("Diagonal sums:");
		// for(int i = 0; i < getDiagonalSums().length; i++) {
		// 	System.out.println(getDiagonalSums()[i]);
		// }

		// System.out.println();

		computeIndex();

		// int[] arr = {3, 3, 1, 2};
		// System.out.println(MyUtils.getIndexOfLargestInt(arr, 3));
		
	}

	private void computeIndex() {

		int computedIndex = -1;

		Index[] indices = {
			new Index(MyUtils.getIndexOfLargestInt(getRowSums(), BOARD_SIZE)),
			new Index(MyUtils.getIndexOfLargestInt(getColumnSums(), BOARD_SIZE)),
			new Index(MyUtils.getIndexOfLargestInt(getDiagonalSums(), BOARD_SIZE))
		};

		int randomInt = (int) Math.floor(Math.random() * indices.length);

		Index randomIndex = indices[randomInt];

		if(randomInt == 0) {
			System.out.println("Will occupy a tile in a row.");
			Tile[] row = getTilesFromRow(randomIndex.getValue());
			Tile randomTileInRow = null;

			do {
				randomTileInRow = row[(int)Math.floor(Math.random() * row.length)];
			} while(!randomTileInRow.isEmpty());

			computedIndex = randomTileInRow.getIndex();
		} else if(randomInt == 1) {
			System.out.println("Will occupy a tile in a column.");
			Tile[] column = getTilesFromColumn(randomIndex.getValue());
			Tile randomTileInColumn = null;

			do {
				randomTileInColumn = column[(int)Math.floor(Math.random() * column.length)];
			} while(!randomTileInColumn.isEmpty());

			computedIndex = randomTileInColumn.getIndex();
		} else if(randomInt == 2) {
			System.out.println("Will occupy a tile in a diagonal.");
			Tile[] diagonal = getTilesFromDiagonal(randomIndex.getValue());
			Tile randomTileInDiagonal = null;

			do {
				randomTileInDiagonal = diagonal[(int)Math.floor(Math.random() * diagonal.length)];
			} while(!randomTileInDiagonal.isEmpty());

			computedIndex = randomTileInDiagonal.getIndex();
		}

		System.out.println("RandomInt: " + randomInt);

		System.out.println("Row index: " + indices[0].getValue());
		System.out.println("Column index: " + indices[1].getValue());
		System.out.println("Diagonal index: " + indices[2].getValue());

		System.out.println("Computed index: " + computedIndex);

		// setSymbol(index);
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

	public Tile[] getTilesFromRow(int row) {
		Tile[] rowTiles = new Tile[BOARD_SIZE];

		for(int col = 0; col < BOARD_SIZE; col++) {
			rowTiles[col] = tiles[row][col];
		}

		return rowTiles;
	}

	public Tile[] getTilesFromColumn(int col) {
		Tile[] columnTiles = new Tile[BOARD_SIZE];

		for(int row = 0; row < BOARD_SIZE; row++) {
			columnTiles[row] = tiles[row][col];
		}

		return columnTiles;
	}

	public Tile[] getTilesFromDiagonal(int diagonal) {
		if(diagonal == 0) {
			return getTopLeftDiagonals();
		} else {
			return getTopRightDiagonals();
		}
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

	private int[] getRowSums() {
		int[] rowSums = new int[BOARD_SIZE];

		for(int i = 0; i < getRowSymbols().length; i++) {
			Pattern p = Pattern.compile(PIECE_REGEX);
      		Matcher m = p.matcher(getRowSymbols()[i]);
			int count = 0;
			while(m.find()) count++;
			rowSums[i] = count;
		}

		return rowSums;
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

	private int[] getColumnSums() {
		int[] colSums = new int[BOARD_SIZE];

		for(int i = 0; i < getRowSymbols().length; i++) {
			Pattern p = Pattern.compile(PIECE_REGEX);
      		Matcher m = p.matcher(getColumnSymbols()[i]);
			int count = 0;
			while(m.find()) count++;
			colSums[i] = count;
		}

		return colSums;
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

	private int[] getDiagonalSums() {
		int[] diagonalSums = new int[2];

		for(int i = 0; i < getDiagonalSymbols().length; i++) {
			Pattern p = Pattern.compile(PIECE_REGEX);
      		Matcher m = p.matcher(getDiagonalSymbols()[i]);
			int count = 0;
			while(m.find()) count++;
			diagonalSums[i] = count;
		}

		return diagonalSums;
	}

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



}