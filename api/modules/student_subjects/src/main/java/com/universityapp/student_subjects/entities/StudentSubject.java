package com.universityapp.student_subjects.entities;

import java.util.UUID;

import com.universityapp.subjects.entities.Subject;
import com.universityapp.users.entities.Student;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "studetn_subjects")
@Data
@IdClass(StudentSubjectId.class)
public class StudentSubject {
    @Id
    @Column(name = "student_id", nullable = false)
    private UUID studentId;

    @ManyToOne()
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;

    @Id
    @Column(name = "subject_id", nullable = false)
    private UUID subjectId;

    @ManyToOne()
    @JoinColumn(name = "subject_id", nullable = false)
    private Subject subject;

    @Column(name = "assignment_score", nullable = true)
    private float assignmentScore;

    @Column(name = "exercise_score", nullable = true)
    private float exerciseScore;

    @Column(name = "exam_score", nullable = true)
    private float examScore;

    @Column(name = "test_score", nullable = true)
    private float testScore;

    @Column(name = "avg_score", nullable = true)
    private float avgScore;

    @Column(name = "semester", nullable = false)
    private String semester;
}
