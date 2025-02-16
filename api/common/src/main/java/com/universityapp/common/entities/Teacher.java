package com.universityapp.common.entities;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "teachers")
public class Teacher {

    @Id
    @Column(name = "user_id")
    private UUID userId;

    @Column(name = "teacher_number", nullable = false, unique = true)
    private String teacherNumber;

    @MapsId
    @OneToOne
    @JoinColumn(name = "user_id")
    @JsonBackReference
    private User user;
}
