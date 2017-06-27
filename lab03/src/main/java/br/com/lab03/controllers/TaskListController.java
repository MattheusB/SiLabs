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

import br.com.lab03.models.TaskList;
import br.com.lab03.services.TaskListService;

@RestController
@RequestMapping(value="/tasklist")
public class TaskListController {
	
	@Autowired
	private TaskListService taskListService;

	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<TaskList>> getAll(){
		Collection<TaskList> taskList = taskListService.getAllTaskLists();
		return new ResponseEntity<>(taskList, HttpStatus.OK);
	}
	
	@RequestMapping(value="/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<TaskList> getTaskListById(@PathVariable Integer id){
		
		TaskList taskListId = taskListService.findTaskList(id);
		return new ResponseEntity<>(taskListId, HttpStatus.OK);
		
		
	}
	
	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<TaskList> registerTaskList(@RequestBody TaskList taskList){
		taskListService.register(taskList);
		return new ResponseEntity<>(taskList, HttpStatus.CREATED);
	}
	
	@RequestMapping(value="/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<TaskList> removeById(@PathVariable Integer id){
		
		
		boolean taskListAux = taskListService.removeTaskList(id);
		if (taskListAux){
			return new ResponseEntity<>(HttpStatus.OK);
		}else{
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
	}
	
	@RequestMapping(method = RequestMethod.DELETE)
	public ResponseEntity<TaskList> removeAllTaskLists(){
		taskListService.removeAll();
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	
	@RequestMapping(method= RequestMethod.PUT, consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<TaskList> updateTask(@RequestBody TaskList taskList){
		TaskList taskListUpdated = taskListService.update(taskList);
		
		return new ResponseEntity<>(taskListUpdated, HttpStatus.OK);
		
	}
	

}
