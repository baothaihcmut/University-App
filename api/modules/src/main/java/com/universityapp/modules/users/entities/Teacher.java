package com.universityapp.modules.users.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.universityapp.modules.courses.entities.Course;
import com.universityapp.modules.faculity_teachers.entities.FaculityTeacher;
import com.universityapp.modules.major_teachers.entities.MajorTeacher;
import com.universityapp.modules.subject_teachers.entities.SubjectTeacher;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.util.List;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Data
@Entity
@Table(name = "teachers")
@AllArgsConstructor
@NoArgsConstructor
@Builder
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

    @OneToMany(mappedBy = "teacher", fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.SET_NULL)
    private List<Course> courses;

    @OneToMany(mappedBy = "teacher", fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<FaculityTeacher> faculityTeachers;

    @OneToMany(mappedBy = "teacher")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<MajorTeacher> majorTeachers;

    @OneToMany(mappedBy = "teacher")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<SubjectTeacher> subjectTeachers;
}
