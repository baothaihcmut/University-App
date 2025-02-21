package com.universityapp.server.initialize;

import com.universityapp.common.enums.Role;
import com.universityapp.modules.auth.services.AuthService;
import com.universityapp.modules.users.entities.Student;
import com.universityapp.modules.users.entities.Teacher;
import com.universityapp.modules.users.entities.User;
import com.universityapp.modules.users.repositories.UserRepository;
import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;
import java.time.LocalDate;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserInitialize {

    private final UserRepository userRepository;
    private final AuthService authService;

    @PostConstruct
    @Transactional
    public void createStudent() {
        if (
            this.userRepository.findUserByEmail(
                    "hieu.hoangminh832004@hcmut.edu.vn"
                ).orElse(null) !=
            null
        ) {
            return;
        }
        User user = User.builder()
            .userId(UUID.randomUUID())
            .role(Role.STUDENT)
            .email("hieu.hoangminh832004@hcmut.edu.vn")
            .firstName("Hoang")
            .lastName("Hieu")
            .password(authService.encodePassword("student123"))
            .birthday(LocalDate.now())
            .birthplace("test")
            .address("test")
            .build();
        Student student = Student.builder()
            .studentNumber("2210264")
            .schoolYear("K22")
            .startYear(LocalDate.now())
            .endYear(LocalDate.now())
            .build();
        user.setStudent(student);
        userRepository.save(user);
    }

    @PostConstruct
    public void createTeacher() {
        if (
            this.userRepository.findUserByEmail(
                    "bao.thaikhmt@hcmut.edu.vn"
                ).orElse(null) !=
            null
        ) {
            return;
        }
        User user = User.builder()
            .userId(UUID.randomUUID())
            .role(Role.STUDENT)
            .email("bao.thaikhmt@hcmut.edu.vn")
            .firstName("Thai")
            .lastName("Bao")
            .password(authService.encodePassword("teacher123"))
            .birthday(LocalDate.now())
            .birthplace("test")
            .address("test")
            .build();
        Teacher teacher = Teacher.builder().teacherNumber("1234").build();
        user.setTeacher(teacher);
        userRepository.save(user);
    }
}
