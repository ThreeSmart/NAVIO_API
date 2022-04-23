package am.threesmart.controllers;

import am.threesmart.models.dto.UserRegisterRequestDetails;
import am.threesmart.models.dto.UserRegisterResponseDetails;
import am.threesmart.services.EmployeeService;
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

    private final EmployeeService employeeService;

    public RegisterController(final EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping("/user")
    public ResponseEntity<?> register(@RequestBody final UserRegisterRequestDetails request) {
        final UserRegisterResponseDetails response = employeeService.register(request);
        return ResponseEntity.ok("saved user");
    }

}
