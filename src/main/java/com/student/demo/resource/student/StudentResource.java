package com.student.demo.resource.student;

import com.student.demo.entity.Student;
import com.student.demo.service.students.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1/api/students")
@CrossOrigin(origins = "http://localhost:3000")
@Validated
public class StudentResource {

    @Autowired
    private StudentService studentService;

    @GetMapping
    public List<Student> getAllStudents() {
        return studentService.getAllStudents();
    }

    @GetMapping("/{studentId}/student")
    public ResponseEntity<Optional<Student>> getByStudentId(@PathVariable("studentId") Long studentId) {
        Optional<Student> student = studentService.getByStudentId(studentId);
        if (student != null) {
            return ResponseEntity.ok(student);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Student> addStudent(@Valid @RequestBody Student studentData) {
        try {
            Student student = new Student();
            student.setStudentName(studentData.getStudentName());
            student.setUniversityName(studentData.getUniversityName());
            student.setUniversityCourse(studentData.getUniversityCourse());
            Student createdStudent = studentService.addStudent(student);
            return ResponseEntity.ok(createdStudent);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }


    @PutMapping("/{studentId}/update")
    public ResponseEntity<Student> updateStudent(@PathVariable("studentId") Long studentId, @RequestBody Student student) {
        Student updateStudent = studentService.updateStudent(studentId, student);
        if (updateStudent != null) {
            return ResponseEntity.ok(updateStudent);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{studentId}/delete")
    public ResponseEntity<Void> deleteStudent(@PathVariable("studentId") Long studentId) {
        boolean deleted = studentService.deleteStudent(studentId);
        if (deleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

