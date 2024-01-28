package model;


import util.Status;

import java.util.ArrayList;
import java.util.List;

public class Epic extends Task {
    private final List<Integer> subtasks = new ArrayList<>();

    public Epic(String name, String description) {
        super(name, Status.NEW, description);
    }

    public List<Integer> getSubtasks() {
        return subtasks;
    }

    @Override
    public String toString() {
        return "Epic{" +
                "name=" + getName() + ", " +
                "id=" + getId() + ", " +
                "subtask Ids=" + subtasks + ", " +
                "status=" + getStatus() + " " +
                '}';
    }
}
