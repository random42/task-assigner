package controllers;

import models.User;

public class UserManager {

    private User currentUser;

    public void initialize() {
        this.currentUser = CateringAppManager.dataManager.loadUser("Tony");
    }


    public User getCurrentUser() {
        return currentUser;
    }
}
