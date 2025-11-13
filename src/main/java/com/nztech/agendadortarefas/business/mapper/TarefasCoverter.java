package com.nztech.agendadortarefas.business.mapper;

import com.nztech.agendadortarefas.business.dto.TarefasDTO;
import com.nztech.agendadortarefas.infrastructure.entity.TarefasEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TarefasCoverter {

    TarefasEntity paraTarefasEntity(TarefasDTO tarefasDTO);

    TarefasDTO paraTarefaDTO(TarefasEntity tarefasEntity);

    List<TarefasEntity> paraListaTarefasEntity(List<TarefasDTO> tarefasDTO);

    List<TarefasDTO> paraListaTarefasDTO(List<TarefasEntity> tarefasEntity);
}
