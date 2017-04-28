//********************************************************************
// TicTacToe.java
//
// The main game class that manages human input and controls the 
// flow of the game.
//********************************************************************

import java.util.Scanner;

public class TicTacToe {

	private Scanner scan = new Scanner(System.in);

	private final char X_PIECE, O_PIECE, EMPTY_PIECE;
	
	private final int BOARD_SIZE, BOARD_TILE_QUANTITY;

	private final char YES = 'y', NO = 'n';

	private final String SYMBOL_PROMPT_EXCEPTION = "Please input an \"%1$s\" or \"%2$s\".";
	private final String TURN_PROMPT_EXCEPTION = "Please input an integer between 0 and %1$d.";

	private final String SYMBOL_PROMPT = "Desired game piece (%1$s/%2$s):";
	private final String TURN_PROMPT = "Want to go first? (%1$s/%2$s):";
	private final String TILE_INDEX_PROMPT = "Place piece on tile (0-%1$d):";
	private final String ANOTHER_PROMPT = "Play again? (%1$s/%2$s):";

	private Character humanSymbolInput = ' ';
	private Integer humanIndex = -1;
	private String playerTurnInput = "", humanIndexInput = "", anotherGameInput = YES + "";

	private Board board;
	private Human human;
	private Ai ai;

	public TicTacToe(char xPiece, char oPiece, char emptyPiece, int boardSize) {

		// assign parameters to constants
		X_PIECE = xPiece;
		O_PIECE = oPiece;
		EMPTY_PIECE = emptyPiece;
		BOARD_SIZE = boardSize;
		BOARD_TILE_QUANTITY = (int) Math.pow(BOARD_SIZE, 2); // 3^2

		// while the human wants to play another game, execute the following:
		while(anotherGameInput.equalsIgnoreCase(YES + "")) {

			board = new Board(BOARD_SIZE, BOARD_TILE_QUANTITY, X_PIECE, O_PIECE, EMPTY_PIECE);
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

	/*
	 * if the tile index provided by the user is not
	 * between 0-8 or is occupied, return false, else true
	 */
	private boolean isValidIndexInput() {
		return ((humanIndex >= 0 && humanIndex <= BOARD_TILE_QUANTITY - 1) && board.isTileEmpty(humanIndex));
	}

	/*
	 * if there are still unoccupied tiles and no winner, 
	 * return true, else false
	 */
	private boolean gameInProgress() {
		return board.getAvailableTiles().length != 0 && !hasWinner();
	}

	/*
	 * returns true if human provides "y" or "n" to the turn prompt
	 */
	private boolean isValidTurnInput() {
		return (playerTurnInput.equals(YES + "") || playerTurnInput.equals(NO + ""));
	}

   /*
	* returns true if human provides "x" or "o" to the symbol prompt (case insensitive)
	*/
	private boolean isValidSymbolInput() {
		return MyUtils.charEqualsIgnoreCase(humanSymbolInput, X_PIECE) || MyUtils.charEqualsIgnoreCase(humanSymbolInput, O_PIECE);
	}

	/*
	 * asks the human if they want to play another game and stores
	 * the input in the anotherGameInput string variable
	 */
	private void askAnother() {
		System.out.print(String.format(ANOTHER_PROMPT, YES, NO));
		anotherGameInput = scan.nextLine();
	}

    /*
     * asks the human for a game piece ("X" or "O") and 
     * throws an exception if it is not one character in length
     */
	private void askForSymbol() {
		System.out.print(String.format(SYMBOL_PROMPT, X_PIECE, O_PIECE));
		try {
			humanSymbolInput = scan.nextLine().charAt(0);
		} catch(StringIndexOutOfBoundsException e) {
			System.out.println(String.format(SYMBOL_PROMPT_EXCEPTION, X_PIECE, O_PIECE));
		}
	}

    /*
     * asks the human if they want to go first
     */
	private void askForFirstTurn() {
		System.out.print(String.format(TURN_PROMPT, YES, NO));
		playerTurnInput = scan.nextLine();
	}

	/*
     * asks the human to input an index value between 0 and 8
     */
	private void askForTileIndex() {
		System.out.print(String.format(TILE_INDEX_PROMPT, BOARD_TILE_QUANTITY - 1));
		humanIndexInput = scan.nextLine();
	}

	/*
     * asks the human for an index value, and if it is valid, 
     * the board tile will be occupied with the human's chosen symbol
     */
	private void humanMove() {
		do {
			askForTileIndex();
			try {
				humanIndex = Integer.parseInt(humanIndexInput);
			} catch(NumberFormatException e) {
				System.out.println(String.format(TURN_PROMPT_EXCEPTION, BOARD_TILE_QUANTITY - 1));
			}

		} while(!isValidIndexInput());
		human.placeSymbol(humanIndex);
	}

	/*
     * prints the game outcome by comparing the winning
     * character to each of the players' symbols
     */
	private void printGameResult() {
		String result = "";

		if(board.getWinningCharacter() == human.getSymbol()) {
			result += "You won!";
		} else if(board.getWinningCharacter() == ai.getSymbol()) {
			result += "You lost!";
		} else {
			result += "Tied game.";
		}

		System.out.println(result);
	}

	/*
     * returns true if no tie
     */
	private boolean hasWinner() {
		return board.getWinningCharacter() != ' ';
	}

	/*
     * prints the board object to the console
     */
	private void printBoard() {
		System.out.print(board);
		System.out.println();
	}

}