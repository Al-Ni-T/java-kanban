package ru.yandex.manager;

import ru.yandex.tasks.Epic;
import ru.yandex.tasks.Subtask;
import ru.yandex.tasks.Task;

import java.util.List;

public interface TaskManager {
    void createTask(Task task);

    void updateTask(Task task);

    void removeTask(int id);

    void createEpic(Epic epic);

    void updateEpic(Epic epic);

    void removeEpic(int id);

    void createSubtask(Subtask subtask);

    void updateSubtask(Subtask subtask);

    void removeSubtask(int id);

    List<Task> getTasks();

    List<Epic> getEpics();

    List<Subtask> getSubtasks();

    List<Subtask> getSubtasksForEpic(int epicId);

    List<Object> listAllTasks();

    void deleteAllTasks();

    Task getTaskById(int id);

    Epic getEpicById(int id);

    Subtask getSubtaskById(int id);
}
