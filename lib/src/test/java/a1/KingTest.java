package a1;

import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;
import java.util.Arrays;

import a1.ChessPiece.Color;

import static org.junit.Assert.*;

public class KingTest {
	private static ChessBoard board;

	@Before
	public void setUp() {
		board = new ChessBoard();
	}

	@Test
	public void testBlackKingToString() {
		String blackKingString = "\u265A";
		ChessPiece blackKing = new King(board, Color.BLACK);

		assertEquals(blackKing.toString(), blackKingString);
	}

	@Test
	public void testBlackKingGetColor() {
		ChessPiece blackKing = new King(board, Color.BLACK);

		assertEquals(blackKing.getColor(), Color.BLACK);
	}

	@Test
	public void testWhiteKingToString() {
		String whiteKingString = "\u2654";
		ChessPiece whiteKing = new King(board, Color.WHITE);

		assertEquals(whiteKing.toString(), whiteKingString);
	}

	@Test
	public void testWhiteKingGetColor() {
		ChessPiece blackKing = new King(board, Color.WHITE);

		assertEquals(blackKing.getColor(), Color.WHITE);
	}

	@Test
	public void testPawnLegalMovesEmptyWhenPieceNotOnBoard() {
		ChessPiece whiteKing = new King(board, Color.WHITE);

		assertEquals(whiteKing.legalMoves(), new ArrayList<String>());
	}

	// Test moves when king is at top edge of board
	@Test
	public void testKingLegalMovesAtTopEdgeOfBoard() {
		ArrayList<String> moves = new ArrayList<String>(Arrays.asList("d8", "d7", "e7", "f7", "f8"));
		ChessPiece blackKing = new King(board, Color.BLACK);
		try {
			blackKing.setPosition("e8");
		} catch (IllegalPositionException e) {
			fail("IllegalPositionException for testKingLegalMovesAtTopEdgeOfBoard");
		}

		ArrayList<String> allLegalMoves = blackKing.legalMoves();

		// This pattern borrowed from
		// https://www.baeldung.com/java-assert-lists-equality-ignore-order
		assertTrue(allLegalMoves.containsAll(moves) && moves.containsAll(allLegalMoves));
	}

	// Test moves when king is at right edge of board
	@Test
	public void testKingLegalMovesAtRightEdgeOfBoard() {
		ArrayList<String> moves = new ArrayList<String>(Arrays.asList("h7", "g7", "g6", "g5", "h5"));
		ChessPiece blackKing = new King(board, Color.BLACK);
		try {
			blackKing.setPosition("h6");
		} catch (IllegalPositionException e) {
			fail("IllegalPositionException for testKingLegalMovesAtRightEdgeOfBoard");
		}

		ArrayList<String> allLegalMoves = blackKing.legalMoves();

		// This pattern borrowed from
		// https://www.baeldung.com/java-assert-lists-equality-ignore-order
		assertTrue(allLegalMoves.containsAll(moves) && moves.containsAll(allLegalMoves));
	}

	// Test moves when king is at bottom edge of board
	@Test
	public void testKingLegalMovesAtBottomEdgeOfBoard() {
		ArrayList<String> moves = new ArrayList<String>(Arrays.asList("d1", "d2", "e2", "f2", "f1"));
		ChessPiece blackKing = new King(board, Color.BLACK);
		try {
			blackKing.setPosition("e1");
		} catch (IllegalPositionException e) {
			fail("IllegalPositionException for testKingLegalMovesAtBottomEdgeOfBoard");
		}

		ArrayList<String> allLegalMoves = blackKing.legalMoves();

		// This pattern borrowed from
		// https://www.baeldung.com/java-assert-lists-equality-ignore-order
		assertTrue(allLegalMoves.containsAll(moves) && moves.containsAll(allLegalMoves));
	}

	// Test moves when king is at left edge of board
	@Test
	public void testKingLegalMovesAtLeftEdgeOfBoard() {
		ArrayList<String> moves = new ArrayList<String>(Arrays.asList("a4", "b4", "b3", "b2", "a2"));
		ChessPiece blackKing = new King(board, Color.BLACK);
		try {
			blackKing.setPosition("a3");
		} catch (IllegalPositionException e) {
			fail("IllegalPositionException for testKingLegalMovesAtLeftEdgeOfBoard");
		}

		ArrayList<String> allLegalMoves = blackKing.legalMoves();

		// This pattern borrowed from
		// https://www.baeldung.com/java-assert-lists-equality-ignore-order
		assertTrue(allLegalMoves.containsAll(moves) && moves.containsAll(allLegalMoves));
	}
	
	// Test moves when king is in corner of board
	@Test
	public void testKingLegalMovesAtCornerOfBoard() {
		ArrayList<String> moves;
		ChessPiece blackKing = new King(board, Color.BLACK);
		ArrayList<String> allLegalMoves;
		
		try {
			blackKing.setPosition("a1");
		} catch (IllegalPositionException e) {
			fail("IllegalPositionException for testKingLegalMovesAtCornerOfBoard at a1");
		}

		moves = new ArrayList<String>(Arrays.asList("a2", "b2", "b1"));
		allLegalMoves = blackKing.legalMoves();

		// This pattern borrowed from
		// https://www.baeldung.com/java-assert-lists-equality-ignore-order
		assertTrue(allLegalMoves.containsAll(moves) && moves.containsAll(allLegalMoves));

		try {
			blackKing.setPosition("h1");
		} catch (IllegalPositionException e) {
			fail("IllegalPositionException for testKingLegalMovesAtCornerOfBoard at h1");
		}

		moves = new ArrayList<String>(Arrays.asList("h2", "g2", "g1"));
		allLegalMoves = blackKing.legalMoves();

		assertTrue(allLegalMoves.containsAll(moves) && moves.containsAll(allLegalMoves));

		try {
			blackKing.setPosition("h8");
		} catch (IllegalPositionException e) {
			fail("IllegalPositionException for testKingLegalMovesAtCornerOfBoard at h8");
		}

		moves = new ArrayList<String>(Arrays.asList("h7", "g7", "g8"));
		allLegalMoves = blackKing.legalMoves();

		assertTrue(allLegalMoves.containsAll(moves) && moves.containsAll(allLegalMoves));

		try {
			blackKing.setPosition("a8");
		} catch (IllegalPositionException e) {
			fail("IllegalPositionException for testKingLegalMovesAtCornerOfBoard at a8");
		}

		moves = new ArrayList<String>(Arrays.asList("a7", "b7", "b8"));
		allLegalMoves = blackKing.legalMoves();

		assertTrue(allLegalMoves.containsAll(moves) && moves.containsAll(allLegalMoves));
	}
	
	// Test moves when king is in center of board
	@Test
	public void testKingLegalMovesAtCenterOfBoard() {
		ArrayList<String> moves = new ArrayList<String>(Arrays.asList("d5", "e5", "e4", "e3", "d3", "c3", "c4", "c5"));
		ChessPiece blackKing = new King(board, Color.BLACK);
		try {
			blackKing.setPosition("d4");
		} catch (IllegalPositionException e) {
			fail("IllegalPositionException for testKingLegalMovesAtCenterOfBoard");
		}

		ArrayList<String> allLegalMoves = blackKing.legalMoves();

		// This pattern borrowed from
		// https://www.baeldung.com/java-assert-lists-equality-ignore-order
		assertTrue(allLegalMoves.containsAll(moves) && moves.containsAll(allLegalMoves));
	}
	
	// Test when king is surrounded by pieces of same color
	@Test
	public void testKingLegalMovesWhenSurroundedByPiecesOfSameColor() {
		ArrayList<String> moves = new ArrayList<String>();
		ChessPiece blackKing = new King(board, Color.BLACK);
    	ChessPiece blackPawn1 = new Pawn(board, Color.BLACK);
    	ChessPiece blackPawn2 = new Pawn(board, Color.BLACK);
    	ChessPiece blackPawn3 = new Pawn(board, Color.BLACK);
    	ChessPiece blackPawn4 = new Pawn(board, Color.BLACK);
    	ChessPiece blackPawn5 = new Pawn(board, Color.BLACK);
    	ChessPiece blackPawn6 = new Pawn(board, Color.BLACK);
    	ChessPiece blackPawn7 = new Pawn(board, Color.BLACK);
    	ChessPiece blackPawn8 = new Pawn(board, Color.BLACK);
		
		try {
			blackKing.setPosition("d4");
		} catch (IllegalPositionException e) {
			fail("IllegalPositionException for testKingLegalMovesWhenSurroundedByPiecesOfSameColor");
		}

		board.placePiece(blackPawn1, "d5");
		board.placePiece(blackPawn2, "e5");
		board.placePiece(blackPawn3, "e4");
		board.placePiece(blackPawn4, "e3");
		board.placePiece(blackPawn5, "d3");
		board.placePiece(blackPawn6, "c3");
		board.placePiece(blackPawn7, "c4");
		board.placePiece(blackPawn8, "c5");

		ArrayList<String> allLegalMoves = blackKing.legalMoves();

		// This pattern borrowed from
		// https://www.baeldung.com/java-assert-lists-equality-ignore-order
		assertTrue(allLegalMoves.containsAll(moves) && moves.containsAll(allLegalMoves));
	}
	
	// Test when king is surrounded by pieces of opposite color
	@Test
	public void testKingLegalMovesWhenSurroundedByPiecesOfOppositeColor() {
		ArrayList<String> moves = new ArrayList<String>(Arrays.asList("d5", "e5", "e4", "e3", "d3", "c3", "c4", "c5"));
		ChessPiece blackKing = new King(board, Color.BLACK);
    	ChessPiece whitePawn1 = new Pawn(board, Color.WHITE);
    	ChessPiece whitePawn2 = new Pawn(board, Color.WHITE);
    	ChessPiece whitePawn3 = new Pawn(board, Color.WHITE);
    	ChessPiece whitePawn4 = new Pawn(board, Color.WHITE);
    	ChessPiece whitePawn5 = new Pawn(board, Color.WHITE);
    	ChessPiece whitePawn6 = new Pawn(board, Color.WHITE);
    	ChessPiece whitePawn7 = new Pawn(board, Color.WHITE);
    	ChessPiece whitePawn8 = new Pawn(board, Color.WHITE);
		
		try {
			blackKing.setPosition("d4");
		} catch (IllegalPositionException e) {
			fail("IllegalPositionException for testKingLegalMovesWhenSurroundedByPiecesOfOppositeColor");
		}

		board.placePiece(whitePawn1, "d5");
		board.placePiece(whitePawn2, "e5");
		board.placePiece(whitePawn3, "e4");
		board.placePiece(whitePawn4, "e3");
		board.placePiece(whitePawn5, "d3");
		board.placePiece(whitePawn6, "c3");
		board.placePiece(whitePawn7, "c4");
		board.placePiece(whitePawn8, "c5");

		ArrayList<String> allLegalMoves = blackKing.legalMoves();

		// This pattern borrowed from
		// https://www.baeldung.com/java-assert-lists-equality-ignore-order
		assertTrue(allLegalMoves.containsAll(moves) && moves.containsAll(allLegalMoves));
	}

	@Test
	public void testKingSetAndGetPositionHappyPath() {
		String position = "f8";
		ChessPiece blackKing = new King(board, Color.BLACK);

		try {
			blackKing.setPosition(position);
			assertEquals(blackKing.getPosition(), position);
		} catch (IllegalPositionException e) {
			fail("IllegalPositionException for testKingSetPositionHappyPath");
		}
	}

	@Test
	public void testKingGetPositionReturnsNullWhenPieceIsNotOnBoard() {
		ChessPiece blackKing = new King(board, Color.BLACK);

		assertNull(blackKing.getPosition());
	}

	@Test
	public void testKingSetPositionWithIncorrectLength() {
		ChessPiece blackKing = new King(board, Color.BLACK);

		try {
			blackKing.setPosition("abc123");
		} catch (IllegalPositionException e) {
			return;
		}

		fail("expected IllegalPositionException for testKingSetPositionWithIncorrectLength");
	}

	@Test
	public void testKingSetInvalidPositionWithCorrectLength() {
		ChessPiece blackKing = new King(board, Color.BLACK);

		try {
			blackKing.setPosition("8f");
		} catch (IllegalPositionException e) {
			return;
		}

		fail("expected IllegalPositionException for testKingSetInvalidPositionWithCorrectLength");
	}

	@Test
	public void testKingSetOutOfBoundsColumnPosition() {
		ChessPiece blackKing = new King(board, Color.BLACK);

		try {
			blackKing.setPosition("m8");
		} catch (IllegalPositionException e) {
			return;
		}

		fail("expected IllegalPositionException for testKingSetOutOfBoundsColumnPosition");
	}

	@Test
	public void testKingSetOutOfBoundsRowPosition() {
		ChessPiece blackKing = new King(board, Color.BLACK);

		try {
			blackKing.setPosition("c9");
		} catch (IllegalPositionException e) {
			return;
		}

		fail("expected IllegalPositionException for testKingSetOutOfBoundsRowPosition");
	}
}
