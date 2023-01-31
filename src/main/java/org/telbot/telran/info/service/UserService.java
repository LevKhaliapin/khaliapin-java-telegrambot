package org.telbot.telran.info.service;

import org.telbot.telran.info.model.User;

import java.util.List;

public interface UserService {

    List<User> list();

    User getUser(int id);

    User save(User user);

    void delete(int id);

    List<User> listRoleUser();

    int numOfUsers();

    List<User> listRoleAdmin();

    int numOfAdmins();
}
