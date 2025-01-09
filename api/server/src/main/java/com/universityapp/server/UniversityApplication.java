package com.universityapp.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication(scanBasePackages = "com.universityapp")
@EntityScan(basePackages = {
        "com.universityapp.users.entities",
        "com.universityapp.files.entities",
        "com.universityapp.admin.entities",
        "com.universityapp.certificates.entities",
        "com.universityapp.faculities.entities",
        "com.universityapp.majors.entities",
        "com.universityapp.faculity_teachers.entities",
        "com.universityapp.major_teachers.entities",
        "com.universityapp.subjects.entities",
        "com.universityapp.subject_teachers.entities",
        "com.universityapp.courses.entities",
        "com.universityapp.student_subjects.entities",
        "com.universityapp.notification.entities",
        "com.universityapp.course_students.entities",
        "com.universityapp.common.enums"
})
public class UniversityApplication {

    public static void main(String[] args) {
        SpringApplication.run(UniversityApplication.class, args);
    }

}
