package am.threesmart.models.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Message {
    private Long id;
    private Long fromUserId;
    private Long toUserId;
    private String content;
    private String sentTime;
}
