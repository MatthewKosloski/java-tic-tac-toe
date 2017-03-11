//********************************************************************
// Computer.java
//
// A class representing a computer player that intelligently
// determines where to place its symbol on the board.
//********************************************************************

public class Computer implements Player {

	private Board board;
	private char symbol;

	public Computer(Board board, char symbol) {
		this.board = board;
		this.symbol = symbol;
	}

	public void placeSymbol(int index) {
		board.getTile(index).setSymbol(symbol);
		System.out.println("Computer placeSymbol");
	}
}