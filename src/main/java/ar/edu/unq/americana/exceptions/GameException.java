package ar.edu.unq.americana.exceptions;

public class GameException extends RuntimeException {

	// ****************************************************************
	// ** CONSTRUCTORS
	// ****************************************************************
	private static final long serialVersionUID = -2962330631975776317L;

	public GameException() {
		super();
	}

	public GameException(final String message, final Throwable cause) {
		super(message, cause);
	}

	public GameException(final Throwable cause) {
		super(cause);
	}

	public GameException(final String description) {
		super(description);
	}
}
