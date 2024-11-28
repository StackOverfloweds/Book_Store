package models;

public abstract class Identifiable {
    private int id;

    public Identifiable(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
