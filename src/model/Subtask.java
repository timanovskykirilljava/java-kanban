package model;

import util.Status;

public class Subtask extends Task {
    private final Integer epicId;

    public Subtask(String name, Status status, String description, Integer epicId) {
        super(name, status, description);
        this.epicId = epicId;
    }

    public Integer getEpicId() {
        return epicId;
    }


    @Override
    public String toString() {
        return "Subtask{" +
                "id= " + getId() + ", " +
                "name= " + getName() + ", " +
                "epicId= " + getEpicId() + ", " +
                "status=" + getStatus() + " " +
                '}';
    }
}
