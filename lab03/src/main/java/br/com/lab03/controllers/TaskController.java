package br.com.lab03.controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.lab03.models.Task;
import br.com.lab03.services.TaskService;

@RestController
@RequestMapping(value = "/tasks")
public class TaskController {

	@Autowired
	private TaskService taskService;

	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<Task>> getAll() {
		Collection<Task> taskList = taskService.getAllTasks();
		return new ResponseEntity<>(taskList, HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Task> getTaskById(@PathVariable Integer id) {

		Task taskId = taskService.findTask(id);
		return new ResponseEntity<>(taskId, HttpStatus.OK);

	}

	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Task> registerTask(@RequestBody Task task) {
		taskService.register(task);
		return new ResponseEntity<>(task, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Task> removeById(@PathVariable Integer id) {
		boolean taskAux = taskService.removeTask(id);
		if (taskAux) {
			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE)
	public ResponseEntity<Task> removeAllTasks() {
		taskService.removeAll();
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Task> updateTask(@RequestBody Task task) {
		Task taskUpdated = taskService.update(task);

		return new ResponseEntity<>(taskUpdated, HttpStatus.OK);

	}

}
