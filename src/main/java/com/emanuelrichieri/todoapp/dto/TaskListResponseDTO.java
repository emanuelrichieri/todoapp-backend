package com.emanuelrichieri.todoapp.dto;

import java.util.ArrayList;
import java.util.List;

public class TaskListResponseDTO extends ResponseDTO {

	private List<TaskDTO> tasks = new ArrayList<TaskDTO>();
	
	public TaskListResponseDTO(Boolean success, Integer statusCode, List<TaskDTO> taskDTOList) {
		this(success, statusCode, "", taskDTOList);
	}
	
	public TaskListResponseDTO(Boolean success, Integer statusCode, String message, List<TaskDTO> taskDTOList) {
		super(success, statusCode, message);
		this.tasks = taskDTOList;
	}
	
	public List<TaskDTO> getTasks() {
		return tasks;
	}

	public void setTasks(List<TaskDTO> tasks) {
		this.tasks = tasks;
	}

	
	@Override
	public String toString() {
		String tasks = " [ ";
		String sep = "";
		for (TaskDTO taskDTO : this.getTasks()) {
			tasks += sep + taskDTO.toString();
			sep = ", ";
		}
		tasks += " ] ";
		return " {"
				+ " success = " + this.getSuccess() + " , "
				+ " message = \"" + this.getMessage() + "\", "
				+ " statusCode = " + this.getStatusCode()
				+ " tasks = " + tasks 
				+ " }";
	}
	
}
