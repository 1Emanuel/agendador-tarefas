package com.nztech.agendadortarefas.business;

import com.nztech.agendadortarefas.business.dto.TarefasDTO;
import com.nztech.agendadortarefas.business.mapper.TarefasCoverter;
import com.nztech.agendadortarefas.infrastructure.entity.TarefasEntity;
import com.nztech.agendadortarefas.infrastructure.enums.StatusNotificaoEnum;
import com.nztech.agendadortarefas.infrastructure.repository.TarefasRepository;
import com.nztech.agendadortarefas.infrastructure.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class TarefasService {
    private final TarefasRepository tarefasRepository;
    private final TarefasCoverter tarefasCoverter;
    private final JwtUtil jwtUtil;

    public TarefasDTO criarTarefa(TarefasDTO dto, String token) {
        String email = jwtUtil.extrairEmailToken(token.substring(7)); // extrair o emial do usuario no token
        dto.setDataCriacao(LocalDateTime.now()); // setar o status quando criar a tarefa
        dto.setStatusNotificaoEnum(StatusNotificaoEnum.PENDENTE); // setar o status pedente quando criar nova notificacao
        dto.setEmailUsuario(email); // setar o email do usuario extraido no token
        TarefasEntity tarefa = tarefasCoverter.paraTarefasEntity(dto); // coverter o DTO para Entity
        return tarefasCoverter.paraTarefaDTO(tarefasRepository.save(tarefa)); // Salvar a Entity e coverter para DTO e retornar o DTO
    }


}
