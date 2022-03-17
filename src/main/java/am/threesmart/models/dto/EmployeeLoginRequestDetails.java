package am.threesmart.models.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class EmployeeLoginRequestDetails {
    private String username;
    private String password;
}
