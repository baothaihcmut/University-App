package com.universityapp.common.initilize;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;

@Component
public class DatabaseMigration {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @PostConstruct
    public void runMigrations() {
        // Load and execute each SQL migration file
        executeSqlFromFile("/db/migrations/V1__insert_admin_user.sql");

    }

    private void executeSqlFromFile(String filePath) {
        InputStream inputStream = getClass().getResourceAsStream(filePath);
        try (Scanner scanner = new Scanner(inputStream, StandardCharsets.UTF_8)) {
            String sql = scanner.useDelimiter("\\A").next();
            jdbcTemplate.execute(sql);
        }
    }
}
