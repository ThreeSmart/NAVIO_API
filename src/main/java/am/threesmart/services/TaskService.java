package am.threesmart.services;

import am.threesmart.enums.TaskStatus;
import am.threesmart.exceptions.UserNotFoundException;
import am.threesmart.mappers.TaskMapper;
import am.threesmart.models.dto.AuthenticatedUser;
import am.threesmart.models.dto.Task;
import am.threesmart.models.entity.TaskEntity;
import am.threesmart.repositories.TaskRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    public TaskService(final TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public List<Task> getAllPendingTasks(final int page, final int size) {
        return getByStatusAndAssigneeId(TaskStatus.PENDING, page, size);
    }

    public List<Task> getAllCompletedTasks(final int page, final int size) {
        return getByStatusAndAssigneeId(TaskStatus.COMPLETED, page, size);
    }

    private List<Task> getByStatusAndAssigneeId(final TaskStatus status, final int page, int size) {
        final Optional<AuthenticatedUser> currentUser = AuthenticatedUserService.getCurrentUser();
        if (currentUser.isEmpty()) {
            System.out.println("Not authenticated user can not get any task list, this case is: " + status);
            throw new UserNotFoundException();
        }

        final List<TaskEntity> byStatus =
                taskRepository.findByStatusAndAssigneeId(
                        status,
                        currentUser.get().getId(),
                        PageRequest.of(page, size)
                );


        byStatus.sort((o1, o2) -> {
            if (o1.getPriority().toString().equals(o2.getPriority().toString())) {
                return 0;
            }
            if ("HIGH".equals(o1.getPriority().toString())) {
                return -1;
            } else if ("MEDIUM".equals(o1.getPriority().toString())) {
                if ("HIGH".equals(o2.getPriority().toString())) {
                    return 1;
                }
            } else if ("LOW".equals(o1.getPriority().toString())) {
                return 1;
            }
            return 0;
        });


        final List<Task> result = new ArrayList<>();
        byStatus.forEach(e -> result.add(TaskMapper.instance.entityToDto(e)));

        return result;
    }

    public void createTask(final Task task) {
        task.setStartDate(System.currentTimeMillis());
        task.setEndDate(0L);
        task.setStatus(TaskStatus.PENDING);
        taskRepository.save(TaskMapper.instance.dtoToEntity(task));
    }

}
