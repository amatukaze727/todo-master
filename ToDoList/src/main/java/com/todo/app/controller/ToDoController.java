package com.todo.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.todo.app.entity.ToDo;
import com.todo.app.service.ToDoService;

@Controller
public class ToDoController {
	@Autowired
	ToDoService toDoService;
	
	@RequestMapping("/")
	public String index(Model model){
		List<ToDo> list = ToDoService.selectAll();
		model.addAttribute("toDos",list);
		return "index";
	}
	@RequestMapping("/add")
	public String add(ToDo toDo){
		ToDoService.add(toDo);
		return "redirect:/";
	}
	@RequestMapping("/delete")
	public String delete(ToDo toDo){
		ToDoService.delete(toDo);
		return "redirect:/";
	}
}
