package it.linksmt.academy.messageSenderApp.entities;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String title;
    private String body;
    private String phone;

    @ManyToOne
    @JoinColumn(name = "id_utente", referencedColumnName = "id")
    private User user;
}
