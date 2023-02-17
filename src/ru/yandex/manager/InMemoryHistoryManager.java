package ru.yandex.manager;

import ru.yandex.tasks.Task;

import java.util.ArrayList;
import java.util.List;

public class InMemoryHistoryManager implements HistoryManager {
    private static final List<Task> historyId = new ArrayList<>(10);

    public void addHistoryId(Task task) {
        if (historyId.size() == 10) {
            historyId.remove(9);
        }
        historyId.add(0, task);
    }

    public List<Task> getHistoryId() {
        return new ArrayList<>(historyId);
    }
}
