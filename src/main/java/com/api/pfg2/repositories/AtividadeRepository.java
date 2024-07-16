package com.api.pfg2.repositories;

import com.api.pfg2.entities.Atividade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AtividadeRepository extends JpaRepository <Atividade, Integer> {
    Atividade findById(int atividadeId);
}