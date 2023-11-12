package com.student.demo.service.students;

import com.student.demo.entity.Student;

import java.util.List;
import java.util.Optional;

public interface StudentService {


    
    Student addStudent(Student employee);
    
    List<Student> getAllStudents();

    Optional<Student> getByStudentId(Long studentId);

    Student updateStudent(Long studentId, Student student);

    boolean deleteStudent(Long studentId);
}
