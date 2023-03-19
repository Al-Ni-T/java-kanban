package ru.yandex.manager;

import java.util.List;

public interface HistoryManager {

    void addHistoryId(int taskId);

    List<Integer> getHistoryId();

    void removeNodeInCustomList(int taskId);


}
