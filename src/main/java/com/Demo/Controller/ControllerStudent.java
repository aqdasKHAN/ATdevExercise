package com.Demo.Controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.Demo.DTO.StudentDTO;
import com.Demo.Model.Student;
import com.Demo.Services.StudentServices;

@Controller
public class ControllerStudent {
	
	@Autowired
	StudentServices studentServices;
	
	@GetMapping("/atdevStudent")
	public String getStudentHomePage() {
		System.out.println("throwing to atdev student page");
		return "studentHomePage";
	}
	
	@GetMapping("/atdev/studentList")
	public String getHomePage(Model model) {
		System.out.println("Jumping to Student lists");
		model.addAttribute("stdnt", studentServices.getAllStudents());
		return "studentsList";
	}
	
	@GetMapping("/atdev/addStudent")
	public String getAddStudentPage(Model model ) {
		System.out.println("Jumping to Student add page");
		model.addAttribute("stdntDTO", new StudentDTO());  
		return "studentAddingPage";
	}
	
	@PostMapping("/atdev/addingStudent")
	public String addingCategory(@ModelAttribute("stdntDTO") StudentDTO studentDTO) {
		
		Student student=new Student();
		student.setId(studentDTO.getId());
		student.setName(studentDTO.getName());
		student.setRollNo(studentDTO.getRollNo());
		student.setStandard(studentDTO.getStandard());
		student.setHouse(studentDTO.getHouse()); 
		
		studentServices.addingStudent(student);
		return "redirect:/atdevStudent";
	}
	
	@DeleteMapping("/atdev/deleting/{id}")
	@ResponseBody
	public ResponseEntity<Void> deleteCategory(@PathVariable int id) {
		System.out.println("Deleting student: " + id);
		studentServices.deletingById(id);
		
		return ResponseEntity.noContent().build(); // Returns No Content
	}
	
	@GetMapping("/students/updating/{id}")
	public String showUpdateForm(@PathVariable("id") int id, Model model) {
		
		System.out.println("need to update student id : " + id);
	    Student optionalStudent = studentServices.getStudentById(id);
	    
    	model.addAttribute("student", optionalStudent);

	    System.out.println("to Update form!!! for :"+ optionalStudent);
	    
	    return "updateStudentForm"; 
	}
	
	@PostMapping("/students/updating")
	public String updateStudent(@ModelAttribute("student") Student student) {
		System.out.println("Updating student!!");
	    studentServices.updateStudent(student);
	    return "redirect:/atdev/studentList";
	}


	
}
