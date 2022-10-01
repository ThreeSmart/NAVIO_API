package am.threesmart.models.entity;

import am.threesmart.enums.TaskPriority;
import am.threesmart.enums.TaskStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "tasks")
@Getter
@Setter
@NoArgsConstructor
public class TaskEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Column(name = "owner_id")
    private Long ownerId;
    @Column(name = "assignee_id")
    private Long assigneeId;
    @Column(name = "start_date")
    private Long startDate;
    @Column(name = "end_date")
    private Long endDate;
    private TaskStatus status;
    private TaskPriority priority;

}
