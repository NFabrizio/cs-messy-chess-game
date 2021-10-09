package a1;

import java.util.ArrayList;
import java.util.function.BiFunction;

public class King extends ChessPiece {
	// 0 base maximums
	private int maxRows = 7;
	private int maxColumns = 7;
	private int minimum = 0;
	private String columns = "abcdefgh";

	public King(ChessBoard board, Color color) {
		super(board, color);
	}

	@Override
	public String toString() {
		if (color == Color.WHITE) {
			return "\u2654";
		}

		return "\u265A";
	}

	private String convertIntToStringPosition(int row, int column) {
		return columns.charAt(column) + Integer.toString(row + 1);
	}

	private String getConditionalPositions(BiFunction<Integer, Integer, Integer> rowFn,
			BiFunction<Integer, Integer, Integer> columnFn) {
		String position = null;

		int newRow = rowFn.apply(this.row, 1);
		int newColumn = columnFn.apply(this.column, 1);

		// Before proceeding, ensure that row and column are both valid
		if (newRow <= maxRows && newRow >= minimum && newColumn <= maxColumns && newColumn >= minimum) {
			String newPosition = convertIntToStringPosition(newRow, newColumn);

			try {
				ChessPiece existingPiece = board.getPiece(newPosition);

				if (existingPiece != null && existingPiece.getColor() == this.getColor()) {
					return position;
				}

				// No piece exists, add position to legal moves array
				position = newPosition;

			} catch (IllegalPositionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return position;
	}

	@Override
	public ArrayList<String> legalMoves() {
		// The king can only move one square horizontally, vertically, or diagonally.
		// Assume that it cannot castle.

		ArrayList<String> allLegalMoves = new ArrayList<String>();
		ArrayList<String> tempLegalMoves = new ArrayList<String>();
		String forward;
		String forwardLeft;
		String forwardRight;
		String right;
		String left;
		String backward;
		String backwardRight;
		String backwardLeft;
		String currentPosition = this.getPosition();

		if (currentPosition != null) {
			forward = getConditionalPositions((a, b) -> a + b, (a, b) -> a);
			tempLegalMoves.add(forward);

			forwardLeft = getConditionalPositions((a, b) -> a + b, (a, b) -> a + b);
			tempLegalMoves.add(forwardLeft);

			forwardRight = getConditionalPositions((a, b) -> a + b, (a, b) -> a - b);
			tempLegalMoves.add(forwardRight);

			right = getConditionalPositions((a, b) -> a, (a, b) -> a + b);
			tempLegalMoves.add(right);

			left = getConditionalPositions((a, b) -> a, (a, b) -> a - b);
			tempLegalMoves.add(left);

			backward = getConditionalPositions((a, b) -> a - b, (a, b) -> a);
			tempLegalMoves.add(backward);

			backwardRight = getConditionalPositions((a, b) -> a - b, (a, b) -> a - b);
			tempLegalMoves.add(backwardRight);

			backwardLeft = getConditionalPositions((a, b) -> a - b, (a, b) -> a + b);
			tempLegalMoves.add(backwardLeft);

			// if so, allow one or two spaces forward
			// Check if there are pieces of opposite color diagonally forward from current
			// position
			// if so, allow diagonal move
			// If piece reaches end of board, return empty ArrayList

			int tempLegalMovesLength = tempLegalMoves.size();
			for (int i = 0; i < tempLegalMovesLength; i++) {
				String currentItem = tempLegalMoves.get(i);

				if (currentItem != null) {
					allLegalMoves.add(tempLegalMoves.get(i));
				}
			}
		}

		return allLegalMoves;
	}

}
