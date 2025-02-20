package com.universityapp.subject_teachers.entities;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SubjectTeacherId {
    private UUID subjectId;
    private UUID teacherId;
}
