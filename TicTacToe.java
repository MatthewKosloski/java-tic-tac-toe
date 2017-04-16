//********************************************************************
// TicTacToe.java
//
// The class for the game itself.
//********************************************************************

import java.util.Scanner;

public class TicTacToe {

	private Scanner scan = new Scanner(System.in);

	private final char X_PIECE = 'X';
	private final char O_PIECE = 'O';
	private final int TILE_QUANTITY = 9;

	private Board board;
	private User user;
	private Computer computer;

	private char userSymbol = ' ';
	private String initString = "";
	private int userIndex = -1;

	public TicTacToe() {

		board = new Board(TILE_QUANTITY);
		user = new User(board, X_PIECE);
		computer = new Computer(board, O_PIECE);

		// Ask player for a game piece
		while(!(charEqualsIgnoreCase(userSymbol, X_PIECE) || charEqualsIgnoreCase(userSymbol, O_PIECE))) {
			System.out.print("Desired game piece (X/O):");
			userSymbol = scan.nextLine().charAt(0);
		}

		// Set the user's desired game piece
		user.setSymbol(userSymbol);

		// Set computer's symbol to the opposing symbol
		computer.setSymbol(charEqualsIgnoreCase(userSymbol, X_PIECE) ? O_PIECE : X_PIECE);

		// Ask player if he wants to go first
		while(!(initString.equalsIgnoreCase("y") || initString.equalsIgnoreCase("n"))) {
			System.out.print("Want to go first? (y/n):");
			initString = scan.nextLine();
		}

		// If player is first...
		if(initString.equalsIgnoreCase("y")) {
			getUserChoice();
			computer.placeSymbol(-1);
		} else {
			computer.placeSymbol(-1);
			printBoard();
			getUserChoice();
		}

		printBoard();
		clearUserIndex();

		while(board.getEmptyTiles().size() != 0) {
			getUserChoice();
			computer.placeSymbol(-1);
			printBoard();
			clearUserIndex();
		}

	}

	public void printBoard() {
		System.out.print(board);
	}

	private boolean charEqualsIgnoreCase(char a, char b) {
		return new String(a + "").equalsIgnoreCase(new String(b + ""));
	}

	private void getUserChoice() {
		while(!(userIndex >= 0 && userIndex <= TILE_QUANTITY - 1)) {
			System.out.print("Place piece on tile (0-8):");
			userIndex = scan.nextInt();
		}
		user.placeSymbol(userIndex);
	}

	private void clearUserIndex() {
		userIndex = -1;
	}

}