package my.task.test.exceptions;

public class DataAlreadyExistException extends RuntimeException {

	public DataAlreadyExistException() {
	}

	public DataAlreadyExistException(String message) {
		super(message);
	}

	public DataAlreadyExistException(Throwable cause) {
		super(cause);
	}

	public DataAlreadyExistException(String message, Throwable cause) {
		super(message, cause);
	}

	public DataAlreadyExistException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
