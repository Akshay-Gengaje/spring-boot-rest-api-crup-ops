package com.rest.student.Services;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.rest.student.Repository.StudentRepository;
import com.rest.student.entities.Student;

@Component
public class StudentService {
    @Autowired
    Student student;
    @Autowired
    StudentRepository studentRepository;

    public ArrayList<Student> getAllStudents() {
       return (ArrayList<Student>) this.studentRepository.findAll();
    }

    // Get Student By ID
    public Student getStudentById(int id) {
        student = null;
        try {
//            student = list.stream().filter(e -> e.getRollNo() == id).findFirst().get();
            student = this.studentRepository.findById(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return student;
    }

    // Add Studnet Post Method
    public Student addStudent(Student student) {
        return this.studentRepository.save(student);
    }

    // Delete Student
    public String deleteStudent(int id) {
        try{
            this.studentRepository.deleteById(id);
            return "true";
        }
        catch(Exception e){
            e.printStackTrace();
            return e.getMessage();
        }
    }

    public String updateStudent(Student s, int id) {
        try {
            s.setRollNo(id);
            this.studentRepository.save(s);
            return "Updated";
        }
        catch (Exception e){
            e.printStackTrace();
            return e.getMessage();
        }
    }
}