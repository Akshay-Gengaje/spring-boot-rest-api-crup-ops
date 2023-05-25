package com.rest.student.Repository;

import org.springframework.data.repository.CrudRepository;

import com.rest.student.entities.Student;

public interface StudentRepository extends CrudRepository<Student, Integer> {
    public Student findById(int id);
}
