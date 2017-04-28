//********************************************************************
// Main.java
//
// The entry point of the game.
//********************************************************************

public class Main {

	public static void main(String[] args) {

		System.out.println("Tic-Tac-Toe");
		System.out.println();
		System.out.println("A game written in object-oriented Java, \n"
			+ "by Logan Miller and Matthew Kosloski.");
		System.out.println();
		System.out.println("=======================================");
		System.out.println();

		/*
		 * new TicTacToe(char xPiece, char oPiece, char emptyPiece, int boardSize)
		 *
		 * Parameters:
		 *
		 * xPiece - game piece one (doesn't have to be 'X')
		 * oPiece - game piece two (doesn't have to be 'O')
		 * emptyPiece - the character that empty tiles occupy
		 * boardSize - the size of the board (rows and columns)
		 */
		TicTacToe t = new TicTacToe('X', 'O', '-', 3);

	}

}