package com.universityapp.modules.certificates.entities;

import java.io.Serializable;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CertificateFileId implements Serializable {
    private UUID certificateId;
    private UUID fileId;
}
