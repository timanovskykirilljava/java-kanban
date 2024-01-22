package model;


import java.util.ArrayList;
import java.util.List;

public class Epic extends Task {
    List<Subtask> subtasks = new ArrayList<>();

    public Epic(String name) {
        super(name);
    }

    public List<Subtask> getSubtasks() {
        return subtasks;
    }

    @Override
    public String toString() {
        return "Epic{" +
                "name=" + getName() + ", " +
                "id=" + getId() + ", " +
                "subtasks=" + subtasks + ", " +
                "status=" + getStatus() + " " +
                '}';
    }
}
