package com.universityapp.courses.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.universityapp.course_students.entities.CourseStudent;
import com.universityapp.subjects.entities.Subject;
import com.universityapp.users.entities.Teacher;

@Data
@Entity
@Table(name = "courses")
public class Course {

    @Id
    @Column(name = "course_id", nullable = false)
    private UUID id;

    @Column(name = "max_student_quantity", nullable = false)
    private int maxStudentQuantity;

    @Column(name = "current_student_quantity", nullable = false)
    private int currentStudentQuantity;

    @Column(name = "room", nullable = false)
    private String room;

    @Column(name = "start_date", nullable = false)
    private LocalDate startDate;

    @Column(name = "end_date", nullable = false)
    private LocalDate endDate;

    @Column(name = "semester", nullable = false)
    private String semester;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "teacher_id", nullable = true)
    private Teacher teacher;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "subject_id", nullable = false)
    private Subject subject;

    @OneToMany(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "course_id")
    private List<CourseSchedule> courseSchedules;

    @OneToMany(mappedBy = "course", fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<CourseStudent> courseStudents;
}
