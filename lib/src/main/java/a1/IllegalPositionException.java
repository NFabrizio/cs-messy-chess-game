package a1;

public class IllegalPositionException extends Exception {
	public IllegalPositionException(String errorMessage, Throwable err) {
        super(errorMessage, err);
    }
}
