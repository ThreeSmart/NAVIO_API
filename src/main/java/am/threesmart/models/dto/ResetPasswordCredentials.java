package am.threesmart.models.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResetPasswordCredentials {
    private String password;
    private String token;
}
