package am.threesmart.controllers;

import am.threesmart.email.EmailService;
import am.threesmart.models.dto.SendEmail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/send/email")
public class EmailController {

    private final EmailService emailService;

    public EmailController(final EmailService emailService) {
        this.emailService = emailService;
    }

    @PostMapping("/by_type")
    public ResponseEntity<?> sendEmail(@RequestBody final SendEmail sendEmail) {
        emailService.send(sendEmail);
        return ResponseEntity.ok(
                Map.of(
                        "response",
                        String.format(
                                "The email to: %s was sent with type: %s",
                                sendEmail.getEmail(),
                                sendEmail.getEmailType()
                        ))
        );
    }

}
