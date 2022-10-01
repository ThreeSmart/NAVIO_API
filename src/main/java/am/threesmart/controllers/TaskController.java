package am.threesmart.controllers;

import am.threesmart.models.dto.Task;
import am.threesmart.services.TaskService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
@PreAuthorize("permitAll()")
public class TaskController {

    private final TaskService taskService;

    public TaskController(final TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/completed")
    public ResponseEntity<List<Task>> getAllCompletedTasks(@RequestParam final int page, @RequestParam final int size) {
        return ResponseEntity.ok(taskService.getAllCompletedTasks(page, size));
    }

    @GetMapping("/pending")
    public ResponseEntity<List<Task>> getAllPendingTasks(@RequestParam final int page, @RequestParam final int size) {
        return ResponseEntity.ok(taskService.getAllPendingTasks(page, size));
    }

    @PostMapping("/create")
    public ResponseEntity<String> createTask(@RequestBody final Task task) {
        taskService.createTask(task);
        return ResponseEntity.ok("saved task");
    }

}
