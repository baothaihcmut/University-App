package com.universityapp.faculity_teachers.entities;
import java.util.UUID;

import com.universityapp.faculities.entities.Faculity;
import com.universityapp.users.entities.Teacher;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "faculity_teachers")
@IdClass(FaculityTeacherId.class)
public class FaculityTeacher {
    @Id
    @Column(name = "teacher_id")
    private UUID teacherId;

    @Id
    @Column(name = "faculity_id")
    private UUID faculityId;

    @ManyToOne
    @JoinColumn(name = "teacher_id")
    private Teacher teacher;

    @ManyToOne
    @JoinColumn(name = "faculity_id")
    private Faculity faculity;
}
