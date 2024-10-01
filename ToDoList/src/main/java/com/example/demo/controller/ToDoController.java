package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.entity.ToDo;
import com.example.demo.service.ToDoService;

@Controller
public class ToDoController {
	@Autowired
	ToDoService toDoService;
	
	@RequestMapping("/index")
	public String index(Model model) throws Exception{
		List<ToDo> list = toDoService.selectAll();
		model.addAttribute("toDos",list);
		return "index";
	}
	@RequestMapping("/add")
	public String add(ToDo toDo){
		toDoService.add(toDo);
		return "redirect:/";
	}
	@RequestMapping("/delete")
	public String delete(ToDo toDo){
		toDoService.delete(toDo);
		return "redirect:/";
	}
}
