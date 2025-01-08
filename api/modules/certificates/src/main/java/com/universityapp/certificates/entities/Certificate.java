package com.universityapp.certificates.entities;

import java.util.List;
import java.util.UUID;

import com.universityapp.users.entities.User;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "certificates")
public class Certificate {

    @Id
    @Column(name = "certificate_id", nullable = false)
    private UUID certificateId;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = true)
    private User user;

    @Column(name = "name", nullable = false)
    private String name;

    @OneToMany(mappedBy = "certificate")
    private List<CertificateFile> files;
}
