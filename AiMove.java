//********************************************************************
// AiMove.java
//
// A class representing a move carried out by the Ai.  
// It holds a tile index and the score of the move.
//********************************************************************

public class AiMove {

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