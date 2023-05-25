package com.rest.student.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.rest.student.Services.StudentService;
import com.rest.student.entities.Student;

@RestController
public class StudentController {
    @Autowired
    Student s1;
    @Autowired
    private StudentService studentService;

    @GetMapping("/student")
    public ResponseEntity<List<Student>> getStudent() {
        List<Student> studentList = studentService.getAllStudents();
        if (studentList.size() <= 0) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.of(Optional.of(studentList));
    }

    @GetMapping("/student/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable int id) {
        Student student = studentService.getStudentById(id);
        if (student == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.of(Optional.of(student));
    }

    @PostMapping("/student")
    public ResponseEntity<Student> addStudent(@RequestBody Student s1) {
        Student student = null;
        try {
            student = this.studentService.addStudent(s1);
            return ResponseEntity.of(Optional.of(student));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/student/{id}")
    public ResponseEntity deleteStudent(@PathVariable int id) {
        String result =  this.studentService.deleteStudent(id);
        if(result == "true"){
            return ResponseEntity.status(HttpStatus.ACCEPTED).build();
        }
        else{
            return ResponseEntity.badRequest().body(result);
        }
    }

    @PutMapping("/student/{id}")
    public ResponseEntity updateStudent(@RequestBody Student student, @PathVariable int id) {
        String result = studentService.updateStudent(student, id);
        if(result == "Updated"){
            return ResponseEntity.ok().build();
        }
        else{
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(result);
        }
    }
}
