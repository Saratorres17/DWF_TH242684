package saratorres.guia2.service;

import org.springframework.stereotype.Service;
import saratorres.guia2.model.Task;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Service
public class TaskService {
    private final Map<Long, Task> tasks = new HashMap<>();
    private final AtomicLong idCounter = new AtomicLong();

    public List<Task> getAllTasks() {
        return new ArrayList<>(tasks.values());
    }

    public Task addTask(Task task) {
        long id = idCounter.incrementAndGet();
        task.setId(id);
        tasks.put(id, task);
        return task;
    }

    public Task markAsCompleted(Long id) {
        Task task = tasks.get(id);
        if (task != null) {
            task.setCompleted(true);
        }
        return task;
    }

    public boolean deleteTask(Long id) {
        return tasks.remove(id) != null;
    }

    public List<Task> searchTasks(String keyword) {
        return tasks.values().stream()
                .filter(task -> task.getDescription().toLowerCase().contains(keyword.toLowerCase()))
                .collect(Collectors.toList());
    }

    public Task updateTask(Long id, String newDescription) {
        Task task = tasks.get(id);
        if (task != null) {
            task.setDescription(newDescription);
        }
        return task;
    }
}
