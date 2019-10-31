package com.emanuelrichieri.todoapp.service;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import com.emanuelrichieri.todoapp.entity.Task;
import com.emanuelrichieri.todoapp.entity.TaskStatus;
import com.emanuelrichieri.todoapp.repository.ITaskRepository;

@Service("taskService")
public class TaskService implements ITaskService {
	
	@Autowired
	private ITaskRepository taskRepository;

	@Override
	public List<Task> findAll() {
		return this.taskRepository.findAll();
	}
	
	@Override
	public Task findById(Long id) {
		try {
			return this.taskRepository.findById(id).get();
		} catch (NoSuchElementException ex) {
			return null;
		}
	}

	@Override
	public Task save(Task task) {
		if (task == null) {
			throw new IllegalArgumentException("Argument task must not be null");
		}
		return this.taskRepository.save(task);
	}

	@Override
	public Task setResolved(Long taskId) throws Exception {
		if (taskId == null) {
			throw new IllegalArgumentException("Argument task id must not be null");
		}
		Task task = this.findById(taskId);
		if (task == null) {
			throw new Exception("Task with id " + taskId + " does not exist.");
		}
		if (!TaskStatus.PENDING.equals(task.getStatus())) {
			throw new Exception("Task status must be PENDING.");
		}
		task.setStatus(TaskStatus.RESOLVED);
		return this.save(task);
	}
	
	@Override
	public List<Task> findByFilters(Long id, String description, String status) throws Exception {
		TaskStatus taskStatus;
		try {
			taskStatus = TaskStatus.valueOf(status);	
		} catch (IllegalArgumentException ex) {
			// Task status has no constant with the specified status
			throw new Exception("Invalid parameter status. It must be PENDING or RESOLVED.");
		} catch (NullPointerException ex) {
			// Given status is null
			taskStatus = null;
		}
		
		ExampleMatcher exampleMatcher = ExampleMatcher.matchingAll()
					.withMatcher("description", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase());
		
		Task taskExample = new Task();
		taskExample.setId(id);
		taskExample.setDescription(description);
		taskExample.setStatus(taskStatus);
		Example<Task> example = Example.of(taskExample, exampleMatcher);
		return this.taskRepository.findAll(example);
	}	
	
			
}
