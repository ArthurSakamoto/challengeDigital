package br.com.fiap.challenge.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.challenge.model.Aluno;

public interface AlunoRepository extends JpaRepository<Aluno, Long> {
    Aluno findByEmail(String email);
    
}
