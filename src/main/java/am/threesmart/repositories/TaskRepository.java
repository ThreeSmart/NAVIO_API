package am.threesmart.repositories;

import am.threesmart.enums.TaskStatus;
import am.threesmart.models.entity.TaskEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends PagingAndSortingRepository<TaskEntity, Long> {

    List<TaskEntity> findByStatusAndAssigneeId(TaskStatus status, Long assigneeId, Pageable pageable);

    long countAllByStatus(TaskStatus status);

}
