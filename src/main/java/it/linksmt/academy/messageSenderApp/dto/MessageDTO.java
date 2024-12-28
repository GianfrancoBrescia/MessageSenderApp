package it.linksmt.academy.messageSenderApp.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
public class MessageDTO {

    private long id;

    @NotBlank
    private String body;

    private String phone;
    private String title;
    private long idUtente;
}
