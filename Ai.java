//********************************************************************
// Ai.java
//
// A subclass of the Player class that represents the computer player.
//********************************************************************

import java.util.ArrayList;

public class Ai extends Player {

	public Ai(Board board, char symbol) {
		super(board, symbol);
	}

	/*
	 * calculates an index value (0-8) based on a 
	 * probability and places the Ai's symbol
	 * on the tile with that index.
	 */
	public void calculateBestMove() {
		if(board.getAvailableTiles().length > 0 && board.getWinningCharacter() == ' ') {
			placeSymbol(minimax(2, symbol)[1]);
		}
	}

	private int[] minimax(int depth, char player) {

		ArrayList<AiMove> moves = new ArrayList<AiMove>();
		
		int bestScore = (player == (symbol)) ? Integer.MIN_VALUE : Integer.MAX_VALUE;
		int bestMove = 0;

		if(depth == 0) {
			bestScore = evaluate(board.getBoardString().split(" "));
		} else {
			// create potential moves for each all unoccupied tiles
			for(int i = 0; i < board.getAvailableTiles().length; i++) {
				// create a move object to store its index and place Ai symbol on that index
				AiMove move = new AiMove(board.getAvailableTiles()[i].getIndex());
				board.setSymbol(move.getIndex(), player);

				if(player == (symbol)) {
					move.setScore(minimax(depth - 1, symbol == (board.X_PIECE) ? board.O_PIECE : board.X_PIECE)[0]);
				} else {
					move.setScore(minimax(depth - 1, symbol)[0]);
				}

				board.setSymbol(move.getIndex(), board.EMPTY_PIECE);
				moves.add(move);
			}

			// sort through the moves:

			if(player == (symbol)) {
				// find the move with the largest score (maximize)
				for(int i = 0; i < moves.size(); i++) {
					if(moves.get(i).getScore() > bestScore) {
						bestScore = moves.get(i).getScore();
						bestMove = moves.get(i).getIndex();
					}
				}
			} else {
				// find the move with the smallest score (minimize)
				for(int i = 0; i < moves.size(); i++) {
					if(moves.get(i).getScore() < bestScore) {
						bestScore = moves.get(i).getScore();
						bestMove = moves.get(i).getIndex();
					}
				}
			}

		}

		// return an array of the best score and best index value
		return new int[] {bestScore, bestMove};
	}

	private int evaluate(String[] boardString) {
		int score = 0;
		for(int i = 0; i < boardString.length; i++) {
			String currentPair = boardString[i];
			for(int j = 0; j < currentPair.length(); j++) {
				char currentSymbol = currentPair.charAt(j);
				if(currentSymbol == symbol) {
					score += 10;
				} else if(currentSymbol == board.EMPTY_PIECE) {
					score += 0;
				} else {
					score += -10;
				}
			}
		}
		return score;
	}

	private class AiMove {

		private int index, score;

		public AiMove(int index) {
			setIndex(index);
		}

		public int getIndex() {
			return index;
		}

		public void setIndex(int index) {
			this.index = index;
		}

		public int getScore() {
			return score;
		}

		public void setScore(int score) {
			this.score = score;
		}

	}

}