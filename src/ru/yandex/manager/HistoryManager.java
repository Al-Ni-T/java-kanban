package ru.yandex.manager;

import ru.yandex.tasks.Task;

import java.util.List;

public interface HistoryManager {

    void addHistoryId(Task task);

    List<Task> getHistoryId();
}
