import model.Epic;
import model.Subtask;
import model.Task;
import service.TaskManager;
import util.Status;

public class Main {
    public static void main(String[] args) {
        TaskManager taskManager = new TaskManager();
        Task task1 = taskManager.createTask(new Task("Задача 1"));
        Task task2 = taskManager.createTask(new Task("Задача 2"));

        Epic epic1 = taskManager.createEpic(new Epic("Эпик 1"));
        Subtask subtask1 = taskManager.createSubtask(new Subtask(epic1, "Подзадача 1"));
        Subtask subtask2 = taskManager.createSubtask(new Subtask(epic1, "Подзадача 2"));
        taskManager.addSubtasksToEpic(epic1);

        Epic epic2 = taskManager.createEpic(new Epic("Эпик 2"));
        Subtask subtask3 = taskManager.createSubtask(new Subtask(epic2, "Подзадача 3"));
        taskManager.addSubtasksToEpic(epic2);

        System.out.println(taskManager.epics);
        System.out.println(taskManager.tasks);
        System.out.println(taskManager.subtasks);

        task1.setStatus(Status.IN_PROGRESS);
        task2.setStatus(Status.IN_PROGRESS);
        taskManager.updateTask(task1);
        taskManager.updateTask(task2);
        System.out.println(taskManager.tasks);

        subtask1.setStatus(Status.DONE);
        subtask2.setStatus(Status.DONE);
        subtask3.setStatus(Status.NEW);
        taskManager.updateSubtask(subtask1);
        taskManager.updateSubtask(subtask2);
        taskManager.updateSubtask(subtask3);
        System.out.println(taskManager.subtasks);

        taskManager.updateEpic(epic1);
        taskManager.updateEpic(epic2);
        System.out.println(taskManager.epics);

        taskManager.deleteTask(1);
        taskManager.deleteEpic(3);

        System.out.println(taskManager.tasks);
        System.out.println(taskManager.epics);

    }
}
