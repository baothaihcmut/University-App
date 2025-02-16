package com.universityapp.common.entities;

import java.util.List;
import java.util.UUID;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "majors")
@Data
public class Major {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "major_id")
    private UUID majorId;

    @Column(name = "name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "faculity_id")
    private Faculity faculity;


    @OneToMany(mappedBy = "major")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<MajorTeacher> majorTeachers;


    @OneToMany(mappedBy = "major")
    @OnDelete(action = OnDeleteAction.SET_NULL)
    private List<Student> students;


    @OneToMany(mappedBy = "major")
    @OnDelete(action = OnDeleteAction.SET_NULL)
    private List<Subject> subjects;
}
