package com.universityapp.modules.course_students.entities;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CourseStudentId {
    private UUID courseId;
    private UUID studentId;
}
