package com.universityapp.modules.major_teachers.entities;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MajorTeacherId {
    private UUID teacherId;
    private UUID majorId;
}
