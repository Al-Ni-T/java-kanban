package ru.yandex.manager;

import ru.yandex.tasks.Epic;
import ru.yandex.tasks.Subtask;
import ru.yandex.tasks.Task;
import ru.yandex.tasks.TaskStatus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class InMemoryTaskManager implements TaskManager {

    HashMap<Integer, Task> taskById = new HashMap<>();
    HashMap<Integer, Epic> epicById = new HashMap<>();
    HashMap<Integer, Subtask> subtaskById = new HashMap<>();

    private int nextId = 1;

    private int getNextId() {
        return nextId;
    }

    HistoryManager historyManager = Managers.getDefaultHistory();

    @Override
    public void createTask(Task task) {
        int id = getNextId();
        task.setId(id);
        taskById.put(id, task);
        nextId++;
    }

    @Override
    public void updateTask(Task task) {
        taskById.put(task.getId(), task);
    }

    @Override
    public void removeTask(int id) {
        taskById.remove(id);
    }

    @Override
    public void createEpic(Epic epic) {
        int id = getNextId();
        epic.setId(id);
        epicById.put(id, epic);
        nextId++;
    }

    @Override
    public void updateEpic(Epic epic) {
        Epic old = epicById.get(epic.getId());
        if (old == null) {
            return;
        }
        old.setTitle(epic.getTitle());
        old.setDescription(epic.getDescription());
        epicById.put(epic.getId(), old);
    }

    @Override
    public void removeEpic(int id) {
        Epic delete = epicById.get(id);
        for (Integer i : delete.getSubtaskList()) {
            subtaskById.remove(i);
        }
        epicById.remove(id);

    }

    @Override
    public void createSubtask(Subtask subtask) {

        Epic epic = epicById.get(subtask.getEpicId());
        if (epic != null) {
            int id = getNextId();
            subtask.setId(id);
            subtaskById.put(id, subtask);
            nextId++;
            epic = epicById.get(subtask.getEpicId());
            epic.getSubtaskList().add(subtask.getId());
        }
        updateStatus(subtask);
    }

    @Override
    public void updateSubtask(Subtask subtask) {
        subtaskById.put(subtask.getId(), subtask);

        updateStatus(subtask);
    }

    @Override
    public void removeSubtask(int id) {
        Subtask subtask = subtaskById.get(id);
        Epic epic = epicById.get(subtask.getEpicId());
        epic.getSubtaskList().remove((Object) subtask.getId());
        subtaskById.remove(id);

        updateStatus(subtask);
    }

    private void updateStatus(Subtask subtask) {
        int sumByNew = 0;
        int sumByDone = 0;
        int counter = 0;
        for (Integer subtaskId : epicById.get(subtask.getEpicId()).getSubtaskList()) {
            counter++;
            if (subtaskById.get(subtaskId).getStatus().equals(TaskStatus.NEW)) {
                sumByNew++;
            } else if (subtaskById.get(subtaskId).getStatus().equals(TaskStatus.DONE)) {
                sumByDone++;
            }
        }
        if (sumByNew == counter) {
            Epic epic = epicById.get(subtask.getEpicId());
            epic.setStatus(TaskStatus.NEW);
        } else if (sumByDone == counter) {
            Epic epic = epicById.get(subtask.getEpicId());
            epic.setStatus(TaskStatus.DONE);
        } else {
            Epic epic = epicById.get(subtask.getEpicId());
            epic.setStatus(TaskStatus.IN_PROGRESS);
        }
    }

    @Override
    public List<Task> getTasks() {
        return new ArrayList<>(taskById.values());
    }

    @Override
    public List<Epic> getEpics() {
        return new ArrayList<>(epicById.values());
    }

    @Override
    public List<Subtask> getSubtasks() {
        return new ArrayList<>(subtaskById.values());
    }

    @Override
    public List<Subtask> getSubtasksForEpic(int epicId) {

        ArrayList<Subtask> getSubtasksFor = new ArrayList<>();
        Epic epic = epicById.get(epicId);
        for (int subtaskId : epic.getSubtaskList()) {
            getSubtasksFor.add(subtaskById.get(subtaskId));
        }
        return getSubtasksFor;
    }

    @Override
    public List<Object> listAllTasks() {

        ArrayList<Object> listAllTasks = new ArrayList<>();
        listAllTasks.add(getTasks());
        listAllTasks.add(getEpics());
        listAllTasks.add(getSubtasks());
        return listAllTasks;
    }

    @Override
    public void deleteAllTasks() {
        taskById.clear();
        epicById.clear();
        subtaskById.clear();
    }

    @Override
    public Task getTaskById(int id) {
        historyManager.addHistoryId(taskById.get(id));
        return taskById.get(id);
    }

    @Override
    public Epic getEpicById(int id) {
        historyManager.addHistoryId(epicById.get(id));
        return epicById.get(id);
    }

    @Override
    public Subtask getSubtaskById(int id) {
        historyManager.addHistoryId(subtaskById.get(id));
        return subtaskById.get(id);
    }

    @Override
    public List<Task> getHistoryId() {
        return historyManager.getHistoryId();
    }


}
