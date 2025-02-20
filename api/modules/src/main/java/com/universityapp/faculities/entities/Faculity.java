package com.universityapp.faculities.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.List;
import java.util.UUID;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.universityapp.faculity_teachers.entities.FaculityTeacher;
import com.universityapp.majors.entities.Major;

@Entity
@Table(name = "faculities")
public class Faculity {

    @Id
    @Column(name = "faculity_id")
    private UUID faculityId;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "faculity", fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<FaculityTeacher> faculityTeachers;

    @OneToMany(mappedBy = "faculity", fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.SET_NULL)
    private List<Major> majors;
}
