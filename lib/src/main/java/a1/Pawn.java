package a1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.BiFunction;

public class Pawn extends ChessPiece {
	// 0 base maximums
	private int maxRows = 7;
	private int maxColumns = 7;
	private int minimum = 0;
	private String columns = "abcdefgh";

	public Pawn(ChessBoard board, Color color) {
		super(board, color);
	}

	@Override
	public String toString() {
		if (color == Color.WHITE) {
			return "\u2659";
		}

		return "\u265F";
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

				if (existingPiece != null) {
					// There is a piece in the newPosition, it is not in the same column as the
					// current piece and it is the opposite color of the current piece
					if (this.column != newColumn && existingPiece.getColor() != this.getColor()) {
						position = newPosition;
					}

					return position;
				}

				// No piece exists, add position to legal moves array
				if (this.column == newColumn) {
					position = newPosition;
				}
			} catch (IllegalPositionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return position;
	}

	@Override
	public ArrayList<String> legalMoves() {
		// A pawn in the initial position may move one or two squares vertically forward
		// to an empty square but cannot leap over any piece. Subsequently it can move
		// only one square vertically forward to an empty square. A pawn may also
		// capture (replace) an opponent's piece diagonally one square in front of it.
		// Pawns can never move backwards. These are the only moves; we will not
		// implement the En passant rule and will also not allow promotion to another
		// piece if the pawn reaches the end of the column.
		ArrayList<String> initialBlackPositions = new ArrayList<String>(
				Arrays.asList("a7", "b7", "c7", "d7", "e7", "f7", "g7", "h7"));
		ArrayList<String> initialWhitePositions = new ArrayList<String>(
				Arrays.asList("a2", "b2", "c2", "d2", "e2", "f2", "g2", "h2"));
		ArrayList<String> allLegalMoves = new ArrayList<String>();
		ArrayList<String> tempLegalMoves = new ArrayList<String>();
		String forwardOne;
		String forwardOneLeft;
		String forwardOneRight;
		String forwardTwo;

		// Check if piece is in initial position by using color and current position
		Color currentPieceColor = this.getColor();
		String currentPosition = this.getPosition();

		// For black pieces, check c-1, c-1&r+1, c-1&r-1, c-2->if initial
		if (currentPieceColor == Color.BLACK && currentPosition != null) {
			boolean isInitial = initialBlackPositions.contains(currentPosition);

			forwardOne = getConditionalPositions((a, b) -> a - b, (a, b) -> a);
			tempLegalMoves.add(forwardOne);

			if (isInitial && forwardOne != null) {
				forwardTwo = getConditionalPositions((a, b) -> a - (b * 2), (a, b) -> a);
				tempLegalMoves.add(forwardTwo);
			}

			forwardOneLeft = getConditionalPositions((a, b) -> a - b, (a, b) -> a + b);
			tempLegalMoves.add(forwardOneLeft);

			forwardOneRight = getConditionalPositions((a, b) -> a - b, (a, b) -> a - b);
			tempLegalMoves.add(forwardOneRight);
		}

		// For white pieces, check c+1, c+1&r+1, c+1&r-1, c+2->if initial
		if (currentPieceColor == Color.WHITE && currentPosition != null) {
			boolean isInitial = initialWhitePositions.contains(currentPosition);

			forwardOne = getConditionalPositions((a, b) -> a + b, (a, b) -> a);
			tempLegalMoves.add(forwardOne);

			if (isInitial && forwardOne != null) {
				forwardTwo = getConditionalPositions((a, b) -> a + (b * 2), (a, b) -> a);
				tempLegalMoves.add(forwardTwo);
			}

			forwardOneLeft = getConditionalPositions((a, b) -> a + b, (a, b) -> a + 1);
			tempLegalMoves.add(forwardOneLeft);

			forwardOneRight = getConditionalPositions((a, b) -> a + b, (a, b) -> a - 1);
			tempLegalMoves.add(forwardOneRight);
		}
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

		return allLegalMoves;
	}

}
