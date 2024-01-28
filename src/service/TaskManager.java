package service;

import model.Epic;
import model.Subtask;
import model.Task;
import util.Status;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TaskManager {
    private final HashMap<Integer, Task> tasks;
    private final HashMap<Integer, Subtask> subtasks;
    private final HashMap<Integer, Epic> epics;
    private int taskCounter = 1;

    public TaskManager() {
        this.tasks = new HashMap<>();
        this.subtasks = new HashMap<>();
        this.epics = new HashMap<>();
    }

    public Task createTask(Task task) {
        task.setId(generateId());
        tasks.put(task.getId(), task);
        return task;
    }

    public Task getTask(int id) {
        return tasks.get(id);
    }

    public void updateTask(Task updatedTask) {
        tasks.put(updatedTask.getId(), updatedTask);
    }

    public void deleteTask(int id) {
        tasks.remove(id);
    }

    public List<Task> getAllTasks() {
        return new ArrayList<>(tasks.values());
    }

    public void removeAllTasks() {
        tasks.clear();
    }

    public Subtask createSubtask(Subtask subtask) {
        Integer epicId = subtask.getEpicId();
        subtask.setId(generateId());
        subtasks.put(subtask.getId(), subtask);
        List<Integer> subtaskIds = epics.get(subtask.getEpicId()).getSubtasks();

        subtaskIds.add(subtask.getId());
        calculateEpicStatus(epics.get(epicId));
        return subtask;
    }

    public Subtask getSubtask(int id) {
        return subtasks.get(id);
    }

    public void updateSubtask(Subtask subtask) {
        subtasks.put(subtask.getId(), subtask);
        calculateEpicStatus(epics.get(subtask.getEpicId()));
    }

    public void deleteSubtask(int id) {
        Subtask subtask = subtasks.get(id);
        List<Integer> subtaskIds = epics.get(subtask.getEpicId()).getSubtasks();

        subtaskIds.remove(subtask.getId());
        subtasks.remove(id);
        calculateEpicStatus(epics.get(subtask.getEpicId()));
    }

    public List<Subtask> getAllSubtasks() {
        return new ArrayList<>(subtasks.values());
    }

    public void removeAllSubtasks() {
        subtasks.clear();
        for (Epic epic : epics.values()) {
            calculateEpicStatus(epic);
        }
    }

    public Epic createEpic(Epic epic) {
        epic.setId(generateId());
        epics.put(epic.getId(), epic);
        return epic;
    }

    public Epic getEpic(int id) {
        return epics.get(id);
    }

    public void updateEpic(Epic epic) {
        Epic saved = epics.get(epic.getId());
        saved.setName(epic.getName());
        saved.setDescription(epic.getDescription());
    }

    public void deleteEpic(int id) {
        Epic epic = epics.remove(id);
        for (int subtaskId : epic.getSubtasks()) {
            subtasks.remove(subtaskId);
        }
    }

    public List<Epic> getAllEpics() {
        return new ArrayList<>(epics.values());
    }

    public List<Integer> getEpicSubtasks(int epicId) {
        return epics.get(epicId).getSubtasks();
    }

    public void removeAllEpics() {
        epics.clear();
        removeAllSubtasks();
    }

    public void calculateEpicStatus(Epic epic) {
        int doneTaskCounter = 0;
        int newTaskCounter = 0;
        List<Integer> subtaskIdList = epic.getSubtasks();

        for (int subtaskId : subtaskIdList) {
            if (subtasks.get(subtaskId).getStatus() == Status.DONE) {
                doneTaskCounter++;
            } else if (subtasks.get(subtaskId).getStatus() == Status.NEW) {
                newTaskCounter++;
            }
        }

        if (subtaskIdList.isEmpty()) {
            epic.setStatus(Status.NEW);
        } else {
            epic.setStatus(Status.IN_PROGRESS);
        }

        if (doneTaskCounter == subtaskIdList.size()) {
            epic.setStatus(Status.DONE);
        }

        if (newTaskCounter == subtaskIdList.size()) {
            epic.setStatus(Status.NEW);
        }
    }

    private Integer generateId() {
        return taskCounter++;
    }
}
