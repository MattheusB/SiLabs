package br.com.lab03.services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.lab03.models.TaskList;
import br.com.lab03.repositories.TaskListRepository;
@Service
public class TaskListService {

	@Autowired
	TaskListRepository taskListRepository;
	public Collection<TaskList> getAllTaskLists() {
		return taskListRepository.findAll();
	
	}

	public TaskList findTaskList(Integer id) {
		return taskListRepository.findOne(id);
	}

	public void register(TaskList taskList) {
		taskListRepository.save(taskList);
	
	}

	public boolean removeTaskList(Integer id) {
		TaskList taskListFound = findTaskList(id);
		
		if (taskListFound == null){
			return false;
		}
		else{
			taskListRepository.delete(id);
			return true;
		}
		
	}

	public void removeAll() {
		taskListRepository.deleteAll();
	}

	public TaskList update(TaskList taskList) {
		taskListRepository.save(taskList);
		return taskList;
	}

}
