import model.Epic;
import model.Subtask;
import model.Task;
import service.TaskManager;
import util.Status;

public class Main {
    public static void main(String[] args) {
        TaskManager taskManager = new TaskManager();
        Task task1 = taskManager.createTask(new Task("Задача 1", Status.NEW, "несложная"));
        Task task2 = taskManager.createTask(new Task("Задача 2", Status.NEW, "нелёгкая"));

        Epic epic1 = taskManager.createEpic(new Epic("Эпик 1", "12357"));
        Subtask subtask1 = taskManager.createSubtask(new Subtask("Саб 1", Status.NEW, "да нормас", epic1.getId()));
        Subtask subtask2 = taskManager.createSubtask(new Subtask("Саб 2", Status.IN_PROGRESS, "пффф", epic1.getId()));

        Epic epic2 = taskManager.createEpic(new Epic("Эпик 2", "second epic"));
        Subtask subtask3 = taskManager.createSubtask(new Subtask("Саб 3", Status.DONE, "несложно", epic2.getId()));

        System.out.println(taskManager.getAllEpics());
        System.out.println("_____________________________________________________________");
        System.out.println(taskManager.getAllTasks());
        System.out.println("_____________________________________________________________");
        System.out.println(taskManager.getAllSubtasks());
        System.out.println("_____________________________________________________________");

        task1.setStatus(Status.IN_PROGRESS);
        task2.setStatus(Status.IN_PROGRESS);
        taskManager.updateTask(task1);
        taskManager.updateTask(task2);
        System.out.println(taskManager.getAllTasks());
        System.out.println("_____________________________________________________________");

        subtask1.setStatus(Status.DONE);
        subtask2.setStatus(Status.DONE);
        subtask3.setStatus(Status.NEW);
        taskManager.updateSubtask(subtask1);
        taskManager.updateSubtask(subtask2);
        taskManager.updateSubtask(subtask3);
        System.out.println(taskManager.getAllSubtasks());
        System.out.println("_____________________________________________________________");

        taskManager.updateEpic(epic1);
        taskManager.updateEpic(epic2);
        System.out.println(taskManager.getAllEpics());
        System.out.println("_____________________________________________________________");

        taskManager.deleteTask(1);
        taskManager.deleteEpic(3);

        System.out.println(taskManager.getAllTasks());
        System.out.println("_____________________________________________________________");
        System.out.println(taskManager.getAllEpics());
        System.out.println("_____________________________________________________________");

    }
}
