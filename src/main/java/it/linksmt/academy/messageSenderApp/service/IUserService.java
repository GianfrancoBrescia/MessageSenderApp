package it.linksmt.academy.messageSenderApp.service;

import it.linksmt.academy.messageSenderApp.entities.User;
import it.linksmt.academy.messageSenderApp.exceptions.UserNotFoundException;

import java.util.List;

public interface IUserService {

    User save(User user);

    List<User> getAll();

    User findById(long id) throws UserNotFoundException;

    void deleteById(long id) throws UserNotFoundException;
}
