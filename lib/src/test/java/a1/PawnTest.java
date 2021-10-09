package a1;

import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;
import java.util.Arrays;

import a1.ChessPiece.Color;

import static org.junit.Assert.*;

public class PawnTest {
	private static ChessBoard board;

	@Before
	public void setUp() {
		board = new ChessBoard();
	}

	@Test
	public void testBlackPawnToString() {
		String blackPawnString = "\u265F";
		ChessPiece blackPawn = new Pawn(board, Color.BLACK);

		assertEquals(blackPawn.toString(), blackPawnString);
	}

	@Test
	public void testBlackPawnGetColor() {
		ChessPiece blackPawn = new Pawn(board, Color.BLACK);

		assertEquals(blackPawn.getColor(), Color.BLACK);
	}

	@Test
	public void testWhitePawnToString() {
		String whitePawnString = "\u2659";
		ChessPiece whitePawn = new Pawn(board, Color.WHITE);

		assertEquals(whitePawn.toString(), whitePawnString);
	}

	@Test
	public void testWhitePawnGetColor() {
		ChessPiece blackPawn = new Pawn(board, Color.WHITE);

		assertEquals(blackPawn.getColor(), Color.WHITE);
	}

	@Test
	public void testPawnLegalMovesEmptyWhenPieceNotOnBoard() {
		ChessPiece blackPawn = new Pawn(board, Color.BLACK);

		assertEquals(blackPawn.legalMoves(), new ArrayList<String>());
	}

	// Test moves from initial position for black pawn
	@Test
	public void testBlackPawnLegalMovesFromInitialPosition() {
		ArrayList<String> moves = new ArrayList<String>(Arrays.asList("c6", "c5"));
		ChessPiece blackPawn = new Pawn(board, Color.BLACK);
		try {
			blackPawn.setPosition("c7");
		} catch (IllegalPositionException e) {
			fail("IllegalPositionException for testBlackPawnLegalMovesFromInitialPosition");
		}

		ArrayList<String> allLegalMoves = blackPawn.legalMoves();

		// This pattern borrowed from
		// https://www.baeldung.com/java-assert-lists-equality-ignore-order
		assertTrue(allLegalMoves.containsAll(moves) && moves.containsAll(allLegalMoves));
	}

	// Test moves from initial position for white pawn
	@Test
	public void testWhitePawnLegalMovesFromInitialPosition() {
		ArrayList<String> moves = new ArrayList<String>(Arrays.asList("c3", "c4"));
		ChessPiece whitePawn = new Pawn(board, Color.WHITE);
		try {
			whitePawn.setPosition("c2");
		} catch (IllegalPositionException e) {
			fail("IllegalPositionException for testWhitePawnLegalMovesFromInitialPosition");
		}

		ArrayList<String> allLegalMoves = whitePawn.legalMoves();

		// This pattern borrowed from
		// https://www.baeldung.com/java-assert-lists-equality-ignore-order
		assertTrue(allLegalMoves.containsAll(moves) && moves.containsAll(allLegalMoves));
	}

	// Test moves from initial position for black pawn with piece of same color two
	// spaces ahead
	@Test
	public void testBlackPawnLegalMovesFromInitialPositionWithSameColorPieceTwoSpacesAhead() {
		ArrayList<String> moves = new ArrayList<String>(Arrays.asList("c6"));
		ChessPiece blackPawn = new Pawn(board, Color.BLACK);
		ChessPiece blackPawn2 = new Pawn(board, Color.BLACK);

		try {
			blackPawn.setPosition("c7");
		} catch (IllegalPositionException e) {
			fail("IllegalPositionException for testBlackPawnLegalMovesFromInitialPositionWithSameColorPieceTwoSpacesAhead");
		}

		board.placePiece(blackPawn2, "c5");

		ArrayList<String> allLegalMoves = blackPawn.legalMoves();

		// This pattern borrowed from
		// https://www.baeldung.com/java-assert-lists-equality-ignore-order
		assertTrue(allLegalMoves.containsAll(moves) && moves.containsAll(allLegalMoves));
	}

	// Test moves from initial position for white pawn with piece of same color two
	// spaces ahead
	@Test
	public void testWhitePawnLegalMovesFromInitialPositionWithSameColorPieceTwoSpacesAhead() {
		ArrayList<String> moves = new ArrayList<String>(Arrays.asList("c4"));
		ChessPiece whitePawn = new Pawn(board, Color.WHITE);
		ChessPiece whitePawn2 = new Pawn(board, Color.WHITE);

		try {
			whitePawn.setPosition("c3");
		} catch (IllegalPositionException e) {
			fail("IllegalPositionException for testWhitePawnLegalMovesFromInitialPositionWithSameColorPieceTwoSpacesAhead");
		}

		board.placePiece(whitePawn2, "c5");

		ArrayList<String> allLegalMoves = whitePawn.legalMoves();

		// This pattern borrowed from
		// https://www.baeldung.com/java-assert-lists-equality-ignore-order
		assertTrue(allLegalMoves.containsAll(moves) && moves.containsAll(allLegalMoves));
	}

	// Test moves from initial position for black pawn with piece of opposite color
	// two spaces ahead
	@Test
	public void testBlackPawnLegalMovesFromInitialPositionWithOppositeColorPieceTwoSpacesAhead() {
		ArrayList<String> moves = new ArrayList<String>(Arrays.asList("c6"));
		ChessPiece blackPawn = new Pawn(board, Color.BLACK);
		ChessPiece whitePawn = new Pawn(board, Color.WHITE);

		try {
			blackPawn.setPosition("c7");
		} catch (IllegalPositionException e) {
			fail("IllegalPositionException for testBlackPawnLegalMovesFromInitialPositionWithOppositeColorPieceTwoSpacesAhead");
		}

		board.placePiece(whitePawn, "c5");

		ArrayList<String> allLegalMoves = blackPawn.legalMoves();

		// This pattern borrowed from
		// https://www.baeldung.com/java-assert-lists-equality-ignore-order
		assertTrue(allLegalMoves.containsAll(moves) && moves.containsAll(allLegalMoves));
	}

	// Test moves from initial position for white pawn with piece of opposite color
	// two spaces ahead
	@Test
	public void testWhitePawnLegalMovesFromInitialPositionWithOppositeColorPieceTwoSpacesAhead() {
		ArrayList<String> moves = new ArrayList<String>(Arrays.asList("c4"));
		ChessPiece whitePawn = new Pawn(board, Color.WHITE);
		ChessPiece blackPawn = new Pawn(board, Color.BLACK);

		try {
			whitePawn.setPosition("c3");
		} catch (IllegalPositionException e) {
			fail("IllegalPositionException for testWhitePawnLegalMovesFromInitialPositionWithOppositeColorPieceTwoSpacesAhead");
		}

		board.placePiece(blackPawn, "c5");

		ArrayList<String> allLegalMoves = whitePawn.legalMoves();

		// This pattern borrowed from
		// https://www.baeldung.com/java-assert-lists-equality-ignore-order
		assertTrue(allLegalMoves.containsAll(moves) && moves.containsAll(allLegalMoves));
	}

	// Test moves for black pawn with same color pieces diagonally forward
	@Test
	public void testBlackPawnLegalMovesWithSameColorPieceDiagonallyForward() {
		ArrayList<String> moves = new ArrayList<String>(Arrays.asList("c5"));
		ChessPiece blackPawn = new Pawn(board, Color.BLACK);
		ChessPiece blackPawn2 = new Pawn(board, Color.BLACK);
		ChessPiece blackPawn3 = new Pawn(board, Color.BLACK);

		try {
			blackPawn.setPosition("c6");
		} catch (IllegalPositionException e) {
			fail("IllegalPositionException for testBlackPawnLegalMovesWithSameColorPieceDiagonallyForward");
		}

		board.placePiece(blackPawn2, "b5");
		board.placePiece(blackPawn3, "d5");

		ArrayList<String> allLegalMoves = blackPawn.legalMoves();

		// This pattern borrowed from
		// https://www.baeldung.com/java-assert-lists-equality-ignore-order
		assertTrue(allLegalMoves.containsAll(moves) && moves.containsAll(allLegalMoves));
	}

	// Test moves for white pawn with same color pieces diagonally forward
	@Test
	public void testWhitePawnLegalMovesWithSameColorPieceDiagonallyForward() {
		ArrayList<String> moves = new ArrayList<String>(Arrays.asList("c4"));
		ChessPiece whitePawn = new Pawn(board, Color.WHITE);
		ChessPiece whitePawn2 = new Pawn(board, Color.WHITE);
		ChessPiece whitePawn3 = new Pawn(board, Color.WHITE);

		try {
			whitePawn.setPosition("c3");
		} catch (IllegalPositionException e) {
			fail("IllegalPositionException for testBlackPawnLegalMovesWithSameColorPieceDiagonallyForward");
		}

		board.placePiece(whitePawn2, "b4");
		board.placePiece(whitePawn3, "d4");

		ArrayList<String> allLegalMoves = whitePawn.legalMoves();

		// This pattern borrowed from
		// https://www.baeldung.com/java-assert-lists-equality-ignore-order
		assertTrue(allLegalMoves.containsAll(moves) && moves.containsAll(allLegalMoves));
	}

	// Test moves for black pawn with opposite color pieces diagonally forward
	@Test
	public void testBlackPawnLegalMovesWithOppositeColorPieceDiagonallyForward() {
		ArrayList<String> moves = new ArrayList<String>(Arrays.asList("c5", "b5", "d5"));
		ChessPiece blackPawn = new Pawn(board, Color.BLACK);
		ChessPiece whitePawn1 = new Pawn(board, Color.WHITE);
		ChessPiece whitePawn2 = new Pawn(board, Color.WHITE);

		try {
			blackPawn.setPosition("c6");
		} catch (IllegalPositionException e) {
			fail("IllegalPositionException for testBlackPawnLegalMovesWithOppositeColorPieceDiagonallyForward");
		}

		board.placePiece(whitePawn1, "b5");
		board.placePiece(whitePawn2, "d5");

		ArrayList<String> allLegalMoves = blackPawn.legalMoves();

		// This pattern borrowed from
		// https://www.baeldung.com/java-assert-lists-equality-ignore-order
		assertTrue(allLegalMoves.containsAll(moves) && moves.containsAll(allLegalMoves));
	}

	// Test moves for white pawn with opposite color pieces diagonally forward
	@Test
	public void testWhitePawnLegalMovesWithOppositeColorPieceDiagonallyForward() {
		ArrayList<String> moves = new ArrayList<String>(Arrays.asList("c4", "b4", "d4"));
		ChessPiece whitePawn = new Pawn(board, Color.WHITE);
		ChessPiece blackPawn1 = new Pawn(board, Color.BLACK);
		ChessPiece blackPawn2 = new Pawn(board, Color.BLACK);

		try {
			whitePawn.setPosition("c3");
		} catch (IllegalPositionException e) {
			fail("IllegalPositionException for testBlackPawnLegalMovesWithOppositeColorPieceDiagonallyForward");
		}

		board.placePiece(blackPawn1, "b4");
		board.placePiece(blackPawn2, "d4");

		ArrayList<String> allLegalMoves = whitePawn.legalMoves();

		// This pattern borrowed from
		// https://www.baeldung.com/java-assert-lists-equality-ignore-order
		assertTrue(allLegalMoves.containsAll(moves) && moves.containsAll(allLegalMoves));
	}

	// Test moves for black pawn with same color piece in front of it
	@Test
	public void testBlackPawnLegalMovesWithSameColorPieceForward() {
		ArrayList<String> moves = new ArrayList<String>();
		ChessPiece blackPawn = new Pawn(board, Color.BLACK);
		ChessPiece blackPawn2 = new Pawn(board, Color.BLACK);

		try {
			blackPawn.setPosition("c6");
		} catch (IllegalPositionException e) {
			fail("IllegalPositionException for testBlackPawnLegalMovesWithSameColorPieceForward");
		}

		board.placePiece(blackPawn2, "c5");

		ArrayList<String> allLegalMoves = blackPawn.legalMoves();

		// This pattern borrowed from
		// https://www.baeldung.com/java-assert-lists-equality-ignore-order
		assertTrue(allLegalMoves.containsAll(moves) && moves.containsAll(allLegalMoves));
	}

	// Test moves for white pawn with same color piece in front of it
	@Test
	public void testWhitePawnLegalMovesWithSameColorPieceForward() {
		ArrayList<String> moves = new ArrayList<String>();
		ChessPiece whitePawn = new Pawn(board, Color.WHITE);
		ChessPiece whitePawn2 = new Pawn(board, Color.WHITE);

		try {
			whitePawn.setPosition("c3");
		} catch (IllegalPositionException e) {
			fail("IllegalPositionException for testWhitePawnLegalMovesWithSameColorPieceForward");
		}

		board.placePiece(whitePawn2, "c4");

		ArrayList<String> allLegalMoves = whitePawn.legalMoves();

		// This pattern borrowed from
		// https://www.baeldung.com/java-assert-lists-equality-ignore-order
		assertTrue(allLegalMoves.containsAll(moves) && moves.containsAll(allLegalMoves));
	}

	// Test moves for black pawn with opposite color piece in front of it
	@Test
	public void testBlackPawnLegalMovesWithOppositeColorPieceForward() {
		ArrayList<String> moves = new ArrayList<String>();
		ChessPiece blackPawn = new Pawn(board, Color.BLACK);
		ChessPiece whitePawn1 = new Pawn(board, Color.WHITE);

		try {
			blackPawn.setPosition("c6");
		} catch (IllegalPositionException e) {
			fail("IllegalPositionException for testBlackPawnLegalMovesWithOppositeColorPieceForward");
		}

		board.placePiece(whitePawn1, "c5");

		ArrayList<String> allLegalMoves = blackPawn.legalMoves();

		// This pattern borrowed from
		// https://www.baeldung.com/java-assert-lists-equality-ignore-order
		assertTrue(allLegalMoves.containsAll(moves) && moves.containsAll(allLegalMoves));
	}

	// Test moves for white pawn with opposite color piece in front of it
	@Test
	public void testWhitePawnLegalMovesWithOppositeColorPieceForward() {
		ArrayList<String> moves = new ArrayList<String>();
		ChessPiece whitePawn = new Pawn(board, Color.WHITE);
		ChessPiece blackPawn1 = new Pawn(board, Color.BLACK);

		try {
			whitePawn.setPosition("c3");
		} catch (IllegalPositionException e) {
			fail("IllegalPositionException for testWhitePawnLegalMovesWithOppositeColorPieceForward");
		}

		board.placePiece(blackPawn1, "c4");

		ArrayList<String> allLegalMoves = whitePawn.legalMoves();

		// This pattern borrowed from
		// https://www.baeldung.com/java-assert-lists-equality-ignore-order
		assertTrue(allLegalMoves.containsAll(moves) && moves.containsAll(allLegalMoves));
	}

	// Test moves for black pawn at end of board
	@Test
	public void testBlackPawnLegalMovesAtEndOfBoard() {
		ArrayList<String> moves = new ArrayList<String>();
		ChessPiece blackPawn = new Pawn(board, Color.BLACK);

		try {
			blackPawn.setPosition("c1");
		} catch (IllegalPositionException e) {
			fail("IllegalPositionException for testBlackPawnLegalMovesAtEndOfBoard");
		}

		ArrayList<String> allLegalMoves = blackPawn.legalMoves();

		// This pattern borrowed from
		// https://www.baeldung.com/java-assert-lists-equality-ignore-order
		assertTrue(allLegalMoves.containsAll(moves) && moves.containsAll(allLegalMoves));
	}

	// Test moves for white pawn at end of board
	@Test
	public void testWhitePawnLegalMovesAtEndOfBoard() {
		ArrayList<String> moves = new ArrayList<String>();
		ChessPiece whitePawn = new Pawn(board, Color.WHITE);

		try {
			whitePawn.setPosition("c8");
		} catch (IllegalPositionException e) {
			fail("IllegalPositionException for testWhitePawnLegalMovesAtEndOfBoard");
		}

		ArrayList<String> allLegalMoves = whitePawn.legalMoves();

		// This pattern borrowed from
		// https://www.baeldung.com/java-assert-lists-equality-ignore-order
		assertTrue(allLegalMoves.containsAll(moves) && moves.containsAll(allLegalMoves));
	}

	@Test
	public void testPawnSetAndGetPositionHappyPath() {
		String position = "a6";
		ChessPiece blackPawn = new Pawn(board, Color.BLACK);

		try {
			blackPawn.setPosition(position);
			assertEquals(blackPawn.getPosition(), position);
		} catch (IllegalPositionException e) {
			fail("IllegalPositionException for testPawnSetPositionHappyPath");
		}
	}

	@Test
	public void testPawnGetPositionReturnsNullWhenPieceIsNotOnBoard() {
		ChessPiece blackPawn = new Pawn(board, Color.BLACK);

		assertNull(blackPawn.getPosition());
	}

	@Test
	public void testPawnSetPositionWithIncorrectLength() {
		ChessPiece blackPawn = new Pawn(board, Color.BLACK);

		try {
			blackPawn.setPosition("abc123");
		} catch (IllegalPositionException e) {
			return;
		}

		fail("expected IllegalPositionException for testPawnSetPositionWithIncorrectLength");
	}

	@Test
	public void testPawnSetInvalidPositionWithCorrectLength() {
		ChessPiece blackPawn = new Pawn(board, Color.BLACK);

		try {
			blackPawn.setPosition("6a");
		} catch (IllegalPositionException e) {
			return;
		}

		fail("expected IllegalPositionException for testPawnSetInvalidPositionWithCorrectLength");
	}

	@Test
	public void testPawnSetOutOfBoundsColumnPosition() {
		ChessPiece blackPawn = new Pawn(board, Color.BLACK);

		try {
			blackPawn.setPosition("m8");
		} catch (IllegalPositionException e) {
			return;
		}

		fail("expected IllegalPositionException for testPawnSetOutOfBoundsColumnPosition");
	}

	@Test
	public void testPawnSetOutOfBoundsRowPosition() {
		ChessPiece blackPawn = new Pawn(board, Color.BLACK);

		try {
			blackPawn.setPosition("c9");
		} catch (IllegalPositionException e) {
			return;
		}

		fail("expected IllegalPositionException for testPawnSetOutOfBoundsRowPosition");
	}
}
