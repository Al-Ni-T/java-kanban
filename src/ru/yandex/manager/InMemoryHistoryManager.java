package ru.yandex.manager;

import ru.yandex.tasks.Task;

import java.util.ArrayList;
import java.util.List;

public class InMemoryHistoryManager implements HistoryManager {
    private static final List<Task> historyId = new ArrayList<>();

    public void addHistoryId(Task task) {
        if (historyId.size() == 10) {
            historyId.remove(0);
            historyId.add(task);
        } else {
            historyId.add(task);
        }
    }

    public List<Task> getHistoryId() {
        return new ArrayList<>(historyId);
    }
}
