package a1;

import java.util.ArrayList;
import java.util.Arrays;
import org.junit.Before;
import org.junit.Test;

import a1.ChessPiece.Color;

import static org.junit.Assert.*;

public class BishopTest {
	private static ChessBoard board;
	
	@Before
	public void setUp() {
		board = new ChessBoard();
	}
	
    @Test 
    public void testBlackBishopToString() {
    	String blackBishopString = "\u265D";
    	ChessPiece blackBishop = new Bishop(board, Color.BLACK);
    	
        assertEquals(blackBishop.toString(), blackBishopString);
    }
	
    @Test 
    public void testBlackBishopGetColor() {
    	ChessPiece blackBishop = new Bishop(board, Color.BLACK);
    	
        assertEquals(blackBishop.getColor(), Color.BLACK);
    }
    
    @Test 
    public void testWhiteBishopToString() {
    	String whiteBishopString = "\u2657";
    	ChessPiece whiteBishop = new Bishop(board, Color.WHITE);

        assertEquals(whiteBishop.toString(), whiteBishopString);
    }
	
    @Test 
    public void testWhiteBishopGetColor() {
    	ChessPiece blackBishop = new Bishop(board, Color.WHITE);
    	
        assertEquals(blackBishop.getColor(), Color.WHITE);
    }
    
    @Test
    public void testBishopLegalMovesEmptyWhenPieceNotOnBoard() {
    	ChessPiece blackBishop = new Bishop(board, Color.BLACK);
    	
    	assertEquals(blackBishop.legalMoves(), new ArrayList<String>());
    }

    // Test when piece is at a1 and can move
    @Test
    public void testBishopLegalMovesFromA1() {
    	ArrayList<String> moves = new ArrayList<String>( Arrays.asList("b2", "c3", "d4", "e5", "f6", "g7", "h8") );
    	ChessPiece blackBishop = new Bishop(board, Color.BLACK);
		try {
			blackBishop.setPosition("a1");
		} catch (IllegalPositionException e) {
			fail("IllegalPositionException for testBishopLegalMovesFromA1");
		}

		ArrayList<String> allLegalMoves = blackBishop.legalMoves();
		
		// This pattern borrowed from https://www.baeldung.com/java-assert-lists-equality-ignore-order
    	assertTrue(allLegalMoves.containsAll(moves) && moves.containsAll(allLegalMoves));
    }
    
    // Test when piece is at h1 and can move
    @Test
    public void testBishopLegalMovesFromH1() {
    	ArrayList<String> moves = new ArrayList<String>( Arrays.asList("g2", "f3", "e4", "d5", "c6", "b7", "a8") );
    	ChessPiece blackBishop = new Bishop(board, Color.BLACK);
		try {
			blackBishop.setPosition("h1");
		} catch (IllegalPositionException e) {
			fail("IllegalPositionException for testBishopLegalMovesFromA1");
		}

		ArrayList<String> allLegalMoves = blackBishop.legalMoves();
		
    	assertTrue(allLegalMoves.containsAll(moves) && moves.containsAll(allLegalMoves));
    }
    
    // Test when piece is at h8 and can move
    @Test
    public void testBishopLegalMovesFromH8() {
    	ArrayList<String> moves = new ArrayList<String>( Arrays.asList("g7", "f6", "e5", "d4", "c3", "b2", "a1") );
    	ChessPiece blackBishop = new Bishop(board, Color.BLACK);
		try {
			blackBishop.setPosition("h8");
		} catch (IllegalPositionException e) {
			fail("IllegalPositionException for testBishopLegalMovesFromA1");
		}

		ArrayList<String> allLegalMoves = blackBishop.legalMoves();
		
    	assertTrue(allLegalMoves.containsAll(moves) && moves.containsAll(allLegalMoves));
    }
    
    // Test when piece is at a8 and can move
    @Test
    public void testBishopLegalMovesFromA8() {
    	ArrayList<String> moves = new ArrayList<String>( Arrays.asList("b7", "c6", "d5", "e4", "f3", "g2", "h1") );
    	ChessPiece blackBishop = new Bishop(board, Color.BLACK);
		try {
			blackBishop.setPosition("a8");
		} catch (IllegalPositionException e) {
			fail("IllegalPositionException for testBishopLegalMovesFromA1");
		}

		ArrayList<String> allLegalMoves = blackBishop.legalMoves();
		
    	assertTrue(allLegalMoves.containsAll(moves) && moves.containsAll(allLegalMoves));
    }
    
    // Test when piece is at d4 and can move
    @Test
    public void testBishopLegalMovesFromD4() {
    	ArrayList<String> moves = new ArrayList<String>( Arrays.asList("e3", "e5", "f2", "f6", "g1", "g7", "h8", "c3", "c5", "b2", "b6", "a1", "a7") );
    	ChessPiece blackBishop = new Bishop(board, Color.BLACK);
		try {
			blackBishop.setPosition("d4");
		} catch (IllegalPositionException e) {
			fail("IllegalPositionException for testBishopLegalMovesFromA1");
		}

		ArrayList<String> allLegalMoves = blackBishop.legalMoves();
		
    	assertTrue(allLegalMoves.containsAll(moves) && moves.containsAll(allLegalMoves));
    }
    
    // Test when piece cannot move due to being surrounded by same color pieces
    @Test
    public void testBishopLegalMovesEmptyWhenSurroundedBySameColorPieces() {
    	ChessPiece blackBishop = new Bishop(board, Color.BLACK);
    	ChessPiece blackPawn1 = new Pawn(board, Color.BLACK);
    	ChessPiece blackPawn2 = new Pawn(board, Color.BLACK);
    	ChessPiece blackPawn3 = new Pawn(board, Color.BLACK);
    	ChessPiece blackPawn4 = new Pawn(board, Color.BLACK);
		try {
			blackBishop.setPosition("d4");
		} catch (IllegalPositionException e) {
			fail("IllegalPositionException for testBishopLegalMovesEmptyWhenSurroundedBySameColorPieces");
		}

		board.placePiece(blackPawn1, "c3");
		board.placePiece(blackPawn2, "c5");
		board.placePiece(blackPawn3, "e3");
		board.placePiece(blackPawn4, "e5");
    	
    	assertEquals(blackBishop.legalMoves(), new ArrayList<String>());
    }
    
    // Test when pieces of opposite color block certain paths
    @Test
    public void testBishopLegalMovesWhenSurroundedByOppositeColorPieces() {
    	ArrayList<String> moves = new ArrayList<String>( Arrays.asList("c3", "c5", "e3", "e5") );
    	ChessPiece blackBishop = new Bishop(board, Color.BLACK);
    	ChessPiece whitePawn1 = new Pawn(board, Color.WHITE);
    	ChessPiece whitePawn2 = new Pawn(board, Color.WHITE);
    	ChessPiece whitePawn3 = new Pawn(board, Color.WHITE);
    	ChessPiece whitePawn4 = new Pawn(board, Color.WHITE);
		try {
			blackBishop.setPosition("d4");
		} catch (IllegalPositionException e) {
			fail("IllegalPositionException for testBishopLegalMovesWhenSurroundedByOppositeColorPieces");
		}

		board.placePiece(whitePawn1, "c3");
		board.placePiece(whitePawn2, "c5");
		board.placePiece(whitePawn3, "e3");
		board.placePiece(whitePawn4, "e5");

		ArrayList<String> allLegalMoves = blackBishop.legalMoves();
		
    	assertTrue(allLegalMoves.containsAll(moves) && moves.containsAll(allLegalMoves));
    }
    

	@Test
	public void testBishopSetAndGetPositionHappyPath() {
		String position = "c3";
		ChessPiece blackBishop = new Bishop(board, Color.BLACK);

		try {
			blackBishop.setPosition(position);
			assertEquals(blackBishop.getPosition(), position);
		} catch (IllegalPositionException e) {
			fail("IllegalPositionException for testBishopSetPositionHappyPath");
		}
	}

	@Test
	public void testBishopGetPositionReturnsNullWhenPieceIsNotOnBoard() {
		ChessPiece blackBishop = new Bishop(board, Color.BLACK);

		assertNull(blackBishop.getPosition());
	}

	@Test
	public void testBishopSetPositionWithIncorrectLength() {
		ChessPiece blackBishop = new Bishop(board, Color.BLACK);

		try {
			blackBishop.setPosition("abc123");
		} catch (IllegalPositionException e) {
			return;
		}

		fail("expected IllegalPositionException for testBishopSetPositionWithIncorrectLength");
	}

	@Test
	public void testBishopSetInvalidPositionWithCorrectLength() {
		ChessPiece blackBishop = new Bishop(board, Color.BLACK);

		try {
			blackBishop.setPosition("3c");
		} catch (IllegalPositionException e) {
			return;
		}

		fail("expected IllegalPositionException for testBishopSetInvalidPositionWithCorrectLength");
	}

	@Test
	public void testBishopSetOutOfBoundsColumnPosition() {
		ChessPiece blackBishop = new Bishop(board, Color.BLACK);

		try {
			blackBishop.setPosition("m8");
		} catch (IllegalPositionException e) {
			return;
		}

		fail("expected IllegalPositionException for testBishopSetOutOfBoundsColumnPosition");
	}

	@Test
	public void testBishopSetOutOfBoundsRowPosition() {
		ChessPiece blackBishop = new Bishop(board, Color.BLACK);

		try {
			blackBishop.setPosition("c9");
		} catch (IllegalPositionException e) {
			return;
		}

		fail("expected IllegalPositionException for testBishopSetOutOfBoundsRowPosition");
	}

	@Test
	public void testBishopSetPositionToNull() {
		ChessPiece blackBishop = new Bishop(board, Color.BLACK);

		try {
			blackBishop.setPosition(null);
		} catch (IllegalPositionException e) {
			fail("expected IllegalPositionException for testBishopSetPositionToNull");
		}
	}
}
