package dao;

import models.User;

import java.util.List;


public interface UserDAO {

    User findById(long id);

    void save(User user);

    void update(User user);

    void delete(User user);

    List<User> getUsers();

    User authorize(String login);

    boolean findByLogin(String login);

}

