package com.universityapp.modules.users.entities;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.universityapp.common.enums.Role;
import com.universityapp.modules.files.entities.File;
import com.universityapp.modules.notification.entities.Notification;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {

    @Id
    @Column(name = "user_id")
    private UUID userId;

    @Column(name = "first_name", length = 255, nullable = false)
    private String firstName;

    @Column(name = "last_name", length = 255, nullable = false)
    private String lastName;

    @Column(name = "email", length = 255, nullable = false, unique = true)
    private String email;

    @Column(name = "password", columnDefinition = "TEXT")
    private String password;

    @Column(name = "current_refresh_token", columnDefinition = "TEXT")
    private String currentRefreshToken;

    @Column(name = "phone_number", length = 20)
    private String phoneNumber;

    @OneToOne
    @JoinColumn(name = "image_id", nullable = true)
    private File image;

    @Column(name = "birthplace", length = 255)
    private String birthplace;

    @Column(name = "birthday")
    private LocalDate birthday;

    @Column(name = "social_network_info", columnDefinition = "TEXT")
    private String socialNetworkInfo;

    @Column(name = "address", columnDefinition = "TEXT")
    private String address;

    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false)
    private Role role;

    @Column(name = "is_active", columnDefinition = "BOOLEAN DEFAULT FALSE")
    private Boolean isActive;

    @OneToOne(mappedBy = "user", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JsonManagedReference
    private Student student;

    @OneToOne(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonManagedReference
    private Teacher teacher;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonManagedReference
    @Builder.Default()
    private List<Dependent> dependents = new ArrayList<>();

    @OneToMany(mappedBy = "receiver")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<Notification> receiveNotifications;

    @OneToMany(mappedBy = "sender")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<Notification> sendNotifications;

    public void setStudent(Student student) {
        if (student != null) {
            student.setUser(this);
            this.student = student;
        }
    }

    public void setTeacher(Teacher teacher) {
        if (teacher != null) {
            teacher.setUser(this);
            this.teacher = teacher;
        }
    }

    public void addDependent(Dependent dependent) {
        dependent.setUser(this);
        this.dependents.add(dependent);
    }
}
