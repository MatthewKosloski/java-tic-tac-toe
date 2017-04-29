// Test file--irrelevant to the program

import java.util.*;

public class Test {

	// private final int BOARD_SIZE = 3;
	// private final int BOARD_TILE_QUANTITY = (int) Math.pow(BOARD_SIZE, 2);
	private char symbol = 'O';

	public Test() {
		System.out.println(evaluateLine(new String[] {"OXO"}));
		System.out.println(evaluateLine(new String[] {"-XX"}));
		System.out.println(evaluateLine(new String[] {"XOO"}));
		System.out.println(evaluateLine(new String[] {"O-X"}));
		System.out.println(evaluateLine(new String[] {"XXO"}));
		System.out.println(evaluateLine(new String[] {"OXO"}));
		System.out.println(evaluateLine(new String[] {"OXO"}));
		System.out.println(evaluateLine(new String[] {"OXX"}));

	}

	private int evaluateLine(String[] tiles) {
		int score = 0;
		for(int i = 0; i < tiles.length; i++) {
			String currentPair = tiles[i];
			for(int j = 0; j < currentPair.length(); j++) {
				char currentSymbol = currentPair.charAt(j);
				if(currentSymbol == symbol) {
					score += 10;
				} else if(currentSymbol == '-') {
					score += 0;
				} else {
					score += -10;
				}
			}
		}
		return score;
	}

	// public void populate() {
	// 	for(int rows = 0; rows < BOARD_SIZE; rows++) {
	// 		for(int cols = 0; cols < BOARD_SIZE; cols++) {
	// 			tiles[rows][cols] = new Tile('-');
	// 		}
	// 	}

	// 	// Set tile indices (between 0 and [BOARD_TILE_QUANTITY - 1])
	// 	for(int i = 0; i < getTiles().length; i++) {
	// 		getTiles()[i].setIndex(i);
	// 	}
	// }

	// public Tile[] getAvailableTiles() {

	// 	// count number of empty/available tiles
	// 	int amountAvailable = 0;
	// 	for(int i = 0; i < BOARD_TILE_QUANTITY; i++) {
	// 		if(isTileEmpty(i)) {
	// 			amountAvailable++;
	// 		}
	// 	}

	// 	Tile[] available = new Tile[amountAvailable];

	// 	int count = 0;
	// 	for(int i = 0; i < BOARD_TILE_QUANTITY; i++) {
	// 		if(isTileEmpty(i)) {
	// 			available[count] = getTile(i); 
	// 			count++;
	// 		}
	// 	}

	// 	return available;
	// }

	// public int getRandomTileIndex() {
	// 	int randomNumber = (int) Math.floor(Math.random() * getAvailableTiles().length);
	// 	Tile randomTile = getAvailableTiles()[randomNumber];
	// 	return randomTile.getIndex();
	// }

	// public Tile getTile(int index) {
	// 	int row = (int) Math.floor(index / BOARD_SIZE);
	// 	int col = (int) Math.floor(index % BOARD_SIZE);
	// 	return tiles[row][col];
	// }

	// public Tile[] getTiles() {
	// 	Tile[] tiles = new Tile[BOARD_TILE_QUANTITY];

	// 	for(int i = 0; i < BOARD_TILE_QUANTITY; i++) tiles[i] = getTile(i);

	// 	return tiles;
	// }

	// public boolean isTileEmpty(int index) {
	// 	return getTile(index).isEmpty();
	// }

	// public void setSymbol(int index, char symbol) {
	// 	getTile(index).setSymbol(symbol);
	// }

	// public void printBoard() {
	// 	String result = "";
	// 	for(int rows = 0; rows < BOARD_SIZE; rows++) {
	// 		for(int cols = 0; cols < BOARD_SIZE; cols++) {
	// 			result += tiles[rows][cols] + " ";
	// 			if(cols == BOARD_SIZE - 1) {
	// 				result += "\n";
	// 			}
	// 		}
	// 	}
	// 	System.out.print(result);
	// }

}