package com.universityapp.modules.courses.entities;

import java.util.UUID;

import com.universityapp.common.enums.DayOfWeek;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "course_schedules")
@IdClass(CourseScheduleId.class)
public class CourseSchedule {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "course_id")
    private UUID courseId;

    @Id
    @Column(name = "day_of_week")
    @Enumerated(EnumType.STRING)
    private DayOfWeek dayOfWeek;

    @Id
    @Column(name = "session")
    private Integer session;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "course_id", nullable = false)
    private Course course;
}
