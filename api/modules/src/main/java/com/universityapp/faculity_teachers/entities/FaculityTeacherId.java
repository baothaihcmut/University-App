package com.universityapp.faculity_teachers.entities;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FaculityTeacherId {
    private UUID teacherId;
    private UUID faculityId;
}
