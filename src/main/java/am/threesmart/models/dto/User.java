package am.threesmart.models.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class User {

    private String name;
    private String surname;
    private String username;
    private String email;
    private Long department_id;

}
