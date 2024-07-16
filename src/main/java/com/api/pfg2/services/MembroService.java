package com.api.pfg2.services;

import com.api.pfg2.entities.Membro;
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
public class MembroService {

    @Autowired
    private AtividadeRepository atividadeRepository;
    @Autowired
    private MembroRepository membroRepository;

    //Validation
    public void membroExistente(int membroId){
        if (!membroRepository.existsById(membroId)){
            throw new NotFoundException("Membro " + membroId + "não encontrado.");
        }
    }
    //Post
    public String createMembro(@Valid @RequestBody Membro membro) {
        membroRepository.save(membro);
        return "Membro cadastrado com sucesso.";
    }
    //Get
    public List<Membro> readMembros(){
        return membroRepository.findAll();
    }
    //GetId
    public Membro readMembroId(int membroId) {
        membroExistente(membroId);
        return membroRepository.findById(membroId);
    }
    //Put
    public String updateMembro(int membroId, Membro membro) {
        this.membroExistente(membroId);
        Membro response = membroRepository.findById(membroId);
        response.setNome(membro.getNome());
        response.setContato(membro.getContato());
        membroRepository.save(response);
        return "Cadastro de Membro atualizado.";
    }
    //Delete
    public String deleteMembro(int membroId) {
        this.membroExistente(membroId);
        Membro response = membroRepository.findById(membroId);
        membroRepository.delete(response);
        return  "Membro removido com sucesso.";
    }
    //Add
    public String addMembroAtividade(int membroId, HashMap<String, ArrayList<Integer>> atividades){
        this.membroExistente(membroId);
        ArrayList<Integer> atividadeIds = atividades.get("atividades");
        Membro response = membroRepository.findById(membroId);
        for (int atividadeId : atividadeIds){
            if (!atividadeRepository.existsById(atividadeId))
                throw new NotFoundException("Atividade " + atividadeId + "não encontrada.");
            response.addAtividades(atividadeRepository.findById(atividadeId));
        }
        membroRepository.save(response);
        return "Atividade inserida.";
    }
    //Remove
    public String removeMembroAtividade(int membroId, HashMap<String, ArrayList<Integer>> atividades) {
        this.membroExistente(membroId);
        ArrayList<Integer> atividadeIds = atividades.get("atividades");
        Membro response = membroRepository.findById(membroId);
        for (int atividadeId : atividadeIds) {
            if (!atividadeRepository.existsById(atividadeId))
                throw new NotFoundException("Atividade " + atividadeId + "não encontrada.");
            response.removeAtividades(atividadeRepository.findById(atividadeId));
        }
        membroRepository.save(response);
        return "Atividade removida.";
    }
}