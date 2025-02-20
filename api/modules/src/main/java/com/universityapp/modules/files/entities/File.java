package com.universityapp.modules.files.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.List;
import java.util.UUID;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.universityapp.modules.certificates.entities.CertificateFile;


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

    @OneToMany(mappedBy = "file", fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<CertificateFile> certificateFiles;

    public enum MimeType {
        IMAGE_JPEG,
        IMAGE_PNG,
        APPLICATION_PDF,
        TEXT_PLAIN,
        OTHER, // Add other MIME types as needed
    }
}
