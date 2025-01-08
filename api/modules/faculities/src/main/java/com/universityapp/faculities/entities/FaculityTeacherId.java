package com.universityapp.faculities.entities;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FaculityTeacherId {
    private UUID faculityId;
    private UUID teacherId;
}
