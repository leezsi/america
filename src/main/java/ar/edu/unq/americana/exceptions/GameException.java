package ar.edu.unq.americana.exceptions;

public class GameException extends RuntimeException {

	// ****************************************************************
	// ** CONSTRUCTORS
	// ****************************************************************
	private static final long serialVersionUID = -2962330631975776317L;

	public GameException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public GameException(final String message, final Throwable cause,
			final boolean enableSuppression, final boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public GameException(final String message, final Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public GameException(final Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	public GameException(final String description) {
		super(description);
	}
}