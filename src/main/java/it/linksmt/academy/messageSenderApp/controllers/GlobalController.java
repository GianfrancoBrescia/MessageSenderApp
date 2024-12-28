package it.linksmt.academy.messageSenderApp.controllers;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
public class GlobalController {

	@ModelAttribute
	public void setMsgGlobal(Model model) {
		model.addAttribute("msgGlobal", "Benvenuto nell'app");
	}
}
