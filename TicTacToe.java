//********************************************************************
// TicTacToe.java
//
// The class for the game itself.
//********************************************************************

import java.util.Scanner;
import java.util.regex.*;

public class TicTacToe {

	private Scanner scan = new Scanner(System.in);

	private final char X_PIECE = 'X', O_PIECE = 'O';
	
	private final int BOARD_SIZE = 3, BOARD_TILE_QUANTITY = (int) Math.pow(BOARD_SIZE, 2);

	private final char YES = 'y', NO = 'n';

	private final String SYMBOL_PROMPT_EXCEPTION = "Please input an \"X\" or \"O\".";
	private final String TURN_PROMPT_EXCEPTION = "Please input an integer between 0 and %1$d.";

	private final String SYMBOL_PROMPT = "Desired game piece (%1$s/%2$s):";
	private final String TURN_PROMPT = "Want to go first? (%1$s/%2$s):";
	private final String PIECE_PROMPT = "Place piece on tile (0-%1$d):";
	private final String ANOTHER_PROMPT = "Play again? (%1$s/%2$s):";

	private Board board;
	private User user;
	private Computer computer;

	private String userSymbolInput, userIndexInput, another = YES + "";

	private char userSymbol;
	private String userFirstTurn;
	private int userIndex;

	public TicTacToe() {

		while(another.equalsIgnoreCase(YES + "")) {

			userSymbol = ' ';
			userFirstTurn = "";
			userIndex = -1;

			board = new Board(BOARD_SIZE, X_PIECE, O_PIECE);
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

			// System.out.println("User's desired piece: " + userSymbolInput);
			// System.out.println("User's symbol: " + user.getSymbol());
			// System.out.println("Computer's symbol: " + computer.getSymbol());

			// user.setSymbol('>');
			// System.out.println(user.getSymbol());

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

			while(board.hasAvailableTiles() && !hasWinner()) {
				userTurn();
				if(!hasWinner()) {
					computer.computeIndex();
				}
				printBoard();
				// System.out.println(board.getBoardString());
			}

			printGameResult();
			askAnother();
		}

	}

	private void askAnother() {
		System.out.print(String.format(ANOTHER_PROMPT, YES, NO));
		another = scan.nextLine();
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

	private char getWinningCharacter() {
		String xPatt = "", oPatt = "";
		char result = ' ';

		for(int i = 0; i < BOARD_SIZE; i++) {
			xPatt += X_PIECE + "";
			oPatt += O_PIECE + "";
		}

		boolean x = Pattern.compile(xPatt).matcher(board.getBoardString()).find();
		boolean o = Pattern.compile(oPatt).matcher(board.getBoardString()).find();

		if(x) {
			result = X_PIECE;
		} else if(o) {
			result = O_PIECE;
		} else {
			result = ' ';
		}

		return result;
	}

	private void printGameResult() {
		String result = "";

		if(getWinningCharacter() == user.getSymbol()) {
			result += "You won!";
		} else if(getWinningCharacter() == computer.getSymbol()) {
			result += "You lost!";
		} else {
			result += "Tied game.";
		}

		System.out.println(result);
	}

	private boolean hasWinner() {
		return getWinningCharacter() != ' ';
	}

	private void printBoard() {
		System.out.print(board);
		System.out.println();
	}

}