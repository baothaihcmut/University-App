package com.universityapp.modules.subjects.entities;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SubjectPrequisiteId {
    private UUID subjectId;
    private UUID prequisiteSubjectId;
}
