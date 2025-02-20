package com.universityapp.common.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;



@Configuration
@EnableJpaRepositories(basePackages = {
    "com.universityapp.modules.admin.repositories",
    "com.universityapp.modules.users.repositories",
})
@EntityScan(basePackages = {
    "com.universityapp.modules.admin.entities",
    "com.universityapp.modules.certificates.entities",
    "com.universityapp.modules.course_students.entities",
    "com.universityapp.modules.courses.entities",
    "com.universityapp.modules.faculities.entities",
    "com.universityapp.modules.faculity_teachers.entities",
    "com.universityapp.modules.files.entities",
    "com.universityapp.modules.major_teachers.entities",
    "com.universityapp.modules.majors.entities",
    "com.universityapp.modules.notification.entities",
    "com.universityapp.modules.student_subjects.entities",
    "com.universityapp.modules.subject_teachers.entities",
    "com.universityapp.modules.subjects.entities",
    "com.universityapp.modules.users.entities",
})
public class JpaConfig {

}
