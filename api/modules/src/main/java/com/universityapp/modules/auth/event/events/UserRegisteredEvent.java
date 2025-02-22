package com.universityapp.modules.auth.event.events;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserRegisteredEvent {
    private String email;
    private String code;
    private String firstName;
    private String lastName;
}
