package com.nztech.agendadortarefas.business;

import com.nztech.agendadortarefas.business.dto.TarefasDTO;
import com.nztech.agendadortarefas.business.mapper.TarefaUpdateConverter;
import com.nztech.agendadortarefas.business.mapper.TarefasCoverter;
import com.nztech.agendadortarefas.infrastructure.entity.TarefasEntity;
import com.nztech.agendadortarefas.infrastructure.enums.StatusNotificaoEnum;
import com.nztech.agendadortarefas.infrastructure.exceptions.ResourceNotFoundException;
import com.nztech.agendadortarefas.infrastructure.repository.TarefasRepository;
import com.nztech.agendadortarefas.infrastructure.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TarefasService {
    private final TarefasRepository tarefasRepository;
    private final TarefasCoverter tarefasCoverter;
    private final TarefaUpdateConverter tarefaUpdateConverter;
    private final JwtUtil jwtUtil;

    // criar metodo para criar terefa
    public TarefasDTO criarTarefa(TarefasDTO dto, String token) {
        String email = jwtUtil.extrairEmailToken(token.substring(7)); // extrair o emial do usuario no token
        dto.setDataCriacao(LocalDateTime.now()); // setar o status quando criar a tarefa
        dto.setStatusNotificaoEnum(StatusNotificaoEnum.PENDENTE); // setar o status pedente quando criar nova notificacao
        dto.setEmailUsuario(email); // setar o email do usuario extraido no token
        TarefasEntity tarefa = tarefasCoverter.paraTarefasEntity(dto); // coverter o DTO para Entity
        return tarefasCoverter.paraTarefaDTO(tarefasRepository.save(tarefa)); // Salvar a Entity e coverter para DTO e retornar o DTO
    }

    // criar o metodo que busca lista de tarefas baseado no periodo
    public List<TarefasDTO> buscarTarefasAgendadasPorPeriodo(LocalDateTime dataInicial, LocalDateTime dataFinal) {
        return tarefasCoverter.paraListaTarefasDTO(tarefasRepository.findByDataEventoBetween(dataInicial, dataFinal));
    }

    // metodo para buscar tarefas por email
    public List<TarefasDTO> buscarTarefasPorEmail(String token) {
        String email = jwtUtil.extrairEmailToken(token.substring(7));
        return tarefasCoverter.paraListaTarefasDTO(tarefasRepository.findByEmailUsuario(email));
    }

    // metodo de deletar tarefas por ID
    public void deleterPorId(String id) {
        try {
            tarefasRepository.deleteById(id);
        }
        catch (ResourceNotFoundException e) {
            throw  new ResourceNotFoundException("Erro ao deletar a tarefa por id, id inexistente" + id, e.getCause());
        }

    }

    // metodo para alterar status da tarefa por ID
    // 1. Buscar a tarefa por ID, captando a excepcao de existencia do ID.
    // 2. Setar o status da tarefa
    // 3. salavar a tarefa atualizada,
    // 4. Retornar Tarefa DTO
    public TarefasDTO alterarStatus(StatusNotificaoEnum staus, String id) {
        try {
            TarefasEntity tarefa = tarefasRepository.findById(id).orElseThrow(
                    () -> new  ResourceNotFoundException("Tarefa nao encontrado"));

            tarefa.setStatusNotificaoEnum(staus);
            return tarefasCoverter.paraTarefaDTO(tarefasRepository.save(tarefa));
        }catch (ResourceNotFoundException e) {
            throw new ResourceNotFoundException("Erro ao alterar o status do tarefa", e.getCause());
        }

    }

    // Buscar tarefa por ID, captando excepcao
    // Copiar e converter de DTO para Entity
    // Salvar e retornar DTO

    public TarefasDTO updateTarefa(TarefasDTO tarefasDTO, String id) {
        try {
            TarefasEntity tarefa = tarefasRepository.findById(id).orElseThrow(
                    () -> new ResourceNotFoundException("Tarefa nao encontrado"));
            tarefaUpdateConverter.updateTarefas(tarefasDTO, tarefa);
            return tarefasCoverter.paraTarefaDTO(tarefasRepository.save(tarefa));
        }
        catch (ResourceNotFoundException e) {
            throw new ResourceNotFoundException("Erro ao alterar o status do tarefa", e.getCause());
        }


    }




}
