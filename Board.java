//********************************************************************
// Board.java
//
// A class that encapsulates the tiles.
//********************************************************************

import java.util.ArrayList;

public class Board {

	private final int TILE_QUANTITY = 9;
	private ArrayList<Tile> tiles = new ArrayList<Tile>();

	// Constructor: Initially populates the board with empty tiles
	public Board() {
		populate();
	}

	// Populates the board with empty tiles
	public void populate() {
		for(int i = 0; i < TILE_QUANTITY; i++) {
			tiles.add(new Tile('-'));
		}
	}

	public ArrayList getAllTiles() {
		return tiles;
	}

	// Returns the tile object from the tiles arraylist with a given index
	public Tile getTile(int index) {
		return tiles.get(index);
	}

	public ArrayList getEmptyTiles() {
		ArrayList<Tile> emptyTiles = new ArrayList<Tile>();
		for(int i = 0; i < tiles.size(); i++) {
			if(getTile(i).isEmpty()) emptyTiles.add(tiles.get(i));
		}
		return emptyTiles;
	}

	public String toString() {
		String result = "";

		int rows = (int) Math.sqrt(tiles.size());
		for(int i = 0; i < tiles.size(); i++) {
			result += (i % rows == 0 ? "" : " ") + tiles.get(i) + "";
			if(i % rows == rows - 1) result += "\n";
		}

		return result;

	}

}