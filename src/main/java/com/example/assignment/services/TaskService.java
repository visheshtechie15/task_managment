package com.example.assignment.services;

import com.example.assignment.model.TaskControl;
import com.example.assignment.model.TaskRequest;
import com.example.assignment.model.TaskStatus;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ThreadPoolExecutor;

@Service
public class TaskService {

    private final ThreadPoolExecutor executor;

    private final Map<String, TaskControl> taskMap = new ConcurrentHashMap<>();

    public TaskService(ThreadPoolExecutor executor) {
        this.executor = executor;
    }

    public TaskStatus queueTask(TaskRequest request) throws InterruptedException {

        if (taskMap.containsKey(request.getId())) {
            throw new IllegalStateException("Duplicate task ID");
        }

        TaskControl control = new TaskControl();
        control.status = TaskStatus.QUEUED;
        taskMap.put(request.getId(), control);

        executor.execute(() -> runTask(request, control));


        control.latch.await();

        return control.status;
    }

    private void runTask(TaskRequest request, TaskControl control) {

        synchronized (control) {
            if (control.status == TaskStatus.STOPPED) {
                control.latch.countDown();
                return;
            }
            control.status = TaskStatus.RUNNING;
            control.runningThread = Thread.currentThread();
        }

        try {
            Thread.sleep(request.getTime() * 1000L);

            synchronized (control) {
                if (control.status != TaskStatus.STOPPED) {
                    control.status = TaskStatus.DONE;
                }
            }

        } catch (InterruptedException e) {
            synchronized (control) {
                control.status = TaskStatus.STOPPED;
            }
            Thread.currentThread().interrupt();
        } finally {
            control.latch.countDown();
        }
    }


    public TaskStatus checkStatus(String id) {
        TaskControl control = taskMap.get(id);
        return control == null ? TaskStatus.DONE : control.status;
    }

    public TaskStatus stopTask(String id) {

        TaskControl control = taskMap.get(id);
        if (control == null) {
            return TaskStatus.DONE;
        }

        synchronized (control) {
            if (control.status == TaskStatus.QUEUED) {
                control.status = TaskStatus.STOPPED;
                control.latch.countDown();
            } else if (control.status == TaskStatus.RUNNING && control.runningThread != null) {
                control.status = TaskStatus.STOPPED;
                control.runningThread.interrupt();
            }
        }
        return TaskStatus.STOPPED;
    }
}
