//********************************************************************
// Computer.java
//
// A class representing the computer.
//********************************************************************

public class Computer extends Player {

	public Computer(Board board, char symbol) {
		super(board, symbol);
	}

	public void computeIndex() {
		if(board.hasAvailableTiles()) {

			int computedIndex = -1;

			Index[] indices = {
				new Index(MyUtils.getIndexOfLargestInt(board.getRowSums(), board.BOARD_SIZE)),
				new Index(MyUtils.getIndexOfLargestInt(board.getColumnSums(), board.BOARD_SIZE)),
				new Index(MyUtils.getIndexOfLargestInt(board.getDiagonalSums(), board.BOARD_SIZE))
			};

			int randomInt = (int) Math.floor(Math.random() * indices.length);

			Index randomIndex = indices[randomInt];

			if(randomInt == 0) {
				Tile[] row = board.getTilesFromRow(randomIndex.getValue());
				Tile randomTileInRow = null;

				do {
					randomTileInRow = row[(int)Math.floor(Math.random() * row.length)];
				} while(!randomTileInRow.isEmpty());

				computedIndex = randomTileInRow.getIndex();
			} else if(randomInt == 1) {
				Tile[] column = board.getTilesFromColumn(randomIndex.getValue());
				Tile randomTileInColumn = null;

				do {
					randomTileInColumn = column[(int)Math.floor(Math.random() * column.length)];
				} while(!randomTileInColumn.isEmpty());

				computedIndex = randomTileInColumn.getIndex();
			} else if(randomInt == 2) {
				Tile[] diagonal = board.getTilesFromDiagonal(randomIndex.getValue());
				Tile randomTileInDiagonal = null;

				do {
					randomTileInDiagonal = diagonal[(int)Math.floor(Math.random() * diagonal.length)];
				} while(!randomTileInDiagonal.isEmpty());

				computedIndex = randomTileInDiagonal.getIndex();
			}

			// After computing a viable index, occupy a tile on the board
			placeSymbol(computedIndex);
		}
	}

}