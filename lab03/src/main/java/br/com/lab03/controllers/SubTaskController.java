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

import br.com.lab03.models.SubTask;
import br.com.lab03.services.SubTaskService;


@RestController
@RequestMapping(value="/subtasks")
public class SubTaskController {

	
	@Autowired
	private SubTaskService subTaskService;

	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<SubTask>> getAll(){
		Collection<SubTask> taskList = subTaskService.getAllSubTasks();
		return new ResponseEntity<>(taskList, HttpStatus.OK);
	}
	
	@RequestMapping(value="/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<SubTask> getSubTaskById(@PathVariable Integer id){
		
		SubTask subTaskId = subTaskService.findSubTask(id);
		return new ResponseEntity<>(subTaskId, HttpStatus.OK);
		
		
	}
	
	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<SubTask> registerSubTask(@RequestBody SubTask subTask){
		subTaskService.register(subTask);
		return new ResponseEntity<>(subTask, HttpStatus.CREATED);
	}
	
	@RequestMapping(value="/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<SubTask> removeById(@PathVariable Integer id){
		boolean subTaskAux = subTaskService.removeSubTask(id);
		
		if(subTaskAux){
			return new ResponseEntity<>(HttpStatus.OK);
		}else{
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
	}
	
	@RequestMapping(method = RequestMethod.DELETE)
	public ResponseEntity<SubTask> removeAllSubTasks(){
		subTaskService.removeAll();
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	
	@RequestMapping(method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<SubTask> updateSubTask(@RequestBody SubTask subTask){
		SubTask subTaskUpdated = subTaskService.update(subTask);
		
		return new ResponseEntity<>(subTaskUpdated, HttpStatus.OK);
		
	}
	
}
