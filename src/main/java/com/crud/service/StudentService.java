package com.crud.service;

import java.util.List;

import com.crud.model.Student;

public interface StudentService {

	List<Student> getAllStudents();
	
	Student saveStudent (Student student);
	
	Student updateStudent(Student studet,long id);
	
	Student getById(long id);
	
	public void deleteById(long id);
}
