package models;

public class Recipe extends Model {

    public String name;

    public Recipe(String name) {
        this.name = name;
    }

    public String toString() {
      String d = ", ";
      return
        "(" + super.toString() + d + name + ")";
    }

}
