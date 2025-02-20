package com.universityapp.modules.users.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.universityapp.common.enums.DependentType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.util.UUID;
import lombok.Data;

@Data
@Entity
@Table(name = "dependents")
public class Dependent {

    @Id
    @Column(name = "dependent_id")
    private UUID dependentId;

    @Enumerated(EnumType.STRING)
    @Column(name = "dependent_type", nullable = false)
    private DependentType dependentType;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "phone_number")
    private String phoneNumber;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false)
    @JsonBackReference
    private User user;
}
