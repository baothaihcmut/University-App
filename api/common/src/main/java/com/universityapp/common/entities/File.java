package com.universityapp.common.entities;

import java.util.List;
import java.util.UUID;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "files")
public class File {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "file_id")
    private UUID fileId;

    @Column(name = "link", nullable = false)
    private String link;

    @Enumerated(EnumType.STRING)
    @Column(name = "mime_type", nullable = false)
    private MimeType mimeType;

    @Column(name = "size", nullable = false)
    private int size;

    @Column(name = "is_uploaded", nullable = false)
    private boolean isUploaded;

    @OneToMany(mappedBy = "file",fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<CertificateFile> certificateFiles;

    public enum MimeType {
        IMAGE_JPEG,
        IMAGE_PNG,
        APPLICATION_PDF,
        TEXT_PLAIN,
        OTHER // Add other MIME types as needed
    }
}
