package am.threesmart.models.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserRegisterRequestDetails {

    private String name;
    private String surname;
    private String username;
    private String email;
    @JsonProperty("department_id")
    private Long departmentId;

}
