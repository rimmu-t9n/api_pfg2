package com.api.pfg2.services;

import com.api.pfg2.entities.Atividade;
import com.api.pfg2.exceptions.NotFoundException;
import com.api.pfg2.repositories.AtividadeRepository;
import com.api.pfg2.repositories.MembroRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class AtividadeService {

    @Autowired
    private MembroRepository membroRepository;
    @Autowired
    private AtividadeRepository atividadeRepository;

    //Validation
    public void atividadeExistente(int atividadeId){
        if (!atividadeRepository.existsById(atividadeId)){
            throw new NotFoundException("Atividade " + atividadeId + "não encontrada.");
        }
    }
    //Post
    public String createAtividade(@Valid @RequestBody Atividade atividade) {
        atividadeRepository.save(atividade);
        return "Atividade registrada com sucesso.";
    }
    //Get
    public List<Atividade> readAtividades(){
        return atividadeRepository.findAll();
    }
    //GetId
    public Atividade readAtividadeId(int atividadeId) {
        atividadeExistente(atividadeId);
        return atividadeRepository.findById(atividadeId);
    }
    //Put
    public String updateAtividade(int atividadeId, Atividade atividade) {
        this.atividadeExistente(atividadeId);
        Atividade response = atividadeRepository.findById(atividadeId);
        response.setNome(atividade.getNome());
        response.setLocal(atividade.getLocal());
        atividadeRepository.save(response);
        return "Atividade atualizada com sucesso.";
    }
    //Delete
    public String deleteAtividade(int atividadeId) {
        this.atividadeExistente(atividadeId);
        Atividade response = atividadeRepository.findById(atividadeId);
        atividadeRepository.delete(response);
        return "Atividade removida com sucesso.";
    }
    //Add
    public String addAtividadeMembro(int atividadeid, HashMap<String, ArrayList<Integer>> membros) {
        this.atividadeExistente(atividadeid);
        ArrayList<Integer> membroIDs = membros.get("membros");
        Atividade atividade = atividadeRepository.findById(atividadeid);
        for (int membroId : membroIDs){
            if (!membroRepository.existsById(membroId))
                throw new NotFoundException("Membro " + membroId + "não encontrado.");
            atividade.addMembros(membroRepository.findById(membroId));
        }
        atividadeRepository.save(atividade);
        return "Membro inserido";
    }
    //Remove
    public String removeAtividadeMembro(int atividadeid, HashMap<String, ArrayList<Integer>> membros) {
        this.atividadeExistente(atividadeid);
        ArrayList<Integer> membroIDs = membros.get("membros");
        Atividade atividade = atividadeRepository.findById(atividadeid);
        for (int membroId : membroIDs){
            if (!membroRepository.existsById(membroId))
                throw new NotFoundException("Membro " + membroId + "não encontrado.");
            atividade.removeMembros(membroRepository.findById(membroId));
        }
        atividadeRepository.save(atividade);
        return "Membro removido";
    }
}