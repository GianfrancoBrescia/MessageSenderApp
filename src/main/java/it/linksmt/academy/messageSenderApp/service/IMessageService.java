package it.linksmt.academy.messageSenderApp.service;

import it.linksmt.academy.messageSenderApp.entities.Message;
import it.linksmt.academy.messageSenderApp.exceptions.MessageNotFoundException;

import java.util.List;

public interface IMessageService {

    Message save(Message message);

    List<Message> getAll();

    Message findById(long id) throws MessageNotFoundException;

    void deletedById(long id) throws MessageNotFoundException;
}
