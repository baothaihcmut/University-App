package com.universityapp.common.entities;
import java.util.List;
import java.util.UUID;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "subjects")
public class Subject {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
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

    @OneToMany(mappedBy = "subject",fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.SET_NULL)
    private List<Course> courses;


    @OneToMany(mappedBy = "subject")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<StudentSubject> studentSubjectScores;


    @OneToMany(mappedBy = "subject")
    @OnDelete(action =  OnDeleteAction.CASCADE)
    private List<SubjectPrequisite> prequisiteSubjects;

    @OneToMany(mappedBy = "prequisiteSubject")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<SubjectPrequisite> postSubjects;


    @OneToMany(mappedBy = "subject")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<SubjectTeacher> subjectTeachers;


}
