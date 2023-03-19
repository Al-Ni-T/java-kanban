package ru.yandex.manager;

public abstract class Managers {
    private static HistoryManager historyManager =new InMemoryHistoryManager();
    private static TaskManager taskManager =new InMemoryTaskManager();

    public static HistoryManager getDefaultHistory() {
        return historyManager;
    }

    public static TaskManager getDefaultTask() {
        return taskManager;
    }
}
