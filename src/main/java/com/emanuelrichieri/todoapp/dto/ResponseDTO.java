package com.emanuelrichieri.todoapp.dto;

public class ResponseDTO implements IEntityDTO {

	private Boolean success;	
	private String message;	
	private Integer statusCode;
		
	/***
	 * Generic response DTO
	 * @param success
	 * @param statusCode
	 * @param message
	 */
	public ResponseDTO(Boolean success, Integer statusCode, String message) {
		this.success = success;
		this.statusCode = statusCode;
		this.message = message;
	}
	
	public Boolean getSuccess() {
		return success;
	}
	public void setSuccess(Boolean success) {
		this.success = success;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Integer getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(Integer code) {
		this.statusCode = code;
	} 
	
	@Override
	public String toString() {
		return " {"
				+ " success = " + this.getSuccess() + " , "
				+ " message = \"" + this.getMessage() + "\", "
				+ " statusCode = " + this.getStatusCode()
			+ " }";
	}
	
}
