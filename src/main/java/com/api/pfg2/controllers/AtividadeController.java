package com.api.pfg2.controllers;

import com.api.pfg2.entities.Atividade;
import com.api.pfg2.services.AtividadeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/api")
public class AtividadeController {

    @Autowired
    private AtividadeService atividadeService;

    //Create
    @PostMapping("/atividades")
    public ResponseEntity<String> createAtividade(@Valid @RequestBody Atividade atividade){
        return ResponseEntity.status(HttpStatus.CREATED).body(atividadeService.createAtividade(atividade));
    }
    //Read
    @GetMapping("/atividades")
    public ResponseEntity <List<Atividade>> readAtividades(){
        return ResponseEntity.status(HttpStatus.OK).body(atividadeService.readAtividades());
    }
    //ReadId
    @GetMapping("/atividades/{id}")
    public ResponseEntity<Atividade> readAtividadeId(@PathVariable("id") int atividadeId){
        return ResponseEntity.status(HttpStatus.OK).body(atividadeService.readAtividadeId(atividadeId));
    }
    //UpdateId
    @PutMapping("/atividades/{id}")
    public ResponseEntity<String> updateAtividade(@PathVariable("id") int atividadeId, @RequestBody Atividade atividade){
        return ResponseEntity.status(HttpStatus.OK).body(atividadeService.updateAtividade(atividadeId, atividade));
    }
    //DeleteId
    @DeleteMapping("/atividades/{id}")
    public ResponseEntity<String> deleteAtividade(@PathVariable("id") int atividadeId){
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(atividadeService.deleteAtividade(atividadeId));
    }
    //Add
    @PutMapping("/atividades/{id}/membros")
    public ResponseEntity<String> addAtividadeMembro(@Valid @PathVariable("id") int atividadeId,
                                                     @RequestBody HashMap<String, ArrayList<Integer>> membros){
        return ResponseEntity.status(HttpStatus.OK).body(atividadeService.addAtividadeMembro(atividadeId, membros));
    }
    //Delete
    @DeleteMapping("/atividades/{id}/membros")
    public ResponseEntity<String> removeAtividadeMembro(@Valid @PathVariable("id") int atividadeId,
                                                        @RequestBody HashMap<String, ArrayList<Integer>> membros){
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(atividadeService.removeAtividadeMembro(atividadeId, membros));
    }
}