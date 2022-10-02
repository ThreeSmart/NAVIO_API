package am.threesmart.services;

import am.threesmart.enums.TaskStatus;
import am.threesmart.exceptions.NoSuchTaskException;
import am.threesmart.exceptions.UserNotFoundException;
import am.threesmart.mappers.TaskMapper;
import am.threesmart.models.dto.AuthenticatedUser;
import am.threesmart.models.dto.Task;
import am.threesmart.models.entity.TaskEntity;
import am.threesmart.repositories.TaskRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;

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
        return getByStatusAndAssigneeId(TaskStatus.COMPLETE, page, size);
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
                if ("LOW".equals(o2.getPriority().toString())) {
                    return -1;
                }
            } else if ("LOW".equals(o1.getPriority().toString())) {
                return 1;
            }
            return 0;
        });


        final List<Task> result = new ArrayList<>();
        byStatus.forEach(e -> {
            final Task task = TaskMapper.instance.entityToDto(e);

            task.setStartDate(getDateFromMilliseconds(e.getStartDate()));

            if (e.getEndDate() == 0) {
                task.setEndDate("xx/xx/xxxx");
            } else {
                task.setEndDate(getDateFromMilliseconds(e.getEndDate()));
            }

            result.add(task);
        });

        return result;
    }

    public void createTask(final Task task) {
        final TaskEntity taskEntity = TaskMapper.instance.dtoToEntity(task);
        taskEntity.setStartDate(System.currentTimeMillis());
        taskEntity.setEndDate(0L);
        taskEntity.setStatus(TaskStatus.PENDING);
        taskRepository.save(taskEntity);
    }

    private String getDateFromMilliseconds(final Long milliseconds) {
        final Date date = new Date(milliseconds);
        final LocalDate startLocalDate = date.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
        final DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return startLocalDate.format(format);
    }

    public Task complete(final Long id) {
        final Optional<TaskEntity> byId = taskRepository.findById(id);
        if (byId.isEmpty()) {
            throw new NoSuchTaskException();
        }

        final TaskEntity taskEntity = byId.get();
        taskEntity.setStatus(TaskStatus.COMPLETE);
        taskEntity.setEndDate(System.currentTimeMillis());
        taskRepository.save(taskEntity);

        return TaskMapper.instance.entityToDto(taskEntity);
    }
}
