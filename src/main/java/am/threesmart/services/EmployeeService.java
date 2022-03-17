package am.threesmart.services;

import am.threesmart.exceptions.UserNotFountException;
import am.threesmart.models.dto.EmployeeLoginRequestDetails;
import am.threesmart.models.dto.EmployeeLoginResponseDetails;
import am.threesmart.models.dto.EmployeeRegisterRequestDetails;
import am.threesmart.models.dto.EmployeeRegisterResponseDetails;
import am.threesmart.models.entity.Employee;
import am.threesmart.repositories.EmployeeRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final PasswordEncoder passwordEncoder;

    public EmployeeService(final EmployeeRepository employeeRepository, final PasswordEncoder passwordEncoder) {
        this.employeeRepository = employeeRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public EmployeeLoginResponseDetails login(final EmployeeLoginRequestDetails request) {
        final Optional<Employee> userOptional = employeeRepository.findByUsername(request.getUsername());

        if(userOptional.isEmpty()) {
            throw new UserNotFountException();
        }

        final Employee employee = userOptional.get();

        if(!passwordEncoder.matches(request.getPassword(), employee.getPassword())) {
            throw new UserNotFountException();
        }

        final EmployeeLoginResponseDetails response = new EmployeeLoginResponseDetails();
        response.setJwt(UUID.randomUUID().toString());
        return response;
    }

    public EmployeeRegisterResponseDetails register(final EmployeeRegisterRequestDetails request) {
        // map dto to entity and save
        return null;
    }
}
