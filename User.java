//********************************************************************
// User.java
//
// A class representing an individual user.
//********************************************************************

public class User implements Player {

	private Board board;
	private char symbol;

	public User(Board board, char symbol) {
		this.board = board;
		this.symbol = symbol;
	}

	public void placeSymbol(int index) {
		board.getTile(index).setSymbol(symbol);
		System.out.println("User placeSymbol");
	}

	public String toString() {
		return symbol + "";
	}

}