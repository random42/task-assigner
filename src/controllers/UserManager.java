package controllers;

import models.User;

public class UserManager {

    private User currentUser;

    public void initialize() {
        currentUser = CateringAppManager.dataManager.getUser("Tony");
    }


    public User getCurrentUser() {
        return currentUser;
    }
}
