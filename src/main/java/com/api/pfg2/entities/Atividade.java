package com.api.pfg2.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "atividades")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Atividade {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;
    @Column (name = "Nome", nullable = false)
    private String nome;
    @Column (name = "Local", nullable = false)
    private String local;

    @JsonIgnoreProperties("atividades")
    @ManyToMany (mappedBy = "atividades")
    private Set<Membro> membros = new HashSet<Membro>();

    public void addMembros(Membro membro){
        this.getMembros().add(membro);
        membro.getAtividades().add(this);
    }
    public void removeMembros(Membro membro){
        this.getMembros().remove(membro);
        membro.getAtividades().remove(this);
    }
}