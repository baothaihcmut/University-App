package com.universityapp.modules.subject_teachers.entities;
import java.util.UUID;

import com.universityapp.modules.subjects.entities.Subject;
import com.universityapp.modules.users.entities.Teacher;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "subject_teachers")
@Data
@IdClass(SubjectTeacher.class)
public class SubjectTeacher {
    @Id
    @Column(name = "subject_id")
    private UUID subjectId;

    @Id
    @Column(name = "teacher_id")
    private UUID teacherId;

    @ManyToOne
    @JoinColumn(name = "teacher_id")
    private Teacher teacher;

    @ManyToOne
    @JoinColumn(name = "subject_id")
    private Subject subject;
}
