package com.emanuelrichieri.todoapp.utils;

import java.util.ArrayList;
import java.util.Base64;
import java.util.Base64.Decoder;
import java.util.Base64.Encoder;
import java.util.List;

import com.emanuelrichieri.todoapp.dto.IEntityDTO;
import com.emanuelrichieri.todoapp.dto.TaskDTO;
import com.emanuelrichieri.todoapp.entity.Task;
import com.emanuelrichieri.todoapp.entity.TaskStatus;

public class TaskDTOMapper implements IEntityDTOMapper {

	@Override
	public IEntityDTO convertToDTO(Object entity) {
		Task task = (Task) entity;
		TaskDTO taskDTO = new TaskDTO();
		if (task != null) {
			taskDTO.setId(task.getId());
			taskDTO.setDescription(task.getDescription());
			if (task.getStatus() != null) {
				taskDTO.setStatus(task.getStatus().name());
			}
			if (task.getAttachedFile()!= null) {
				Encoder base64Encoder = Base64.getEncoder();
				String attachedFile = new String(base64Encoder.encode(task.getAttachedFile()));
				taskDTO.setAttachedFile(attachedFile);
			}
			taskDTO.setFilename(task.getFilename());
		}
		return taskDTO;
	}

	@Override
	public Object convertToEntity(IEntityDTO entityDTO) {
		TaskDTO taskDTO = (TaskDTO) entityDTO;
		if (taskDTO == null) {
			return null;
		}
		Task task = new Task();
		task.setId(taskDTO.getId());
		task.setDescription(taskDTO.getDescription());
		if (taskDTO.getStatus() != null) {
			try {
				TaskStatus taskStatus = TaskStatus.valueOf(taskDTO.getStatus());
				task.setStatus(taskStatus);
			} catch (Exception ex) {
				task.setStatus(null);
			}	
		}		
		if (taskDTO.getAttachedFile() != null) {
			Decoder base64Decoder = Base64.getDecoder();
			byte[] attachedFile = base64Decoder.decode(taskDTO.getAttachedFile());
			task.setAttachedFile(attachedFile);
		}
		task.setFilename(taskDTO.getFilename());
		return task;
	}
	
	/***
	 * Convert a list of task entities into a list of taskDTOs
	 * @param taskList
	 * @return
	 * 	List of Task DTOs
	 */
	public List<TaskDTO> convertToDTOList(List<Task> taskList) {
		List<TaskDTO> taskDTOList = new ArrayList<TaskDTO>();
		
		for(Task task : taskList) {
			TaskDTO taskDTO = (TaskDTO) this.convertToDTO(task);
			taskDTOList.add(taskDTO);
		}
		
		return taskDTOList;
	}

}
