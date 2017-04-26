//********************************************************************
// TicTacToe.java
//
// The class for the game itself.
//********************************************************************

import java.util.Scanner;
import java.util.regex.*;

public class TicTacToe {

	private Scanner scan = new Scanner(System.in);

	private final char X_PIECE = 'X', O_PIECE = 'O', EMPTY_PIECE = '-';
	
	private final int BOARD_SIZE = 3, BOARD_TILE_QUANTITY = (int) Math.pow(BOARD_SIZE, 2);

	private final char YES = 'y', NO = 'n';

	private final String SYMBOL_PROMPT_EXCEPTION = "Please input an \"%1$s\" or \"%2$s\".";
	private final String TURN_PROMPT_EXCEPTION = "Please input an integer between 0 and %1$d.";

	private final String SYMBOL_PROMPT = "Desired game piece (%1$s/%2$s):";
	private final String TURN_PROMPT = "Want to go first? (%1$s/%2$s):";
	private final String TILE_INDEX_PROMPT = "Place piece on tile (0-%1$d):";
	private final String ANOTHER_PROMPT = "Play again? (%1$s/%2$s):";

	private Character humanSymbolInput = null;
	private Integer humanIndex = null;
	private String playerTurnInput = null, humanIndexInput = null, anotherGameInput = YES + "";

	private Board board;
	private Human human;
	private Ai ai;

	public TicTacToe() {

		while(anotherGameInput.equalsIgnoreCase(YES + "")) {

			board = new Board(BOARD_SIZE, X_PIECE, O_PIECE, EMPTY_PIECE);
			human = new Human(board, X_PIECE);
			ai = new Ai(board, O_PIECE);

			// Ask for the user's desired game piece (X or O)
			do { askForSymbol(); } while(!isValidSymbolInput());

			// Set both player's symbols
			human.setSymbol(humanSymbolInput);
			ai.setSymbol(MyUtils.charEqualsIgnoreCase(humanSymbolInput, X_PIECE) ? O_PIECE : X_PIECE);

			// Ask if the user wants to go first
			do { askForFirstTurn(); } while(!isValidTurnInput());

			// If player is first...
			if(playerTurnInput.equals(YES+"")) {
				humanMove();
				ai.calculateBestMove();
				printBoard();
			} else {
				ai.calculateBestMove();
				printBoard();
			}

			while(gameInProgress()) {
				humanMove();
				if(!hasWinner()) ai.calculateBestMove();
				printBoard();
			}

			printGameResult();
			askAnother();
		}

	}

	private boolean isValidIndexInput() {
		return !((humanIndex >= 0 && humanIndex <= BOARD_TILE_QUANTITY - 1) || board.isTileEmpty(humanIndex));
	}

	private boolean gameInProgress() {
		return board.getAvailableTiles().length != 0 && !hasWinner();
	}

	private boolean isValidTurnInput() {
		return (playerTurnInput.equals(YES + "") || playerTurnInput.equals(YES + ""));
	}

	private boolean isValidSymbolInput() {
		return MyUtils.charEqualsIgnoreCase(humanSymbolInput, X_PIECE) || MyUtils.charEqualsIgnoreCase(humanSymbolInput, O_PIECE);
	}

	private void askAnother() {
		System.out.print(String.format(ANOTHER_PROMPT, YES, NO));
		anotherGameInput = scan.nextLine();
	}

	private void askForSymbol() {
		System.out.print(String.format(SYMBOL_PROMPT, X_PIECE, O_PIECE));
		try {
			humanSymbolInput = scan.nextLine().charAt(0);
		} catch(StringIndexOutOfBoundsException e) {
			System.out.println(String.format(SYMBOL_PROMPT_EXCEPTION, X_PIECE, O_PIECE));
		}
	}

	private void askForFirstTurn() {
		System.out.print(String.format(TURN_PROMPT, YES, NO));
		playerTurnInput = scan.nextLine();
	}

	private void askForTileIndex() {
		System.out.print(String.format(TILE_INDEX_PROMPT, BOARD_TILE_QUANTITY - 1));
		humanIndexInput = scan.nextLine();
	}

	// Ask for a tile index from player
	private void humanMove() {
		do {
			askForTileIndex();
			try {
				humanIndex = Integer.parseInt(humanIndexInput);
			} catch(NumberFormatException e) {
				System.out.println(String.format(TURN_PROMPT_EXCEPTION, BOARD_TILE_QUANTITY - 1));
			}

		} while(isValidIndexInput());
		human.placeSymbol(humanIndex);
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

		if(getWinningCharacter() == human.getSymbol()) {
			result += "You won!";
		} else if(getWinningCharacter() == ai.getSymbol()) {
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