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
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "faculities")
public class Faculity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "faculity_id")
    private UUID faculityId;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "faculity",fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<FaculityTeacher> faculityTeachers;


    @OneToMany(mappedBy = "faculity",fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.SET_NULL)
    private List<Major> majors;
}
