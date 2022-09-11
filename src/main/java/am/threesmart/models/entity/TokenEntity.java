package am.threesmart.models.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "tokens")
@Getter
@Setter
@NoArgsConstructor
public class TokenEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String value;

    private Boolean expired = false;

    private Long userId;

    public TokenEntity(final String value, final Long userId) {
        this.value = value;
        this.userId = userId;
    }

    public boolean isExpired() {
        return this.expired;
    }

}
