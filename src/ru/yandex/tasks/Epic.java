package ru.yandex.tasks;

import java.util.ArrayList;

public class Epic extends Task {

    ArrayList<Integer> subtaskList = new ArrayList<>();

    public Epic(int id, String title, String description, TaskStatus status, ArrayList<Integer> subtaskList) {
        super(id, title, description, status);
        this.subtaskList = subtaskList;
    }

    public Epic(int id, String title, String description, TaskStatus status) {
        super(id, title, description, status);
    }

    public ArrayList<Integer> getSubtaskList() {
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
