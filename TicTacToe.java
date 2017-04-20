//********************************************************************
// TicTacToe.java
//
// The class for the game itself.
//********************************************************************

import java.util.Scanner;

public class TicTacToe {

	private Scanner scan = new Scanner(System.in);

	private final char X_PIECE = 'X', O_PIECE = 'O';
	
	private final int BOARD_SIZE = 3;
	private final int BOARD_TILE_QUANTITY = (int) Math.pow(BOARD_SIZE, 2);

	private final char YES = 'y', NO = 'n';

	private final String SYMBOL_PROMPT_EXCEPTION = "Please input an \"X\" or \"O\".";
	private final String TURN_PROMPT_EXCEPTION = "Please input an integer between 0 and %1$d.";

	private final String SYMBOL_PROMPT = "Desired game piece (%1$s/%2$s):";
	private final String TURN_PROMPT = "Want to go first? (%1$s/%2$s):";
	private final String PIECE_PROMPT = "Place piece on tile (0-%1$d):";

	private Board board;
	private User user;
	private Computer computer;

	private String userSymbolInput;
	private String userIndexInput;

	private char userSymbol;
	private String userFirstTurn = "";
	private int userIndex = -1;

	public TicTacToe() {

		board = new Board(BOARD_SIZE);
		user = new User(board, X_PIECE);
		computer = new Computer(board, O_PIECE);

		// Ask for the user's desired game piece (X or O)
		while(!(MyUtils.charEqualsIgnoreCase(userSymbol, X_PIECE) || MyUtils.charEqualsIgnoreCase(userSymbol, O_PIECE))) {
			askForSymbol();

			try {
				userSymbol = userSymbolInput.charAt(0);
			} catch(StringIndexOutOfBoundsException e) {
				System.out.println(SYMBOL_PROMPT_EXCEPTION);
			}

		}

		// Set both player's symbols
		user.setSymbol(userSymbol);
		computer.setSymbol(MyUtils.charEqualsIgnoreCase(userSymbol, X_PIECE) ? O_PIECE : X_PIECE);

		// Ask if the user wants to go first
		while(!(userFirstTurn.equalsIgnoreCase(YES+"") || userFirstTurn.equalsIgnoreCase(NO+""))) {
			askForFirstTurn();
		}

		// If player is first...
		if(userFirstTurn.equalsIgnoreCase(YES+"")) {
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
		System.out.print(String.format(SYMBOL_PROMPT, X_PIECE, O_PIECE));
		userSymbolInput = scan.nextLine();
	}

	private void askForFirstTurn() {
		System.out.print(String.format(TURN_PROMPT, YES, NO));
		userFirstTurn = scan.nextLine();
	}

	private void askForPiece() {
		System.out.print(String.format(PIECE_PROMPT, BOARD_TILE_QUANTITY - 1));
		userIndexInput = scan.nextLine();
	}

	// Ask for a tile index from player
	private void userTurn() {
		do {
			askForPiece();

			try {
				userIndex = Integer.parseInt(userIndexInput);
			} catch(NumberFormatException e) {
				System.out.println(String.format(TURN_PROMPT_EXCEPTION, BOARD_TILE_QUANTITY - 1));
			}

		} while(!(userIndex >= 0 && userIndex <= BOARD_TILE_QUANTITY - 1) || !(board.isTileEmpty(userIndex)));
		user.placeSymbol(userIndex);
	}

	private void printBoard() {
		System.out.print(board);
	}

}