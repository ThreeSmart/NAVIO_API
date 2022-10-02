package am.threesmart.models.dto;

import am.threesmart.enums.TaskPriority;
import am.threesmart.enums.TaskStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Task {

    private Long id;
    private String name;
    private Long ownerId;
    private Long assigneeId;
    private String startDate;
    private String endDate;
    private TaskStatus status;
    private TaskPriority priority;

}
