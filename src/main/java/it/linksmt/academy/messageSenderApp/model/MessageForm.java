package it.linksmt.academy.messageSenderApp.model;

import it.linksmt.academy.messageSenderApp.validators.BadWordConstraint;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

// Una classe POJO (Plain Old Java Object) è una classe contenente solo la dichiarazione di attributi e
// l'implementazione dei metodi di accesso get e set

@Data
public class MessageForm {

    @NotNull
    private long id;

    @NotBlank(message = "Inserire Titolo!")
    private String title;

    @NotBlank
    @BadWordConstraint
    private String body;

    @NotBlank
    @Size(min = 4, max = 10)
    private String phone;
}
