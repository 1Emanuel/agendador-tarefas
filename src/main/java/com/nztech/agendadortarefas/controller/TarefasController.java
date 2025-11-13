package com.nztech.agendadortarefas.controller;

import com.nztech.agendadortarefas.business.TarefasService;
import com.nztech.agendadortarefas.business.dto.TarefasDTO;
import com.nztech.agendadortarefas.infrastructure.enums.StatusNotificaoEnum;
import com.nztech.agendadortarefas.infrastructure.exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/tarefas")
@RequiredArgsConstructor
public class TarefasController {
    private final TarefasService tarefasService;

    @PostMapping
    public ResponseEntity<TarefasDTO> criarTarefa(@RequestHeader("Authorization") String token,
                                                  @RequestBody TarefasDTO tarefaDTO) {
        return ResponseEntity.ok(tarefasService.criarTarefa(tarefaDTO, token));
    }

    @GetMapping("/eventos")
    public ResponseEntity<List<TarefasDTO>> buscarListaTarefasPorPeriodo(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataInicial,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataFinal
    ) {
        return ResponseEntity.ok(tarefasService.buscarTarefasAgendadasPorPeriodo(dataInicial, dataFinal));
    }

    @GetMapping
    public ResponseEntity<List<TarefasDTO>> buscarListaTarefasPorEmail(@RequestHeader("Authorization") String token) {
        return ResponseEntity.ok(tarefasService.buscarTarefasPorEmail(token));
    }

    @DeleteMapping
    public ResponseEntity<Void> deletarTarefaPorId(@RequestParam("id") String id) {
        tarefasService.deleterPorId(id);
        return ResponseEntity.noContent().build();

    }

    @PatchMapping
    public ResponseEntity<TarefasDTO> alterarStatus(@RequestParam("status") StatusNotificaoEnum status, @RequestParam("id") String id) {
        return ResponseEntity.ok(tarefasService.alterarStatus(status, id));
    }

    @PutMapping
    public ResponseEntity<TarefasDTO> updateTarefa(@RequestParam("id") String id, @RequestBody TarefasDTO tarefaDTO) {
        return ResponseEntity.ok(tarefasService.updateTarefa(tarefaDTO, id));
    }
}
