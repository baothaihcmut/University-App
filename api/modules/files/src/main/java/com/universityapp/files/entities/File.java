package com.universityapp.files.entities;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "files")
public class File {

    @Id
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

    public enum MimeType {
        IMAGE_JPEG,
        IMAGE_PNG,
        APPLICATION_PDF,
        TEXT_PLAIN,
        OTHER // Add other MIME types as needed
    }
}
