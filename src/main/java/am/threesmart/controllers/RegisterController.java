package am.threesmart.controllers;

import am.threesmart.models.dto.UserRegisterRequestDetails;
import am.threesmart.models.dto.UserRegisterResponseDetails;
import am.threesmart.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/register")
@PreAuthorize("permitAll()")
public class RegisterController {

    private final UserService userService;

    public RegisterController(final UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/user")
    public ResponseEntity<?> register(@RequestBody final UserRegisterRequestDetails request) {
        final UserRegisterResponseDetails response = userService.register(request);
        return ResponseEntity.ok("saved user " + response.getUsername());
    }

}
