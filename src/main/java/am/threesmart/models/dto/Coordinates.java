package am.threesmart.models.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Coordinates {

    private Long id;
    private Double x;
    private Double y;
    private Double z;
    private Long userId;

}
