package com.nztech.agendadortarefas.controller;

import com.nztech.agendadortarefas.business.TarefasService;
import com.nztech.agendadortarefas.business.dto.TarefasDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tarefas")
@RequiredArgsConstructor
public class TarefasController {
    private final TarefasService tarefasService;

    @PostMapping
    public ResponseEntity<TarefasDTO> criarTarefa(@RequestHeader("Authorization") String token,
                                                  @RequestBody TarefasDTO tarefaDTO) {
        return ResponseEntity.ok(tarefasService.criarTarefa(tarefaDTO,token));
    }
}
