package am.threesmart.models.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AuthenticatedUser {
    private String id;
    private String username;
    private String name;
    private String surname;
    private String email;
    private Long departmentId;
}