package com.student.demo.service.students;

import com.student.demo.entity.Student;
import com.student.demo.repository.student.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepository;


    @Override
    public Student addStudent(Student student) {
        student.setStudentName(student.getStudentName());
        student.setUniversityName(student.getUniversityName());
        student.setUniversityCourse(student.getUniversityCourse());
        return studentRepository.save(student);
    }

    @Override
    public List<Student> getAllStudents() {
        return null;
    }

    @Override
    public Optional<Student> getByStudentId(Long studentId) {
        return Optional.empty();
    }

    @Override
    public Student updateStudent(Long studentId, Student student) {
        return null;
    }

    @Override
    public boolean deleteStudent(Long studentId) {
        return false;
    }
}
