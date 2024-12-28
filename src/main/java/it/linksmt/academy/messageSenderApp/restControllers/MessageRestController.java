package it.linksmt.academy.messageSenderApp.restControllers;

import it.linksmt.academy.messageSenderApp.dto.MessageDTO;
import it.linksmt.academy.messageSenderApp.entities.Message;
import it.linksmt.academy.messageSenderApp.entities.User;
import it.linksmt.academy.messageSenderApp.exceptions.MessageNotFoundException;
import it.linksmt.academy.messageSenderApp.exceptions.UserNotFoundException;
import it.linksmt.academy.messageSenderApp.service.IMessageService;
import it.linksmt.academy.messageSenderApp.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/message")
public class MessageRestController {

    @Autowired
    IMessageService messageService;

    @Autowired
    IUserService userService;

    @GetMapping(value = "/messaggi", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<MessageDTO> recuperaMessaggi() {
        return Optional.ofNullable(messageService.getAll()).orElse(new ArrayList<>()).stream()
                .map(this::convertEntityToDto)
                .collect(Collectors.toList());
    }

    @GetMapping(value = "/messaggio/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public MessageDTO recuperaMessaggio(@PathVariable("id") long id) throws MessageNotFoundException {
        return convertEntityToDto(messageService.findById(id));
    }

    @PostMapping(value = "/salva-messaggio", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public MessageDTO salvaMessaggio(@RequestBody @Valid MessageDTO messageDTO) throws UserNotFoundException {
        Message message = new Message();
        message.setBody(messageDTO.getBody());
        message.setPhone(messageDTO.getPhone());
        message.setTitle(messageDTO.getTitle());
        User user = userService.findById(messageDTO.getIdUtente());
        message.setUser(user);
        message = messageService.save(message);

        if (user.getListaMessaggi() == null || user.getListaMessaggi().isEmpty()) {
            user.setListaMessaggi(new ArrayList<>());
        }
        user.getListaMessaggi().add(message);

        return convertEntityToDto(message);
    }

    private MessageDTO convertEntityToDto(Message message) {
        MessageDTO messageDTO = new MessageDTO();
        messageDTO.setBody(message.getBody());
        messageDTO.setId(message.getId());
        messageDTO.setPhone(message.getPhone());
        messageDTO.setTitle(message.getTitle());
        messageDTO.setIdUtente(message.getUser().getId());
        return messageDTO;
    }
}
