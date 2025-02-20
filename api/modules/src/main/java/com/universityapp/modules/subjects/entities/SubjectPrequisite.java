package com.universityapp.modules.subjects.entities;
import java.util.UUID;

import com.universityapp.common.enums.SubjectPrequisiteType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "subject_prequisites")
@IdClass(SubjectPrequisiteId.class)
public class SubjectPrequisite {
    @Id
    @Column(name = "subject_id")
    private UUID subjectId;

    @Id
    @Column(name = "prequisite_subject_id")
    private UUID prequisiteSubjectId;

    @Enumerated(EnumType.STRING)
    @Column(name = "prerequisite_type", nullable = true)
    private SubjectPrequisiteType type;

    @ManyToOne()
    @JoinColumn(name = "subject_id")
    private Subject subject;

    @ManyToOne()
    @JoinColumn(name = "prequisite_subject_id")
    private Subject prequisiteSubject;

}