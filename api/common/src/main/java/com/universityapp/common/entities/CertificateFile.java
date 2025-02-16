package com.universityapp.common.entities;

import java.util.UUID;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "certificate_files")
@IdClass(CertificateFileId.class)
@Data
public class CertificateFile {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "certificate_id", nullable = false)
    private UUID certificateId;

    @Id
    @Column(name = "file_id", nullable = false)
    private UUID fileId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "certificate_id", nullable = false, insertable = false, updatable = false)
    private Certificate certificate;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "file_id", nullable = false, insertable = false, updatable = false)
    private File file;

    @Column(name = "description", nullable = true)
    private String description;
}
