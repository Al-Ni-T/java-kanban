package ru.yandex;

import ru.yandex.manager.TaskManager;
import ru.yandex.tasks.Epic;
import ru.yandex.tasks.Subtask;
import ru.yandex.tasks.Task;
import ru.yandex.tasks.TaskStatus;

public class Main {

    public static void main(String[] args) {
        TaskManager taskManager = new TaskManager();

        Task tNumber1 = new Task(taskManager.getNextId(), "Задача №1", "Создаем задачу №1", TaskStatus.NEW);
        taskManager.createTask(tNumber1);

        Task tNumber2 = new Task(taskManager.getNextId(), "Задача №2", "Создаем задачу №2", TaskStatus.NEW);
        taskManager.createTask(tNumber2);

        Epic eNumber1 = new Epic(taskManager.getNextId(), "Эпик №1", "Создаем эпик в нем 2 подзадачи", TaskStatus.NEW); //, new ArrayList<>());
        taskManager.createEpic(eNumber1);

        Subtask sNumber1 = new Subtask(taskManager.getNextId(), "Подзадача №1 эпика №1",
                "Создаем подзадачу №1", TaskStatus.NEW, eNumber1.getId());
        taskManager.createSubtask(sNumber1);

        Subtask sNumber2 = new Subtask(taskManager.getNextId(), "Подзадача №2 эпика №1",
                "Создаем подзадачу №2", TaskStatus.NEW, eNumber1.getId());
        taskManager.createSubtask(sNumber2);

        Epic eNumber2 = new Epic(taskManager.getNextId(), "Эпик №2", "Создаем эпик в нем 1 подзадача", TaskStatus.NEW);
        taskManager.createEpic(eNumber2);

        Subtask sNumber3 = new Subtask(taskManager.getNextId(), "Подзадача №1 эпика №2",
                "Создаем подзадачу №1", TaskStatus.NEW, eNumber2.getId());
        taskManager.createSubtask(sNumber3);

        taskManager.printTask();

        tNumber1 = new Task(1, "Задача №1", "Редактируем задачу №1", TaskStatus.DONE);
        taskManager.updateTask(tNumber1);

        tNumber2 = new Task(2, "Задача №2", "Редактируем задачу №2", TaskStatus.DONE);
        taskManager.updateTask(tNumber2);

        eNumber1 = new Epic(3, "Эпик №1", "Редактируем эпик в нем 2 подзадачи", TaskStatus.NEW,
                eNumber1.getSubtaskList());
        taskManager.updateEpic(eNumber1);

        sNumber1 = new Subtask(4, "Подзадача №2 эпика №1", "Редактируем подзадачу №2",
                TaskStatus.DONE, eNumber1.getId());
        taskManager.updateSubtask(sNumber1);

        sNumber2 = new Subtask(5, "Подзадача №2 эпика №1",
                "Редактируем подзадачу №2", TaskStatus.NEW, eNumber1.getId());
        taskManager.updateSubtask(sNumber2);

        eNumber2 = new Epic(6, "Эпик №2", "Редактируем эпик в нем 1 подзадача", TaskStatus.NEW,
                eNumber2.getSubtaskList());
        taskManager.updateEpic(eNumber2);

        sNumber3 = new Subtask(7, "Подзадача №1 эпика №2",
                "Создаем подзадачу №1", TaskStatus.DONE, eNumber2.getId());
        taskManager.updateSubtask(sNumber3);

        taskManager.printTask();

        taskManager.removeTask(tNumber1);

        taskManager.removeEpic(eNumber2);

        taskManager.removeSubtask(sNumber2);

        taskManager.printTask();

    }
}
