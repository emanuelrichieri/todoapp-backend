package com.emanuelrichieri.todoapp.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.emanuelrichieri.todoapp.dto.ResponseDTO;

public class CommonRestController {
	
	/***
	 * Handle a generic exception and returns a ResponseEntity with Internal server error
	 * @param exception
	 * @return
	 * 	ResponseEntity with given Internal Server Error and exception error message
	 */
	public ResponseEntity<ResponseDTO> getExceptionResponse(Exception exception) {
		return this.getExceptionResponse(exception, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	/***
	 * Handle an exception and returns a ResponseEntity
	 * @param exception
	 * @param httpStatus
	 * @return
	 * 	ResponseEntity with given httpStatus and exception error message
	 */
	public ResponseEntity<ResponseDTO> getExceptionResponse(Exception exception, HttpStatus httpStatus) {
		int statusCode = httpStatus.value();
		ResponseDTO response = new ResponseDTO(false, statusCode, exception.getMessage());
		return ResponseEntity.status(statusCode).body(response);
	}

}
