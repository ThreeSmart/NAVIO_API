package am.threesmart.models.dto;

import am.threesmart.enums.EmailType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SendEmail {
    private String email;
    private EmailType emailType;
}
