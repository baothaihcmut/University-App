package com.universityapp.auth.presenters.input;
import java.time.LocalDate;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class SignUpInput {

    @NotNull(message = "First name is required")
    private String firstName;

    @NotNull(message = "Last name is required")
    private String lastName;

    @NotNull(message = "Email is required")
    @Email(message = "Email should be valid")
    private String email;

    @NotNull(message = "Password is required")
    private String password;

    @NotNull(message = "Phone number is required")
    @Pattern(regexp = "^(\\+?\\d{1,4}[-\\s]?)?(\\(?\\d{1,4}\\)?[-\\s]?)?\\d{7,10}$", message = "Phone number is invalid")
    private String phoneNumber;

    @NotNull(message = "Birthplace is required")
    private String birthplace;

    @NotNull(message = "Birthday is required")
    private LocalDate birthday;

    @NotNull(message = "Social network information is required")
    private String socialNetworkInfo;

    @NotNull(message = "Address is required")
    private String address;

    @NotNull(message = "Role is required")
    @Pattern(regexp = "^(TEACHER|STUDENT)$", message = "Role must be either TEACHER or STUDENT")
    private String role;
}
