package am.threesmart.controllers;

import am.threesmart.models.dto.ResetPasswordCredentials;
import am.threesmart.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/reset")
public class ResetPassword {

    private final UserService userService;

    public ResetPassword(final UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/password")
    public ResponseEntity<?> resetPassword(@RequestBody final ResetPasswordCredentials resetPasswordCredentials) {
        userService.resetPassword(resetPasswordCredentials);
        return ResponseEntity.ok(Map.of("response", "Now you can login with your new password!"));
    }

}
