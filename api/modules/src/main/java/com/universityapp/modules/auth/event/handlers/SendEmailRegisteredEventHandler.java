package com.universityapp.modules.auth.event.handlers;

import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.universityapp.modules.auth.event.events.UserRegisteredEvent;
import com.universityapp.modules.auth.services.UserConfirmService;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class SendEmailRegisteredEventHandler {

    private final UserConfirmService userConfirmService;

    @Async
    @EventListener
    public void sendEmailConfirm(UserRegisteredEvent event) throws Exception {
        this.userConfirmService.sendEmail(event.getCode(), 
            event.getEmail(),
            event.getFirstName(),
            event.getLastName());
    }

}
