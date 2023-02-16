package ru.yandex.tasks;

import java.util.ArrayList;
import java.util.List;

public class Epic extends Task {

    List<Integer> subtaskList = new ArrayList<>();

    public Epic(int id, String title, String description) {
        super(id, title, description);
    }

    public Epic(String title, String description, TaskStatus status) {
        super(title, description, status);
    }

    public List<Integer> getSubtaskList() {
        return subtaskList;
    }

    @Override
    public String toString() {
        return "Epic{" +
                "id=" + getId() +
                ", title='" + getTitle() + '\'' +
                ", description='" + getDescription() + '\'' +
                ", status=" + getStatus() + '\'' +
                ", ArrList=" + getSubtaskList() +
                '}';
    }
}
