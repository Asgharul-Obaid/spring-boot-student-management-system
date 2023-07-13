package com.crud.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.crud.model.Student;
import com.crud.service.StudentService;

import jakarta.validation.Valid;

@Controller
@RequestMapping(path = "student")
public class StudentController {
	
	@Autowired
	StudentService studentService;
	
	@GetMapping("/home")
	public String getStudent(Model model) {
		List<Student> student = studentService.getAllStudents();
		model.addAttribute("stud",student);	
		return "index";
	}
	
	@GetMapping("/update")
	public String UpdateStudentList(Model model) {
		List<Student> student = studentService.getAllStudents();
		model.addAttribute("stud",student);	
		return "UpdateFrom";
	}
	
	@GetMapping("/updateStudent/{id}")
	public String updateStudent(Model model,@PathVariable long id) {
		Student student = studentService.getById(id);
		model.addAttribute("stud",student);
		return "updateStudent";
	}
	
	
	@PostMapping("/updatestudent/{id}")
	public String updateString(@ModelAttribute("stud") Student student,@PathVariable long id) {	
		studentService.updateStudent(student,id);
		return "redirect:/student/home";	
	}
	
	
	@GetMapping("/delete")
	public String deleString(Model model) {
		List<Student> student = studentService.getAllStudents();
		model.addAttribute("stud",student);
		return "Delete";
	}
	
	@GetMapping("/deleteStudent/{id}")
	public String deteleStudent(@PathVariable long id) {
		studentService.deleteById(id);
		return "redirect:/student/home";
	}
	
	@GetMapping("/save")
	public String saveStudent(Model model , Student student){
		model.addAttribute("stud",student);
		return "InsertStudent";
	}
	
	@PostMapping(path = "/saveStudent")
	public String saveStudent(@Valid @ModelAttribute("stud") Student student , BindingResult result) {
		if (result.hasErrors()) {
		    return "InsertStudent";
		  }
		
		studentService.saveStudent(student);
		return "index";
	}
	
}
