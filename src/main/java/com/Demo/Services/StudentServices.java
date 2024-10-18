package com.Demo.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Demo.Model.Student;
import com.Demo.Repositories.StudentRepository;

@Service
public class StudentServices {
	
	@Autowired
	StudentRepository studentRepository;
	
	public void addingStudent(Student student) {
		studentRepository.save(student);
	}
	
	public List<Student> getAllStudents(){
		return  studentRepository.findAll(); 
	}

	public void deletingById(int studentId) {
		studentRepository.deleteById(studentId); 		
	}
	
	public Student getStudentById(int id) {
		return studentRepository.findById(id).orElse(null);
	}

	public void updateStudent(Student student) {
		// TODO Auto-generated method stub
        studentRepository.save(student);                         // this will update if the student already exitss.
	}
}
