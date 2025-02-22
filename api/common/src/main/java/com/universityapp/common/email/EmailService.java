package com.universityapp.common.email;

import java.util.Map;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.universityapp.common.properties.MailProperties;

import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmailService {
    private final JavaMailSender mailSender;
    private final TemplateEngine templateEngine;
    private final MailProperties mailProperties;

    public void sendEmail(String to, String subject, String templateName, Map<String, Object> attribute)
            throws Exception {
        Context context = new Context();
        for (Map.Entry<String, Object> entry : attribute.entrySet()) {
            context.setVariable(entry.getKey(), entry.getValue());
        }
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
        String htmlContent = templateEngine.process(templateName, context);
        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(htmlContent, true);
        helper.setFrom(mailProperties.getUsername());
        mailSender.send(message);
    }

}
