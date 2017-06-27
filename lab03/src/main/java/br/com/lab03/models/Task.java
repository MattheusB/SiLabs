package br.com.lab03.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import br.com.lab03.models.priority.Priority;

@Entity
public class Task {

	@Enumerated(EnumType.STRING)
	private Priority priority;

	@Id
	@GeneratedValue
	private Integer id;
	private String description;
	private boolean done;
	private String title;
	private String category;

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval=true)
	private List<SubTask> subtasks;

	
	public Task(){
		this.subtasks = new ArrayList<>();
		this.done = false;
	}
	
	public Integer getId(){
		return id;
	}
	
	public void setId(Integer id){
		this.id = id;
		
	}
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isDone() {
		return done;
	}

	public void setDone(boolean done) {
		this.done = done;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}


	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

}
