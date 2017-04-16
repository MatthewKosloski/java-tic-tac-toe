//********************************************************************
// Computer.java
//
// A class representing the computer.
//********************************************************************

public class Computer extends Player {

	public Computer(Board board, char symbol) {
		super(board, symbol);
	}

	public void placeSymbol(int index) {
		int randomIndex = board.getAvailableTileIndex();
		Tile tile = board.getTile(randomIndex);
		tile.setSymbol(symbol);
	}

}