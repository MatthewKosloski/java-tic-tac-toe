//********************************************************************
// Computer.java
//
// A class representing the computer.
//********************************************************************

public class Computer extends Player {

	public Computer(Board board, char symbol) {
		super(board, symbol);
	}

	// Algorithm goes here.
	public void computeIndex() {
		if(board.hasAvailableTiles()) {
			int randomIndex = board.getAvailableTileIndex();
			placeSymbol(randomIndex);
		}
	}

}