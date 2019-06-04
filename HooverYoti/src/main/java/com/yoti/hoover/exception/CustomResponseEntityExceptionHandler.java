package com.yoti.hoover.exception;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
/*
 * CustomResponseEntityExceptionHandler is used for handling application wide exception.
 * Whenever exception thrown, this will be handled by this class.
 */
@ControllerAdvice
public class CustomResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

	@Autowired
	private MessageSource messageSource;

	/*
	 * Method for handling MoveNotSupportedException. If hoover not supporting move operation then this exception occur.
	 */
	@ExceptionHandler(MoveNotSupportedException.class)
	public final ResponseEntity<Object> handleMoveException(Exception ex, WebRequest request) throws Exception {

		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(),
				request.getDescription(false));

		return new ResponseEntity<Object>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);

	}

	/*
	 * Method for handling CleanNotSupportedException. If hoover not supporting clean operation then this exception occur.
	 */
	@ExceptionHandler(CleanNotSupportedException.class)
	public final ResponseEntity<Object> handleCleanException(Exception ex, WebRequest request) throws Exception {

		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(),
				request.getDescription(false));

		return new ResponseEntity<Object>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	/*
	 * Method for handling other that are not handled by specific methods.
	 */
	@ExceptionHandler(Exception.class)
	public final ResponseEntity<Object> handleRemaningException(Exception ex, WebRequest request) throws Exception {

		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(),
				messageSource.getMessage("no.valid.input", null, LocaleContextHolder.getLocale()),
				request.getDescription(false));

		return new ResponseEntity<Object>(exceptionResponse, HttpStatus.BAD_REQUEST);
	}

	/*
	 * Input validation exception handler.
	 */
	@ExceptionHandler(InvalidInputException.class)
	public final ResponseEntity<Object> handleInvalidInputException(Exception ex, WebRequest request) throws Exception {

		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(),	ex.getMessage(),request.getDescription(false));

		return new ResponseEntity<Object>(exceptionResponse, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(Throwable.class)
	public final ResponseEntity<Object> handleAdviceInvalidInputException(Exception ex, WebRequest request) throws Exception {

		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(),ex.getMessage(),	request.getDescription(false));

		return new ResponseEntity<Object>(exceptionResponse, HttpStatus.BAD_REQUEST);
	}
	
	/*
	 * Input move instructions invalid exception handler.
	 */
	@ExceptionHandler(InvalidInstructionException.class)
	public final ResponseEntity<Object> handleInvalidInstructionException(Exception ex, WebRequest request) throws Exception {

		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(),ex.getMessage(),	request.getDescription(false));

		return new ResponseEntity<Object>(exceptionResponse, HttpStatus.BAD_REQUEST);
	}
	/*
	 * Input validation exception handler.
	 */
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		ExceptionResponse errorDetails = new ExceptionResponse(new Date(), "Validation Failed",
				ex.getBindingResult().toString());
		return new ResponseEntity<Object>(errorDetails, HttpStatus.BAD_REQUEST);
	}
}
