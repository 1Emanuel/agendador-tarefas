package com.nztech.agendadortarefas.business.mapper;

import com.nztech.agendadortarefas.business.dto.TarefasDTO;
import com.nztech.agendadortarefas.infrastructure.entity.TarefasEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TarefasCoverter {

    TarefasEntity paraTarefasEntity(TarefasDTO tarefasDTO);

    TarefasDTO paraTarefaDTO(TarefasEntity tarefasEntity);
}
