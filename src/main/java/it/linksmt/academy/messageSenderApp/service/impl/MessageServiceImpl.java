package it.linksmt.academy.messageSenderApp.service.impl;

import it.linksmt.academy.messageSenderApp.entities.Message;
import it.linksmt.academy.messageSenderApp.exceptions.MessageNotFoundException;
import it.linksmt.academy.messageSenderApp.repositories.MessageRepository;
import it.linksmt.academy.messageSenderApp.service.IMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class MessageServiceImpl implements IMessageService {

    @Autowired
    MessageRepository msgRepo;

    @Override
    @Transactional
    public Message save(Message message) {
        return msgRepo.save(message);
    }

    @Override
    @Transactional
    public List<Message> getAll() {
        return msgRepo.findAll();
    }

    @Override
    @Transactional(rollbackOn = MessageNotFoundException.class)
    public Message findById(long id) throws MessageNotFoundException {
        return msgRepo.findById(id).orElseThrow(MessageNotFoundException::new);
    }

    @Override
    public void deletedById(long id) throws MessageNotFoundException {
		// da una transazione non si puo' mai richiamare il metodo che e' a sua volta una transazione
        Message message = msgRepo.findById(id).orElseThrow(MessageNotFoundException::new);
        msgRepo.delete(message);
    }
}
