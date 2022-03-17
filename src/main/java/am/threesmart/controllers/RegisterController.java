package am.threesmart.controllers;

import am.threesmart.models.dto.EmployeeRegisterRequestDetails;
import am.threesmart.models.dto.EmployeeRegisterResponseDetails;
import am.threesmart.services.EmployeeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/register")
public class RegisterController {

    private final EmployeeService employeeService;

    public RegisterController(final EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    public ResponseEntity<?> register(@RequestBody final EmployeeRegisterRequestDetails request) {
        final EmployeeRegisterResponseDetails response = employeeService.register(request);
        return ResponseEntity.ok(response);
    }

}
