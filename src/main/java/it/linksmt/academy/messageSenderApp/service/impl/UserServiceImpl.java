package it.linksmt.academy.messageSenderApp.service.impl;

import it.linksmt.academy.messageSenderApp.entities.User;
import it.linksmt.academy.messageSenderApp.exceptions.UserNotFoundException;
import it.linksmt.academy.messageSenderApp.repositories.UserRepository;
import it.linksmt.academy.messageSenderApp.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    UserRepository userRepo;

    @Override
    @Transactional
    public User save(User user) {
        return userRepo.save(user);
    }

    @Override
    @Transactional
    public List<User> getAll() {
        return userRepo.findAll();
    }

    @Override
    @Transactional(rollbackOn = UserNotFoundException.class)
    public User findById(long id) throws UserNotFoundException {
        // Primo metodo
        // User user = userRepo.getOne(id);
        // if (user != null) return user;
        // else throw new UserNotFoundException();  // il return null provoca NullPointerException

        // Secondo metodo
        return userRepo.findById(id).orElseThrow(UserNotFoundException::new);
    }

    @Override
    public void deleteById(long id) throws UserNotFoundException {
        // da una transazione non si puo' mai richiamare il metodo che e' a sua volta una transazione
        User user = userRepo.findById(id).orElseThrow(UserNotFoundException::new);
        userRepo.delete(user);
    }
}
