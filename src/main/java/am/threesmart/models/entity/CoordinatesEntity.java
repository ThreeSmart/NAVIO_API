package am.threesmart.models.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "coordinates")
@Getter
@Setter
@NoArgsConstructor
public class CoordinatesEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double x;
    private Double y;
    private Double z;
    private Long userId;

}
