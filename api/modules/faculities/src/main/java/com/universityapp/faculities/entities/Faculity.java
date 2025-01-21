package com.universityapp.faculities.entities;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "faculities")
public class Faculity {
    @Id
    @Column(name = "faculity_id")
    private UUID faculityId;

    @Column(name = "name")
    private String name;
}
