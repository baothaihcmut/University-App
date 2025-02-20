package com.universityapp.modules.student_subjects.entities;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class StudentSubjectId {
    private UUID studentId;
    private UUID subjectId;
}
