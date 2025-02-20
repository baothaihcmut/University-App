package com.universityapp.majors.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.List;
import java.util.UUID;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.universityapp.faculities.entities.Faculity;
import com.universityapp.major_teachers.entities.MajorTeacher;
import com.universityapp.subjects.entities.Subject;
import com.universityapp.users.entities.Student;

@Entity
@Table(name = "majors")
@Data
public class Major {

    @Id
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
