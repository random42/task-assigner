package models;

public class User extends Model {

    private String name;
    private String role;

    public User(String name, String role) {
        this.name = name;
        this.role = role;
    }

    public boolean isCook() {
      return role.equals("cook");
    }

    public boolean isChef() {
        return role.equals("chef");
    }

    public String toString() {
        return this.name;
    }
}
