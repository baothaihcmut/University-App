package com.universityapp.common.entities;

import java.util.UUID;



import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "course_students")
@IdClass(CourseStudentId.class)
public class CourseStudent {
    @Id
    @Column(name = "course_id")
    private UUID courseId;

    @Id
    @Column(name = "student_id")
    private UUID studentId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "course_id", nullable = false)
    private Course course;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;
    @Column(name = "assignment_score", nullable = true)
    private Float assignmentScore;

    @Column(name = "exercise_score", nullable = true)
    private Float exerciseScore;

    @Column(name = "exam_score", nullable = true)
    private Float examScore;

    @Column(name = "test_score", nullable = true)
    private Float testScore;

    @Column(name = "avg_score", nullable = true)
    private Float avgScore;
}
