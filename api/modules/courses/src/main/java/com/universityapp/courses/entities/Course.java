package com.universityapp.courses.entities;

import java.time.LocalDate;
import java.util.UUID;

import com.universityapp.subjects.entities.Subject;
import com.universityapp.users.entities.Teacher;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "courses")
public class Course {

    @Id
    @Column(name = "course_id", nullable = false)
    private UUID id;

    @Column(name = "max_student_quantity", nullable = false)
    private int maxStudentQuantity;

    @Column(name = "current_student_quantity", nullable = false)
    private int currentStudentQuantity;

    @Column(name = "room", nullable = false)
    private String room;

    @Column(name = "start_date", nullable = false)
    private LocalDate startDate;

    @Column(name = "end_date", nullable = false)
    private LocalDate endDate;

    @Column(name = "semester", nullable = false)
    private String semester;

    @ManyToOne()
    @JoinColumn(name = "teacher_id", nullable = true)
    private Teacher teacher;

    @ManyToOne()
    @JoinColumn(name = "subject_id", nullable = false)
    private Subject subject;
}
