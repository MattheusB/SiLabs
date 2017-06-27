package br.com.lab03.services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.lab03.models.Task;
import br.com.lab03.repositories.TaskRepository;

@Service
public class TaskService {
	
	@Autowired
	private TaskRepository taskRepository;

	public Collection<Task> getAllTasks() {
		return taskRepository.findAll();
	}

	public void register(Task task) {
		taskRepository.save(task);
	}

	public boolean removeTask(Integer id) {
		Task taskFound = findTask(id);
		if (taskFound == null) {
			return false;
		} else {
			taskRepository.delete(id);
			return true;

		}
	}

	public void removeAll() {
		taskRepository.deleteAll();
	}

	public Task findTask(Integer id) {
		return taskRepository.findOne(id);
	}

	public Task update(Task task) {
		taskRepository.save(task);
		return task;
	
	}

}
