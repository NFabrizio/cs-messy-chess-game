package a1;

import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;
import java.util.Arrays;

import a1.ChessPiece.Color;

import static org.junit.Assert.*;

public class RookTest {
	private static ChessBoard board;

	@Before
	public void setUp() {
		board = new ChessBoard();
	}

	@Test
	public void testBlackRookToString() {
		String blackRookString = "\u265C";
		ChessPiece blackRook = new Rook(board, Color.BLACK);

		assertEquals(blackRook.toString(), blackRookString);
	}

	@Test
	public void testBlackRookGetColor() {
		ChessPiece blackRook = new Rook(board, Color.BLACK);

		assertEquals(blackRook.getColor(), Color.BLACK);
	}

	@Test
	public void testWhiteRookToString() {
		String whiteRookString = "\u2656";
		ChessPiece whiteRook = new Rook(board, Color.WHITE);

		assertEquals(whiteRook.toString(), whiteRookString);
	}

	@Test
	public void testWhiteRookGetColor() {
		ChessPiece blackRook = new Rook(board, Color.WHITE);

		assertEquals(blackRook.getColor(), Color.WHITE);
	}

	@Test
	public void testRookLegalMovesEmptyWhenPieceNotOnBoard() {
		ChessPiece blackRook = new Rook(board, Color.BLACK);

		assertEquals(blackRook.legalMoves(), new ArrayList<String>());
	}

	// Test when piece is at a1 and can move
	@Test
	public void testRookLegalMovesFromA1() {
		ArrayList<String> moves = new ArrayList<String>(
				Arrays.asList("b1", "c1", "d1", "e1", "f1", "g1", "h1", "a2", "a3", "a4", "a5", "a6", "a7", "a8"));
		ChessPiece blackRook = new Rook(board, Color.BLACK);
		try {
			blackRook.setPosition("a1");
		} catch (IllegalPositionException e) {
			fail("IllegalPositionException for testRookLegalMovesFromA1");
		}

		ArrayList<String> allLegalMoves = blackRook.legalMoves();

		// This pattern borrowed from
		// https://www.baeldung.com/java-assert-lists-equality-ignore-order
		assertTrue(allLegalMoves.containsAll(moves) && moves.containsAll(allLegalMoves));
	}

	// Test when piece is at h1 and can move
	@Test
	public void testRookLegalMovesFromH1() {
		ArrayList<String> moves = new ArrayList<String>(
				Arrays.asList("b1", "c1", "d1", "e1", "f1", "g1", "a1", "h2", "h3", "h4", "h5", "h6", "h7", "h8"));
		ChessPiece blackRook = new Rook(board, Color.BLACK);
		try {
			blackRook.setPosition("h1");
		} catch (IllegalPositionException e) {
			fail("IllegalPositionException for testRookLegalMovesFromA1");
		}

		ArrayList<String> allLegalMoves = blackRook.legalMoves();

		assertTrue(allLegalMoves.containsAll(moves) && moves.containsAll(allLegalMoves));
	}

	// Test when piece is at h8 and can move
	@Test
	public void testRookLegalMovesFromH8() {
		ArrayList<String> moves = new ArrayList<String>(
				Arrays.asList("b8", "c8", "d8", "e8", "f8", "g8", "a8", "h2", "h3", "h4", "h5", "h6", "h7", "h1"));
		ChessPiece blackRook = new Rook(board, Color.BLACK);
		try {
			blackRook.setPosition("h8");
		} catch (IllegalPositionException e) {
			fail("IllegalPositionException for testRookLegalMovesFromA1");
		}

		ArrayList<String> allLegalMoves = blackRook.legalMoves();

		assertTrue(allLegalMoves.containsAll(moves) && moves.containsAll(allLegalMoves));
	}

	// Test when piece is at a8 and can move
	@Test
	public void testRookLegalMovesFromA8() {
		ArrayList<String> moves = new ArrayList<String>(
				Arrays.asList("b8", "c8", "d8", "e8", "f8", "g8", "h8", "a2", "a3", "a4", "a5", "a6", "a7", "a1"));
		ChessPiece blackRook = new Rook(board, Color.BLACK);
		try {
			blackRook.setPosition("a8");
		} catch (IllegalPositionException e) {
			fail("IllegalPositionException for testRookLegalMovesFromA1");
		}

		ArrayList<String> allLegalMoves = blackRook.legalMoves();

		assertTrue(allLegalMoves.containsAll(moves) && moves.containsAll(allLegalMoves));
	}

	// Test when piece is at d4 and can move
	@Test
	public void testRookLegalMovesFromD4() {
		ArrayList<String> moves = new ArrayList<String>(
				Arrays.asList("d1", "d2", "d3", "d5", "d6", "d7", "d8", "a4", "b4", "c4", "e4", "f4", "g4", "h4"));
		ChessPiece blackRook = new Rook(board, Color.BLACK);
		try {
			blackRook.setPosition("d4");
		} catch (IllegalPositionException e) {
			fail("IllegalPositionException for testRookLegalMovesFromA1");
		}

		ArrayList<String> allLegalMoves = blackRook.legalMoves();

		assertTrue(allLegalMoves.containsAll(moves) && moves.containsAll(allLegalMoves));
	}

	// Test when piece cannot move due to being surrounded by same color pieces
	@Test
	public void testRookLegalMovesEmptyWhenSurroundedBySameColorPieces() {
		ChessPiece blackRook = new Rook(board, Color.BLACK);
		ChessPiece blackPawn1 = new Pawn(board, Color.BLACK);
		ChessPiece blackPawn2 = new Pawn(board, Color.BLACK);
		ChessPiece blackPawn3 = new Pawn(board, Color.BLACK);
		ChessPiece blackPawn4 = new Pawn(board, Color.BLACK);
		try {
			blackRook.setPosition("d4");
		} catch (IllegalPositionException e) {
			fail("IllegalPositionException for testRookLegalMovesEmptyWhenSurroundedBySameColorPieces");
		}

		board.placePiece(blackPawn1, "d3");
		board.placePiece(blackPawn2, "d5");
		board.placePiece(blackPawn3, "c4");
		board.placePiece(blackPawn4, "e4");

		assertEquals(blackRook.legalMoves(), new ArrayList<String>());
	}

	// Test when pieces of opposite color block certain paths
	@Test
	public void testRookLegalMovesWhenSurroundedByOppositeColorPieces() {
		ArrayList<String> moves = new ArrayList<String>(Arrays.asList("d3", "d5", "c4", "e4"));
		ChessPiece blackRook = new Rook(board, Color.BLACK);
		ChessPiece whitePawn1 = new Pawn(board, Color.WHITE);
		ChessPiece whitePawn2 = new Pawn(board, Color.WHITE);
		ChessPiece whitePawn3 = new Pawn(board, Color.WHITE);
		ChessPiece whitePawn4 = new Pawn(board, Color.WHITE);
		try {
			blackRook.setPosition("d4");
			whitePawn1.setPosition("d2");
			whitePawn2.setPosition("d4");
			whitePawn3.setPosition("c3");
			whitePawn4.setPosition("e3");
		} catch (IllegalPositionException e) {
			fail("IllegalPositionException for testRookLegalMovesWhenSurroundedByOppositeColorPieces");
		}

		board.placePiece(whitePawn1, "d3");
		board.placePiece(whitePawn2, "d5");
		board.placePiece(whitePawn3, "c4");
		board.placePiece(whitePawn4, "e4");

		ArrayList<String> allLegalMoves = blackRook.legalMoves();

		assertTrue(allLegalMoves.containsAll(moves) && moves.containsAll(allLegalMoves));
	}

	@Test
	public void testRookSetAndGetPositionHappyPath() {
		String position = "a3";
		ChessPiece blackRook = new Rook(board, Color.BLACK);

		try {
			blackRook.setPosition(position);
			assertEquals(blackRook.getPosition(), position);
		} catch (IllegalPositionException e) {
			fail("IllegalPositionException for testRookSetPositionHappyPath");
		}
	}

	@Test
	public void testRookGetPositionReturnsNullWhenPieceIsNotOnBoard() {
		ChessPiece blackRook = new Rook(board, Color.BLACK);

		assertNull(blackRook.getPosition());
	}

	@Test
	public void testRookSetPositionWithIncorrectLength() {
		ChessPiece blackRook = new Rook(board, Color.BLACK);

		try {
			blackRook.setPosition("abc123");
		} catch (IllegalPositionException e) {
			return;
		}

		fail("expected IllegalPositionException for testRookSetPositionWithIncorrectLength");
	}

	@Test
	public void testRookSetInvalidPositionWithCorrectLength() {
		ChessPiece blackRook = new Rook(board, Color.BLACK);

		try {
			blackRook.setPosition("3a");
		} catch (IllegalPositionException e) {
			return;
		}

		fail("expected IllegalPositionException for testRookSetInvalidPositionWithCorrectLength");
	}

	@Test
	public void testRookSetOutOfBoundsColumnPosition() {
		ChessPiece blackRook = new Rook(board, Color.BLACK);

		try {
			blackRook.setPosition("m8");
		} catch (IllegalPositionException e) {
			return;
		}

		fail("expected IllegalPositionException for testRookSetOutOfBoundsColumnPosition");
	}

	@Test
	public void testRookSetOutOfBoundsRowPosition() {
		ChessPiece blackRook = new Rook(board, Color.BLACK);

		try {
			blackRook.setPosition("c9");
		} catch (IllegalPositionException e) {
			return;
		}

		fail("expected IllegalPositionException for testRookSetOutOfBoundsRowPosition");
	}
}
