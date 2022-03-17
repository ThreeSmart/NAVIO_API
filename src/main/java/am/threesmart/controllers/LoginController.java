package am.threesmart.controllers;

import am.threesmart.models.dto.EmployeeLoginRequestDetails;
import am.threesmart.models.dto.EmployeeLoginResponseDetails;
import am.threesmart.services.EmployeeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class LoginController {

    private final EmployeeService employeeService;

    public LoginController(final EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping
    public ResponseEntity<?> login(@RequestBody EmployeeLoginRequestDetails request) {
        final EmployeeLoginResponseDetails response = employeeService.login(request);
        return ResponseEntity.ok(response);
    }


}
