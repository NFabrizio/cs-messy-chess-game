package a1;

import java.util.ArrayList;
import java.util.function.BiFunction;

public class Bishop extends ChessPiece {
	// 0 base maximums
	private int maxRows = 7;
	private int maxColumns = 7;
	private int minimum = 0;
	private String columns = "abcdefgh";

	public Bishop(ChessBoard board, Color color) {
		super(board, color);
	}

	@Override
	public String toString() {
		if (color == Color.WHITE) {
			return "\u2657";
		}

		return "\u265D";
	}

	private ArrayList<String> getConditionalPositions(BiFunction<Integer, Integer, Integer> rowFn,
			BiFunction<Integer, Integer, Integer> columnFn) {
		int maxRowDifference = maxRows - this.row;
		int minRowDifference = this.row - minimum;
		int maxColumnDifference = maxColumns - this.column;
		int minColumnDifference = this.column - minimum;
		int maxDifferenceRow = Math.max(maxRowDifference, minRowDifference);
		int maxDifferenceColumn = Math.max(maxColumnDifference, minColumnDifference);
		int maxDifference = Math.max(maxDifferenceRow, maxDifferenceColumn);
		ArrayList<String> positions = new ArrayList<String>();

		// Start at 1 since we do not need to check if the current position is valid
		for (int i = 1; i <= maxDifference; i++) {
			int newRow = rowFn.apply(this.row, i);
			int newColumn = columnFn.apply(this.column, i);

			// Before proceeding, ensure that row and column are both valid
			if (newRow <= maxRows && newRow >= minimum && newColumn <= maxColumns && newColumn >= minimum) {
				String newPosition = convertIntToStringPosition(newRow, newColumn);

				try {
					ChessPiece existingPiece = board.getPiece(newPosition);

					if (existingPiece != null) {
						if (existingPiece.getColor() != this.getColor()) {
							positions.add(newPosition);
						}

						return positions;
					}

					// No piece exists, add position to legal moves array
					positions.add(newPosition);
				} catch (IllegalPositionException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

		return positions;
	}

	private String convertIntToStringPosition(int row, int column) {
		return columns.charAt(column) + Integer.toString(row + 1);
	}

	@Override
	public ArrayList<String> legalMoves() {
		// A bishop can move any number of squares diagonally in any direction as long
		// as it does not have to leap over other pieces. At the end of the move, it can
		// occupy a previously empty square or capture (replace) an opponent's piece but
		// it cannot replace another piece of the same player.

		ArrayList<String> allLegalMoves = new ArrayList<String>();

		// Get current position
		String currentPosition = this.getPosition();

		// If current position is null, piece is not on board, return empty ArrayList
		if (currentPosition == null) {
			return new ArrayList<String>();
		}

		// Calculate legal moves in each direction until another piece is encountered or
		// edge of board is reached
		// for each row, increment/decrement starting from current row until result is
		// either 0 or maxRows
		// for each column, increment/decrement starting from current column until
		// result is either 0 or maxColumns
		ArrayList<String> quadrantIPositions = getConditionalPositions((a, b) -> a + b, (a, b) -> a + b);
		ArrayList<String> quadrantIIPositions = getConditionalPositions((a, b) -> a + b, (a, b) -> a - b);
		ArrayList<String> quadrantIIIPositions = getConditionalPositions((a, b) -> a - b, (a, b) -> a - b);
		ArrayList<String> quadrantIVPositions = getConditionalPositions((a, b) -> a - b, (a, b) -> a + b);
		
		allLegalMoves.addAll(quadrantIPositions);
		allLegalMoves.addAll(quadrantIIPositions);
		allLegalMoves.addAll(quadrantIIIPositions);
		allLegalMoves.addAll(quadrantIVPositions);

		return allLegalMoves;
	}

}
