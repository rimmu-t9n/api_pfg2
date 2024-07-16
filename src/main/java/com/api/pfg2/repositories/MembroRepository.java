package com.api.pfg2.repositories;

import com.api.pfg2.entities.Membro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MembroRepository extends JpaRepository <Membro, Integer> {
    Membro findById(int membroId);
}