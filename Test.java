// Test file--irrelevant to the program

import java.util.*;

public class Test {

	private Tile[][] tiles;
	private final int BOARD_SIZE = 3;
	private final int BOARD_TILE_QUANTITY = (int) Math.pow(BOARD_SIZE, 2);

	public Test() {
		// tiles = new Tile[BOARD_SIZE][BOARD_SIZE];
		// populate();
		// setSymbol(0, 'X');
		// printBoard();
		// System.out.println(getRandomTileIndex());

		Random generator = new Random();
		int probability = 90, rdm = generator.nextInt(100) + 1; 
		String predicate = (rdm <= probability) ? "9/10" : "1/10";
		System.out.println("Predicate: " + predicate);


	}

	public void populate() {
		for(int rows = 0; rows < BOARD_SIZE; rows++) {
			for(int cols = 0; cols < BOARD_SIZE; cols++) {
				tiles[rows][cols] = new Tile('-');
			}
		}

		// Set tile indices (between 0 and [BOARD_TILE_QUANTITY - 1])
		for(int i = 0; i < getTiles().length; i++) {
			getTiles()[i].setIndex(i);
		}
	}

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

	public int getRandomTileIndex() {
		int randomNumber = (int) Math.floor(Math.random() * getAvailableTiles().length);
		Tile randomTile = getAvailableTiles()[randomNumber];
		return randomTile.getIndex();
	}

	public Tile getTile(int index) {
		int row = (int) Math.floor(index / BOARD_SIZE);
		int col = (int) Math.floor(index % BOARD_SIZE);
		return tiles[row][col];
	}

	public Tile[] getTiles() {
		Tile[] tiles = new Tile[BOARD_TILE_QUANTITY];

		for(int i = 0; i < BOARD_TILE_QUANTITY; i++) tiles[i] = getTile(i);

		return tiles;
	}

	public boolean isTileEmpty(int index) {
		return getTile(index).isEmpty();
	}

	public void setSymbol(int index, char symbol) {
		getTile(index).setSymbol(symbol);
	}

	public void printBoard() {
		String result = "";
		for(int rows = 0; rows < BOARD_SIZE; rows++) {
			for(int cols = 0; cols < BOARD_SIZE; cols++) {
				result += tiles[rows][cols] + " ";
				if(cols == BOARD_SIZE - 1) {
					result += "\n";
				}
			}
		}
		System.out.print(result);
	}

}