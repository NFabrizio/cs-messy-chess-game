package a1;

import java.util.ArrayList;
import java.util.function.BiFunction;

public class Rook extends ChessPiece {
	// 0 base maximums
	private int maxRows = 7;
	private int maxColumns = 7;
	private int minimum = 0;
	private String columns = "abcdefgh";

	public Rook(ChessBoard board, Color color) {
		super(board, color);
	}

	@Override
	public String toString() {
		if (color == Color.WHITE) {
			return "\u2656";
		}

		return "\u265C";
	}

	private String convertIntToStringPosition(int row, int column) {
		return columns.charAt(column) + Integer.toString(row + 1);
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

	@Override
	public ArrayList<String> legalMoves() {
		// A rook can move any number of squares horizontally or vertically, forward or
		// backward, as long as it does not have to leap over other pieces. At the end
		// of the move, it can occupy a previously empty square or capture (replace) an
		// opponent's piece but it cannot replace another piece of the same player.

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
		ArrayList<String> horizontalRightPositions = getConditionalPositions((a, b) -> a, (a, b) -> a + b);
		ArrayList<String> horizontalLeftPositions = getConditionalPositions((a, b) -> a, (a, b) -> a - b);
		ArrayList<String> verticalTopPositions = getConditionalPositions((a, b) -> a + b, (a, b) -> a);
		ArrayList<String> verticalBottomPositions = getConditionalPositions((a, b) -> a - b, (a, b) -> a);

		allLegalMoves.addAll(horizontalRightPositions);
		allLegalMoves.addAll(horizontalLeftPositions);
		allLegalMoves.addAll(verticalTopPositions);
		allLegalMoves.addAll(verticalBottomPositions);

		return allLegalMoves;
	}

}
