package service;

import model.Epic;
import model.Subtask;
import model.Task;
import util.Status;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TaskManager {
    public HashMap<Integer, Task> tasks;
    public HashMap<Integer, Subtask> subtasks;
    public HashMap<Integer, Epic> epics;
    private int taskCounter = 1;

    public TaskManager() {
        this.tasks = new HashMap<>();
        this.subtasks = new HashMap<>();
        this.epics = new HashMap<>();
    }

    public int generateId() {
        return taskCounter++;
    }

    public Task createTask(Task task) {
        task.setId(generateId());
        task.setStatus(Status.NEW);
        task.setDescription("Шаблонное описание...");
        tasks.put(task.getId(), task);
        return task;
    }

    public Task getTask(int id) {
        Task foundTask = tasks.get(id);
        return foundTask;
    }

    public void updateTask(Task updatedTask) {
        tasks.put(updatedTask.getId(), updatedTask);
    }

    public void deleteTask(int id) {
        tasks.remove(id);
    }

    public List<Task> getAllTasks() {
        List<Task> taskList = new ArrayList<>();
        Task task;
        for (int key : tasks.keySet()) {
            task = tasks.get(key);
            taskList.add(task);
        }
        return taskList;
    }

    public void removeAllTasks() {
        tasks.clear();
    }

    public Subtask createSubtask(Subtask subtask) {
        subtask.setId(generateId());
        subtask.setEpic(subtask.getEpic());
        subtask.setStatus(Status.NEW);
        subtask.setDescription("Шаблонное описание...");
        subtasks.put(subtask.getId(), subtask);
        return subtask;
    }

    public Subtask getSubtask(int id) {
        Subtask foundSubtask = subtasks.get(id);
        return foundSubtask;
    }

    public void updateSubtask(Subtask subtask) {
        subtasks.put(subtask.getId(), subtask);
        calculateEpicStatus(subtask.getEpic());
    }

    public void deleteSubtask(int id) {
        Subtask subtask = subtasks.get(id);
        if (subtask.getId() == id) {
            subtasks.remove(id);
        }
    }

    public List<Subtask> getAllSubtasks() {
        List<Subtask> subtaskList = new ArrayList<>();
        Subtask subtask;
        for (int key : subtasks.keySet()) {
            subtask = subtasks.get(key);
            subtaskList.add(subtask);
        }
        return subtaskList;
    }

    public void removeAllSubtasks() {
        subtasks.clear();
    }

    public Epic createEpic(Epic epic) {
        epic.setId(generateId());
        calculateEpicStatus(epic);
        epic.setDescription("Шаблонное описание...");
        epics.put(epic.getId(), epic);
        return epic;
    }

    public void addSubtasksToEpic(Epic epic) {
        Subtask subtask;
        for (int key : subtasks.keySet()) {
            if (subtasks.get(key).getEpic().equals(epic)) {
                subtask = subtasks.get(key);
                epic.getSubtasks().add(subtask);
            }
        }
    }

    public Epic getEpic(int id) {
        Epic foundEpic = epics.get(id);
        return foundEpic;
    }

    public void updateEpic(Epic epic) {
        Epic saved = epics.get(epic.getId());
        calculateEpicStatus(saved);
        epics.put(epic.getId(), saved);
    }

    public void deleteEpic(int id) {
        Epic epic = epics.get(id);

        for (Subtask subtask : epic.getSubtasks()) {
            if (subtask.getEpic().getId() == id) {
                subtasks.remove(subtask.getId());
            }
        }

        if (epic.getId() == id) {
            epics.remove(id);
        }
    }

    public List<Epic> getAllEpics() {
        List<Epic> epicList = new ArrayList<>();
        Epic epic;
        for (int key : epics.keySet()) {
            epic = epics.get(key);
            epicList.add(epic);
        }
        return epicList;
    }

    public List<Subtask> getEpicSubtasks(Epic epic) {
        return epic.getSubtasks();
    }

    public void removeAllEpics() {
        epics.clear();
        removeAllSubtasks();
    }

    public void calculateEpicStatus(Epic epic) {
        int doneTaskCounter = 0;
        int newTaskCounter = 0;
        List<Subtask> epicSubtasks = epic.getSubtasks();

        if (epicSubtasks.size() == 0) {
            epic.setStatus(Status.NEW);
        }

        for (Subtask subtask : epicSubtasks) {
            if (subtask.getStatus() == Status.DONE) {
                doneTaskCounter++;
                if (doneTaskCounter == epicSubtasks.size()) {
                    epic.setStatus(Status.DONE);
                } else {
                    epic.setStatus(Status.IN_PROGRESS);
                }
            } else if (subtask.getStatus() == Status.NEW) {
                newTaskCounter++;
                if (newTaskCounter == epicSubtasks.size()) {
                    epic.setStatus(Status.NEW);
                } else {
                    epic.setStatus(Status.IN_PROGRESS);
                }
            }
        }
    }
}
