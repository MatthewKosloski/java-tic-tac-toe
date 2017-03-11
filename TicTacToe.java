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
		user.placeSymbol(1);
		user.placeSymbol(2);
		user.placeSymbol(3);
		user.placeSymbol(4);
		user.placeSymbol(5);
		user.placeSymbol(6);
		// user.placeSymbol(7);
		computer.placeSymbol();

		System.out.print(board);

	}

}