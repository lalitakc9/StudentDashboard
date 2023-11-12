package com.student.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Student")
@Table(name = "students")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long studentId;

    @Column(name = "student_name")
    @NotNull(message = "Student name required.")
    private String studentName;

    @Column(name = "university_name")
    @NotNull(message = "University name required.")
    private String universityName;

    @Column(name = "university_course")
    @NotNull(message = "University Course required.")
    private String universityCourse;
}
