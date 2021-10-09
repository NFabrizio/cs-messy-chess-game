package a1;

import java.util.ArrayList;

public class Queen extends ChessPiece {

	public Queen(ChessBoard board, Color color) {
		super(board, color);
	}
	
	@Override
	public String toString() {
		if (color == Color.WHITE) {
			return "\u2655";
		}
		
		return "\u265B";
	}
	
	@Override
	public ArrayList<String> legalMoves() {
		return new ArrayList<String>();
	}

}
