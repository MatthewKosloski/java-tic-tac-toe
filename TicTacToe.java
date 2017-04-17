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

	private char userSymbol;
	private String userFirstTurn;
	private int userIndex;

	public TicTacToe() {

		board = new Board(TILE_QUANTITY);
		user = new User(board, X_PIECE);
		computer = new Computer(board, O_PIECE);

		// Initially ask for a game piece
		askForSymbol();

		// Keep on asking until provided input matches the following condition
		while(!(MyUtils.charEqualsIgnoreCase(userSymbol, X_PIECE) || MyUtils.charEqualsIgnoreCase(userSymbol, O_PIECE))) {
			askForSymbol();
		}

		// Set the validated user input as game piece
		user.setSymbol(userSymbol);

		// Set computer's symbol to the opposing symbol
		computer.setSymbol(MyUtils.charEqualsIgnoreCase(userSymbol, X_PIECE) ? O_PIECE : X_PIECE);

		// Ask if the user wants to go first
		askForFirstTurn();

		// Keep on asking until provided input matches the following condition
		while(!(userFirstTurn.equalsIgnoreCase("y") || userFirstTurn.equalsIgnoreCase("n"))) {
			askForFirstTurn();
		}

		// If player is first...
		if(userFirstTurn.equalsIgnoreCase("y")) {
			userTurn();
			computer.computeIndex();
			printBoard();
		} else {
			computer.computeIndex();
			printBoard();
		}

		while(board.hasAvailableTiles()) {
			userTurn();
			computer.computeIndex();
			printBoard();
		}

		System.out.println("Game over.");

	}

	private void askForSymbol() {
		System.out.print("Desired game piece (X/O):");
		userSymbol = scan.nextLine().charAt(0);
	}

	private void askForFirstTurn() {
		System.out.print("Want to go first? (y/n):");
		userFirstTurn = scan.nextLine();
	}

	private void askForPiece() {
		System.out.print("Place piece on tile (0-8):");
		userIndex = scan.nextInt();
	}

	private void userTurn() {
		// Ask for a tile index value from the player
		askForPiece();
		// Keep on asking until provided input matches the following conditions
		while(!(userIndex >= 0 && userIndex <= TILE_QUANTITY - 1) || !(board.isTileEmpty(userIndex))) {
			askForPiece();
		}
		// Set the validated symbol index
		user.placeSymbol(userIndex);
	}

	private void printBoard() {
		System.out.print(board);
	}

}