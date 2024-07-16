package com.api.pfg2.controllers;

import com.api.pfg2.entities.Membro;
import com.api.pfg2.services.MembroService;
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
public class MembroController {

    @Autowired
    private MembroService membroService;

    //Create
    @PostMapping("/membros")
    public ResponseEntity<String> createMembro(@Valid @RequestBody Membro membro){
        return ResponseEntity.status(HttpStatus.CREATED).body(membroService.createMembro(membro));
    }
    //Read
    @GetMapping("/membros")
    public ResponseEntity<List<Membro>> readMembros(){
        return ResponseEntity.status(HttpStatus.OK).body(membroService.readMembros());
    }
    //ReadId
    @GetMapping("/membros/{id}")
    public ResponseEntity<Membro> readMembroId(@PathVariable("id") int membroId){
        return ResponseEntity.status(HttpStatus.OK).body(membroService.readMembroId(membroId));
    }
    //UpdateId
    @PutMapping("/membros/{id}")
    public ResponseEntity<String> updateMembro(@PathVariable("id") int membroId, @Valid @RequestBody Membro membro){
        return ResponseEntity.status(HttpStatus.OK).body(membroService.updateMembro(membroId, membro));
    }
    //DeleteId
    @DeleteMapping("/membros/{id}")
    public ResponseEntity<String> deleteMembro(@PathVariable("id") int membroId){
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(membroService.deleteMembro(membroId));
    }
    //Add
    @PutMapping("/membros/{id}/atividades")
    public ResponseEntity<String> addMembroAtividade(@Valid @PathVariable("id") int membroId,
                                                     @RequestBody HashMap<String, ArrayList<Integer>> atividades){
        return ResponseEntity.status(HttpStatus.OK).body(membroService.addMembroAtividade(membroId, atividades));
    }
    //Remove
    @DeleteMapping("/membros/{id}/atividades")
    public ResponseEntity<String> removeMembroAtividade(@Valid @PathVariable("id") int membroId,
                                                        @RequestBody HashMap<String, ArrayList<Integer>> atividades){
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(membroService.removeMembroAtividade(membroId, atividades));
    }
}