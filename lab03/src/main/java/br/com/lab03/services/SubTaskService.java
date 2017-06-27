package br.com.lab03.services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.lab03.models.SubTask;
import br.com.lab03.repositories.SubTaskRepository;

@Service
public class SubTaskService {

	@Autowired
	private SubTaskRepository subTaskRepository;

	public Collection<SubTask> getAllSubTasks() {
		return subTaskRepository.findAll();
	}

	public SubTask findSubTask(Integer id) {
		return subTaskRepository.findOne(id);

	}

	public void register(SubTask subTask) {
		subTaskRepository.save(subTask);
	}

	public boolean removeSubTask(Integer id) {
		SubTask subTaskFound = findSubTask(id);

		if (subTaskFound == null) {
			return false;
		} else {
			subTaskRepository.delete(id);
			return true;
		}

	}

	public void removeAll() {
		subTaskRepository.deleteAll();
	}

	public SubTask update(SubTask subTask) {
		subTaskRepository.save(subTask);
		
		return subTask;
	
	
	}

}
