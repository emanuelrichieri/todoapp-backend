package com.emanuelrichieri.todoapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.emanuelrichieri.todoapp.dto.ResponseDTO;
import com.emanuelrichieri.todoapp.dto.TaskDTO;
import com.emanuelrichieri.todoapp.dto.TaskListResponseDTO;
import com.emanuelrichieri.todoapp.dto.TaskResponseDTO;
import com.emanuelrichieri.todoapp.entity.Task;
import com.emanuelrichieri.todoapp.service.ITaskService;
import com.emanuelrichieri.todoapp.utils.TaskDTOMapper;

@RestController
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT})
@RequestMapping("/api/v1")
public class TaskRestController extends CommonRestController {

	@Autowired
	private ITaskService taskService;
	
	private TaskDTOMapper taskDTOMapper = new TaskDTOMapper();
	/***
	 * Get all tasks that match the filters
	 * id (optional)
	 * description (optional)
	 * status (optional)
	 * @return 
	 * 	ResponseDTO that includes tasks list
	 */
	@GetMapping("/tasks")
	public ResponseEntity<ResponseDTO> getTasks(@RequestParam(name = "id", required = false) Long id, 
										@RequestParam(name = "description", required = false) String description,
										@RequestParam(name = "status", required = false) String status) {
		try {
			List<Task> tasksList = this.taskService.findByFilters(id, description, status);
			List<TaskDTO> taskDTOList = this.taskDTOMapper.convertToDTOList(tasksList);
			TaskListResponseDTO response = new TaskListResponseDTO(true, HttpStatus.OK.value(), taskDTOList);
			
			return ResponseEntity.ok(response);	
		} catch (Exception exception) {
			return getExceptionResponse(exception);
		}
	}
	
	/***
	 * Get a task by id
	 * @param taskId
	 * @return 
	 * ResponseDTO that includes
	 *  - The task with the given id <br>
	 *  - <b>null</b> if it does not exist
	 */
	@GetMapping("/tasks/{id}")
	public ResponseEntity<ResponseDTO> getTaskById(@PathVariable(value = "id") Long taskId) {
		try {
			Task task = this.taskService.findById(taskId);
			TaskDTO taskDTO = (TaskDTO) this.taskDTOMapper.convertToDTO(task);
			TaskResponseDTO response = new TaskResponseDTO(true, HttpStatus.OK.value(), taskDTO);
			
			return ResponseEntity.ok(response);
		} catch (Exception ex) {
			return getExceptionResponse(ex);
		}		
	}
	
	/***
	 * Save a new or existing task. 
	 * @param taskDTO - task DTO object {id: Long, description: string, status: PENDING/RESOLVED, file: base64 string } 
	 * @return 
	 * 	ResponseDTO that includes
	 * 	- Saved task <br>
	 *  - Bad Request response if there is an IllegalArgumentException <br>
	 * 	- An internal server error response otherwise
	 */
	@PostMapping("/tasks")
	public ResponseEntity<ResponseDTO> saveTask(@RequestBody TaskDTO taskDTO) {
		try {
			Task task = (Task) this.taskDTOMapper.convertToEntity(taskDTO);
			Task savedTask = this.taskService.save(task);
			if (savedTask == null) {
				throw new Exception("Error while trying to save task.");
			}
			TaskDTO savedTaskDTO = (TaskDTO) this.taskDTOMapper.convertToDTO(savedTask);
			TaskResponseDTO response = new TaskResponseDTO(true, HttpStatus.OK.value(), savedTaskDTO);
			return ResponseEntity.ok(response);			
		} catch(IllegalArgumentException exception) {
			return getExceptionResponse(exception, HttpStatus.BAD_REQUEST);
		} catch (Exception exception) {
			return getExceptionResponse(exception);
		}		
	}
	
	/***
	 * Set a task as resolved.
	 * @param taskId
	 * @return
	 * 	ResponseDTO that includes
	 * 	- The updated task <br>
	 *  - Bad Request response if there is an IllegalArgumentException <br>
	 * 	- An internal server error response otherwise
	 */
	@PutMapping("/tasks/{id}/resolved")
	public ResponseEntity<ResponseDTO> setTaskResolvedById(@PathVariable(value = "id") Long taskId) {
		try {
			Task updatedTask = this.taskService.setResolved(taskId);
			if (updatedTask == null) {
				throw new Exception("Error while trying to update task.");
			}
			TaskDTO updatedTaskDTO = (TaskDTO) this.taskDTOMapper.convertToDTO(updatedTask);
			TaskResponseDTO response = new TaskResponseDTO(true, HttpStatus.OK.value(), updatedTaskDTO);
			return ResponseEntity.ok(response);
		} catch(IllegalArgumentException exception) {
			return getExceptionResponse(exception, HttpStatus.BAD_REQUEST);
		} catch (Exception exception) {
			return getExceptionResponse(exception);
		}	
	}
	

}
