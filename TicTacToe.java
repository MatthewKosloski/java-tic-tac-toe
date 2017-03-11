//********************************************************************
// TicTacToe.java
//
// The main class of the game.
//********************************************************************

public class TicTacToe {

	public static void main(String[] args) {

		final char X_PIECE = 'X';
		final char O_PIECE = 'O';

		Board board;
		Player user, computer;

		board = new Board();

		user = new Player(board, X_PIECE);
		computer = new Player(board, O_PIECE);

		// board.getTile(0).setSymbol('X');

		System.out.print(board);

	}

}