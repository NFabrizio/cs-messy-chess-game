package a1;

import java.util.ArrayList;

import a1.ChessPiece.Color;

public class ChessBoard {
	private ChessPiece[][] board;
	private String columns = "abcdefgh";
	private boolean isInitializing = true;

	public ChessBoard() {
		board = new ChessPiece[8][8];
	}

	private int[] convertPositionToInt(String position) {
		int[] intPosition = new int[2];

		char colChar = position.charAt(0);
		char rowChar = position.charAt(1);

		// This pattern borrowed from https://www.javatpoint.com/java-char-to-int
		// Subtract 1 from rowChar integer to convert to 0 base
		intPosition[0] = Integer.parseInt(String.valueOf(rowChar)) - 1;
		intPosition[1] = columns.indexOf(colChar);

		return intPosition;
	}

	private void setIsInitializingFalse() {
		this.isInitializing = false;
	}

	public void initialize() {
		// Set up black pieces
		ChessPiece blackRook0 = new Rook(this, Color.BLACK);
		placePiece(blackRook0, "a8");
		ChessPiece blackKnight0 = new Knight(this, Color.BLACK);
		placePiece(blackKnight0, "b8");
		ChessPiece blackBishop0 = new Bishop(this, Color.BLACK);
		placePiece(blackBishop0, "c8");
		ChessPiece blackQueen = new Queen(this, Color.BLACK);
		placePiece(blackQueen, "d8");
		ChessPiece blackKing = new King(this, Color.BLACK);
		placePiece(blackKing, "e8");
		ChessPiece blackBishop1 = new Bishop(this, Color.BLACK);
		placePiece(blackBishop1, "f8");
		ChessPiece blackKnight1 = new Knight(this, Color.BLACK);
		placePiece(blackKnight1, "g8");
		ChessPiece blackRook1 = new Rook(this, Color.BLACK);
		placePiece(blackRook1, "h8");

		int columnLength = columns.length();
		for (int i = 0; i < columnLength; i++) {
			ChessPiece blackPawn = new Pawn(this, Color.BLACK);
			placePiece(blackPawn, columns.charAt(i) + "7");
		}

		// Set up white pieces
		ChessPiece whiteRook0 = new Rook(this, Color.WHITE);
		placePiece(whiteRook0, "a1");
		ChessPiece whiteKnight0 = new Knight(this, Color.WHITE);
		placePiece(whiteKnight0, "b1");
		ChessPiece whiteBishop0 = new Bishop(this, Color.WHITE);
		placePiece(whiteBishop0, "c1");
		ChessPiece whiteQueen = new Queen(this, Color.WHITE);
		placePiece(whiteQueen, "d1");
		ChessPiece whiteKing = new King(this, Color.WHITE);
		placePiece(whiteKing, "e1");
		ChessPiece whiteBishop1 = new Bishop(this, Color.WHITE);
		placePiece(whiteBishop1, "f1");
		ChessPiece whiteKnight1 = new Knight(this, Color.WHITE);
		placePiece(whiteKnight1, "g1");
		ChessPiece whiteRook1 = new Rook(this, Color.WHITE);
		placePiece(whiteRook1, "h1");

		for (int i = 0; i < columnLength; i++) {
			ChessPiece whitePawn = new Pawn(this, Color.WHITE);
			placePiece(whitePawn, columns.charAt(i) + "2");
		}

		setIsInitializingFalse();
	}

	public ChessPiece getPiece(String position) throws IllegalPositionException {
		if (position.length() != 2) {
			throw new IllegalPositionException("Position has incorrect number of characters",
					new Exception("Position must have length of exactly 2"));
		}

		try {
			// Convert position to int
			int[] intPosition = convertPositionToInt(position);

			// Return piece from board
			int row = intPosition[0];
			int column = intPosition[1];

			return board[row][column];

			// Catch the types of errors that can occur from conversion and invalid
			// positions
		} catch (IndexOutOfBoundsException | NumberFormatException err) {
			throw new IllegalPositionException("Illegal position", err);
		}
	}

	private void movePieceOnBoard(String originalPosition, String newPosition, ChessPiece piece) throws IllegalPositionException {
		ChessPiece capturedPiece;
		try {
			capturedPiece = getPiece(newPosition);

			if (capturedPiece != null) {
				capturedPiece.setPosition(null);
			}
		} catch (IllegalPositionException e) {
			throw new IllegalPositionException("Error while capturing piece", e);
		}
		
		// Convert position to row & column
		int[] intPosition = convertPositionToInt(newPosition);

		// Move piece to new position
		int row = intPosition[0];
		int column = intPosition[1];
		board[row][column] = piece;

		if (isInitializing != true) {
			// After successfully placing piece in new position, remove it from old position
			int[] intOldPosition = convertPositionToInt(originalPosition);
			int oldRow = intOldPosition[0];
			int oldColumn = intOldPosition[1];

			board[oldRow][oldColumn] = null;
		}
	}

	public boolean placePiece(ChessPiece piece, String position) {
		if (position.length() != 2) {
			return false;
		}

		String oldPosition = "";

		// Get piece current position
		if (isInitializing != true) {
			oldPosition = piece.getPosition();
		}

		// Place piece on board if move is legal, return true if successful, false if
		// piece of same color already exists or move is illegal
		// 1. Check if position is within piece legal moves
		ArrayList<String> legalMoves = piece.legalMoves();
		boolean isMoveLegal = legalMoves.contains(position);

		if (!isMoveLegal && isInitializing != true) {
			return false;
		}

		try {
			movePieceOnBoard(oldPosition, position, piece);

			// Call setPosition on piece if successful
			piece.setPosition(position);
		} catch (IndexOutOfBoundsException | NumberFormatException | IllegalPositionException err) {
			return false;
		}

		return true;
	}

	public void move(String fromPosition, String toPosition) throws IllegalMoveException {
		if (fromPosition.length() != 2 || toPosition.length() != 2) {
			throw new IllegalMoveException("Illegal move", new Exception("Invalid from or to position provided"));
		}
		
		// Get piece at fromPosition
		ChessPiece piece;
		try {
			piece = this.getPiece(fromPosition);
		} catch (IllegalPositionException e) {
			throw new IllegalMoveException("Illegal move: unable to get piece at fromPosition", e);
		}
		
		// If no piece found at from position, stop and throw
		if(piece == null) {
			throw new IllegalMoveException("Illegal move", new Exception("No piece located at from position"));
		}

		// Check that toPosition is within legal moves for piece at fromPosition
		ArrayList<String> legalMoves = piece.legalMoves();
		boolean isMoveLegal = legalMoves.contains(toPosition);

		if (!isMoveLegal && isInitializing != true) {
			throw new IllegalMoveException("Illegal move", new Exception("Illegal to position provided"));
		}

		// All checks have passed, so move piece and capture any opponent's piece in toPosition
		try {
			movePieceOnBoard(fromPosition, toPosition, piece);

			// Call setPosition on piece if successful
			piece.setPosition(toPosition);
		} catch (IndexOutOfBoundsException | NumberFormatException | IllegalPositionException err) {
			throw new IllegalMoveException("Illegal move", err);
		}

	}

	public String toString() {
		String chess = "";
		String upperLeft = "\u250C";
		String upperRight = "\u2510";
		String horizontalLine = "\u2500";
		String horizontal3 = horizontalLine + "\u3000" + horizontalLine;
		String verticalLine = "\u2502";
		String upperT = "\u252C";
		String bottomLeft = "\u2514";
		String bottomRight = "\u2518";
		String bottomT = "\u2534";
		String plus = "\u253C";
		String leftT = "\u251C";
		String rightT = "\u2524";

		String topLine = upperLeft;
		for (int i = 0; i < 7; i++) {
			topLine += horizontal3 + upperT;
		}
		topLine += horizontal3 + upperRight;

		String bottomLine = bottomLeft;
		for (int i = 0; i < 7; i++) {
			bottomLine += horizontal3 + bottomT;
		}
		bottomLine += horizontal3 + bottomRight;
		chess += topLine + "\n";

		for (int row = 7; row >= 0; row--) {
			String midLine = "";
			for (int col = 0; col < 8; col++) {
				if (board[row][col] == null) {
					midLine += verticalLine + " \u3000 ";
				} else {
					midLine += verticalLine + " " + board[row][col] + " ";
				}
			}
			midLine += verticalLine;
			String midLine2 = leftT;
			for (int i = 0; i < 7; i++) {
				midLine2 += horizontal3 + plus;
			}
			midLine2 += horizontal3 + rightT;
			chess += midLine + "\n";
			if (row >= 1)
				chess += midLine2 + "\n";
		}

		chess += bottomLine;
		return chess;
	}
}
