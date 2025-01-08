package com.universityapp.major_teachers.entities;

import java.util.UUID;

import com.universityapp.majors.entities.Major;
import com.universityapp.users.entities.Teacher;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "major_teachers")
@IdClass(MajorTeacherId.class)
public class MajorTeacher {
    @Id
    @Column(name = "major_id")
    private UUID majorId;

    @Id
    @Column(name = "teacher_id")
    private UUID teacherId;

    @ManyToOne
    @JoinColumn(name = "major_id")
    private Major major;

    @ManyToOne
    @JoinColumn(name = "teacher_id")
    private Teacher teacher;
}
