package com.nztech.agendadortarefas.business.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.nztech.agendadortarefas.infrastructure.enums.StatusNotificaoEnum;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TarefasDTO {
    private String id;
    private String nome;
    private String descricao;
    private LocalDateTime dataCriacao;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime dataEvento;
    private String emailUsuario;
    private LocalDateTime dataAlteracao;
    private StatusNotificaoEnum statusNotificaoEnum;
}
