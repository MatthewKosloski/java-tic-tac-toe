//********************************************************************
// TicTacToe.java
//
// The main class of the game.
//********************************************************************

public class TicTacToe {

	public static void main(String[] args) {

		final char X_PIECE = 'X';
		final char O_PIECE = 'O';

		Board board = new Board();
		User user = new User(board, X_PIECE);
		Computer computer = new Computer(board, O_PIECE);

		user.placeSymbol(0);
		computer.placeSymbol(4);

		System.out.print(board);

	}

}