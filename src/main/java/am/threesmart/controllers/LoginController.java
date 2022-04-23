package am.threesmart.controllers;

import am.threesmart.models.dto.UserLoginRequestDetails;
import am.threesmart.models.dto.UserLoginResponseDetails;
import am.threesmart.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
@PreAuthorize("permitAll()")
public class LoginController {

    private final UserService userService;

    public LoginController(final UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/user")
    public ResponseEntity<?> login(@RequestBody UserLoginRequestDetails request) {
        final UserLoginResponseDetails response = userService.login(request);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/admin")
    public ResponseEntity<?> loginAdmin(@RequestBody UserLoginRequestDetails request) {
        final UserLoginResponseDetails response = userService.loginAdmin(request);
        return ResponseEntity.ok(response);
    }


}
