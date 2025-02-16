package com.universityapp.common.entities;
import java.util.UUID;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "subjects")
public class Subject {

    @Id
    @Column(name = "subject_id", nullable = false)
    private UUID subjectId;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "credits", nullable = false)
    private int credits;

    @Column(name = "description", nullable = true)
    private String description;

    @ManyToOne
    @JoinColumn(name = "major_id")
    private Major major;

    @Column(name = "exam_ratio")
    private int examRatio;

    @Column(name = "test_ratio")
    private int testRatio;

    @Column(name = "assignment_ratio")
    private int assignmentRatio;

    @Column(name = "exercise_ratio")
    private int exerciseRatio;
}
