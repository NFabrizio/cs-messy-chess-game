package a1;

import java.util.ArrayList;

public abstract class ChessPiece {
	
	public enum Color {WHITE, BLACK};

	private String columns = "abcdefgh";
	private int maxRows = 8;
	private boolean isCaptured = false;
	
	protected ChessBoard board;
	protected int row = maxRows;
	protected int column;
	protected Color color;
	
	public ChessPiece(ChessBoard board, Color color) {
		this.board = board;
		this.color = color;
	}
	
	private void setIsCapturedTrue() {
		this.isCaptured = true;
	}
	
	public Color getColor() {
		return color;
	}
	
	public String getPosition() {
		// If row/column null or piece isCaptured, return null because piece is not on board
		if (isCaptured == true || row >= maxRows || column > columns.length()) {
			return null;
		}
		
		// Convert row and column ints to String format, increment row to convert out of 0 base
		return columns.charAt(column) + Integer.toString(row + 1);
	}
	
	public void setPosition(String position) throws IllegalPositionException {
		// If position is null piece has been captured, set flag to true and return
		if (position == null) {
			setIsCapturedTrue();
			return;
		}
		
		if (position.length() != 2) {
			throw new IllegalPositionException("Position has incorrect number of characters",
					new Exception("Position must have length of exactly 2"));
		}

		try {
			// Convert position to int
			char colChar = position.charAt(0);
			char rowChar = position.charAt(1);
			
			int tempRow = Integer.parseInt(String.valueOf(rowChar)) - 1;
			int tempColumn = columns.indexOf(colChar);

			// Ensure that row and column values are valid before setting
			if(tempColumn < 0 || tempRow >= maxRows) {
				throw new IllegalPositionException("Illegal position", new Exception("Invalid row or column provided"));
			}

			// This pattern borrowed from https://www.javatpoint.com/java-char-to-int
			// Subtract 1 from rowChar integer to convert to 0 base
			row = tempRow;
			column = tempColumn;

			// Catch the types of errors that can occur from conversion and invalid
			// positions
		} catch (NumberFormatException err) {
			throw new IllegalPositionException("Illegal position", err);
		}
	}
	
	abstract public String toString();
	
	abstract public ArrayList<String> legalMoves();
}
