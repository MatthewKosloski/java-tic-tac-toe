import java.util.ArrayList;

public class Ai extends Player {

	public Ai(Board board, char symbol) {
		super(board, symbol);
	}

	public void calculateBestMove() {
		// place the Ai symbol on an index value received from minimax
		placeSymbol(minimax(2, symbol)[1]);
	}

	// evaluate the state of the board
	private int evaluate() {
		int score = 0;
		score += evaluateLine(0, 1, 2); // row 1
		score += evaluateLine(3, 4, 5); // row 2
		score += evaluateLine(6, 7, 8); // row 3
		score += evaluateLine(0, 3, 6); // col 1
		score += evaluateLine(1, 4, 7); // col 2
		score += evaluateLine(2, 5, 8); // col 3
		score += evaluateLine(0, 4, 8); // diag 1
		score += evaluateLine(2, 4, 6); // diag 2
		return score;
	}

	// return -100, -10, -1, 
	private int evaluateLine(int a, int b, int c) {
		int score = 0;

		if(board.getTile(a).getSymbol() == (symbol)) {
			score = 1;
		} else if(board.getTile(a).getSymbol() != (symbol)) {
			score = -1;
		}

		if(board.getTile(b).getSymbol() == (symbol)) {
			if(score == 1) {
				score = 10;
			} else if(score == -1) {
				return 0;
			} else {
				score = 1;
			}
		} else if(board.getTile(b).getSymbol() != (symbol)) {
			if(score == -1) {
				score = -10;
			} else if(score == 1) {
				return 0;
			} else {
				score = -1;
			}
		}

		if(board.getTile(c).getSymbol() == (symbol)) {
			if(score > 0) {
				score *= 10;
			} else if(score < 0) {
				return 0;
			} else {
				score = 1;
			}
		} else if(board.getTile(c).getSymbol() != (symbol)) {
			if(score < 0) {
				score *= 10;
			} else if(score > 1) {
				return 0;
			} else {
				score = -1;
			}
		}

		return score;
	}

	private int[] minimax(int depth, char player) {

		ArrayList<AiMove> moves = new ArrayList<AiMove>();
		
		int bestScore = (player == (symbol)) ? Integer.MIN_VALUE : Integer.MAX_VALUE;
		int bestMove = 0;

		if(board.getAvailableTiles().length == 0 || depth == 0) {
			bestScore = evaluate();
		} else {

			for(int i = 0; i < board.getAvailableTiles().length; i++) {
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

			if(player == (symbol)) {
				for(int i = 0; i < moves.size(); i++) {
					if(moves.get(i).getScore() > bestScore) {
						bestScore = moves.get(i).getScore();
						bestMove = moves.get(i).getIndex();
					}
				}
			} else {
				for(int i = 0; i < moves.size(); i++) {
					if(moves.get(i).getScore() < bestScore) {
						bestScore = moves.get(i).getScore();
						bestMove = moves.get(i).getIndex();
					}
				}
			}

		}

		// return arr of best score and best index value
		return new int[] {bestScore, bestMove};
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