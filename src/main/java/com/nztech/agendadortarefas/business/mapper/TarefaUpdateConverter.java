package com.nztech.agendadortarefas.business.mapper;

import com.nztech.agendadortarefas.business.dto.TarefasDTO;
import com.nztech.agendadortarefas.infrastructure.entity.TarefasEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

// essa anotacao pega campos nulls e set o valor que vem do entity quando converte pra DTO vice-versa
// @MapingTarget de quem ira copiar quando os dados forem null
@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface TarefaUpdateConverter {

    void updateTarefas(TarefasDTO dto, @MappingTarget TarefasEntity entity);
}
