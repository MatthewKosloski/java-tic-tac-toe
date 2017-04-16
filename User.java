//********************************************************************
// User.java
//
// A class representing an individual user.
//********************************************************************

public class User extends Player {

	public User(Board board, char symbol) {
		super(board, symbol);
	}

	public void placeSymbol(int index) {
		Tile tile = board.getTile(index);
		tile.setSymbol(symbol);
	}

}