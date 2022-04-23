package am.threesmart.services;

import am.threesmart.exceptions.UserNotFountException;
import am.threesmart.jwt.JWTService;
import am.threesmart.mappers.UserMapper;
import am.threesmart.models.dto.UserLoginRequestDetails;
import am.threesmart.models.dto.UserLoginResponseDetails;
import am.threesmart.models.dto.UserRegisterRequestDetails;
import am.threesmart.models.dto.UserRegisterResponseDetails;
import am.threesmart.models.entity.UserEntity;
import am.threesmart.repositories.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
public class EmployeeService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    private final JWTService jwtService;

    public EmployeeService(final UserRepository userRepository,
                           final PasswordEncoder passwordEncoder,
                           final JWTService jwtService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    public UserLoginResponseDetails login(final UserLoginRequestDetails request) {
        final Optional<UserEntity> userOptional = userRepository.findByUsername(request.getUsername());

        if (userOptional.isEmpty()) {
            throw new UserNotFountException();
        }

        final UserEntity userEntity = userOptional.get();

        if (!passwordEncoder.matches(request.getPassword(), userEntity.getPassword())) {
            throw new UserNotFountException();
        }

        final UserLoginResponseDetails response = new UserLoginResponseDetails();
        response.setJwt(UUID.randomUUID().toString());
        return response;
    }

    public UserRegisterResponseDetails register(final UserRegisterRequestDetails request) {
        final UserEntity userEntity = UserMapper.instance.registerRequestToEntity(request);
        userEntity.setPassword("asd");
        userEntity.setRole_id(1);
        userEntity.setMapping_id(null);
        userEntity.setWorking_status(true);
        userEntity.setStatus(true);
        userEntity.setParent_id(123L);
        final UserEntity save = userRepository.save(userEntity);
        return null;
    }

    public UserLoginResponseDetails loginAdmin(final UserLoginRequestDetails request) {
        final Optional<UserEntity> userOptional = userRepository.findByUsername(request.getUsername());

        if (userOptional.isEmpty()) {
            System.out.println("No Admin found with username: " + request.getUsername());
            throw new UserNotFountException();
        }

        final UserEntity userEntity = userOptional.get();

        if (Objects.equals(userEntity.getPassword(), "admin")) {
            if (!Objects.equals(request.getPassword(), "admin")) {
                System.out.println("Error to login Admin: password missmatch");
                throw new UserNotFountException();
            }
            System.out.print("Admin without changing password logged in, ");
        } else if (!passwordEncoder.matches(request.getPassword(), userEntity.getPassword())) {
            System.out.println("Error to login Admin: password missmatch");
            throw new UserNotFountException();
        }

        final UserLoginResponseDetails response = new UserLoginResponseDetails();
        final String jwt = jwtService.generate(userEntity);
        response.setJwt(jwt);
        System.out.println("Successfully logged in Admin with jwt: " + jwt);
        return response;
    }

}
