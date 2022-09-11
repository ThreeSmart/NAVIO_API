package am.threesmart.models.dto;

import am.threesmart.enums.EmailType;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SendEmail {
    private String email;
    @JsonProperty("email_type")
    private EmailType emailType;
}
