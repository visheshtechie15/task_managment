package com.example.assignment.controller;

import com.example.assignment.model.TaskRequest;
import com.example.assignment.model.TaskStatus;
import com.example.assignment.services.TaskService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class TaskController {

    private final TaskService taskManager;

    public TaskController(TaskService taskManager) {
        this.taskManager = taskManager;
    }

    @PostMapping("/queueTask")
    public ResponseEntity<?> queueTask(@RequestBody TaskRequest request)
            throws InterruptedException {

        TaskStatus status = taskManager.queueTask(request);

        return ResponseEntity.ok(Map.of(
                "id", request.getId(),
                "status", status
        ));
    }

    @PostMapping("/checkStatus")
    public ResponseEntity<?> checkStatus(@RequestBody Map<String, String> body) {

        String id = body.get("id");
        TaskStatus status = taskManager.checkStatus(id);

        return ResponseEntity.ok(Map.of(
                "id", id,
                "status", status
        ));
    }

    @PostMapping("/stopTask")
    public ResponseEntity<?> stopTask(@RequestBody Map<String, String> body) {

        String id = body.get("id");
        TaskStatus status = taskManager.stopTask(id);

        return ResponseEntity.ok(Map.of(
                "id", id,
                "status", status
        ));
    }
}
