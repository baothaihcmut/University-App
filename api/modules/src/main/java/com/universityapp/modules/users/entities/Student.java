package com.universityapp.modules.users.entities;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.universityapp.modules.majors.entities.Major;
import com.universityapp.modules.student_subjects.entities.StudentSubject;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "students")
public class Student {

    @Id
    @Column(name = "user_id")
    private UUID userId;

    @Column(name = "student_number", nullable = false, unique = true)
    private String studentNumber;

    @Column(name = "school_year")
    private String schoolYear;

    @Column(name = "start_year")
    private LocalDate startYear;

    @Column(name = "end_year")
    private LocalDate endYear;

    @ManyToOne()
    @JoinColumn(name = "major_id")
    private Major major;

    @MapsId
    @OneToOne
    @JoinColumn(name = "user_id")
    @JsonBackReference
    private User user;


    @OneToMany(mappedBy = "student")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<StudentSubject> subjectScores;
}
