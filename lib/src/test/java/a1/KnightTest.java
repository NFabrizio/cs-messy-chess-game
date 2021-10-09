package a1;

import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;

import a1.ChessPiece.Color;

import static org.junit.Assert.*;

public class KnightTest {
	private static ChessBoard board;
	
	@Before
	public void setUp() {
		board = new ChessBoard();
	}
	
    @Test 
    public void testBlackKnightToString() {
    	String blackKnightString = "\u265E";
    	ChessPiece blackKnight = new Knight(board, Color.BLACK);
    	
        assertEquals(blackKnight.toString(), blackKnightString);
    }
	
    @Test 
    public void testBlackKnightGetColor() {
    	ChessPiece blackKnight = new Knight(board, Color.BLACK);
    	
        assertEquals(blackKnight.getColor(), Color.BLACK);
    }
    
    @Test 
    public void testWhiteKnightToString() {
    	String whiteKnightString = "\u2658";
    	ChessPiece whiteKnight = new Knight(board, Color.WHITE);

        assertEquals(whiteKnight.toString(), whiteKnightString);
    }
	
    @Test 
    public void testWhiteKnightGetColor() {
    	ChessPiece blackKnight = new Knight(board, Color.WHITE);
    	
        assertEquals(blackKnight.getColor(), Color.WHITE);
    }
    
    @Test
    public void testKnightLegalMoves() {
    	ChessPiece whiteKnight = new Knight(board, Color.WHITE);
    	
    	assertEquals(whiteKnight.legalMoves(), new ArrayList<String>());
    }

	@Test
	public void testKnightSetAndGetPositionHappyPath() {
		String position = "a6";
		ChessPiece blackKnight = new Knight(board, Color.BLACK);

		try {
			blackKnight.setPosition(position);
			assertEquals(blackKnight.getPosition(), position);
		} catch (IllegalPositionException e) {
			fail("IllegalPositionException for testKnightSetPositionHappyPath");
		}
	}

	@Test
	public void testKnightGetPositionReturnsNullWhenPieceIsNotOnBoard() {
		ChessPiece blackKnight = new Knight(board, Color.BLACK);

		assertNull(blackKnight.getPosition());
	}

	@Test
	public void testKnightSetPositionWithIncorrectLength() {
		ChessPiece blackKnight = new Knight(board, Color.BLACK);

		try {
			blackKnight.setPosition("abc123");
		} catch (IllegalPositionException e) {
			return;
		}

		fail("expected IllegalPositionException for testKnightSetPositionWithIncorrectLength");
	}

	@Test
	public void testKnightSetInvalidPositionWithCorrectLength() {
		ChessPiece blackKnight = new Knight(board, Color.BLACK);

		try {
			blackKnight.setPosition("6a");
		} catch (IllegalPositionException e) {
			return;
		}

		fail("expected IllegalPositionException for testKnightSetInvalidPositionWithCorrectLength");
	}

	@Test
	public void testKnightSetOutOfBoundsColumnPosition() {
		ChessPiece blackKnight = new Knight(board, Color.BLACK);

		try {
			blackKnight.setPosition("m8");
		} catch (IllegalPositionException e) {
			return;
		}

		fail("expected IllegalPositionException for testKnightSetOutOfBoundsColumnPosition");
	}

	@Test
	public void testKnightSetOutOfBoundsRowPosition() {
		ChessPiece blackKnight = new Knight(board, Color.BLACK);

		try {
			blackKnight.setPosition("c9");
		} catch (IllegalPositionException e) {
			return;
		}

		fail("expected IllegalPositionException for testKnightSetOutOfBoundsRowPosition");
	}
}
