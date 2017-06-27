package br.com.lab03.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class TaskList {
	
	@Id
	@GeneratedValue
	private Integer id;

	private String title;
	
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval=true)
	private List<Task> tasks;

	public TaskList (){
		tasks = new ArrayList<>();
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	
	
	
	

	
	
}
