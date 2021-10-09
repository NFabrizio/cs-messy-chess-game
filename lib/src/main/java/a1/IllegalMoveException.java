package a1;

public class IllegalMoveException extends Exception {
	public IllegalMoveException(String errorMessage, Throwable err) {
        super(errorMessage, err);
    }
}
