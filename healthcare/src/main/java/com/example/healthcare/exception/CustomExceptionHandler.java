package com.example.healthcare.exception;

import java.util.NoSuchElementException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.healthcare.userandroledto.ResponseDto;

import jakarta.validation.ConstraintViolationException;

/**
 * The Class CustomExceptionHandler.
 */
/**
 * @author Yogesh
 *
 */
@RestControllerAdvice
public class CustomExceptionHandler {

	/** The internal server error. */
	private String INTERNAL_SERVER_ERROR = "INTERNAL_SERVER_ERROR";

	/**
	 * Handle validationt exception.
	 *
	 * @param ex the ex
	 * @return the response dto
	 */
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseDto handleValidationtException(ConstraintViolationException ex) {
		ResponseDto responseDto = new ResponseDto();
		responseDto.setErrorCode(INTERNAL_SERVER_ERROR);
		responseDto.setFailure(INTERNAL_SERVER_ERROR, "Getting Constraint Voilation");
		return responseDto;

	}

	/**
	 * Handle validationt exception.
	 *
	 * @param ex the ex
	 * @return the response dto
	 */
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(NoSuchElementException.class)
	public ResponseDto handleValidationtException(NoSuchElementException ex) {
		ResponseDto responseDto = new ResponseDto();
		responseDto.setErrorCode(INTERNAL_SERVER_ERROR);
		responseDto.setFailure(INTERNAL_SERVER_ERROR, "No Value present");
		return responseDto;
	}

}
