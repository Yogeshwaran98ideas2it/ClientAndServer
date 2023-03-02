package com.example.healthcare.exception;

import java.util.NoSuchElementException;

import org.springframework.http.HttpStatus;
import org.springframework.security.web.authentication.Http403ForbiddenEntryPoint;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException.Forbidden;

import com.example.healthcare.dto.ResponseDto;

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
	private String internalServerError = "INTERNAL_SERVER_ERROR";

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
		responseDto.setErrorCode(internalServerError);
		responseDto.setFailure(internalServerError, "Getting Constraint Voilation");
		return responseDto;

	}
	
	
	  @ResponseStatus(HttpStatus.FORBIDDEN)
	  
	  @ExceptionHandler(Forbidden.class) 
	  public ResponseDto  handleValidationtException(Forbidden ex) { ResponseDto responseDto = new
	  ResponseDto(); responseDto.setErrorCode("Forbidden Error");
	  responseDto.setFailure("Forbidden Error", "Forbidden Error Occured");
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
		responseDto.setErrorCode(internalServerError);
		responseDto.setFailure(internalServerError, "No Value present");
		return responseDto;
	}

}
