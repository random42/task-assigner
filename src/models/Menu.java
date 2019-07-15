package models;

import java.util.*;

public class Menu extends Model {

  public String title;
  public Collection<Recipe> recipes;

  public Menu() {
    recipes = new ArrayList<>();
  }

  public Menu(String t, Collection<Recipe> r) {
    title = t;
    recipes = r;
  }

  public String toString() {
    String d = ", ";
    return
      "(" + super.toString() + d + title + d + recipes.size() + ")";
  }

}
