package br.com.fiap.challenge.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name="TB_PROFESSOR")
public class Professor {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank
    private String nome;

    @NotBlank
    private String escola;

    @NotBlank
    private String materia;

    @NotBlank
    private String classe;

    @NotBlank
    private String tarefa;

    @NotBlank
    private String email;

    @NotBlank
    private String senha;

    public Professor() {}

    public Professor(String nome, String escola, String materia, String classe, String tarefa, String email,
    String senha) {
        this.nome = nome;
        this.escola = escola;
        this.materia = materia;
        this.classe = classe;
        this.tarefa = tarefa;
        this.email = email;
        this.senha = senha;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEscola() {
        return escola;
    }

    public void setEscola(String escola) {
        this.escola = escola;
    }

    public String getMateria() {
        return materia;
    }

    public void setMateria(String materia) {
        this.materia = materia;
    }

    public String getClasse() {
        return classe;
    }

    public void setClasse(String classe) {
        this.classe = classe;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTarefa() {
        return tarefa;
    }

    public void setTarefa(String tarefa) {
        this.tarefa = tarefa;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
    
}
