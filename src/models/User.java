package models;

public class User extends Account {
    public User(final int id, final String username, final String password) {
        super(id, username, password);
    }

    @Override
    public String toString() {
        return "User{" + super.toString() + "}";
    }
}
