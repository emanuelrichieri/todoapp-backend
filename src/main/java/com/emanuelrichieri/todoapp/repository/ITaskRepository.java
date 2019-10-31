package com.emanuelrichieri.todoapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.emanuelrichieri.todoapp.entity.Task;

@Repository("taskRepository")
public interface ITaskRepository extends JpaRepository<Task, Long> { }
