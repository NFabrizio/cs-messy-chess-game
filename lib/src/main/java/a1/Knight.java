package a1;

import java.util.ArrayList;

import a1.ChessPiece.Color;

public class Knight extends ChessPiece {

	public Knight(ChessBoard board, Color color) {
		super(board, color);
	}
	
	@Override
	public String toString() {
		if (color == Color.WHITE) {
			return "\u2658";
		}
		
		return "\u265E";
	}
	
	@Override
	public ArrayList<String> legalMoves() {
		return new ArrayList<String>();
	}

}
