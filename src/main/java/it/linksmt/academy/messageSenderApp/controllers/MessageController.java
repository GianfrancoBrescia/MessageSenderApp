package it.linksmt.academy.messageSenderApp.controllers;

import it.linksmt.academy.messageSenderApp.entities.Message;
import it.linksmt.academy.messageSenderApp.entities.User;
import it.linksmt.academy.messageSenderApp.exceptions.UserNotFoundException;
import it.linksmt.academy.messageSenderApp.model.MessageForm;
import it.linksmt.academy.messageSenderApp.service.IMessageService;
import it.linksmt.academy.messageSenderApp.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/messaggio")
public class MessageController {

    @Autowired
    IUserService userService;

    @Autowired
    IMessageService messageService;

    @RequestMapping(value = "/invia-messaggio", method = RequestMethod.GET)
    public String inviaMessaggio(Model model) {
        model.addAttribute("messageForm", new MessageForm());

        List<User> users = userService.getAll();
        model.addAttribute("users", users);

        return "messageForm";
    }

    @PostMapping("/aggiungi-messaggio")
    public String aggiungiMessaggio(@ModelAttribute @Valid MessageForm messageForm, BindingResult bindingResult,
                                    Model model) throws UserNotFoundException {
        if (bindingResult.hasErrors()) {
            List<User> users = userService.getAll();
            model.addAttribute("users", users);
            return "messageForm";
        }

        model.addAttribute("messageForm", messageForm);

        Message messaggio = new Message();
        messaggio.setBody(messageForm.getBody());
        messaggio.setTitle(messageForm.getTitle());
        messaggio.setPhone(messageForm.getPhone());
        User user = userService.findById(messageForm.getId());
        messaggio.setUser(user);

        if (user.getListaMessaggi() == null || user.getListaMessaggi().isEmpty()) {
            user.setListaMessaggi(new ArrayList<>());
        }
        user.getListaMessaggi().add(messageService.save(messaggio));

        return "success";
    }
}
