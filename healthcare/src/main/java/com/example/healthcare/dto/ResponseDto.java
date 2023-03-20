package com.example.healthcare.dto;

import java.io.Serializable;

import lombok.Data;


/**
 * Instantiates a new response dto.
 */
/**
 * @author Yogesh
 *
 */
@Data
public class ResponseDto  implements  Serializable{

	/** The status. */
	private String status;
	
	/** The data. */
	private Object data;
	
	
	/** The message. */
	private String message;
	
	/** The error code. */
	private String errorCode;
	
	/**
	 * Sets the success.
	 *
	 * @param data the data
	 * @param message the message
	 */
	public void setSuccess(Object data, String message) {
		this.status="Success";
		this.data=data;
		this.message=message;
	}
	
	/**
	 * Sets the failure.
	 *
	 * @param errorCode the error code
	 * @param message the message
	 */
	public void setFailure(String errorCode, String message) {
		this.status="Failure";
		
		this.message=message;
		this.errorCode=errorCode;
		
	}
}
