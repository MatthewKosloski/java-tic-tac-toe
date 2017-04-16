//********************************************************************
// Board.java
//
// A class that encapsulates the tiles.
// This is an aggregate object, for it contains references to 
// Tiles as instance data.
//********************************************************************

import java.util.ArrayList;

public class Board {

	public final int TILE_QUANTITY;
	private ArrayList<Tile> tiles = new ArrayList<Tile>();

	// Constructor: Initially populates the board with empty tiles
	public Board(int tiles) {
		this.TILE_QUANTITY = tiles;
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

	public boolean isTileEmpty(int index) {
		return tiles.get(index).isEmpty();
	}

	public ArrayList getEmptyTiles() {
		ArrayList<Tile> emptyTiles = new ArrayList<Tile>();
		for(int i = 0; i < tiles.size(); i++) {
			if(getTile(i).isEmpty()) emptyTiles.add(tiles.get(i));
		}
		return emptyTiles;
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