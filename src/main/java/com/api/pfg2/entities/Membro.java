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
@Table(name = "membros")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Membro {
    @Id
    @GeneratedValue (strategy=GenerationType.IDENTITY)
    private Integer id;
    @Column (name = "Nome", nullable = false)
    private String nome;
    @Column (name = "Contato", nullable = false)
    private String contato;

    @JsonIgnoreProperties("membros")
    @ManyToMany
    @JoinTable (name = "membro_atividade",
            joinColumns =  {@JoinColumn (name = "membro_Id", referencedColumnName="id")} ,
            inverseJoinColumns =  {@JoinColumn (name = "atividade_Id", referencedColumnName="id")})
    private Set<Atividade> atividades = new HashSet<Atividade>();

    public void addAtividades(Atividade atividade){
        this.getAtividades().add(atividade);
        atividade.getMembros().add(this);
    }
    public void removeAtividades(Atividade atividade){
        this.getAtividades().remove(atividade);
        atividade.getMembros().remove(this);
    }
}