package a1;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class ChessBoardTest {
	private static ChessBoard board;
	private static String whiteKingString = "\u2654";
	private static String whiteQueenString = "\u2655";
	private static String whiteRookString = "\u2656";
	private static String whiteBishopString = "\u2657";
	private static String whiteKnightString = "\u2658";
	private static String whitePawnString = "\u2659";
	private static String blackKingString = "\u265A";
	private static String blackQueenString = "\u265B";
	private static String blackRookString = "\u265C";
	private static String blackBishopString = "\u265D";
	private static String blackKnightString = "\u265E";
	private static String blackPawnString = "\u265F";
	private String columns = "abcdefgh";

	@Before
	public void setUp() {
		board = new ChessBoard();
		board.initialize();
	}
	
	@Test
	public void testGetPieceHappyPath() {
		try {
			ChessPiece blackRook = board.getPiece("a8");
			assertTrue(blackRook.toString().equals(blackRookString));
		} catch (IllegalPositionException e) {
			fail("IllegalPositionException for testGetPieceHappyPath");
		}
	}
	
	@Test
	public void testGetPiecePositionWithIncorrectLength() {
		try {
			ChessPiece blackRook = board.getPiece("abc123");
			assertTrue(blackRook.toString().equals(blackRookString));
		} catch (IllegalPositionException e) {
			return;
		}
		
		fail("expected IllegalPositionException for testGetPieceIllegalPosition");
	}
	
	@Test
	public void testGetPieceInvalidPositionWithCorrectLength() {
		try {
			ChessPiece blackRook = board.getPiece("8a");
			assertTrue(blackRook.toString().equals(blackRookString));
		} catch (IllegalPositionException e) {
			return;
		}
		
		fail("expected IllegalPositionException for testGetPieceIllegalPosition");
	}
	
	@Test
	public void testGetPieceOutOfBoundsColumnPosition() {
		try {
			ChessPiece blackRook = board.getPiece("m8");
			assertTrue(blackRook.toString().equals(blackRookString));
		} catch (IllegalPositionException e) {
			return;
		}
		
		fail("expected IllegalPositionException for testGetPieceIllegalPosition");
	}
	
	@Test
	public void testGetPieceOutOfBoundsRowPosition() {
		try {
			ChessPiece blackRook = board.getPiece("a9");
			assertTrue(blackRook.toString().equals(blackRookString));
		} catch (IllegalPositionException e) {
			return;
		}
		
		fail("expected IllegalPositionException for testGetPieceIllegalPosition");
	}

	@Test
	public void testInitialBlackRook1Position() {
		try {
			ChessPiece blackRook = board.getPiece("a8");
			assertTrue(blackRook.toString().equals(blackRookString));
		} catch (IllegalPositionException e) {
			fail("IllegalPositionException for testInitialBlackRook1Position");
		}
	}

	@Test
	public void testInitialBlackKnight1Position() {
		try {
			ChessPiece blackKnight = board.getPiece("b8");
			assertTrue(blackKnight.toString().equals(blackKnightString));
		} catch (IllegalPositionException e) {
			fail("IllegalPositionException for testInitialBlackKnight1Position");
		}
	}

	@Test
	public void testInitialBlackBishop1Position() {
		try {
			ChessPiece blackBishop = board.getPiece("c8");
			assertTrue(blackBishop.toString().equals(blackBishopString));
		} catch (IllegalPositionException e) {
			fail("IllegalPositionException for testInitialBlackBishop1Position");
		}
	}

	@Test
	public void testInitialBlackQueenPosition() {
		try {
			ChessPiece blackQueen = board.getPiece("d8");
			assertTrue(blackQueen.toString().equals(blackQueenString));
		} catch (IllegalPositionException e) {
			fail("IllegalPositionException for testInitialBlackQueenPosition");
		}
	}

	@Test
	public void testInitialBlackKingPosition() {
		try {
			ChessPiece blackKing = board.getPiece("e8");
			assertTrue(blackKing.toString().equals(blackKingString));
		} catch (IllegalPositionException e) {
			fail("IllegalPositionException for testInitialBlackKingPosition");
		}
	}

	@Test
	public void testInitialBlackBishop2Position() {
		try {
			ChessPiece blackBishop2 = board.getPiece("f8");
			assertTrue(blackBishop2.toString().equals(blackBishopString));
		} catch (IllegalPositionException e) {
			fail("IllegalPositionException for testInitialBlackBishop2Position");
		}
	}

	@Test
	public void testInitialBlackKnight2Position() {
		try {
			ChessPiece blackKnight2 = board.getPiece("g8");
			assertTrue(blackKnight2.toString().equals(blackKnightString));
		} catch (IllegalPositionException e) {
			fail("IllegalPositionException for testInitialBlackKnight2Position");
		}
	}

	@Test
	public void testInitialBlackRook2Position() {
		try {
			ChessPiece blackRook2 = board.getPiece("h8");
			assertTrue(blackRook2.toString().equals(blackRookString));
		} catch (IllegalPositionException e) {
			fail("IllegalPositionException for testInitialBlackRook2Position");
		}
	}

	@Test
	public void testInitialBlackPawnPositions() {
		int columnLength = columns.length();

		for (int i = 0; i < columnLength; i++) {
			try {
				ChessPiece blackPawn = board.getPiece(columns.charAt(i) + "7");
				assertTrue(blackPawn.toString().equals(blackPawnString));
			} catch (IllegalPositionException e) {
				fail("IllegalPositionException for testInitialBlackPawnPositions");
			}
		}
	}

	@Test
	public void testInitialWhiteRook1Position() {
		try {
			ChessPiece whiteRook = board.getPiece("a1");
			assertTrue(whiteRook.toString().equals(whiteRookString));
		} catch (IllegalPositionException e) {
			fail("IllegalPositionException for testInitialWhiteRook1Position");
		}
	}

	@Test
	public void testInitialWhiteKnight1Position() {
		try {
			ChessPiece whiteKnight = board.getPiece("b1");
			assertTrue(whiteKnight.toString().equals(whiteKnightString));
		} catch (IllegalPositionException e) {
			fail("IllegalPositionException for testInitialWhiteKnight1Position");
		}
	}

	@Test
	public void testInitialWhiteBishop1Position() {
		try {
			ChessPiece whiteBishop = board.getPiece("c1");
			assertTrue(whiteBishop.toString().equals(whiteBishopString));
		} catch (IllegalPositionException e) {
			fail("IllegalPositionException for testInitialWhiteBishop1Position");
		}
	}

	@Test
	public void testInitialWhiteQueenPosition() {
		try {
			ChessPiece whiteQueen = board.getPiece("d1");
			assertTrue(whiteQueen.toString().equals(whiteQueenString));
		} catch (IllegalPositionException e) {
			fail("IllegalPositionException for testInitialWhiteQueenPosition");
		}
	}

	@Test
	public void testInitialWhiteKingPosition() {
		try {
			ChessPiece whiteKing = board.getPiece("e1");
			assertTrue(whiteKing.toString().equals(whiteKingString));
		} catch (IllegalPositionException e) {
			fail("IllegalPositionException for testInitialWhiteKingPosition");
		}
	}

	@Test
	public void testInitialWhiteBishop2Position() {
		try {
			ChessPiece whiteBishop2 = board.getPiece("f1");
			assertTrue(whiteBishop2.toString().equals(whiteBishopString));
		} catch (IllegalPositionException e) {
			fail("IllegalPositionException for testInitialWhiteBishop2Position");
		}
	}

	@Test
	public void testInitialWhiteKnight2Position() {
		try {
			ChessPiece whiteKnight2 = board.getPiece("g1");
			assertTrue(whiteKnight2.toString().equals(whiteKnightString));
		} catch (IllegalPositionException e) {
			fail("IllegalPositionException for testInitialWhiteKnight2Position");
		}
	}

	@Test
	public void testInitialWhiteRook2Position() {
		try {
			ChessPiece whiteRook2 = board.getPiece("h1");
			assertTrue(whiteRook2.toString().equals(whiteRookString));
		} catch (IllegalPositionException e) {
			fail("IllegalPositionException for testInitialWhiteRook2Position");
		}
	}

	@Test
	public void testInitialWhitePawnPositions() {
		int columnLength = columns.length();

		for (int i = 0; i < columnLength; i++) {
			try {
				ChessPiece whitePawn = board.getPiece(columns.charAt(i) + "2");
				assertTrue(whitePawn.toString().equals(whitePawnString));
			} catch (IllegalPositionException e) {
				fail("IllegalPositionException for testInitialWhitePawnPositions");
			}
		}
	}
	
	@Test
	public void testPlacePiecePositionWithIncorrectLength() {
		try {
			ChessPiece blackRook = board.getPiece("a8");
			assertFalse(board.placePiece(blackRook, "abc123"));
		} catch (IllegalPositionException e) {
			fail("IllegalPositionException for testPlacePiecePositionWithIncorrectLength");
		}
	}
	
	@Test
	public void testPlacePieceInvalidPositionWithCorrectLength() {
		try {
			ChessPiece blackRook = board.getPiece("a8");
			assertFalse(board.placePiece(blackRook, "8a"));
		} catch (IllegalPositionException e) {
			fail("IllegalPositionException for testPlacePieceInvalidPositionWithCorrectLength");
		}
	}
	
	@Test
	public void testPlacePieceOutOfBoundsColumnPosition() {
		try {
			ChessPiece blackRook = board.getPiece("a8");
			assertFalse(board.placePiece(blackRook, "m8"));
		} catch (IllegalPositionException e) {
			fail("IllegalPositionException for testPlacePieceOutOfBoundsColumnPosition");
		}
	}
	
	@Test
	public void testPlacePieceOutOfBoundsRowPosition() {
		try {
			ChessPiece blackRook = board.getPiece("a8");
			assertFalse(board.placePiece(blackRook, "a9"));
		} catch (IllegalPositionException e) {
			fail("IllegalPositionException for testPlacePieceOutOfBoundsRowPosition");
		}
	}
	
	@Test
	public void testPlacePieceWithNoLegalMoves() {
		try {
			ChessPiece blackRook = board.getPiece("a8");
			assertFalse(board.placePiece(blackRook, "a3"));
		} catch (IllegalPositionException e) {
			fail("IllegalPositionException for testPlacePieceWithNoLegalMoves");
		}
	}
	
	@Test
	public void testPlacePieceWhenOpponentPieceIsCaptured() {
		try {
			ChessPiece blackPawn = board.getPiece("d7");
			ChessPiece whitePawn = board.getPiece("e2");

			board.placePiece(blackPawn, "d5");
			board.placePiece(whitePawn, "e4");
			board.placePiece(blackPawn, "e4");
			
			assertNull(whitePawn.getPosition());
		} catch (IllegalPositionException e) {
			fail("IllegalPositionException for testPlacePieceWithNoLegalMoves");
		}
	}
	
	// Test place piece to make sure piece does not exist at old location
	@Test
	public void testPlacePieceToEnsurePieceIsNoLongerAtOldLocation() {
		try {
			String originalLocation = "d7";
			String newLocation = "d5";
			
			ChessPiece blackPawn = board.getPiece(originalLocation);

			board.placePiece(blackPawn, newLocation);

			assertTrue(blackPawn.getPosition().equals(newLocation));
			assertNull(board.getPiece(originalLocation));
		} catch (IllegalPositionException e) {
			fail("IllegalPositionException for testPlacePieceWithNoLegalMoves");
		}
	}

	// Test move with bad from position
	@Test
	public void testMoveFromPositionWithIncorrectLength() {
		try {
			board.move("abc123", "a6");
		} catch (IllegalMoveException e) {
			return;
		}
		
		fail("expected IllegalMoveException for testMoveFromPositionWithIncorrectLength");
	}
	
	@Test
	public void testMoveFromInvalidPositionWithCorrectLength() {
		try {
			board.move("8a", "a6");
		} catch (IllegalMoveException e) {
			return;
		}
		
		fail("expected IllegalMoveException for testMoveFromInvalidPositionWithCorrectLength");
	}
	
	@Test
	public void testMoveFromOutOfBoundsColumnPosition() {
		try {
			board.move("m8", "a6");
		} catch (IllegalMoveException e) {
			return;
		}
		
		fail("expected IllegalMoveException for testMoveFromOutOfBoundsColumnPosition");
	}
	
	@Test
	public void testMoveFromOutOfBoundsRowPosition() {
		try {
			board.move("a9", "a6");
		} catch (IllegalMoveException e) {
			return;
		}
		
		fail("expected IllegalMoveException for testMoveFromOutOfBoundsRowPosition");
	}
	
	// Test move with bad to position
	@Test
	public void testMoveToPositionWithIncorrectLength() {
		try {
			board.move("a8", "abc123");
		} catch (IllegalMoveException e) {
			return;
		}
		
		fail("expected IllegalMoveException for testMoveToPositionWithIncorrectLength");
	}
	
	@Test
	public void testMoveToInvalidPositionWithCorrectLength() {
		try {
			board.move("a8", "6a");
		} catch (IllegalMoveException e) {
			return;
		}
		
		fail("expected IllegalMoveException for testMoveToInvalidPositionWithCorrectLength");
	}
	
	@Test
	public void testMoveToOutOfBoundsColumnPosition() {
		try {
			board.move("a8", "m6");
		} catch (IllegalMoveException e) {
			return;
		}
		
		fail("expected IllegalMoveException for testMoveToOutOfBoundsColumnPosition");
	}
	
	@Test
	public void testMoveToOutOfBoundsRowPosition() {
		try {
			board.move("a8", "a9");
		} catch (IllegalMoveException e) {
			return;
		}
		
		fail("expected IllegalMoveException for testMoveToOutOfBoundsRowPosition");
	}
	
	// Test move using from position with no piece
	@Test
	public void testMoveFromPositionWithNoPiece() {
		try {
			board.move("a6", "a7");
		} catch (IllegalMoveException e) {
			return;
		}
		
		fail("expected IllegalMoveException for testMoveToOutOfBoundsRowPosition");
	}
	
	// Test move with piece with no legal moves
	@Test
	public void testMovePieceWithNoLegalMoves() {
		try {
			// Move pawn diagonally forward
			board.move("a8", "a3");
		} catch (IllegalMoveException e) {
			return;
		}
		
		fail("expected IllegalMoveException for testMovePieceWithNoLegalMoves");
	}
	
	
	// Test move with out of range illegal to position
	@Test
	public void testMoveFromPositionToIllegalLocation() {
		try {
			// Move pawn diagonally forward
			board.move("a7", "b8");
		} catch (IllegalMoveException e) {
			return;
		}
		
		fail("expected IllegalMoveException for testMoveToOutOfBoundsRowPosition");
	}
	
	// Test move with same color piece in to position
	@Test
	public void testMoveWithSameColorPieceInToPosition() {
		try {
			// Move pawn diagonally forward
			board.move("a8", "a7");
		} catch (IllegalMoveException e) {
			return;
		}
		
		fail("expected IllegalMoveException for testMoveWithSameColorPieceInToPosition");
	}
	
	// Test move with opposite color piece in to position
	@Test
	public void testMoveWithOppositeColorPieceInToPosition() {
		try {
			ChessPiece blackPawn = null;
			ChessPiece whitePawn = null;
			
			try {
				blackPawn = board.getPiece("d7");
				whitePawn = board.getPiece("e2");
			} catch (IllegalPositionException e) {
				fail("IllegalPositionException for testMoveWithOppositeColorPieceInToPosition");
			}

			board.placePiece(blackPawn, "d5");
			board.placePiece(whitePawn, "e4");
			
			board.move("d5", "e4");

			assertTrue(blackPawn.getPosition().equals("e4"));
		} catch (IllegalMoveException e) {
			fail("IllegalMoveException for testMoveWithOppositeColorPieceInToPosition");
		}
	}
	
	// Test move to ensure that piece is no longer at from position
	@Test
	public void testMoveToEnsurePieceIsNoLongerAtFromLocation() {
		ChessPiece blackPawn = null;
		String fromPosition = "d7";
		String toPosition = "d5";
		
		try {
			blackPawn = board.getPiece(fromPosition);
		} catch (IllegalPositionException e1) {
			fail("IllegalPositionException for testMoveToEnsurePieceIsNoLongerAtFromLocation");
		}
		
		try {
			// Move pawn diagonally forward
			board.move(fromPosition, toPosition);
		} catch (IllegalMoveException e) {
			fail("expected IllegalMoveException for testMoveToEnsurePieceIsNoLongerAtFromLocation");
		}

		assertTrue(blackPawn.getPosition().equals(toPosition));
		
		try {
			assertNull(board.getPiece(fromPosition));
		} catch (IllegalPositionException e) {
			fail("IllegalPositionException in assertion for testMoveToEnsurePieceIsNoLongerAtFromLocation");
		}
	}

	// Test move to ensure that piece of opposite color in to position is captured
	@Test
	public void testMoveAndEnsureOpponentPieceIsCaptured() {
		try {
			ChessPiece blackPawn = null;
			ChessPiece whitePawn = null;
			
			try {
				blackPawn = board.getPiece("d7");
				whitePawn = board.getPiece("e2");
			} catch (IllegalPositionException e) {
				fail("IllegalPositionException for testMoveAndEnsureOpponentPieceIsCaptured");
			}

			board.placePiece(blackPawn, "d5");
			board.placePiece(whitePawn, "e4");
			
			board.move("d5", "e4");

			assertNull(whitePawn.getPosition());
		} catch (IllegalMoveException e) {
			fail("IllegalMoveException for testMoveAndEnsureOpponentPieceIsCaptured");
		}
	}
	
}
