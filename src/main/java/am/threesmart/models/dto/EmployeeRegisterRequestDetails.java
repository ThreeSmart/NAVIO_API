package am.threesmart.models.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class EmployeeRegisterRequestDetails {

    private String name;
    private String surname;
    private String username;
    private String password;
    private String email;
    private String image_url;
    private String department_id;
    private String role_id;
    private Boolean working_status;
    private Boolean status;

}
