package it.linksmt.academy.messageSenderApp.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;
    private String email;

    @OneToMany(mappedBy = "user")
    private List<Message> listaMessaggi;
}
