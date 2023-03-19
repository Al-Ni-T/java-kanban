package ru.yandex;

import ru.yandex.manager.*;
import ru.yandex.tasks.Epic;
import ru.yandex.tasks.Subtask;
import ru.yandex.tasks.Task;
import ru.yandex.tasks.TaskStatus;

public class Main {

    public static void main(String[] args) {

        TaskManager taskManager = Managers.getDefaultTask();


        Task tNumber1 = new Task("Задача №1", "Создаем задачу №1", TaskStatus.NEW);
        taskManager.createTask(tNumber1);

        Task tNumber2 = new Task("Задача №2", "Создаем задачу №2", TaskStatus.NEW);
        taskManager.createTask(tNumber2);

        Epic eNumber1 = new Epic("Эпик №1", "Создаем эпик в нем 2 подзадачи", TaskStatus.NEW);
        taskManager.createEpic(eNumber1);

        Subtask sNumber1 = new Subtask("Подзадача №1 эпика №1",
                "Создаем подзадачу №1", TaskStatus.NEW, eNumber1.getId());
        taskManager.createSubtask(sNumber1);

        Subtask sNumber2 = new Subtask("Подзадача №2 эпика №1",
                "Создаем подзадачу №2", TaskStatus.NEW, eNumber1.getId());
        taskManager.createSubtask(sNumber2);

        Epic eNumber2 = new Epic("Эпик №2", "Создаем эпик в нем 1 подзадача", TaskStatus.NEW);
        taskManager.createEpic(eNumber2);

        Subtask sNumber3 = new Subtask("Подзадача №1 эпика №2",
                "Создаем подзадачу №1", TaskStatus.NEW, eNumber2.getId());
        taskManager.createSubtask(sNumber3);

        taskManager.getTaskById(1);
        taskManager.getTaskById(2);
        taskManager.getTaskById(1);
        taskManager.getEpicById(3);
        taskManager.getEpicById(6);
        taskManager.getTaskById(2);
        taskManager.getSubtaskById(4);
        taskManager.getSubtaskById(5);
        taskManager.getEpicById(6);
        taskManager.getSubtaskById(7);
        taskManager.getSubtaskById(7);
        taskManager.getTaskById(2);
        taskManager.getTaskById(1);
        taskManager.getTaskById(1);
        taskManager.getTaskById(2);

        for (int taskId : Managers.getDefaultTask().getHistoryId()) {
            System.out.println(taskId);
        }

        taskManager.removeEpic(6);
        taskManager.removeSubtask(5);
        taskManager.removeTask(1);

        System.out.println();
        for (int taskId : Managers.getDefaultTask().getHistoryId()) {
            System.out.println(taskId);
        }
    }
}
