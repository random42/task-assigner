package models;

import java.util.*;

public class Menu extends Model {

  public String title;
  public List<Recipe> recipes;

  public Menu() {
    recipes = new ArrayList<>();
  }

  public Menu(String t, List<Recipe> r) {
    title = t;
    recipes = r;
  }

  public String toString() {
    String d = ", ";
    return
      "(" + super.toString() + d + title + d + recipes.size() + ")";
  }

}
