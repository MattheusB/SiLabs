package br.com.lab03.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

	@RequestMapping(value = "/home")
	public String goToHome() {
		return "index";
	}

	@RequestMapping(value = "/contact")
	public String goToContact() {
		return "contact";
	}

	@RequestMapping(value = "/addTaskList")
	public String creatTaskList() {
		return "addTaskList";
	}

	@RequestMapping(value = "/addTask")
	public String createTask() {
		return "addTask";
	}

	@RequestMapping(value = "/addSubTask")
	public String createSubTask() {
		return "addSubTask";
	}
}
