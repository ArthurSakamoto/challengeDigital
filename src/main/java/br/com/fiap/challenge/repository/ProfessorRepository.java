package br.com.fiap.challenge.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.challenge.model.Professor;

public interface ProfessorRepository extends JpaRepository<Professor, Long> {
    Professor findByEmail(String email);
}
