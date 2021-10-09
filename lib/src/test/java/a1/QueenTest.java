package a1;

import a1.ChessPiece.Color;
import java.util.ArrayList;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class QueenTest {
	private static ChessBoard board;

	@Before
	public void setUp() {
		board = new ChessBoard();
	}

	@Test
	public void testBlackQueenToString() {
		String blackQueenString = "\u265B";
		ChessPiece blackQueen = new Queen(board, Color.BLACK);

		assertEquals(blackQueen.toString(), blackQueenString);
	}

	@Test
	public void testBlackQueenGetColor() {
		ChessPiece blackQueen = new Queen(board, Color.BLACK);

		assertEquals(blackQueen.getColor(), Color.BLACK);
	}

	@Test
	public void testWhiteQueenToString() {
		String whiteQueenString = "\u2655";
		ChessPiece whiteQueen = new Queen(board, Color.WHITE);

		assertEquals(whiteQueen.toString(), whiteQueenString);
	}

	@Test
	public void testWhiteQueenGetColor() {
		ChessPiece blackQueen = new Queen(board, Color.WHITE);

		assertEquals(blackQueen.getColor(), Color.WHITE);
	}

	@Test
	public void testQueenLegalMoves() {
		ChessPiece whiteQueen = new Queen(board, Color.WHITE);

		assertEquals(whiteQueen.legalMoves(), new ArrayList<String>());
	}

	@Test
	public void testQueenSetAndGetPositionHappyPath() {
		String position = "h4";
		ChessPiece blackQueen = new Queen(board, Color.BLACK);

		try {
			blackQueen.setPosition(position);
			assertEquals(blackQueen.getPosition(), position);
		} catch (IllegalPositionException e) {
			fail("IllegalPositionException for testQueenSetPositionHappyPath");
		}
	}

	@Test
	public void testQueenGetPositionReturnsNullWhenPieceIsNotOnBoard() {
		ChessPiece blackQueen = new Queen(board, Color.BLACK);

		assertNull(blackQueen.getPosition());
	}

	@Test
	public void testQueenSetPositionWithIncorrectLength() {
		ChessPiece blackQueen = new Queen(board, Color.BLACK);

		try {
			blackQueen.setPosition("abc123");
		} catch (IllegalPositionException e) {
			return;
		}

		fail("expected IllegalPositionException for testQueenSetPositionWithIncorrectLength");
	}

	@Test
	public void testQueenSetInvalidPositionWithCorrectLength() {
		ChessPiece blackQueen = new Queen(board, Color.BLACK);

		try {
			blackQueen.setPosition("4h");
		} catch (IllegalPositionException e) {
			return;
		}

		fail("expected IllegalPositionException for testQueenSetInvalidPositionWithCorrectLength");
	}

	@Test
	public void testQueenSetOutOfBoundsColumnPosition() {
		ChessPiece blackQueen = new Queen(board, Color.BLACK);

		try {
			blackQueen.setPosition("m8");
		} catch (IllegalPositionException e) {
			return;
		}

		fail("expected IllegalPositionException for testQueenSetOutOfBoundsColumnPosition");
	}

	@Test
	public void testQueenSetOutOfBoundsRowPosition() {
		ChessPiece blackQueen = new Queen(board, Color.BLACK);

		try {
			blackQueen.setPosition("c9");
		} catch (IllegalPositionException e) {
			return;
		}

		fail("expected IllegalPositionException for testQueenSetOutOfBoundsRowPosition");
	}
}
