package br.com.fiap.challenge.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.fiap.challenge.model.Professor;
import br.com.fiap.challenge.repository.ProfessorRepository;

@Service
public class ProfessorService {

    @Autowired
    private ProfessorRepository repository;

    public Page<Professor> listaAll(Pageable pageable){
        return repository.findAll(pageable);

    }

    public Professor save(Professor professor){
        return repository.save(professor);
        
    }

    public Optional<Professor> get(Long id) {
        return repository.findById(id);
    }

    public void remove(Long id) {
        repository.deleteById(id);
    }
    
}
