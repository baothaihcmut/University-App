package com.universityapp.majors.entities;

import java.util.UUID;

import com.universityapp.faculities.entities.Faculity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "majors")
@Data
public class Major {

    @Id
    @Column(name = "major_id")
    private UUID majorId;

    @Column(name = "name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "faculity_id")
    private Faculity faculity;
}
