package ru.yandex.manager;

import ru.yandex.tasks.Epic;
import ru.yandex.tasks.Subtask;
import ru.yandex.tasks.Task;
import ru.yandex.tasks.TaskStatus;

import java.util.ArrayList;
import java.util.HashMap;

public class TaskManager {

    HashMap<Integer, Task> taskById = new HashMap<>();
    HashMap<Integer, Epic> epicById = new HashMap<>();
    HashMap<Integer, Subtask> subtaskById = new HashMap<>();

    private int nextId = 1;

    public int getNextId() {
        return nextId;
    }

    public void createTask(Task task) {
        taskById.put(nextId, task);
        nextId++;
    }

    public void updateTask(Task task) {
        taskById.put(task.getId(), task);
    }

    public void removeTask(Task task) {
        taskById.remove(task.getId());
    }

    public void createEpic(Epic epic) {
        epicById.put(nextId, epic);
        nextId++;
    }

    public void updateEpic(Epic epic) {
        epicById.put(epic.getId(), epic);
    }

    public void removeEpic(Epic epic) {
        Epic delete = epicById.get(epic.getId());
        for (Integer i : delete.getSubtaskList()) {
            subtaskById.remove(i);
        }
        epicById.remove(epic.getId());

    }

    public void createSubtask(Subtask subtask) {
        if (!epicById.isEmpty()) {
            subtaskById.put(nextId, subtask);
            nextId++;
            Epic epic = epicById.get(subtask.getEpicId());
            epic.getSubtaskList().add(subtask.getId());
        }
    }

    public void updateSubtask(Subtask subtask) {
        subtaskById.put(subtask.getId(), subtask);

        updateStatus(subtask);
    }

    public void removeSubtask(Subtask subtask) {

        Epic epic = epicById.get(subtask.getEpicId());
        epic.getSubtaskList().remove((Object) subtask.getId());
        subtaskById.remove(subtask.getId());

        updateStatus(subtask);
    }

    public void updateStatus(Subtask subtask) {
        ArrayList<TaskStatus> statusList = new ArrayList<>();

        for (Subtask subtask1 : subtaskById.values()) {
            if (subtask1.getEpicId() == subtask.getEpicId()) {
                statusList.add(subtask1.getStatus());
            }
        }
        boolean flagNew = true;
        for (int i = 0; i < statusList.size(); i++) {
            if (!statusList.get(i).equals(TaskStatus.NEW)) {
                flagNew = false;
                break;
            }
        }
        boolean flagDone = true;
        for (int i = 0; i < statusList.size(); i++) {
            if (!statusList.get(i).equals(TaskStatus.DONE)) {
                flagDone = false;
                break;
            }
        }
        if (flagNew) {
            Epic epic = epicById.get(subtask.getEpicId());
            epic.setStatus(TaskStatus.NEW);
        } else if (flagDone) {
            Epic epic = epicById.get(subtask.getEpicId());
            epic.setStatus(TaskStatus.DONE);
        } else {
            Epic epic = epicById.get(subtask.getEpicId());
            epic.setStatus(TaskStatus.IN_PROGRESS);
        }
    }

    public void printTask() {
        if (!taskById.isEmpty()) {
            System.out.println("Список Задач:");
            for (Task task : taskById.values()) {
                System.out.println(task);
            }
        }
        if (!epicById.isEmpty()) {
            System.out.println("Список Эпиков:");
            for (Task task : epicById.values()) {
                System.out.println(task);
            }
        }
        if (!subtaskById.isEmpty()) {
            System.out.println("Список Субзадач:");
            for (Task task : subtaskById.values()) {
                System.out.println(task);
            }
        }
        System.out.println();
    }
}
