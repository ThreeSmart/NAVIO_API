package am.threesmart.models.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SentMessage {
    private Long from;
    private Long to;
    private String content;
    private String sentTime;
}
