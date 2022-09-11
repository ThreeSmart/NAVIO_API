package am.threesmart.services;

import am.threesmart.exceptions.InvalidTokenException;
import am.threesmart.exceptions.UserNotFountException;
import am.threesmart.jwt.JWTService;
import am.threesmart.mappers.UserMapper;
import am.threesmart.models.dto.*;
import am.threesmart.models.entity.TokenEntity;
import am.threesmart.models.entity.UserEntity;
import am.threesmart.repositories.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JWTService jwtService;
    private final TokenService tokenService;

    public UserService(final UserRepository userRepository,
                       final PasswordEncoder passwordEncoder,
                       final JWTService jwtService,
                       final TokenService tokenService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.tokenService = tokenService;
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
        final String jwt = jwtService.generate(userEntity);
        response.setJwt(jwt);
        return response;
    }

    public UserRegisterResponseDetails register(final UserRegisterRequestDetails request) {
        final UserEntity userEntity = UserMapper.instance.registerRequestToEntity(request);
        userEntity.setPassword(passwordEncoder.encode("user"));
        userEntity.setRole_id(1);
        userEntity.setWorking_status(true);
        userEntity.setStatus(true);
        userEntity.setParent_id(1L);
        userEntity.setDepartment_id(request.getDepartmentId());
        final UserEntity saved = userRepository.save(userEntity);
        final UserRegisterResponseDetails response = new UserRegisterResponseDetails();
        response.setUsername(saved.getUsername());
        return response;
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

    public List<User> getAllUsers() {
        final Iterable<UserEntity> all = userRepository.findAll();
        final ArrayList<User> result = new ArrayList<>();
        all.forEach(e -> {
            User user = UserMapper.instance.entityToDto(e);
            result.add(user);
        });
        return result;
    }

    public User getUserById(final Long userId) {
        final Optional<UserEntity> byId = userRepository.findById(userId);
        return byId.map(UserMapper.instance::entityToDto).orElse(null);
    }

    public User getUserByEmail(final String email) {
        final Optional<UserEntity> byEmail = userRepository.findByEmail(email);
        return byEmail.map(UserMapper.instance::entityToDto).orElse(null);
    }

    public void resetPassword(final ResetPasswordCredentials resetPasswordCredentials) {
        final TokenEntity token = tokenService.getToken(resetPasswordCredentials.getToken());
        if (token == null) {
            System.out.printf("No such token: %s\n", resetPasswordCredentials.getToken());
            throw new InvalidTokenException();
        }

        if (token.isExpired()) {
            System.out.printf("Token: %s is expired\n", token.getValue());
            throw new InvalidTokenException();
        }

        final Optional<UserEntity> userOptional = userRepository.findById(token.getUserId());

        if (userOptional.isEmpty()) {
            System.out.printf("User with %s id does not exists for token %s\n", token.getUserId(), token.getValue());
            throw new UserNotFountException();
        }

        final UserEntity user = userOptional.get();
        user.setPassword(passwordEncoder.encode(resetPasswordCredentials.getPassword()));
        userRepository.save(user);

        tokenService.expireToken(token);
    }

}
