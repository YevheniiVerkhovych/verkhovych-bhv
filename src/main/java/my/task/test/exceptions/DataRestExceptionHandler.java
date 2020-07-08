package my.task.test.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import java.util.logging.Level;
import java.util.logging.Logger;

@ControllerAdvice
public class DataRestExceptionHandler {

	private Logger logger = Logger.getLogger(getClass().getName());

	@ResponseStatus(HttpStatus.NOT_FOUND) // 404
	@ExceptionHandler()
	public void handleException(DataNotFoundException exc) {
		logger.log(Level.WARNING , exc.getMessage());
	}
	
	@ResponseStatus(HttpStatus.CONFLICT) // 409
	@ExceptionHandler()
	public void handleException(DataAlreadyExistException exc) {
		logger.log(Level.WARNING, exc.getMessage());
	}
	
	@ResponseStatus(HttpStatus.BAD_REQUEST) // 400
	@ExceptionHandler()
	public void handleException(Exception exc) {
		logger.log(Level.WARNING, exc.getMessage());
	}
	
}





