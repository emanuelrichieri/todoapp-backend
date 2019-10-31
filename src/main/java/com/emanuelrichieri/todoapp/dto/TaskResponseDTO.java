package com.emanuelrichieri.todoapp.dto;

public class TaskResponseDTO extends ResponseDTO {

	private TaskDTO task;
	
	public TaskResponseDTO(Boolean success, Integer statusCode, TaskDTO taskDTO) {
		this(success, statusCode, "", taskDTO);
	}
	
	public TaskResponseDTO(Boolean success, Integer statusCode, String message, TaskDTO taskDTO) {
		super(success, statusCode, message);
		this.task = taskDTO;
	}

	public TaskDTO getTask() {
		if (this.task == null) {
			return new TaskDTO();
		}
		return task;
	}

	public void setTask(TaskDTO taskDTO) {
		this.task = taskDTO;
	}
	
	@Override
	public String toString() {
		return " {"
				+ " success = " + this.getSuccess() + " , "
				+ " message = \"" + this.getMessage() + "\", "
				+ " statusCode = " + this.getStatusCode()
				+ " task = " + this.getTask().toString() 
				+ " }";
	}
	

}
