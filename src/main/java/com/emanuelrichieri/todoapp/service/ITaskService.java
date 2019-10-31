package com.emanuelrichieri.todoapp.service;

import java.util.List;

import com.emanuelrichieri.todoapp.entity.Task;

public interface ITaskService {
	
	/***
	 * Find all tasks
	 * @return
	 */
	List<Task> findAll();
	
	/***
	 * Find task by id
	 * @param taskId
	 * @return 
	 * 	Task task if exists any task with id = taskId, null otherwise
	 */
	Task findById(Long taskId);
	
	/***
	 * Save new or existing Task
	 * @param task
	 * @return saved task
	 */
	Task save(Task task);
	
	
	/***
	 * Set a pending Task as resolved
	 * @param taskId 
	 * @throws
	 * 	IllegalArgumentException if taskId is null 
	 * 	Exception if there is no task with the given id, or if task status is not PENDING
	 * @return task with updated status
	 */
	Task setResolved(Long taskId) throws Exception;
	
	/***
	 * Find tasks by id, description and/or status
	 * @param id (optional)
	 * @param description (optional) --> substring of task description 
	 * @param status (optional) --> must be PENDING or RESOLVED
	 * @return
	 * 	List of Tasks that match the given filters
	 * @throws
	 * 	Exception if given status is invalid
	 */
	List<Task> findByFilters(Long id, String description, String status) throws Exception;
}
