package model;

public class Subtask extends Task {
    Epic epic;

    public Subtask(Epic epic, String name) {
        super(name);
        this.epic = epic;
    }

    public Epic getEpic() {
        return epic;
    }

    public void setEpic(Epic epic) {
        this.epic = epic;
    }

    @Override
    public String toString() {
        return "Subtask{" +
                "id= " + getId() + ", " +
                "name= " + getName() + ", " +
                "epic= " + epic.getId() + ", " +
                "status=" + getStatus() + " " +
                '}';
    }
}
