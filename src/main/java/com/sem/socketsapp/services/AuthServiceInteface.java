package com.sem.socketsapp.services;


import com.sem.socketsapp.models.User;

public interface AuthServiceInteface {
    boolean isAdmin(User user);
    User authUser(User user);
    String registrateUser(User user);
}
