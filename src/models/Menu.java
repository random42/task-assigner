package models;

import java.util.*;

public class Menu extends Model {

  public String title;
  public ArrayList<Recipe> recipes;

  public Menu(String t, ArrayList<Recipe> r) {
    title = t;
    recipes = r;
  }

}
