package am.threesmart.models.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "messages")
@Getter
@Setter
@NoArgsConstructor
public class MessagesEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String content;
    private Long fromUserId;
    private Long toUserId;
    private String sentTime;
    private Boolean seen;
}
