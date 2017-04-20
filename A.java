// Test file (not part of program)

import java.util.*;

public class A {

	public final int BOARD_SIZE;
	public final int BOARD_TILE_QUANTITY;
	private Tile[][] tiles;

	public A(int boardSize) {			
		BOARD_SIZE = boardSize;
		BOARD_TILE_QUANTITY = (int) Math.pow(BOARD_SIZE, 2);
		tiles = new Tile[BOARD_SIZE][BOARD_SIZE];

		System.out.println(BOARD_SIZE); // 3
		System.out.println(BOARD_TILE_QUANTITY); // 9
		System.out.println(tiles.length); // 3

		// add tiles to 2-D array
		for(int rows = 0; rows < BOARD_SIZE; rows++) {
			for(int cols = 0; cols < BOARD_SIZE; cols++) {
				tiles[rows][cols] = new Tile('X');
			}
		}

		// toString
		String result = "";
		for(int rows = 0; rows < BOARD_SIZE; rows++) {
			for(int cols = 0; cols < BOARD_SIZE; cols++) {
				result += tiles[rows][cols];
				if(cols == BOARD_SIZE - 1) {
					result += "\n";
				}
			}
		}

		System.out.println(hasAvailableTiles());


	}

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

}